package ar.com.healthyapple.crm_web.service.Product;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.exceptions.PageDoesNotExistException;
import ar.com.healthyapple.crm_web.model.Product.ComponentProfile;
import ar.com.healthyapple.crm_web.model.Product.ProductProfile;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public interface ProductProfileService {

    ProductProfile create(ProductProfile productProfile) throws AlreadyExistException;

    ProductProfile read(Long id) throws NotFoundException;

    ProductProfile readByName(String name) throws NotFoundException;

    Map<Long, String> getProfileIdAndTypes() throws NotFoundException;

    List<ProductProfile> findAll() throws PageDoesNotExistException;

    ProductProfile update(ProductProfile productProfile) throws NotFoundException;
}
