package ar.com.healthyapple.crm_web.service.Client;

import ar.com.healthyapple.crm_web.exceptions.*;
import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.model.Product.Product;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;


public interface ClientService {

    Client create(Client client) throws AlreadyExistException;

    Client read(Long id) throws NotFoundException;


    Client update(Client client) throws NotFoundException;

    void deleteById(Long id) throws NotFoundException;

    void delete(Client client) throws NotFoundException;

    Page<Client> findByFirstNameOrLastName(String firstNameOrLastName, Pageable pageable) throws PageDoesNotExistException;

    Page<Client> findAll(Pageable pageable) throws PageDoesNotExistException;

    Client updateInfo(Long id, Map<String, String> params) throws NotFoundException, InvalidMobileNumberFormatException, InvalidClientNameException,
            InvalidAddressException, InvalidDateFormatException, InvalidEmailAddressException, NotYetImplementedException, AlreadyExistException;

    Client updateProduct(Long mobile, Product product) throws NotFoundException;

    List<Product> findProductsByClientId(Long id) throws NotFoundException;

    Map<Long, String> findProductsIdAndDescriptionByClientId(Long id) throws NotFoundException;

    void deleteClientProductById(Long clientId, Long productId) throws NotFoundException;

    List<Product> addProduct(Long id, Product product) throws NotFoundException, AlreadyExistException;
}
