package nellehsoft.sdk_factus.validator;

import nellehsoft.sdk_factus.model.AllowanceCharge;
import nellehsoft.sdk_factus.model.BillBase;
import nellehsoft.sdk_factus.model.BillingPeriod;
import nellehsoft.sdk_factus.model.Customer;
import nellehsoft.sdk_factus.model.Item;
import nellehsoft.sdk_factus.model.OrderReference;
import nellehsoft.sdk_factus.model.WithholdingTax;
import nellehsoft.sdk_factus.model.*;

public class DraftBillValidator {

    public static void validate(BillBase invoice) {
        if (invoice == null) {
            throw new IllegalArgumentException("La factura no puede ser nula");
        }

        // Campos obligatorios de InvoiceBase / DraftInvoice
        validarRequerido("referenceCode", invoice.getReferenceCode());
        validarRequerido("customer", invoice.getCustomer());
        validarRequerido("items", invoice.getItems());

        if (invoice.getItems() != null && invoice.getItems().isEmpty()) {
            throw new IllegalArgumentException("La factura debe contener al menos un item");
        }

        // Validar Customer
        validarCustomer(invoice.getCustomer());

        // Validar Items
        if (invoice.getItems() != null) {
            for (Item item : invoice.getItems()) {
                validarItem(item);
            }
        }

        // Validar OrderReference opcional
        if (invoice.getOrderReference() != null) {
            validarOrderReference(invoice.getOrderReference());
        }

        // Validar BillingPeriod opcional
        if (invoice.getBillingPeriod() != null) {
            validarBillingPeriod(invoice.getBillingPeriod());
        }

        // Validar AllowanceCharges opcional
        if (invoice.getAllowanceCharges() != null) {
            for (AllowanceCharge ac : invoice.getAllowanceCharges()) {
                validarAllowanceCharge(ac);
            }
        }
    }

    private static void validarCustomer(Customer customer) {
        validarRequerido("customer.identification", customer.getIdentification());
        validarRequerido("customer.identificationDocumentId", customer.getIdentificationDocumentId());
        validarRequerido("customer.legalOrganizationId", customer.getLegalOrganizationId());
        validarRequerido("customer.tributeId", customer.getTributeId());

        // Debe tener nombres o company
        if ((customer.getNames() == null || customer.getNames().isBlank()) &&
                (customer.getCompany() == null || customer.getCompany().isBlank())) {
            throw new IllegalArgumentException("El cliente debe tener nombres o razón social");
        }
    }

    private static void validarItem(Item item) {
        validarRequerido("item.codeReference", item.getCodeReference());
        validarRequerido("item.name", item.getName());
        validarRequerido("item.quantity", item.getQuantity());
        validarRequerido("item.price", item.getPrice());
        validarRequerido("item.taxRate", item.getTaxRate());
        validarRequerido("item.unitMeasureId", item.getUnitMeasureId());
        validarRequerido("item.standardCodeId", item.getStandardCodeId());
        validarRequerido("item.isExcluded", item.getIsExcluded());
        validarRequerido("item.tributeId", item.getTributeId());

        // Reglas de negocio
        if (item.getQuantity() != null && item.getQuantity() <= 0) {
            throw new IllegalArgumentException("La cantidad del item debe ser mayor a 0");
        }
        if (item.getDiscountRate() != null && (item.getDiscountRate() < 0 || item.getDiscountRate() > 100)) {
            throw new IllegalArgumentException("El porcentaje de descuento del item debe estar entre 0 y 100");
        }

        try {
            Double.parseDouble(item.getTaxRate());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El valor de taxRate del item debe ser numérico");
        }

        // Retenciones
        if (item.getWithholdingTaxes() != null) {
            for (WithholdingTax tax : item.getWithholdingTaxes()) {
                validarRequerido("withholdingTax.code", tax.getCode());
                validarRequerido("withholdingTax.withholdingTaxRate", tax.getWithholdingTaxRate());
            }
        }
    }

    private static void validarOrderReference(OrderReference orderReference) {
        validarRequerido("orderReference.referenceCode", orderReference.getReferenceCode());
        // issueDate es opcional
    }

    private static void validarBillingPeriod(BillingPeriod bp) {
        validarRequerido("billingPeriod.startDate", bp.getStartDate());
        validarRequerido("billingPeriod.endDate", bp.getEndDate());
        // startTime y endTime son opcionales
    }

    private static void validarAllowanceCharge(AllowanceCharge ac) {
        validarRequerido("allowanceCharge.conceptType", ac.getConceptType());
        validarRequerido("allowanceCharge.isSurcharge", ac.getIsSurcharge());
        validarRequerido("allowanceCharge.reason", ac.getReason());
        validarRequerido("allowanceCharge.baseAmount", ac.getBaseAmount());
        validarRequerido("allowanceCharge.amount", ac.getAmount());
    }

    private static void validarRequerido(String campo, Object valor) {
        if (valor == null) {
            throw new IllegalArgumentException("El campo '" + campo + "' es obligatorio");
        }
        if (valor instanceof String && ((String) valor).isBlank()) {
            throw new IllegalArgumentException("El campo '" + campo + "' no puede estar vacío");
        }
    }
}