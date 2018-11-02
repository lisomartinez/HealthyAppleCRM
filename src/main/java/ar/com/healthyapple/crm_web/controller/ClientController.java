package ar.com.healthyapple.crm_web.controller;

import ar.com.healthyapple.crm_web.dto.Client.ClientDto;
import ar.com.healthyapple.crm_web.dto.Client.ThinClientDto;
import ar.com.healthyapple.crm_web.dto.Product.ProductDto;
import ar.com.healthyapple.crm_web.exceptions.*;
import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.model.Product.Product;
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

    private final String MOBILE = "mobile";

    private final String NAME = "firstName";

    private final String LAST_NAME = "lastName";

    private final String EMAIL = "email";

    private final String START_DATE = "startDate";

    private final String ADDRESS = "address";

    private final String PRODUCT = "product";

    private final String SERVICE = "service";

    private Set<String> validParams;

    private ClientService clientService;

    private EntityDtoConverter entityDtoConverter;


    @Autowired
    public ClientController(ClientService clientService, EntityDtoConverter entityDtoConverter) {
        this.clientService = clientService;
        this.entityDtoConverter = entityDtoConverter;
        this.validParams = new HashSet<>(Arrays.asList(MOBILE, NAME, LAST_NAME, EMAIL, START_DATE, ADDRESS, PRODUCT, SERVICE));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ThinClientDto createClient(@RequestBody ThinClientDto clientDto) throws AlreadyExistException {
        Client client = clientService.create(entityDtoConverter.convertThinClientToEntity(clientDto, Client.class));
        return entityDtoConverter.convertToThinClientDto(client, ThinClientDto.class);
    }

    @GetMapping(Uris.ID)
    @ResponseStatus(HttpStatus.OK)
    public ThinClientDto readClient(@PathVariable Long id) throws NotFoundException {
        return entityDtoConverter.convertToThinClientDto(clientService.read(id), ThinClientDto.class);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ThinClientDto updateClient(@RequestBody ThinClientDto clientDto) throws NotFoundException {
        Client client = clientService.update(entityDtoConverter.convertThinClientToEntity(clientDto, Client.class));
        return entityDtoConverter.convertToThinClientDto(client, ThinClientDto.class);
    }

    @DeleteMapping(Uris.ID)
    @ResponseStatus(HttpStatus.OK)
    public void deleteClientById(@PathVariable Long id) throws NotFoundException {
        clientService.deleteById(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteClient(@RequestBody ClientDto clientDto) throws NotFoundException {
        clientService.delete(entityDtoConverter.convertToEntity(clientDto, Client.class));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ThinClientDto> findAllClients(Pageable pageable) throws PageDoesNotExistException {

        Page<Client> clientPage = this.clientService.findAll(pageable);

        return clientPage.map(client -> entityDtoConverter.convertToThinClientDto(client, ThinClientDto.class));

    }

    @GetMapping(Uris.SEARCH)
    @ResponseStatus(HttpStatus.OK)
    public Page<ThinClientDto> findClientByNameOrLastNameContaining(@RequestParam(required = true) String name, Pageable pageable) throws PageDoesNotExistException {

        Page<Client> clientPage = this.clientService.findByFirstNameOrLastName(name, pageable);

        List<ThinClientDto> clients =  clientPage.getContent().stream()
                .map(client -> entityDtoConverter.convertToThinClientDto(client, ThinClientDto.class))
                .collect(Collectors.toList());

        return clientPage.map(client -> entityDtoConverter.convertToThinClientDto(client, ThinClientDto.class));

    }

    @PatchMapping(Uris.ID)
    @ResponseStatus(HttpStatus.OK)
    public ThinClientDto updateClient(@PathVariable Long id, @RequestParam Map<String, String> requestParams) throws NotFoundException, InvalidParameterException, InvalidMobileNumberFormatException, InvalidClientNameException,
            InvalidAddressException, InvalidDateFormatException, InvalidEmailAddressException, NotYetImplementedException, AlreadyExistException {

        Client client;

        if (requestParams.keySet().stream().anyMatch(key -> !validParams.contains(key))) {
            throw new InvalidParameterException();
        } else {
            client = clientService.updateInfo(id, requestParams);
        }

        return entityDtoConverter.convertToThinClientDto(client, ThinClientDto.class);
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
                .map(product -> entityDtoConverter.convertToDto(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping(Uris.ID + Uris.PRODUCTS)
    @ResponseStatus(HttpStatus.CREATED)
    public List<ProductDto> addProductToClient(@PathVariable Long id, @RequestBody ProductDto productDto) throws NotFoundException, AlreadyExistException {

        return this.clientService.addProduct(id, entityDtoConverter.convertToEntity(productDto, Product.class))
                .stream()
                .map(product -> entityDtoConverter.convertToDto(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @DeleteMapping(Uris.ID + Uris.PRODUCTS)
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductById(@PathVariable Long id, @RequestParam Long productId) throws NotFoundException {
        clientService.deleteClientProductById(id, productId);
    }

}
