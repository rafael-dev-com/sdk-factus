package nellehsoft.sdk_factus.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import java.util.Date;

@Getter
@SuperBuilder
public class DraftBill extends BillBase {
    private Integer numberingRangeId;
    private String paymentForm;
    private Integer paymentMethodCode;
    private Boolean sendEmail;
    private Date paymentDueDate;
}
