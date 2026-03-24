package nellehsoft.sdk_factus.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nellehsoft.sdk_factus.model.FactusResponse;

@AllArgsConstructor
@Getter
public class FactusException extends Exception{
    private int status;
    private FactusResponse response;
}
