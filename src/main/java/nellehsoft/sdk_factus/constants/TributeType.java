package nellehsoft.sdk_factus.constants;

public enum TributeType {
    IVA(18, "01", "IVA"),
    NO_APLICA(21, "ZZ", "No aplica");

    private final int id;
    private final String code;
    private final String name;

    TributeType(int id, String code, String name) {
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

    // Buscar por ID
    public static TributeType fromId(int id) {
        for (TributeType t : values()) {
            if (t.id == id) return t;
        }
        throw new IllegalArgumentException("Tributo con ID " + id + " no existe");
    }

    // Buscar por código
    public static TributeType fromCode(String code) {
        for (TributeType t : values()) {
            if (t.code.equals(code)) return t;
        }
        throw new IllegalArgumentException("Tributo con código " + code + " no existe");
    }
}
