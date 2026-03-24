package nellehsoft.sdk_factus.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import nellehsoft.sdk_factus.provider.auth.TokenProvider;

public class AuthInterceptor implements RequestInterceptor {

    private final TokenProvider tokenProvider;

    public AuthInterceptor(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header(
                "Authorization",
                "Bearer " + tokenProvider.getAccessToken()
        );
    }
}
