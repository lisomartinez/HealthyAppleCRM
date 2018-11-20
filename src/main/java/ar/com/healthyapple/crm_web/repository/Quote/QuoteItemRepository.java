package ar.com.healthyapple.crm_web.repository.Quote;

import ar.com.healthyapple.crm_web.model.Quote.QuoteItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteItemRepository extends JpaRepository<QuoteItem, Long> {
}
