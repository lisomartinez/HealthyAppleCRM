package ar.com.healthyapple.crm_web.service.Quote;

import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.exceptions.QuoteNotFoundException;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Quote.QuoteList;
import ar.com.healthyapple.crm_web.repository.Quote.QuoteListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class QuoteListServiceImpl implements QuoteListService {

    private QuoteListRepository quoteListRepository;

    @Autowired
    public QuoteListServiceImpl(QuoteListRepository quoteListRepository) {
        this.quoteListRepository = quoteListRepository;
    }

    @Override
    public QuoteList addQuoteToQuoteList(Quote quote) {
        QuoteList quoteList = quoteListRepository.findByQuoteNumber(quote.getNumber())
                .orElseThrow(() -> new QuoteNotFoundException("The quote does not exists."));
        try {
            quoteList.getQuotes().add(quote);
        } catch (Exception ex) {
            throw new NotFoundException("Quotes in Quote List do not exist");
        }
        return quoteList;
    }

    @Override
    public boolean isQuoteTheLastVersionInList(Quote quote) {
        QuoteList quoteList = quoteListRepository.findByQuoteNumber(quote.getNumber())
                .orElseThrow(() -> new QuoteNotFoundException("The quote does not exists."));

        Quote lastQuote = quoteList
                .getQuotes().stream()
                .max(Comparator.comparing(Quote::getVersion)).orElseThrow(() -> new QuoteNotFoundException("The quote does not exists."));


        if (!lastQuote.getVersion().equals(quote.getVersion())) {
            return false;
        } else {
            return true;
        }

    }
}
