package nellehsoft.sdk_factus.constants;

public enum CorrectionConceptCode {

    DEVOLUCION_PARCIAL(1),          // Devolución parcial de los bienes y/o no aceptación parcial del servicio
    ANULACION_FACTURA(2),           // Anulación de factura electrónica
    REBAJA_DESCUENTO(3),            // Rebaja o descuento parcial o total
    AJUSTE_PRECIO(4),               // Ajuste de precio
    DESCUENTO_PRONTO_PAGO(5),       // Descuento comercial por pronto pago
    DESCUENTO_VOLUMEN(6);           // Descuento comercial por volumen de ventas
    private final int code;

    CorrectionConceptCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static CorrectionConceptCode fromCode(int code) {
        for (CorrectionConceptCode c : values()) {
            if (c.code == code) {
                return c;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}