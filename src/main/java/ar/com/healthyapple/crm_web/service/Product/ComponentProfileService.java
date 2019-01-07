package ar.com.healthyapple.crm_web.service.Product;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Product.ComponentProfile;

import java.util.List;
import java.util.Map;


public interface ComponentProfileService {

    ComponentProfile create(ComponentProfile componentProfile) throws AlreadyExistException;

    ComponentProfile read(Long id) throws NotFoundException;

    ComponentProfile update(ComponentProfile componentProfile) throws NotFoundException;

    List<ComponentProfile> findAll();

    Map<Long, String> getProfileIdAndNames();

}
