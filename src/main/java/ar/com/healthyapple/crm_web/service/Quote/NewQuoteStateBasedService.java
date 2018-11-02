package ar.com.healthyapple.crm_web.service.Quote;

import ar.com.healthyapple.crm_web.exceptions.*;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Quote.QuoteList;
import ar.com.healthyapple.crm_web.model.Quote.QuoteState;
import ar.com.healthyapple.crm_web.repository.Quote.QuoteListRepository;
import ar.com.healthyapple.crm_web.repository.Quote.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;

@Service
public class NewQuoteStateBasedService implements QuoteStateBasedService {

    private final static Integer FIRST = 1;

    private Quote quote;

    private QuoteRepository quoteRepository;

    private QuoteListRepository quoteListRepository;

    @Autowired
    public NewQuoteStateBasedService(QuoteRepository quoteRepository, QuoteListRepository quoteListRepository) {
        this.quoteRepository = quoteRepository;
        this.quoteListRepository = quoteListRepository;
    }

    @Override
    public boolean isResponsibleFor(QuoteState quoteState) {
        return quoteState.equals(QuoteState.NEW);
    }

    @Override
    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    @Override
    public Quote request() throws QuoteOperationNotAllowedException, AlreadyExistException {

        if (quoteListRepository.findByQuoteNumber(quote.getNumber()).isPresent()) {
            throw new AlreadyExistException("The quote already exists.");
        }

        quote.setStatus(QuoteState.REQUESTED);
        quote.setVersion(FIRST);
        quote.setCreated(LocalDateTime.now());

        Quote savedQuote = quoteRepository.save(quote);

        quoteListRepository.save(new QuoteList(quote.getNumber(), Arrays.asList(savedQuote)));

        return savedQuote;
    }


}
