package ar.com.healthyapple.crm_web.controller;

import ar.com.healthyapple.crm_web.dto.Computer.ClientDto;
import ar.com.healthyapple.crm_web.dto.Computer.ThinClientDto;
import ar.com.healthyapple.crm_web.dto.Computer.ThinClientPageDto;
import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.exceptions.PageDoesNotExistException;
import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(Uris.CLIENTS)
public class ClientController {

    private ClientService clientService;

    private EntityDtoConverter entityDtoConverter;

    private ThinClientPageDto clientPageDto;

    @Autowired
    public ClientController(ClientService clientService, EntityDtoConverter entityDtoConverter, ThinClientPageDto clientPageDto) {
        this.clientService = clientService;
        this.entityDtoConverter = entityDtoConverter;
        this.clientPageDto = clientPageDto;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDto create(@RequestBody ClientDto clientDto) throws AlreadyExistException {
        Client client = clientService.create(entityDtoConverter.convertToEntity(clientDto, Client.class));
        return entityDtoConverter.convertToDto(client, ClientDto.class);
    }

    @GetMapping(Uris.ID)
    @ResponseStatus(HttpStatus.OK)
    public ClientDto read(@PathVariable String id) throws NotFoundException {
        return entityDtoConverter.convertToDto(clientService.read(Long.parseLong(id)), ClientDto.class);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ClientDto update(@RequestBody ClientDto clientDto) throws NotFoundException {
        Client client = clientService.update(entityDtoConverter.convertToEntity(clientDto, Client.class));
        return entityDtoConverter.convertToDto(client, ClientDto.class);
    }

    @DeleteMapping(Uris.ID)
    @ResponseStatus(HttpStatus.OK)
    public void deleteByMobile(@PathVariable String id) throws NotFoundException {
        clientService.deleteByMobile(Long.parseLong(id));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestBody ClientDto clientoDto) throws NotFoundException {
        clientService.delete(entityDtoConverter.convertToEntity(clientoDto, Client.class));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ThinClientPageDto findAll(@RequestParam int page, @RequestParam int pageSize) throws PageDoesNotExistException {
        Page<Client> clientPage = this.clientService.findAll(page, pageSize);

        List<ThinClientDto> clients =  clientPage.getContent().stream()
                .map(client -> entityDtoConverter.convertToThinClientDto(client, ThinClientDto.class))
                .collect(Collectors.toList());

        clientPageDto.setActualPage(page);
        clientPageDto.setMaxPage(clientPage.getTotalPages());
        clientPageDto.setClients(clients);

        return clientPageDto;

    }

    @GetMapping(Uris.SEARCH)
    @ResponseStatus(HttpStatus.OK)
    public ThinClientPageDto findNameOrLastNameContaining(@RequestParam (required = true) String name, @RequestParam int page, @RequestParam int pageSize) throws PageDoesNotExistException {

        Page<Client> clientPage = this.clientService.findByFirstNameOrLastName(name, page, pageSize);

        List<ThinClientDto> clients =  clientPage.getContent().stream()
                .map(client -> entityDtoConverter.convertToThinClientDto(client, ThinClientDto.class))
                .collect(Collectors.toList());

        clientPageDto.setActualPage(page);
        clientPageDto.setMaxPage(clientPage.getTotalPages());
        clientPageDto.setClients(clients);

        return clientPageDto;
    }
}
