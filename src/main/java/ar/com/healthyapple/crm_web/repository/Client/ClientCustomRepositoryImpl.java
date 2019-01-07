package ar.com.healthyapple.crm_web.repository.Client;

import ar.com.healthyapple.crm_web.model.Client.QClient;
import ar.com.healthyapple.crm_web.model.Product.QProduct;
import ar.com.healthyapple.crm_web.model.Product.QProductProfile;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ClientCustomRepositoryImpl implements ClientCustomRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    public Map<Long, String> findClientProductsProfileDescriptionByProductType(Long clientId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QClient client = QClient.client;
        QProduct product = QProduct.product;
        QProductProfile productProfile = QProductProfile.productProfile;

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
