package ar.com.healthyapple.crm_web.dto.quote;

import ar.com.healthyapple.crm_web.model.Client.Client;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RequestQuoteDto {

    private Integer number;

    private Client client;

    private String description;
}
