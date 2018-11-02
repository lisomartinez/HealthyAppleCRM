package ar.com.healthyapple.crm_web.service.Client;

import ar.com.healthyapple.crm_web.exceptions.*;
import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.model.Client.QClient;
import ar.com.healthyapple.crm_web.model.Product.Product;
import ar.com.healthyapple.crm_web.model.Sale.Sale;
import ar.com.healthyapple.crm_web.repository.Client.ClientRepository;
import ar.com.healthyapple.crm_web.repository.Product.ProductRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final String MOBILE = "mobile";

    private final String NEW_MOBILE = "newMobile";

    private final String NAME = "firstName";

    private final String LAST_NAME = "lastName";

    private final String EMAIL = "email";

    private final String START_DATE = "startDate";

    private final String ADDRESS = "address";

    private final String PRODUCT = "product";

    private final String SERVICE = "service";


    private ClientRepository clientRepository;

    private ClientInfoValidator clientInfoValidator;


    private ProductRepository productRepository;

    public ClientServiceImpl(ClientRepository clientRepository, ClientInfoValidator clientInfoValidator, ProductRepository productRepository) {
        this.clientRepository = clientRepository;
        this.clientInfoValidator = clientInfoValidator;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Client create(Client client) throws AlreadyExistException {
        if (clientRepository.findByMobile(client.getMobile()).isPresent()) {
            throw new AlreadyExistException("The client already exists");
        } else {
            return clientRepository.save(client);
        }
    }

    @Override
    public Client read(Long id) throws NotFoundException {
        return clientRepository.findById(id).orElseThrow(() -> new NotFoundException("The client does not exist."));
    }

    @Override
    @Transactional
    public Client update(Client client) throws NotFoundException {
        clientRepository.findById(client.getId()).orElseThrow(() -> new NotFoundException("The client does not exist."));
        return clientRepository.save(client);
    }


    @Override
    @Transactional
    public void deleteById(Long id) throws NotFoundException {
        clientRepository.findById(id).orElseThrow(() -> new NotFoundException("The client does not exist."));
        clientRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(Client client) throws NotFoundException {
        clientRepository.findById(client.getMobile()).orElseThrow(() -> new NotFoundException("The client does not exist."));
        clientRepository.delete(client);
    }

    @Override
    public Page<Client> findAll(Pageable pageable) throws PageDoesNotExistException {
        Page<Client> clients = clientRepository.findAll(pageable);
        return clients;
    }

    @Override
    public Page<Client> findByFirstNameOrLastName(String firstNameOrLastName, Pageable pageable) throws PageDoesNotExistException {

        BooleanExpression firstNameContainsIgnoringCase = QClient.client.firstName.containsIgnoreCase(firstNameOrLastName);

        BooleanExpression lastNameContainsIgnoringCase = QClient.client.lastName.containsIgnoreCase(firstNameOrLastName);

        BooleanBuilder booleanBuilder = new BooleanBuilder().or(firstNameContainsIgnoringCase).or(lastNameContainsIgnoringCase);

        OrderSpecifier<LocalDate> newestClients = QClient.client.startDate.desc();

        return clientRepository.findAll(booleanBuilder, pageable);
    }

    @Override
    @Transactional
    public Client updateInfo(Long id, Map<String, String> params)
            throws NotFoundException, InvalidMobileNumberFormatException,
            InvalidClientNameException, InvalidAddressException,
            InvalidDateFormatException, InvalidEmailAddressException,
            NotYetImplementedException, AlreadyExistException {

        Client client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException("The client does not exist."));
        for (String field : params.keySet()) {
            switch (field) {
                case MOBILE:
                    client = updateMobile(client, params.get(MOBILE));
                    break;
                case NAME:
                    updateFirstName(client, params.get(NAME));
                    break;
                case LAST_NAME:
                    updateLastName(client, params.get(LAST_NAME));
                    break;
                case EMAIL:
                    updateEmail(client, params.get(EMAIL));
                    break;
                case ADDRESS:
                    updateAddress(client, params.get(ADDRESS));
                    break;
                case START_DATE:
                    updateStartDate(client, params.get(START_DATE));
                    break;
                default:
                    throw new NotYetImplementedException();
            }
        }
        return clientRepository.save(client);
    }

    private Client updateMobile(Client client, String newMobileNumber) throws InvalidMobileNumberFormatException, AlreadyExistException {
        if (clientInfoValidator.isMobileValid(newMobileNumber)) {
            if (clientRepository.findByMobile(Long.parseLong(newMobileNumber)).isPresent()) {
                throw new AlreadyExistException("Mobile number already exists " + newMobileNumber);
            } else {
                client.setMobile(Long.parseLong(newMobileNumber));
                return clientRepository.save(client);
            }
        } else {
            throw new InvalidMobileNumberFormatException(newMobileNumber);
        }
    }

    private void updateFirstName(Client client, String firstName) throws InvalidClientNameException {
        if (clientInfoValidator.isFirstNameValid(firstName)) {
            client.setFirstName(firstName);
        } else {
            throw new InvalidClientNameException(firstName);
        }
    }

    private void updateLastName(Client client, String lastName) throws InvalidClientNameException {
        if (clientInfoValidator.isLastNameValid(lastName)) {
            client.setFirstName(lastName);
        } else {
            throw new InvalidClientNameException(lastName);
        }
    }

    private void updateAddress(Client client, String address) throws InvalidAddressException {
        if (clientInfoValidator.isAddressValid(address)) {
            client.setAddress(address);
        } else {
            throw new InvalidAddressException(address);
        }
    }

    private void updateStartDate(Client client, String startDate) throws InvalidDateFormatException {
        if (clientInfoValidator.isStartDateValid(startDate)) {
//            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(startDate);
            client.setStartDate(localDate);
        } else {
            throw new InvalidDateFormatException(startDate);
        }
    }

    private void updateEmail(Client client, String email) throws InvalidEmailAddressException {
        if (clientInfoValidator.isEmailValid(email)) {
            client.setEmail(email);
        } else {
            throw new InvalidEmailAddressException(email);
        }
    }

    @Override
    @Transactional
    public Client updateProduct(Long mobile, Product product) throws NotFoundException {
        Client client = clientRepository.findById(mobile)
                .orElseThrow(() -> new NotFoundException("The client does not exist."));
        Product clientProduct = client.getProducts().stream()
                .filter(clientProd -> clientProd.getId().equals(product.getId()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("The Product does not exist."));
        clientProduct = product;
        return clientRepository.save(client);
    }

    @Override
    @Transactional
    public Client updateService(Long mobile, Sale sale) throws NotFoundException {
        throw new NotYetImplementedException();
    }

    @Override
    @Transactional
    public List<Product> findProductsByClientId(Long id) throws NotFoundException {
        Client client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException("The client does not exist."));
        return client.getProducts();
    }

    @Override
    @Transactional
    public Map<Long, String> findProductsIdAndDescriptionByClientId(Long id) throws NotFoundException {
//        List<Product> products = findProductsByClientId(id);
//        return products.stream().collect(Collectors.toMap(Product::getId, Product::getDescription));
        return clientRepository.findClientProductsProfileDescriptionByProductType(id);
//        return null;
    }



    @Override
    @Transactional
    public void deleteClientProductById(Long clientId, Long productId) throws NotFoundException {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new NotFoundException("The client does not exist."));
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("The product does not exist."));
        client.getProducts().remove(product);
        clientRepository.save(client);
    }

    @Override
    @Transactional
    public List<Product> addProduct(Long id, Product product) throws NotFoundException, AlreadyExistException{
        Client client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException("The client does not exist."));
        client.getProducts().add(product);
        Client savedClient = clientRepository.save(client);
        return savedClient.getProducts();
    }


}
