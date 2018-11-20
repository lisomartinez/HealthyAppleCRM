package ar.com.healthyapple.crm_web.dto.quote;

import ar.com.healthyapple.crm_web.dto.Client.ThinClientDto;
import ar.com.healthyapple.crm_web.model.Client.Client;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RequestQuoteDto {

    private String description;

    private Long clientId;

    public RequestQuoteDto(String description) {
        this.description = description;
    }
}
