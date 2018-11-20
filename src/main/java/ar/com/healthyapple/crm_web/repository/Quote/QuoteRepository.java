package ar.com.healthyapple.crm_web.repository.Quote;

import ar.com.healthyapple.crm_web.model.Quote.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long>, QuerydslPredicateExecutor<Quote>, QuoteCustomRepository {
}
