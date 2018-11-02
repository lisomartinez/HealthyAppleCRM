package ar.com.healthyapple.crm_web;

import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.model.Product.*;
import ar.com.healthyapple.crm_web.model.Sale.Sale;
import ar.com.healthyapple.crm_web.repository.Client.ClientRepository;
import ar.com.healthyapple.crm_web.repository.Product.*;
import org.apache.logging.log4j.LogManager;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


@org.springframework.stereotype.Component
public class DatabaseLoader implements ApplicationRunner {

    private ClientRepository clientRepository;

    private ProductTypeRepository productTypeRepository;

    private ComponentTypeRepository componentTypeRepository;

    private ComponentRepository componentRepository;

    private SpecificationRepository specificationRepository;

    private ProductProfileRepository productProfileRepository;

    private ComponentProfileRepository componentProfileRepository;


    @Autowired
    public DatabaseLoader(ClientRepository clientRepository, ProductTypeRepository productTypeRepository, ComponentTypeRepository componentTypeRepository, ComponentRepository componentRepository, SpecificationRepository specificationRepository, ProductProfileRepository productProfileRepository, ComponentProfileRepository componentProfileRepository) {
        this.clientRepository = clientRepository;
        this.productTypeRepository = productTypeRepository;
        this.componentTypeRepository = componentTypeRepository;
        this.componentRepository = componentRepository;
        this.specificationRepository = specificationRepository;
        this.productProfileRepository = productProfileRepository;
        this.componentProfileRepository = componentProfileRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        execute();
    }

    public void execute() {
        Map<String, ComponentProfile> componentProfileList = componentProfileGenerator();

        ComponentType macBookComponentType =  new ComponentType("macBookDescription");
        componentTypeRepository.save(macBookComponentType);

        ComponentType imacComponentType =  new ComponentType("imacDescription");
        componentTypeRepository.save(imacComponentType);

        ComponentType genericMemoryComponentType =  new ComponentType("genericMemory");
        componentTypeRepository.save(genericMemoryComponentType);

        ComponentType genericaHardDriveComponentType =  new ComponentType("genericHardDrive");
        componentTypeRepository.save(genericaHardDriveComponentType);


        ProductType macBookProductType = new ProductType("macBook");
        productTypeRepository.save(macBookProductType);

        ProductType iMacproductType = new ProductType("iMac");
        productTypeRepository.save(iMacproductType);

        productProfileGenerator(componentProfileList);

        clientGenerator(10);
    }

