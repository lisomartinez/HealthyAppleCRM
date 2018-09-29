package ar.com.healthyapple.crm_web.dto.Computer;

import com.fasterxml.jackson.annotation.JsonInclude.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


public class ClientDto {

    @JsonInclude(Include.NON_NULL)
    private Long mobile;

    private String startDate;

    private String firstName;

    private String lastName;

    private String email;

    private String address;

    private List<ProductDto> products;

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

    public ClientDto(Long mobile, String startDate, String firstName, String lastName, String email, String address) {
        this.mobile = mobile;
        this.startDate = startDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
    }

    public ClientDto() {
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    public List<SaleDto> getServices() {
        return services;
    }

    public void setServices(List<SaleDto> services) {
        this.services = services;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDto clientDto = (ClientDto) o;
        return Objects.equals(mobile, clientDto.mobile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mobile);
    }
}
