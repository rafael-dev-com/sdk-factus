package nellehsoft.sdk_factus.provider.auth;

import com.github.benmanes.caffeine.cache.Expiry;
import java.time.Duration;
import java.time.Instant;

public class OAuthTokenExpiry implements Expiry<String, OAuthToken> {
    @Override
    public long expireAfterCreate(String key, OAuthToken token, long currentTime) {
        return Duration.between(Instant.now(), token.getExpiresAt()).toNanos();
    }

    @Override
    public long expireAfterUpdate(String key, OAuthToken token, long currentTime, long currentDuration) {
        return expireAfterCreate(key, token, currentTime);
    }

    @Override
    public long expireAfterRead(String key, OAuthToken token, long currentTime, long currentDuration) {
        return currentDuration;
    }
}
