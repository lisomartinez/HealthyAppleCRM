package ar.com.healthyapple.crm_web.repository.Quote;

import ar.com.healthyapple.crm_web.model.Quote.QuoteList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuoteListRepository extends JpaRepository<QuoteList, Long>, QuerydslPredicateExecutor<QuoteList> {

    Optional<QuoteList> findByQuoteNumber(Long quoteNumber);
}
