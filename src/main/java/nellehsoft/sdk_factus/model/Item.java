package nellehsoft.sdk_factus.model;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Item {

    private String codeReference;
    private String name;
    private Integer quantity;
    private Double discountRate;
    private Double price;
    private String taxRate;
    private Integer unitMeasureId;
    private Integer standardCodeId;
    private Integer isExcluded;
    private Integer tributeId;
    private List<WithholdingTax> withholdingTaxes;

    public static class ItemBuilder {

        public Item build() {
            // Validaciones obligatorias
            validateRequired("Código de referencia", codeReference);
            validateRequired("Nombre", name);
            validateRequired("Cantidad", quantity);
            validateRequired("Precio", price);
            validateRequired("Tasa de impuesto", taxRate);
            validateRequired("ID de unidad de medida", unitMeasureId);
            validateRequired("ID de código estándar", standardCodeId);
            validateRequired("Excluido de IVA", isExcluded);
            validateRequired("ID de tributo", tributeId);
            validateRequired("Tasa de descuento", discountRate);

            // Validaciones de valores
            if (quantity <= 0) {
                throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
            }

            if (price < 0) {
                throw new IllegalArgumentException("El precio no puede ser negativo");
            }

            if (discountRate < 0) {
                throw new IllegalArgumentException("La tasa de descuento no puede ser negativa");
            }

            return new Item(
                    codeReference,
                    name,
                    quantity,
                    discountRate,
                    price,
                    taxRate,
                    unitMeasureId,
                    standardCodeId,
                    isExcluded,
                    tributeId,
                    withholdingTaxes
            );
        }
    }

    private Item(
            String codeReference,
            String name,
            Integer quantity,
            Double discountRate,
            Double price,
            String taxRate,
            Integer unitMeasureId,
            Integer standardCodeId,
            Integer isExcluded,
            Integer tributeId,
            List<WithholdingTax> withholdingTaxes
    ) {
        this.codeReference = codeReference;
        this.name = name;
        this.quantity = quantity;
        this.discountRate = discountRate;
        this.price = price;
        this.taxRate = taxRate;
        this.unitMeasureId = unitMeasureId;
        this.standardCodeId = standardCodeId;
        this.isExcluded = isExcluded;
        this.tributeId = tributeId;
        this.withholdingTaxes = withholdingTaxes;
    }

    private static void validateRequired(String field, Object value) {
        if (value == null) {
            throw new IllegalArgumentException("El campo '" + field + "' es obligatorio");
        }
        if (value instanceof String && ((String) value).isBlank()) {
            throw new IllegalArgumentException("El campo '" + field + "' no puede estar vacío");
        }
    }
}