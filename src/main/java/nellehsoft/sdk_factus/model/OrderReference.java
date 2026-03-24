package nellehsoft.sdk_factus.model;

import lombok.Data;

import java.util.Date;

@Data
public class OrderReference {
    private String referenceCode;
    private Date issueDate;
}
