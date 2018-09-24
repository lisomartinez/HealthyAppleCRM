package ar.com.healthyapple.crm_web.model.Sale;

import lombok.*;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public abstract class SaleState {

    private String name;

    private String description;

}
