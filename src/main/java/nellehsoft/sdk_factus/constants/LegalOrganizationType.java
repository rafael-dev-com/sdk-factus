package nellehsoft.sdk_factus.constants;

public enum LegalOrganizationType {
    PERSONA_JURIDICA(1, "Persona Jurídica"),
    PERSONA_NATURAL(2, "Persona Natural");

    private final int id;
    private final String description;

    LegalOrganizationType(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    // Buscar enum por ID
    public static LegalOrganizationType fromId(int id) {
        for (LegalOrganizationType type : LegalOrganizationType.values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("ID de tipo de organización no válido: " + id);
    }

    public boolean isLegalEntity() {
        return this == PERSONA_JURIDICA;
    }
}