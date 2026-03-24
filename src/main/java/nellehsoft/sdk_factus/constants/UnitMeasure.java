package nellehsoft.sdk_factus.constants;

public enum UnitMeasure {

    UNIDAD(70, "94", "unidad"),
    KILOGRAMO(414, "KGM", "kilogramo"),
    LIBRA(449, "LBR", "libra"),
    MILILITRO(499, "MLT", "mililitro"),
    METRO(512, "MTR", "metro"),
    GALON(874, "GLL", "galón");

    private final int id;
    private final String code;
    private final String name;

    UnitMeasure(int id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static UnitMeasure fromId(int id) {
        for (UnitMeasure value : values()) {
            if (value.id == id) {
                return value;
            }
        }
        throw new IllegalArgumentException("Unidad de medida no válida: " + id);
    }

    public static UnitMeasure fromCode(String code) {
        for (UnitMeasure value : values()) {
            if (value.code.equalsIgnoreCase(code)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código de unidad no válido: " + code);
    }
}
