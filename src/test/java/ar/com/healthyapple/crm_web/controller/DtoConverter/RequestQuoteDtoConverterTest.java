package ar.com.healthyapple.crm_web.controller.DtoConverter;

import ar.com.healthyapple.crm_web.controller.DtoConverter.RequestQuoteDtoConverter;
import ar.com.healthyapple.crm_web.dto.Client.ThinClientDto;
import ar.com.healthyapple.crm_web.dto.quote.RequestQuoteDto;
import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
public class RequestQuoteDtoConverterTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    public static final Long ID = 1l;
    public static final Long NUMBER = 2L;
    public static final String DESCRIPTION = "description";

    private ModelMapper modelMapper;

    private RequestQuoteDtoConverter converter;

    private Quote quote;

    private RequestQuoteDto requestQuoteDto;

    @MockBean
    private ThinClientDto clientDto;

    @MockBean
    private Client client;

    @Before
    public void setUp() throws Exception {
        modelMapper = new ModelMapper();
        converter = new RequestQuoteDtoConverter(modelMapper);

        quote = new Quote();
        quote.setNumber(NUMBER);
        quote.setDescription(DESCRIPTION);

        requestQuoteDto = new RequestQuoteDto(DESCRIPTION);

    }

    @Test
    public void convertToDto() {
        RequestQuoteDto result = converter.convertToDto(quote);
        assertThat(result).isEqualTo(requestQuoteDto);
    }

    @Test
    public void convertToEntity() {
        Quote result = converter.convertToEntity(requestQuoteDto);
        assertThat(result).isEqualTo(quote);
    }
    @Test
    public void convertToDtoWithNull() {
        exception.expect(IllegalArgumentException.class);
        converter.convertToDto(null);
    }

    @Test
    public void convertToEntityWithNull() {
        exception.expect(IllegalArgumentException.class);
        converter.convertToEntity(null);
    }

}