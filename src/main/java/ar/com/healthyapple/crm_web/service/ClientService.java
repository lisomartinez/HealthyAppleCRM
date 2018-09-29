package ar.com.healthyapple.crm_web.service;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.exceptions.PageDoesNotExistException;
import ar.com.healthyapple.crm_web.model.Client.Client;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {

    Client create(Client client) throws AlreadyExistException;

    Client read(Long mobile) throws NotFoundException;

    Client update(Client client) throws NotFoundException;

    void deleteByMobile(Long mobile) throws NotFoundException;

    void delete(Client client) throws NotFoundException;

    Page<Client> findByFirstNameOrLastName(String firstNameOrLastName, int page, int pageSize) throws PageDoesNotExistException;

    Page<Client> findAll(int page, int pageSize) throws PageDoesNotExistException;


}
