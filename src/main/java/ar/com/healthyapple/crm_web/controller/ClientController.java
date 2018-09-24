package ar.com.healthyapple.crm_web.controller;

import ar.com.healthyapple.crm_web.dto.Computer.ClientDto;
import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
public class ClientController {

    private ClientService clientService;

    private ModelMapper modelMapper;

    public ClientController(ClientService clientService, ModelMapper modelMapper) {
        this.clientService = clientService;
        this.modelMapper = modelMapper;
    }

//    @GetMapping
    public List<Client> findAll() {
        return this.clientService.findAll();

    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
    public ClientDto createClient(@RequestBody ClientDto clientDto){
        return null;
    }

    private Client convertToEntity(ClientDto clientDto) {
        return null;
    }
}
