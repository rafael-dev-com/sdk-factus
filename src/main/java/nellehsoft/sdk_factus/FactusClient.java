package nellehsoft.sdk_factus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import nellehsoft.sdk_factus.exception.FactusException;
import nellehsoft.sdk_factus.model.Auth;
import nellehsoft.sdk_factus.model.CreditNote;
import nellehsoft.sdk_factus.model.DraftBill;
import nellehsoft.sdk_factus.model.FactusResponse;

public class FactusClient extends Api{

    ObjectMapper mapper = new ObjectMapper();

    public FactusClient(Auth auth, String urlBase) {
        super(auth, urlBase);
    }

    public FactusResponse validateAndCreateBill(DraftBill draftBill) throws FactusException {
        try {
            return apiClient.validateAndCreateBill(draftBill);
        } catch (FeignException e) {
            throw new FactusException(e.status(), parseException(e.contentUTF8()));
        }
    }

    public FactusResponse validateAndCreditNotes(CreditNote creditNote) throws FactusException {
        try {
            return apiClient.validateAndCreateCreditNotes(creditNote);
        } catch (FeignException e) {
            throw new FactusException(e.status(), parseException(e.contentUTF8()));
        }
    }

    public FactusResponse numberingRanges() throws FactusException {
        try {
            return apiClient.numberingRanges();
        } catch (FeignException e) {
            throw new FactusException(e.status(), parseException(e.contentUTF8()));
        }
    }

    private FactusResponse parseException(String json){
        try {
            return mapper.readValue(json, FactusResponse.class);
        } catch (JsonProcessingException e) {
            return FactusResponse.builder()
                    .status("JsonProcessingException")
                    .message(e.getMessage())
                    .build();
        }
    }


}
