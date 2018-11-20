package ar.com.healthyapple.crm_web.controller;

import ar.com.healthyapple.crm_web.controller.DtoConverter.ProductProfileDtoConverter;
import ar.com.healthyapple.crm_web.dto.Product.ProductProfileDto;
import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.exceptions.PageDoesNotExistException;
import ar.com.healthyapple.crm_web.model.Product.ProductProfile;
import ar.com.healthyapple.crm_web.service.Product.ProductProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(Uris.PRODUCTS + Uris.PROFILES)
public class ProductProfileController {

    private ProductProfileService productProfileService;

    private ProductProfileDtoConverter productProfileDtoConverter;

    @Autowired
    public ProductProfileController(ProductProfileService productProfileService, ProductProfileDtoConverter productProfileDtoConverter) {
        this.productProfileService = productProfileService;
        this.productProfileDtoConverter = productProfileDtoConverter;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductProfileDto createP(@RequestBody ProductProfileDto profileDto) throws AlreadyExistException {
        ProductProfile profile = productProfileService.create(productProfileDtoConverter.convertToEntity(profileDto));
        return productProfileDtoConverter.convertToDto(profile);
    }

    @GetMapping(Uris.ID)
    @ResponseStatus(HttpStatus.OK)
    public ProductProfileDto read(@PathVariable Long id) throws NotFoundException {
        ProductProfile productProfile = productProfileService.read(id);
        return productProfileDtoConverter.convertToDto(productProfile);
    }


    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductProfileDto update(@RequestBody ProductProfileDto profileDto) throws NotFoundException {
        ProductProfile profile = productProfileService.update(productProfileDtoConverter.convertToEntity(profileDto));
        return productProfileDtoConverter.convertToDto(profile);
    }


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ProductProfileDto> findAll() throws PageDoesNotExistException {

        return this.productProfileService.findAll().stream()
                .map(profile -> productProfileDtoConverter.convertToDto(profile)).collect(Collectors.toList());
    }

    @GetMapping(Uris.NAMES)
    @ResponseStatus(HttpStatus.OK)
    public Map<Long, String> getAllNames() throws PageDoesNotExistException {
        return this.productProfileService.getProfileIdAndTypes();
    }

}
