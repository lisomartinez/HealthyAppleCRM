package ar.com.healthyapple.crm_web.repository.Product;

import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Product.QProductProfile;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ProductProfileCustomRepositoryImpl implements ProductProfileCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Map<Long, String> getProfilesNames() throws NotFoundException {
        JPAQueryFactory query = new JPAQueryFactory(em);

        QProductProfile profile = QProductProfile.productProfile;

        List<Tuple> results = query.from(profile).select(Projections.tuple(profile.id, profile.description)).fetch();

        return results.parallelStream().collect(Collectors.toMap(row -> row.get(profile.id), row -> row.get(profile.description)));

    }
}
