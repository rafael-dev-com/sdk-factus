package nellehsoft.sdk_factus.provider.auth;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import nellehsoft.sdk_factus.client.AuthClient;
import nellehsoft.sdk_factus.constants.GrantType;
import nellehsoft.sdk_factus.model.Auth;
import nellehsoft.sdk_factus.model.TokenResponse;

@Slf4j
public class TokenProvider {

    public final static String KEY = "token";
    private final LoadingCache<String, OAuthToken> cache;
    private final AuthClient authClient;
    private final Auth auth;

    public TokenProvider(AuthClient authClient, Auth auth) {
        this.authClient = authClient;
        this.auth = auth;
        this.cache = this.getCache();
    }

    public String getAccessToken() {
        OAuthToken token = cache.get(KEY);

        // Si el token está próximo a expirar, disparar refresh async
        if (token.shouldRefresh()) {
            cache.refresh(KEY);
        }

        // Siempre devolvemos el token actual (viejo si el refresh aún no termina)
        return token.getAccessToken();
    }

    private LoadingCache<String, OAuthToken> getCache() {
         return Caffeine.newBuilder()
                .expireAfter(new OAuthTokenExpiry())
                .build(new CacheLoader<>() {
                    @Override
                    public OAuthToken load(String key) {
                        return requestNewToken();  // Primer token
                    }

                    @Override
                    public OAuthToken reload(String key, OAuthToken oldToken) {
                        return refreshToken(oldToken.getRefreshToken()); // Refresh usando refresh_token
                    }
                });
    }

    private OAuthToken refreshToken(String refreshToken) {
        log.debug("Intentar refresh token");
        var response = authClient.refreshToken(
                GrantType.REFRESH_TOKEN.getValue(),
                auth.getClientId(),
                auth.getClientSecret(),
                refreshToken
        );
        return getOAuthToken(response);
    }

    private OAuthToken requestNewToken() {
        log.debug("Pedir token nuevo");
        var response = authClient.token(
                GrantType.PASSWORD.getValue(),
                auth.getClientId(),
                auth.getClientSecret(),
                auth.getUsername(),
                auth.getPassword()
        );
        return getOAuthToken(response);
    }

    private OAuthToken getOAuthToken(TokenResponse response) {
        return new OAuthToken(
                response.access_token,
                response.refresh_token,
                response.expires_in
        );
    }
}
