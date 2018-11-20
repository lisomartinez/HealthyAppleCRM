package ar.com.healthyapple.crm_web.controller;

import ar.com.healthyapple.crm_web.controller.DtoConverter.ClientDtoConverter;
import ar.com.healthyapple.crm_web.controller.DtoConverter.ProductDtoConverter;
import ar.com.healthyapple.crm_web.controller.DtoConverter.ThinClientDtoConverter;
import ar.com.healthyapple.crm_web.dto.Client.ClientDto;
import ar.com.healthyapple.crm_web.dto.Client.ThinClientDto;
import ar.com.healthyapple.crm_web.dto.Product.ProductDto;
import ar.com.healthyapple.crm_web.exceptions.*;
import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.service.Client.ClientService;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(Uris.CLIENTS)
public class ClientController {

    private ClientService clientService;

    private ClientDtoConverter clientDtoConverter;

    private ProductDtoConverter productDtoConverter;

    private ThinClientDtoConverter thinClientDtoConverter;

    @Autowired
    public ClientController(ClientService clientService, ClientDtoConverter clientDtoConverter, ProductDtoConverter productDtoConverter, ThinClientDtoConverter thinClientDtoConverter) {
        this.clientService = clientService;
        this.clientDtoConverter = clientDtoConverter;
        this.productDtoConverter = productDtoConverter;
        this.thinClientDtoConverter = thinClientDtoConverter;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ThinClientDto createClient(@RequestBody ThinClientDto clientDto) throws AlreadyExistException {
        Client client = clientService.create(thinClientDtoConverter.convertToEntity(clientDto));
        return thinClientDtoConverter.convertToDto(client);
    }

    @GetMapping(Uris.ID)
    @ResponseStatus(HttpStatus.OK)
    public ThinClientDto readClient(@PathVariable Long id) throws NotFoundException {
        return thinClientDtoConverter.convertToDto(clientService.read(id));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ThinClientDto updateClient(@RequestBody ThinClientDto clientDto) throws NotFoundException {
        Client client = clientService.update(thinClientDtoConverter.convertToEntity(clientDto));
        return thinClientDtoConverter.convertToDto(client);
    }

    @DeleteMapping(Uris.ID)
    @ResponseStatus(HttpStatus.OK)
    public void deleteClientById(@PathVariable Long id) throws NotFoundException {
        clientService.deleteById(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteClient(@RequestBody ClientDto clientDto) throws NotFoundException {
        clientService.delete(clientDtoConverter.convertToEntity(clientDto));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ThinClientDto> findAllClients(Pageable pageable) throws PageDoesNotExistException {

        Page<Client> clientPage = this.clientService.findAll(pageable);

        return clientPage.map(client -> thinClientDtoConverter.convertToDto(client));

    }

    @GetMapping(Uris.SEARCH)
    @ResponseStatus(HttpStatus.OK)
    public Page<ThinClientDto> findClientByNameOrLastNameContaining(@RequestParam(required = true) String name, Pageable pageable) throws PageDoesNotExistException {

        Page<Client> clientPage = this.clientService.findByFirstNameOrLastName(name, pageable);

        List<ThinClientDto> clients =  clientPage.getContent().stream()
                .map(client -> thinClientDtoConverter.convertToDto(client))
                .collect(Collectors.toList());

        return clientPage.map(client -> thinClientDtoConverter.convertToDto(client));

    }

    @PatchMapping(Uris.ID)
    @ResponseStatus(HttpStatus.OK)
    public ThinClientDto updateClient(@PathVariable Long id, @RequestParam Map<String, String> requestParams) throws NotFoundException, InvalidParameterException, InvalidMobileNumberFormatException, InvalidClientNameException,
            InvalidAddressException, InvalidDateFormatException, InvalidEmailAddressException, NotYetImplementedException, AlreadyExistException {

        final String MOBILE = "mobile";
        final String NAME = "firstName";
        final String LAST_NAME = "lastName";
        final String EMAIL = "email";
        final String START_DATE = "startDate";
        final String ADDRESS = "address";
        final String PRODUCT = "component";
        Set<String> validParams = new HashSet<>(Arrays.asList(MOBILE, NAME, LAST_NAME, EMAIL, START_DATE, ADDRESS, PRODUCT));

        Client client;

        if (requestParams.keySet().stream().anyMatch(key -> !validParams.contains(key))) {
            throw new InvalidParameterException();
        } else {
            client = clientService.updateInfo(id, requestParams);
        }

        return thinClientDtoConverter.convertToDto(client);
    }


    @GetMapping(Uris.ID + Uris.PRODUCTS + Uris.NAMES)
    @ResponseStatus(HttpStatus.OK)
    public Map<Long, String> findProductsIdAndDescriptionByClientId(@PathVariable Long id) throws NotFoundException {
        return this.clientService.findProductsIdAndDescriptionByClientId(id);
    }


    @GetMapping(Uris.ID + Uris.PRODUCTS)
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> findProductsByClientId(@PathVariable Long id) throws NotFoundException {
        return this.clientService.findProductsByClientId(id)
                .stream()
                .map(product -> productDtoConverter.convertToDto(product))
                .collect(Collectors.toList());
    }

    @PostMapping(Uris.ID + Uris.PRODUCTS)
    @ResponseStatus(HttpStatus.CREATED)
    public List<ProductDto> addProductToClient(@PathVariable Long id, @RequestBody ProductDto productDto) throws NotFoundException, AlreadyExistException {

        return this.clientService.addProduct(id, productDtoConverter.convertToEntity(productDto))
                .stream()
                .map(product -> productDtoConverter.convertToDto(product))
                .collect(Collectors.toList());
    }

    @DeleteMapping(Uris.ID + Uris.PRODUCTS)
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductById(@PathVariable Long id, @RequestParam Long productId) throws NotFoundException {
        clientService.deleteClientProductById(id, productId);
    }

}
