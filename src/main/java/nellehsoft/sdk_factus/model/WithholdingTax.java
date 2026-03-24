package nellehsoft.sdk_factus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class WithholdingTax {
    private String code;
    private String withholdingTaxRate;
}