package nellehsoft.sdk_factus.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@SuperBuilder
public class IssuedInvoice extends BillBase {
    private Integer id;
    private String number;
    private Integer status;
    private String qr;
    private String qrImage;
    private String cufe;
    private Date validated;
    private Double grossValue;
    private Double taxableAmount;
    private Double taxAmount;
    private Double discountAmount;
    private Double surchargeAmount;
    private Double total;
    private Company company;
    private NumberingRange numberingRange;
}
