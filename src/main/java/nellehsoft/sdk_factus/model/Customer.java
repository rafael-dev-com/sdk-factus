package nellehsoft.sdk_factus.model;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class Customer {

    // Obligatorios
    private String identification;
    private Integer identificationDocumentId;
    private Integer legalOrganizationId;
    private Integer tributeId;

    // Condicionales
    private String dv;           // Requerido si es NIT (identificationDocumentId == 6)
    private String company;      // Obligatorio si es persona jurídica (legalOrganizationId != null y aplica)
    private String names;        // Solo para persona natural
    private String tradeName;    // Opcional

    // Opcionales
    private String address;
    private String email;
    private String phone;
    private Integer municipalityId;

    /**
     * Builder personalizado con validaciones
     */
    public static class CustomerBuilder {

        public Customer build() {
            // Validaciones obligatorias
            validateRequired("identification", identification);
            validateRequired("identificationDocumentId", identificationDocumentId);
            validateRequired("legalOrganizationId", legalOrganizationId);
            validateRequired("tributeId", tributeId);

            // Validaciones condicionales
            if (identificationDocumentId != null && identificationDocumentId == 6) {
                validateRequired("dv", dv); // NIT requiere DV
            }

            // Si es persona jurídica (ejemplo: legalOrganizationId > 1 podría indicar jurídica)
            if (isLegalEntity(legalOrganizationId)) {
                validateRequired("company", company);
            } else { // persona natural
                validateRequired("names", names);
            }

            return new Customer(
                    identification,
                    identificationDocumentId,
                    legalOrganizationId,
                    tributeId,
                    dv,
                    company,
                    names,
                    tradeName,
                    address,
                    email,
                    phone,
                    municipalityId
            );
        }

        private void validateRequired(String field, Object value) {
            if (value == null || (value instanceof String && ((String) value).trim().isEmpty())) {
                throw new IllegalStateException(
                        "Customer: el campo obligatorio '" + field + "' es requerido"
                );
            }
        }

        private boolean isLegalEntity(Integer legalOrganizationId) {
            try {
                int id = legalOrganizationId;
                return id == 1; // 1 => Persona Jurídica
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    // Constructor completo
    private Customer(String identification,
                     Integer identificationDocumentId,
                     Integer legalOrganizationId,
                     Integer tributeId,
                     String dv,
                     String company,
                     String names,
                     String tradeName,
                     String address,
                     String email,
                     String phone,
                     Integer municipalityId) {
        this.identification = identification;
        this.identificationDocumentId = identificationDocumentId;
        this.legalOrganizationId = legalOrganizationId;
        this.tributeId = tributeId;
        this.dv = dv;
        this.company = company;
        this.names = names;
        this.tradeName = tradeName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.municipalityId = municipalityId;
    }
}