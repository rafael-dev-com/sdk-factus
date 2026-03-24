package nellehsoft.sdk_factus.provider.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;
import java.time.Instant;

@Getter
public class OAuthToken {

    private final int REFRESH_MARGIN_SECONDS;

    private final String accessToken;
    private final String refreshToken;
    private final Instant expiresAt;

    public OAuthToken(String accessToken, String refreshToken, long expiresIn) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresAt = Instant.now().plusSeconds(expiresIn);
        this.REFRESH_MARGIN_SECONDS = (int)(expiresIn * 0.20);
    }



    // Determina si debe refrescarse el token
    public boolean shouldRefresh() {
        return Instant.now().isAfter(expiresAt.minusSeconds(REFRESH_MARGIN_SECONDS));
    }

    public long secondsUntilRefresh() {
        Duration duration = Duration.between(Instant.now(), expiresAt.minusSeconds(REFRESH_MARGIN_SECONDS));
        return Math.max(duration.getSeconds(), 0); // no negativo
    }
}