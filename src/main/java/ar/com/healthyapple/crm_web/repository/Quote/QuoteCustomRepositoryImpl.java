package ar.com.healthyapple.crm_web.repository.Quote;

import ar.com.healthyapple.crm_web.model.Quote.QQuote;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

public class QuoteCustomRepositoryImpl implements QuoteCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Quote> getNewestQuotes(int quantity) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QQuote quote = QQuote.quote;
        return  queryFactory
                .selectFrom(quote)
                .where(
                        Expressions.list(quote.number, quote.created)
                                .in(JPAExpressions
                                        .select(quote.number, quote.created.max())
                                        .from(quote)
                                        .groupBy(quote.number)
                                )
                ).orderBy(quote.created.desc())
                .limit(quantity)
                .fetch();
    }

    @Override
    public Long findLastQuoteNumber() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QQuote quote = QQuote.quote;

        return queryFactory.select(quote.number).from(quote).where(quote.number.eq(
                JPAExpressions
                        .select(quote.number.max())
                        .from(quote)
                )
                ).fetchFirst();


    }
}
