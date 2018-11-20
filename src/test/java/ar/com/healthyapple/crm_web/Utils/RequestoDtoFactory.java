package ar.com.healthyapple.crm_web.Utils;

import ar.com.healthyapple.crm_web.dto.quote.RequestQuoteDto;

public class RequestoDtoFactory {
    public static RequestQuoteDto makeRequestoDto() {
        return new RequestQuoteDto("description", ThinClientDtoFactory.makeThinClientDto().getId());
    }
}
