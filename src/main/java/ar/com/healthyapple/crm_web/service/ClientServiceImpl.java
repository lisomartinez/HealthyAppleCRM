package ar.com.healthyapple.crm_web.service;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.exceptions.PageDoesNotExistException;
import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.repository.Client.ClientRepository;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional
    public Client create(Client client) throws AlreadyExistException {
        if (clientRepository.findById(client.getMobile()).isPresent()) {
            throw new AlreadyExistException("The client already exists");
        } else {
            return clientRepository.save(client);
        }
    }

    @Override
    public Client read(Long mobile) throws NotFoundException {
        return clientRepository.findById(mobile).orElseThrow(() -> new NotFoundException("The client does not exist."));
    }

    @Override
    @Transactional
    public Client update(Client client) throws NotFoundException {
        clientRepository.findById(client.getMobile()).orElseThrow(() -> new NotFoundException("The client does not exist."));
        return clientRepository.save(client);
    }

    @Override
    @Transactional
    public void deleteByMobile(Long mobile) throws NotFoundException {
        clientRepository.findById(mobile).orElseThrow(() -> new NotFoundException("The client does not exist."));
        clientRepository.deleteById(mobile);
    }

    @Override
    @Transactional
    public void delete(Client client) throws NotFoundException {
        clientRepository.findById(client.getMobile()).orElseThrow(() -> new NotFoundException("The client does not exist."));
        clientRepository.delete(client);
    }

    @Override
    public Page<Client> findAll(int page, int pageSize) throws PageDoesNotExistException{
        Page<Client> clients = clientRepository.findAll(PageRequest.of(page - 1, pageSize, Sort.by("startDate")));
        if (page > clients.getTotalPages()) throw new PageDoesNotExistException("There is not page " + page);
        return clients;
    }

    @Override
    public Page<Client> findByFirstNameOrLastName(String firstNameOrLastName, int page, int pageSize) throws  PageDoesNotExistException {
        Page<Client> clients = clientRepository.findByFirstNameContainingAndLastNameContaining(firstNameOrLastName.toLowerCase(), PageRequest.of(page - 1, pageSize));
        if (page > clients.getTotalPages()) throw new PageDoesNotExistException("There is not page " + page);
        return clients;
    }

}
