package ar.com.healthyapple.crm_web.dto.Client;

import ar.com.healthyapple.crm_web.dto.Product.ProductDto;
import ar.com.healthyapple.crm_web.dto.SaleDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
public class ClientDto {

    private Long id;

    @JsonInclude(Include.NON_NULL)
    private Long mobile;

    @JsonInclude(Include.NON_NULL)
    private String startDate;

    @JsonInclude(Include.NON_NULL)
    private String firstName;

    @JsonInclude(Include.NON_NULL)
    private String lastName;

    @JsonInclude(Include.NON_NULL)
    private String email;

    @JsonInclude(Include.NON_NULL)
    private String address;

    @JsonInclude(Include.NON_NULL)
    private List<ProductDto> products;

    @JsonInclude(Include.NON_NULL)
    private List<SaleDto> services;

    public ClientDto(Long mobile, String startDate, String firstName, String lastName, String email, String address, List<ProductDto> products, List<SaleDto> services) {
        this.mobile = mobile;
        this.startDate = startDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.products = products;
        this.services = services;
    }
}
