package ar.com.healthyapple.crm_web.model.Quote;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
@Table(name = "quote_lists")
public class QuoteList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long quoteNumber;

    @ManyToMany
    private List<Quote> quotes;

    public QuoteList(Long quoteNumber, List<Quote> quotes) {
        this.quoteNumber = quoteNumber;
        this.quotes = quotes;
    }
}
