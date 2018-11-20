package ar.com.healthyapple.crm_web.service.Quote;

import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Quote.QuoteList;
import ar.com.healthyapple.crm_web.repository.Quote.QuoteListRepository;
import ar.com.healthyapple.crm_web.repository.Quote.QuoteRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class QuoteListServiceTest {

    private final static Long QUOTE_NUMBER = 100L;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @TestConfiguration
    static class QuoteListServiceTestContextConfiguration {

        @Bean
        public QuoteListService quoteListService(QuoteListRepository quoteListRepository) {
            return new QuoteListServiceImpl(quoteListRepository);
        }
    }

    @Autowired
    private QuoteListService quoteListService;

    @MockBean
    private QuoteListRepository quoteListRepository;

    @MockBean
    private Quote quote;

    private QuoteList quoteList;

    @Before
    public void setUp() throws Exception {
        quoteList = new QuoteList(QUOTE_NUMBER, new ArrayList<>());
    }

    @Test
    public void addQuoteToQuoteList() {
        when(quote.getNumber()).thenReturn(QUOTE_NUMBER);
        when(quoteListRepository.findByQuoteNumber(QUOTE_NUMBER)).thenReturn(Optional.of(quoteList));
        QuoteList result = quoteListService.addQuoteToQuoteList(quote);
        assertThat(result.getQuotes()).isEqualTo(Arrays.asList(quote));
    }

    @Test
    public void addQuoteToQuoteListWithNullShouldThowAssertionError() {
        exception.expect(AssertionError.class);
        quoteListService.addQuoteToQuoteList(null);
    }

    @Test
    public void isQuoteTheLastVersionInList() {

        final Integer quote1Version = 1;
        final Integer quote2Version = 2;
        final Integer quote3Version = 3;
        final Integer quote4Version = 4;

        Quote quote1 = Mockito.mock(Quote.class);
        when(quote1.getVersion()).thenReturn(quote1Version);

        Quote quote2 = Mockito.mock(Quote.class);
        when(quote2.getVersion()).thenReturn(quote2Version);

        Quote quote3 = Mockito.mock(Quote.class);
        when(quote3.getVersion()).thenReturn(quote3Version);

        Quote quote4 = Mockito.mock(Quote.class);
        when(quote4.getVersion()).thenReturn(quote4Version);

        when(quote4.getNumber()).thenReturn(QUOTE_NUMBER);
        when(quoteListRepository.findByQuoteNumber(QUOTE_NUMBER)).thenReturn(Optional.of(new QuoteList(QUOTE_NUMBER, new ArrayList<>(Arrays.asList(quote1, quote2, quote3, quote4)))));

        assertThat(quoteListService.isQuoteTheLastVersionInList(quote4)).isTrue();

    }



    @Test
    public void isNotQuoteTheLastVersionInList() {

        final Integer quote1Version = 1;
        final Integer quote2Version = 2;
        final Integer quote3Version = 3;
        final Integer quote4Version = 4;

        Quote quote1 = Mockito.mock(Quote.class);
        when(quote1.getVersion()).thenReturn(quote1Version);

        Quote quote2 = Mockito.mock(Quote.class);
        when(quote2.getVersion()).thenReturn(quote2Version);

        Quote quote3 = Mockito.mock(Quote.class);
        when(quote3.getVersion()).thenReturn(quote3Version);

        Quote quote4 = Mockito.mock(Quote.class);
        when(quote4.getVersion()).thenReturn(quote4Version);

        when(quote1.getNumber()).thenReturn(QUOTE_NUMBER);
        when(quoteListRepository.findByQuoteNumber(QUOTE_NUMBER)).thenReturn(Optional.of(new QuoteList(QUOTE_NUMBER, new ArrayList<>(Arrays.asList(quote1, quote2, quote3, quote4)))));

        assertThat(quoteListService.isQuoteTheLastVersionInList(quote1)).isFalse();

    }

    @Test
    public void isQuoteTheLastVersionInListWithNullShouldThowAssertionError() {
        exception.expect(AssertionError.class);
        assertThat(quoteListService.isQuoteTheLastVersionInList(null)).isTrue();

    }
}