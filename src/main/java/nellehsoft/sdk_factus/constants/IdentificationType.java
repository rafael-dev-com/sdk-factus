package nellehsoft.sdk_factus.constants;

public enum IdentificationType {

    REGISTRO_CIVIL(1, "Registro civil"),
    TARJETA_IDENTIDAD(2, "Tarjeta de identidad"),
    CEDULA_CIUDADANIA(3, "Cédula de ciudadanía"),
    TARJETA_EXTRANJERIA(4, "Tarjeta de extranjería"),
    CEDULA_EXTRANJERIA(5, "Cédula de extranjería"),
    NIT(6, "NIT"),
    PASAPORTE(7, "Pasaporte"),
    DOCUMENTO_EXTRANJERO(8, "Documento de identificación extranjero"),
    PEP(9, "PEP"),
    NIT_OTRO_PAIS(10, "NIT otro país"),
    NUIP(11, "NUIP");

    private final int id;
    private final String name;

    IdentificationType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Método opcional para buscar por id
    public static IdentificationType fromId(int id) {
        for (IdentificationType type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("ID de documento inválido: " + id);
    }

}
