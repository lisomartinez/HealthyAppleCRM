package ar.com.healthyapple.crm_web.dto;

import ar.com.healthyapple.crm_web.dto.Client.ClientDto;
import ar.com.healthyapple.crm_web.dto.service.ServiceDto;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ={"id"})
public class SaleDto {

    private Long id;

    private String tag;

    private String description;

    private SaleStateDto state;

    private ClientDto client;

    private LocalDate originDate;

    private LocalDate dueDate;

    private BigDecimal totalCost;

    private BigDecimal finalPrice;

    private List<ServiceDto> services;
}
