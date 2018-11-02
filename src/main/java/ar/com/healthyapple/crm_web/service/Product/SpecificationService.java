package ar.com.healthyapple.crm_web.service.Product;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.exceptions.PageDoesNotExistException;
import ar.com.healthyapple.crm_web.model.Product.Specification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;

@Service
public interface SpecificationService {

    Specification create(Specification specification) throws AlreadyExistException;

    Specification read(Long id) throws NotFoundException;

    Specification update(Specification specification) throws NotFoundException;

    void deleteById(Long id) throws NotFoundException;

    void delete(Specification specification) throws NotFoundException;

    Specification changeName(Long id, @NotEmpty @NonNull String name) throws NotFoundException;

    Specification changeDescription(Long id, @NotEmpty @NonNull String description) throws NotFoundException;

    Page<Specification> findAll(Pageable pageable) throws PageDoesNotExistException;
}
