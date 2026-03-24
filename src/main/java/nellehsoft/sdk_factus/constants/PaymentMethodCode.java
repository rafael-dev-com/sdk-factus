package nellehsoft.sdk_factus.constants;

public enum PaymentMethodCode {

    EFECTIVO(10, "Efectivo"),
    CONSIGNACION(42, "Consignación"),
    CHEQUE(20, "Cheque"),
    TRANSFERENCIA(47, "Transferencia"),
    BONOS(71, "Bonos"),
    VALES(72, "Vales"),
    NO_DEFINIDO(1, "Medio de pago no definido"),
    TARJETA_DEBITO(49, "Tarjeta Débito"),
    TARJETA_CREDITO(48, "Tarjeta Crédito"),
    OTRO(-1, "Otro"); // Usamos -1 para representar ZZZ como valor especial

    private final int code;
    private final String description;

    PaymentMethodCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    // Obtener enum a partir del código
    public static PaymentMethodCode fromCode(int code) {
        for (PaymentMethodCode method : PaymentMethodCode.values()) {
            if (method.getCode() == code) {
                return method;
            }
        }
        if (code == -1) return OTRO; // Caso especial ZZZ
        throw new IllegalArgumentException("Código de método de pago no válido: " + code);
    }
}
