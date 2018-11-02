package ar.com.healthyapple.crm_web.controller;

import ar.com.healthyapple.crm_web.dto.Client.ClientDto;
import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.model.Product.*;
import ar.com.healthyapple.crm_web.model.Sale.Sale;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)

public class EntityDtoConverterTest {
/*
    private MacComputer mac;
    private MacComputerDto macDto;
    private EntityDtoConverter entityDtoConverter;

    private final String MODEL_IDENTIFIER = "AAA1234";
    private final String SERIAL_NUMBER = "123";
    private final String PREINSTALLED_MACOS = "Mavericks";
    private final Double DISPLAY_SIZE = 15.0;

    @Before
    public void setUp() throws Exception {
        mac = new MacComputer(
                new MotherBoard(),
                new Processor(),
                new Memory(),
                new HardDrive(),
                MODEL_IDENTIFIER,
                SERIAL_NUMBER,
                LocalDate.of(2010,8,4),
                PREINSTALLED_MACOS,
                DISPLAY_SIZE
        );
        entityDtoConverter = new EntityDtoConverter(new ModelMapper());
    }

    @Test
    @Ignore
    public void converComputerDtoToEntity() throws Exception{
       MacComputer mac1 = new MacComputer(
                new MotherBoard("Gigabyte", "B85M","AAA","775"),
                new Processor("Intel", "i3", "AAA",4, "775", 1000),
                new Memory("Kingston", "Detonator","AAA", "DDR4",1600,8),
                new HardDrive("WD", "caviar black", "AAA","ssd", "5000"),
                MODEL_IDENTIFIER,
                SERIAL_NUMBER,
                LocalDate.of(2010,8,4),
                PREINSTALLED_MACOS,
                DISPLAY_SIZE
        );
        macDto = entityDtoConverter.convertToDto(mac1, MacComputerDto.class);

    }

*/



    @Test
    public void convertToDto() {



        List<Specification> specifications = Arrays.asList(new Specification("SpecificationName", "SpecificationDescription"));

        ComponentType componentType = new ComponentType("ComponentType");

        Component component = new Component("ComponentType", componentType, specifications);

        List<Component> components = Arrays.asList(component);

        ProductType productType = new ProductType("Mac");

        Product product = new Product(new ProductType(), "i7", components);

        List<Product> productList = Arrays.asList(product);

        Client client = new Client(1111123123213L, LocalDate.of(2010, 3, 1), "Juan", "Perez", "jp@gmail.com", "LALALA 123",
                        productList,
                Arrays.asList(new Sale()));

        ModelMapper modelMapper = new ModelMapper();
//         modelMapper.createTypeMap(Client.class, ClientDto.class).addMappings(mapper -> {
//            mapper.map(Client::getMobile, ClientDto::setMobile);
//            mapper.map(Client::getStartDate, ClientDto::setAddress);
//            mapper.map(Client::getFirstName, ClientDto::setFirstName);
//            mapper.map(Client::getLastName, ClientDto::setLastName);
//            mapper.map(Client::getEmail, ClientDto::setEmail);
//            mapper.map(Client::getAddress, ClientDto::setAddress);
//            mapper.map(Client::getProducts, ClientDto::setProducts);
//             mapper.map(Client::getProductServices, ClientDto::setProductServices);
//         });
//
//         modelMapper.createTypeMap(Product.class, ProductDto.class).addMappings(mapper -> {
//             mapper.map(Product::getId, ProductDto::setId);
//             mapper.map(Product::getType, ProductDto::setType);
//             mapper.map(Product::getDescription, ProductDto::setDescription);
//             mapper.map(Product::getComponents, ProductDto::setComponents);
//
//         });

//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        ClientDto clientDto = new EntityDtoConverter(modelMapper).convertToDto(client, ClientDto.class);

        assertThat(clientDto.getProducts().get(0)).isNotNull();
    }
}