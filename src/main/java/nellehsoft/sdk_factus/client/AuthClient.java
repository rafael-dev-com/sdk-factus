package nellehsoft.sdk_factus.client;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import nellehsoft.sdk_factus.model.TokenResponse;

public interface AuthClient {

    @RequestLine("POST /oauth/token")
    @Headers("Content-Type: multipart/form-data")
    TokenResponse token(
            @Param("grant_type") String grantType,
            @Param("client_id") String clientId,
            @Param("client_secret") String clientSecret,
            @Param("username") String username,
            @Param("password") String password
    );

    @RequestLine("POST /oauth/token")
    @Headers("Content-Type: multipart/form-data")
    TokenResponse refreshToken(
            @Param("grant_type") String grantType,
            @Param("client_id") String clientId,
            @Param("client_secret") String clientSecret,
            @Param("refresh_token") String refreshToken
    );
}
