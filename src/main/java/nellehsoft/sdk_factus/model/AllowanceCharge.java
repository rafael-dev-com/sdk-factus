package nellehsoft.sdk_factus.model;

import lombok.Getter;

@Getter
public class AllowanceCharge {

    private final String conceptType;
    private final Boolean isSurcharge;
    private final String reason;
    private final Double baseAmount;
    private final Double amount;

    private AllowanceCharge(Builder builder) {
        this.conceptType = builder.conceptType;
        this.isSurcharge = builder.isSurcharge;
        this.reason = builder.reason;
        this.baseAmount = builder.baseAmount;
        this.amount = builder.amount;
    }

    public static class Builder {
        private String conceptType;
        private Boolean isSurcharge;
        private String reason;
        private Double baseAmount;
        private Double amount;

        public Builder conceptType(String conceptType) {
            this.conceptType = conceptType;
            return this;
        }

        public Builder isSurcharge(Boolean isSurcharge) {
            this.isSurcharge = isSurcharge;
            return this;
        }

        public Builder reason(String reason) {
            this.reason = reason;
            return this;
        }

        public Builder baseAmount(Double baseAmount) {
            this.baseAmount = baseAmount;
            return this;
        }

        public Builder amount(Double amount) {
            this.amount = amount;
            return this;
        }

        public AllowanceCharge build() {
            // Validaciones obligatorias
            if (conceptType == null || conceptType.isBlank()) {
                throw new IllegalStateException("El campo 'conceptType' es obligatorio");
            }
            if (isSurcharge == null) {
                throw new IllegalStateException("El campo 'isSurcharge' es obligatorio");
            }
            if (reason == null || reason.isBlank()) {
                throw new IllegalStateException("El campo 'reason' es obligatorio");
            }
            if (baseAmount == null || baseAmount < 0) {
                throw new IllegalStateException("El campo 'baseAmount' es obligatorio y no puede ser negativo");
            }
            if (amount == null || amount < 0) {
                throw new IllegalStateException("El campo 'amount' es obligatorio y no puede ser negativo");
            }

            return new AllowanceCharge(this);
        }
    }
}
