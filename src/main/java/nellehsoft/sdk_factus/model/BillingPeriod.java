package nellehsoft.sdk_factus.model;

import lombok.Data;

import java.util.Date;

@Data
public class BillingPeriod {
    private Date startDate;
    private String startTime;
    private Date endDate;
    private String endTime;
}
