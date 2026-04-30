package nellehsoft.sdk_factus.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import nellehsoft.sdk_factus.constants.CorrectionConceptCode;
import nellehsoft.sdk_factus.constants.CreditNoteOperationType;
import nellehsoft.sdk_factus.constants.PaymentMethodCode;

import java.util.List;

@Getter
@SuperBuilder
public class CreditNote {
    private Integer numberingRangeId;
    private Integer correctionConceptCode;
    private Integer customizationId;
    private Integer billId;
    private String referenceCode;
    private Integer paymentMethodCode;
    private boolean sendEmail;
    private String observation;
    private BillingPeriod billingPeriod;
    private Establishment establishment;
    private Customer customer;
    private List<Item> items;
    private List<AllowanceCharge> allowanceCharges;
}
