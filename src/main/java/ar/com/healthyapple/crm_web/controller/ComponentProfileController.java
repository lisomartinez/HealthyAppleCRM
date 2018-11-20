package ar.com.healthyapple.crm_web.controller;

import ar.com.healthyapple.crm_web.controller.DtoConverter.ComponentProfileDtoConverter;
import ar.com.healthyapple.crm_web.dto.Product.ComponentProfileDto;
import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.exceptions.PageDoesNotExistException;
import ar.com.healthyapple.crm_web.model.Product.ComponentProfile;
import ar.com.healthyapple.crm_web.service.Product.ComponentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(Uris.COMPONENTS + Uris.PROFILES)
public class ComponentProfileController {

    private ComponentProfileService componentProfileService;

    private ComponentProfileDtoConverter componentProfileDtoConverter;

    @Autowired
    public ComponentProfileController(ComponentProfileService service, ComponentProfileDtoConverter componentProfileDtoConverter) {
        this.componentProfileService = service;
        this.componentProfileDtoConverter = componentProfileDtoConverter;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ComponentProfileDto create(@RequestBody ComponentProfileDto profileDto) throws AlreadyExistException {
        ComponentProfile profile = componentProfileService.create(componentProfileDtoConverter.convertToEntity(profileDto));
        return componentProfileDtoConverter.convertToDto(profile);
    }

    @GetMapping(Uris.ID)
    @ResponseStatus(HttpStatus.OK)
    public ComponentProfileDto read(@PathVariable Long id) throws NotFoundException {
        ComponentProfile ComponentProfile = componentProfileService.read(id);
        return componentProfileDtoConverter.convertToDto(ComponentProfile);
    }


    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ComponentProfileDto update(@RequestBody ComponentProfileDto profileDto) throws NotFoundException {
        ComponentProfile profile = componentProfileService.update(componentProfileDtoConverter.convertToEntity(profileDto));
        return componentProfileDtoConverter.convertToDto(profile);
    }


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ComponentProfileDto> findAll() throws PageDoesNotExistException {

        return this.componentProfileService.findAll().stream()
                .map(profile -> componentProfileDtoConverter.convertToDto(profile)).collect(Collectors.toList());
    }

    @GetMapping(Uris.NAMES)
    @ResponseStatus(HttpStatus.OK)
    public Map<Long, String> getAllNames() throws PageDoesNotExistException {
        return this.componentProfileService.getProfileIdAndNames();
    }

}
