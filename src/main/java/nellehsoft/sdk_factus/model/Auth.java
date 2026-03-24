package nellehsoft.sdk_factus.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Auth {
    String clientId;
    String clientSecret;
    String username;
    String password;
}
