package ar.com.healthyapple.crm_web.repository.Client;

import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.model.Client.QClient;
import ar.com.healthyapple.crm_web.model.Product.*;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.CollectionExpression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.ListPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class ClientCustomRepositoryImpl implements ClientCustomRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    public Map<Long, String> findClientProductsProfileDescriptionByProductType(Long clientId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QClient client = QClient.client;
                QClient client1 = new QClient("client1");
        NumberPath<Long> id = QProduct.product.id;
        ListPath<Product, QProduct> products = QClient.client.products;
        QProduct product = QProduct.product;
        QProductType productType = QProductType.productType;
        QProductProfile productProfile = QProductProfile.productProfile;
        QProduct product1 = new QProduct("product1");

                List<Tuple> results =  queryFactory.select(product.id, productProfile.description)
                        .from(productProfile)
                        .from(product).orderBy(product.id.asc())
                         .from(client)
                        .where(client.id.eq(clientId).and(client.products.contains(product)).and(productProfile.type.eq(product.productType.name))).fetch();

        LogManager.getLogger(this.getClass().getName()).debug("---------------------> ID AND DESC <-------------------------");
        LogManager.getLogger(this.getClass().getName()).debug(results);
            return results.stream().collect(Collectors.toMap(row -> row.get(product.id), row -> row.get(productProfile.description)));

    }

}
