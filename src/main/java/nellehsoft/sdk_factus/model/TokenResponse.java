package nellehsoft.sdk_factus.model;

import lombok.Data;

@Data
public class TokenResponse {
    public String access_token;
    public String refresh_token;
    public long expires_in;
}
