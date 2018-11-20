package ar.com.healthyapple.crm_web.service.Quote;

import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.exceptions.QuoteListNotFoundException;
import ar.com.healthyapple.crm_web.exceptions.QuoteNotFoundException;
import ar.com.healthyapple.crm_web.exceptions.QuoteVersionMismatchException;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Quote.QuoteList;
import ar.com.healthyapple.crm_web.repository.Quote.QuoteListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

@Service
@Transactional
public class QuoteListServiceImpl implements QuoteListService {

    private QuoteListRepository quoteListRepository;

    @Autowired
    public QuoteListServiceImpl(QuoteListRepository quoteListRepository) {
        this.quoteListRepository = quoteListRepository;
    }

    @Override
    public QuoteList addQuoteToQuoteList(Quote quote) throws QuoteListNotFoundException {
        assert quote != null : "Quote can not be null";
        QuoteList quoteList = quoteListRepository.findByQuoteNumber(quote.getNumber())
                .orElseThrow(() -> new QuoteListNotFoundException("The quote List does not exists."));
        try {
            quoteList.getQuotes().add(quote);
        } catch (Exception ex) {
            throw new QuoteListNotFoundException("Quotes in Quote List do not exist");
        }
        return quoteList;
    }

    @Override
    public QuoteList createQuoteList(Quote quote) {
        QuoteList quoteList = new QuoteList();
        quoteList.setQuoteNumber(quote.getNumber());
        quoteList.setQuotes(new ArrayList<>(Arrays.asList(quote)));
        return quoteListRepository.save(quoteList);
    }

    @Override
    public boolean isQuoteTheLastVersionInList(Quote quote) throws QuoteNotFoundException, QuoteListNotFoundException, QuoteVersionMismatchException {
        assert quote != null : "Quote can not be null";
        QuoteList quoteList = quoteListRepository.findByQuoteNumber(quote.getNumber())
                .orElseThrow(() -> new QuoteListNotFoundException("The Quote List does not exists."));

        Quote lastQuote = quoteList
                .getQuotes().stream()
                .max(Comparator.comparing(Quote::getVersion))
                .orElseThrow(() -> new QuoteVersionMismatchException("The current quote is not the last version"));


        if (!lastQuote.getVersion().equals(quote.getVersion())) {
            return false;
        } else {
            return true;
        }

    }

    @Override
    public Optional<QuoteList> read(Long number) {
        return quoteListRepository.findByQuoteNumber(number);
    }
}
