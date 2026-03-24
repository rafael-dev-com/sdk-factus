package nellehsoft.sdk_factus.constants;

public enum ProductStandardCode {

    CONTRIBUTOR_STANDARD(1, "Estándar de adopción del contribuyente"),
    UNSPSC(2, "UNSPSC"),
    TARIFF_HEADING(3, "Partida Arancelaria"),
    GTIN(4, "GTIN");

    private final int id;
    private final String description;

    ProductStandardCode(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static ProductStandardCode fromId(int id) {
        for (ProductStandardCode value : values()) {
            if (value.id == id) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código de estándar de producto no válido: " + id);
    }
}