    private void clientGenerator(int quantity) {


        List<String> clients = null;

        ClassLoader classLoader = getClass().getClassLoader();

        try {
            clients = Files.readAllLines(Paths.get("src/fullnames.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        int min = 110000000;
        int max = 119999999;
        Random mobile = new Random();

        int minDay = 1;
        int maxDay = 7;
        Random day = new Random();


        int minMonth = 1;
        int maxMonth = 12;
        Random month = new Random();

        int minYear = 1988;
        int maxYear = 2050;
        Random year = new Random();

        int clientMax = clients.size() - 1;
        int clientMin = 1;
        Random clientRandom = new Random();

        StringBuilder email = new StringBuilder();

        String[] emailOptions = new String[] {"@gmail.com", "@hotmail.com", "@aol.com", "@fibertel.com.ar"};
        int emailOptionMin = 0;
        int emailOptionMax = emailOptions.length - 1;
        Random emailOptionsRandom = new Random();


        for (int i = 0; i < quantity; i++) {
            int index = clientRandom.nextInt((clientMax - clientMin) + 1) + clientMin;

            String[] clientName = clients.get(index).split(",");



            List<Product> productList = productGenerator(5, 5, i);

            String firstName = clientName[0].trim().toLowerCase();

            String lastName = clientName[1].trim().toLowerCase();


            Client client = new Client(
                    (long) mobile.nextInt((max - min) + 1) + min,
                    LocalDate.of(
                            year.nextInt((maxYear - minYear) + 1) + minYear,
                            month.nextInt((maxMonth - minMonth) + 1) + minMonth,
                            day.nextInt((maxDay - minDay) + 1) + minDay),
                    clientName[0], clientName[1],
                    email
                            .append(clientName[0].toLowerCase())
                            .append(clientName[1])
                            .append(emailOptions[emailOptionsRandom.nextInt((emailOptionMax - emailOptionMin) + 1) + emailOptionMin])
                            .toString(), "LALALA 123",
                    productList,
                    Arrays.asList(new Sale()));

            email.setLength(0);

            clientRepository.save(client);
        }
    }


    private List<Product> productGenerator(int min, int max, int clientId) {

        List<String> productsNames = new ArrayList<>(Arrays.asList("iMac", "macBook"));


        Supplier<Integer> productNamesIndex = () -> new Random().nextInt((productsNames.size() - 1) + 1);

        Supplier<Integer> productQuantity = () -> new Random().nextInt((max - min) + 1) + min;

        Supplier<String> getName = () -> productsNames.get(productNamesIndex.get());

        int quantity = productQuantity.get();

        Supplier<String> getValue = () -> String.valueOf(clientId + "|value-" + quantity + "|");


        Function<String, Product> generateProduct = s -> {
            Product prod = null;
            switch (s) {
                case "iMac":
                    prod = imacProductGenerator(getValue.get());
                break;
                case "macBook":
                    prod = macBookGenerator(getValue.get());
                    break;
                default:
                    break;
            }
            return prod;
        };

        return Stream.generate(getName).limit(quantity).map(generateProduct).collect(Collectors.toList());

    }



    private Product macBookGenerator(String id) {


        Component macDescription = componentGenerator(id, "macBookDescription");

        Component memory = componentGenerator(id, "genericMemory");

        Component hardDrive = componentGenerator(id, "genericHardDrive");

        List<Component> components = new ArrayList<>(Arrays.asList(macDescription, memory, hardDrive));



        Product product = new Product(productTypeRepository.findByNameIgnoreCase("macBook").orElseThrow(NotFoundException::new),
                new StringBuilder().append(id).append("macBook Product").toString(),
                components);

        return product;
    }


    private Product imacProductGenerator(String id) {


        Component macDescription = componentGenerator(id, "imacDescription");

        Component memory =  componentGenerator(id, "genericMemory");

        Component genericHardDrive = componentGenerator(id, "genericHardDrive");

        List<Component> components = new ArrayList<>(Arrays.asList(macDescription, memory, genericHardDrive));

        Product product = new Product(productTypeRepository.findByNameIgnoreCase("iMac").orElseThrow(NotFoundException::new),
                new StringBuilder().append(id).append("iMac Product").toString(),
                components);

        return product;
    }


    private Component componentGenerator(String id, String type) {

        List<Specification> specifications;
        Component component;

        switch (type) {
            case "macBookDescription":
                specifications = new ArrayList<>(Arrays.asList(
                        new Specification("name", new StringBuilder().append(id).append("MacBook9,1").toString()),
                        new Specification("modelIdentifier", new StringBuilder().append(id).append("MacBook9,1").toString()),
                        new Specification("serialNumber", new StringBuilder().append(id).append("FASDAA2132SD").toString()),
                        new Specification("preInstalledOS", new StringBuilder().append(id).append("Mavericks").toString()),
                        new Specification("introductionDate", new StringBuilder().append(id).append("2014-03-10").toString()),
                        new Specification("displaySize", new StringBuilder().append(id).append("15.4").toString())));

                component = new Component("macBook-" + id, componentTypeRepository.findByNameIgnoreCase("macBookDescription").orElseThrow(NotFoundException::new), specifications);

                break;
            case "imacDescription":
                specifications = new ArrayList<>(Arrays.asList(
                        new Specification("name", new StringBuilder().append(id).append("imac14,2").toString()),
                        new Specification("modelIdentifier", new StringBuilder().append(id).append("imac14,2").toString()),
                        new Specification("serialNumber", new StringBuilder().append(id).append("CASDA2132SD").toString()),
                        new Specification("preInstalledOS", new StringBuilder().append(id).append("Mavericks").toString()),
                        new Specification("introductionDate", new StringBuilder().append(id).append("2010-03-10").toString()),
                        new Specification("displaySize", new StringBuilder().append(id).append("15.4").toString())));

                component = new Component("iMac-" + id, componentTypeRepository.findByNameIgnoreCase("imacDescription").orElseThrow(NotFoundException::new), specifications);
                break;
            case "genericMemory":

                specifications = new ArrayList<>(Arrays.asList(
                        new Specification("type", new StringBuilder().append(id).append("DDR3").toString()),
                        new Specification("capacity", new StringBuilder().append(id).append("16gb").toString())));

                component = new Component(new StringBuilder().append(id).append("Memory").toString(), componentTypeRepository.findByNameIgnoreCase("genericMemory").orElseThrow(NotFoundException::new), specifications);

                break;
            case "genericHardDrive":

                specifications = new ArrayList<>(Arrays.asList(
                        new Specification("type", new StringBuilder().append(id).append("SSD").toString()),
                        new Specification("capacity", new StringBuilder().append(id).append("500GB").toString())));

                component = new Component(new StringBuilder().append(id).append("HardDrive").toString(), componentTypeRepository.findByNameIgnoreCase("genericHardDrive").orElseThrow(NotFoundException::new), specifications);

                break;
            default:
                throw new NotYetImplementedException();
        }

        return component;

    }

    private List<ProductProfile> productProfileGenerator(Map<String, ComponentProfile> profiles) {

        List<ProductProfile> productProfiles = new ArrayList<>();

        ProductProfile imacProductProfile = new ProductProfile("iMac", "Computadora iMac",
                new ArrayList<>(Arrays.asList(profiles.get("imacDescription"), profiles.get("genericMemory"), profiles.get("genericHardDrive"))));
        ProductProfile iMacProProductProfile = new ProductProfile("iMacPro", "Computadora iMac Pro",
                new ArrayList<>(Arrays.asList(profiles.get("imacDescription"),  profiles.get("genericMemory"), profiles.get("genericHardDrive"))));
        ProductProfile macBookProductProfile = new ProductProfile("macBook", "Laptop MacBook",
                new ArrayList<>(Arrays.asList(profiles.get("macBookDescription"), profiles.get("genericMemory"), profiles.get("genericHardDrive"))));
        ProductProfile macMiniProductProfile = new ProductProfile("macMini", "Computadora macMini",
                new ArrayList<>(Arrays.asList(profiles.get("imacDescription"),  profiles.get("genericMemory"), profiles.get("genericHardDrive"))));

        productProfiles.add(productProfileRepository.save(imacProductProfile));
        productProfiles.add(productProfileRepository.save(iMacProProductProfile));
        productProfiles.add(productProfileRepository.save(macBookProductProfile));
        productProfiles.add(productProfileRepository.save(macMiniProductProfile));

        return productProfiles;

    }

    private Map<String, ComponentProfile> componentProfileGenerator() {


        Map<String, ComponentProfile> profiles = new HashMap<>();

        Map<String, String> macGeneralDescription = new HashMap<>();
        macGeneralDescription.put("name", "Nombre");
        macGeneralDescription.put("modelIdentifier", "Identificador de Modelo");
        macGeneralDescription.put("serialNumber", "Número de serie");
        macGeneralDescription.put("preInstalledOS", "Sistema operativo de fábrica");
        macGeneralDescription.put("introductionDate", "fecha de lanzamiento");
        macGeneralDescription.put("displaySize", "Tamano de pantalla");

        ComponentProfile macGeneralDescriptionComponent = new ComponentProfile("imacDescription", "Description General Computadora iMac", false, macGeneralDescription);
        profiles.put("imacDescription", componentProfileRepository.save(macGeneralDescriptionComponent));


        ComponentProfile macbookDescriptionComponent = new ComponentProfile("macBookDescription", "Description General Macbook", false, macGeneralDescription);
        profiles.put("macBookDescription", componentProfileRepository.save(macbookDescriptionComponent));

        Map<String, String> memoryDescription = new HashMap<>();
        memoryDescription.put("brand", "Marca");
        memoryDescription.put("model", "Modelo");
        memoryDescription.put("type", "Tipo");
        memoryDescription.put("speed", "Velocidad");
        memoryDescription.put("capacity", "Capacidad");

        ComponentProfile memory = new ComponentProfile("memory", "Memoria Ram", true, memoryDescription);
        profiles.put("memory", componentProfileRepository.save(memory));

        Map<String, String> hardDriveDescription = new HashMap<>();
        hardDriveDescription.put("brand", "Marca");
        hardDriveDescription.put("model", "Modelo");
        hardDriveDescription.put("type", "Tipo");
        hardDriveDescription.put("capacity", "Capacidad");

        ComponentProfile hardDrive = new ComponentProfile("hardDrive", "Disco Rígido", true, hardDriveDescription);
        profiles.put("hardDrive", componentProfileRepository.save(hardDrive));


        Map<String, String> genericMemoryDescription = new HashMap<>();
        genericMemoryDescription.put("type", "Tipo");
        genericMemoryDescription.put("speed", "Velocidad");
        genericMemoryDescription.put("capacity", "Capacidad");

        ComponentProfile genericMemory = new ComponentProfile("genericMemory", "Memoria Ram genérica", true, genericMemoryDescription);
        profiles.put("genericMemory", componentProfileRepository.save(genericMemory));

        Map<String, String> genericHardDriveDescription = new HashMap<>();

        genericHardDriveDescription.put("type", "Tipo");
        genericHardDriveDescription.put("capacity", "Capacidad");

        ComponentProfile genericHardDrive = new ComponentProfile("genericHardDrive", "Disco Rígido genérico", true, genericHardDriveDescription);
        profiles.put("genericHardDrive", componentProfileRepository.save(genericHardDrive));


        return profiles;
    }


}
