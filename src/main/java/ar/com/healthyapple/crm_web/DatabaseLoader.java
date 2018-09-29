package ar.com.healthyapple.crm_web;

import ar.com.healthyapple.crm_web.model.*;
import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.model.Sale.Sale;
import ar.com.healthyapple.crm_web.repository.*;
import ar.com.healthyapple.crm_web.repository.Client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


@org.springframework.stereotype.Component
public class DatabaseLoader implements ApplicationRunner {

    private ClientRepository clientRepository;

    private TechnicalSpecificationTypeRepository technicalSpecificationTypeRepository;

    private TechnicalSpecificationRepository technicalSpecificationRepository;

    private ComponentRespository componentRespository;


    private SpecificationRepository specificationRepository;

    @Autowired
    public DatabaseLoader(ClientRepository clientRepository, TechnicalSpecificationTypeRepository technicalSpecificationTypeRepository, TechnicalSpecificationRepository technicalSpecificationRepository, ComponentRespository componentRespository,  SpecificationRepository specificationRepository) {
        this.clientRepository = clientRepository;
        this.technicalSpecificationTypeRepository = technicalSpecificationTypeRepository;
        this.technicalSpecificationRepository = technicalSpecificationRepository;
        this.componentRespository = componentRespository;
        this.specificationRepository = specificationRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        final String MODEL_IDENTIFIER = "AAA1234";
        final String SERIAL_NUMBER = "123";
        final String PREINSTALLED_MACOS = "Mavericks";
        final String DISPLAY_SIZE = "15.0";

        List<Specification> specifications = Arrays.asList(new Specification("SpecificationName", "SpecificationDescription"));

        Component component = new Component("ComponentType111", specifications);
        componentRespository.save(component);

        List<Component> components = Arrays.asList(component);

        TechnicalSpecificationType technicalSpecificationType = new TechnicalSpecificationType("Mac");
        technicalSpecificationTypeRepository.save(technicalSpecificationType);

        TechnicalSpecification macComputer = new TechnicalSpecification(technicalSpecificationType, components);

        Product product = new Product("imac", "i7", macComputer);

        List<Product> productList = Arrays.asList(product);

        Client client = new Client(1111123123213L, LocalDate.of(2010, 3, 1), "Juan", "Perez", "jp@gmail.com", "LALALA 123",
                productList,
                Arrays.asList(new Sale()));

//        TechnicalSpecification hackintoshComputer=  new TechnicalSpecification(
//                hackTechnicalSpecificationType, Arrays.asList(
//                new MotherBoard(),
//                new Processor(),
//                new Memory(),
//                new HardDrive(),
//                new PcCase(),
//                new PowerSupply(),
//                new VideoCard()
//        )
//        );

        clientRepository.save(client);

        client = new Client(998131123213L, LocalDate.of(2011, 3, 1), "Pedro", "Salame", "ps@gmail.com", "LALALA 456");
        clientRepository.save(client);

        client = new Client(242323123213L, LocalDate.of(2010, 2, 12), "Jose", "SinApellido", "js@gmail.com", "LALALA 7856");
        clientRepository.save(client);

        client = new Client(4123123L, LocalDate.of(2008, 8, 11),"Ramiro", "Novaro", "rn@gmail.com", "LALALA 12313");
        clientRepository.save(client);

        client = new Client(423121233L, LocalDate.of(2016, 9, 22), "Joaquin", "Martinez", "jm@gmail.com", "LALALA 97834");
        clientRepository.save(client);

        client = new Client(1223213L,  LocalDate.of(2017, 11, 1) ,"Saleme", "Lopez", "sl@gmail.com", "LALALA 6345");
        clientRepository.save(client);

        client = new Client(312123213L,  LocalDate.of(2017, 9, 11),  "Juan", "Alfonso", "ja@gmail.com", "LALALA 47567");
        clientRepository.save(client);


        client = new Client(3121232167573L,  LocalDate.of(2017, 8, 11), "Joaco", "Alfonso", "ja@gmail.com", "LALALA 47567");
        clientRepository.save(client);

        client = new Client(3121225675673213L,  LocalDate.of(2018, 2, 1), "Juan Martin", "Alfonso", "ja@gmail.com", "LALALA 47567");
        clientRepository.save(client);
        client = new Client(31212234243213L, LocalDate.of(2018, 2, 3), "Juan Manuel", "Alfonso", "ja@gmail.com", "LALALA 47567");
        clientRepository.save(client);


    }

}
