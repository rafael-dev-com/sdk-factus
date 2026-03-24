package nellehsoft.sdk_factus.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class BillBase {
    private String referenceCode;
    private String observation;
    private Customer customer;
    private Establishment establishment;
    private List<Item> items;
    private OrderReference orderReference;
    private BillingPeriod billingPeriod;
    private List<AllowanceCharge> allowanceCharges;
    private String operationType;
}
