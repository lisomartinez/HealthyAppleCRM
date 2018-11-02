package ar.com.healthyapple.crm_web.repository.Product;

import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ComponentProfileCustomRepository {

    Map<Long, String> getProfilesNames() throws NotFoundException;

}
