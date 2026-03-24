package nellehsoft.sdk_factus.constants;

public enum PaymentForm {

    CONTADO("1", "Pago de contado"),
    CREDITO("2", "Pago a crédito");

    private final String code;
    private final String description;

    PaymentForm(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static PaymentForm fromCode(String code) {
        for (PaymentForm value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Forma de pago no válida: " + code);
    }
}
