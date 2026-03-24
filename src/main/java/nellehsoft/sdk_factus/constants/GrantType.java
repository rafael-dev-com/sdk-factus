package nellehsoft.sdk_factus.constants;

public enum GrantType {

    REFRESH_TOKEN("refresh_token"),
    PASSWORD("password");

    private final String value;

    GrantType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
