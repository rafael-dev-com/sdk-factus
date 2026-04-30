package nellehsoft.sdk_factus.constants;

public enum CreditNoteOperationType {

    REFERENCIADA_FACTURA(20),   // Nota crédito que referencia una factura electrónica
    SIN_REFERENCIA(22);         // Nota crédito sin referencia a una factura electrónica

    private final int code;

    CreditNoteOperationType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static CreditNoteOperationType fromCode(int code) {
        for (CreditNoteOperationType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}
