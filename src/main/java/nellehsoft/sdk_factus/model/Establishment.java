package nellehsoft.sdk_factus.model;

import lombok.Data;

@Data
public class Establishment {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private Municipality municipality;
}
