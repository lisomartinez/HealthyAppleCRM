package ar.com.healthyapple.crm_web.dto.Computer;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ={"actualPage"})
@Component
public class ThinClientPageDto {

    private int actualPage;

    private int maxPage;

    private List<ThinClientDto> clients;

}
