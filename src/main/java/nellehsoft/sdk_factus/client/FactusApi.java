package nellehsoft.sdk_factus.client;

import feign.Headers;
import feign.RequestLine;
import nellehsoft.sdk_factus.model.CreditNote;
import nellehsoft.sdk_factus.model.DraftBill;
import nellehsoft.sdk_factus.model.FactusResponse;

public interface FactusApi {

    @RequestLine("POST /v1/bills/validate")
    @Headers("Content-Type: application/json")
    FactusResponse validateAndCreateBill(DraftBill draftBill);

    @RequestLine("GET /v1/numbering-ranges?filter[id]&filter[document]&filter[resolution_number]&filter[technical_key]&filter[is_active]")
    @Headers("Content-Type: application/json")
    FactusResponse numberingRanges();

    @RequestLine("POST /v1/credit-notes/validate")
    @Headers("Content-Type: application/json")
    FactusResponse validateAndCreateCreditNotes(CreditNote creditNote);
}
