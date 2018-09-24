package ar.com.healthyapple.crm_web.service;

import ar.com.healthyapple.crm_web.dto.Computer.ClientDto;
import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.repository.Client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAll() {
        //return this.clientRepository.findAll(IllegalArgumentException::new);
        return null;
    }

    public Client create(ClientDto clientDto) {
        return null;
    }
}
