package ar.com.healthyapple.crm_web.controller;

import ar.com.healthyapple.crm_web.dto.Product.ComponentDto;
import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.exceptions.PageDoesNotExistException;
import ar.com.healthyapple.crm_web.model.Product.Component;
import ar.com.healthyapple.crm_web.service.Product.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(Uris.COMPONENTS)
public class ComponentController {

    private ComponentService componentService;

    private EntityDtoConverter entityDtoConverter;


    @Autowired
    public ComponentController(ComponentService componentService,  EntityDtoConverter entityDtoConverter) {
        this.componentService = componentService;
        this.entityDtoConverter = entityDtoConverter;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ComponentDto createComponent(@RequestBody ComponentDto componentDto) throws AlreadyExistException {
        Component component = componentService.create(entityDtoConverter.convertToEntity(componentDto, Component.class));
        return entityDtoConverter.convertToDto(component, ComponentDto.class);
    }

    @GetMapping(Uris.ID)
    @ResponseStatus(HttpStatus.OK)
    public ComponentDto readComponent(@PathVariable Long id) throws NotFoundException {
        return entityDtoConverter.convertToDto(componentService.read(id), ComponentDto.class);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ComponentDto updateComponent(@RequestBody ComponentDto componentDto) throws NotFoundException {
        Component component = componentService.update(entityDtoConverter.convertToEntity(componentDto, Component.class));
        return entityDtoConverter.convertToDto(component, ComponentDto.class);
    }

    @DeleteMapping(Uris.ID)
    @ResponseStatus(HttpStatus.OK)
    public void deleteComponentById(@PathVariable Long id) throws NotFoundException {
        componentService.deleteById(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteComponent(@RequestBody ComponentDto componentDto) throws NotFoundException {
        componentService.delete(entityDtoConverter.convertToEntity(componentDto, Component.class));
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ComponentDto> findAllComponents(Pageable pageable) throws PageDoesNotExistException {

        Page<Component> componentPage = this.componentService.findAll(pageable);

        List<ComponentDto> componentDtos = componentPage.getContent().stream()
                .map(component -> entityDtoConverter.convertToDto(component, ComponentDto.class))
                .collect(Collectors.toList());

        Page<ComponentDto> componentDtoPage = componentPage.map(component -> entityDtoConverter.convertToDto(component, ComponentDto.class));

        return componentDtoPage;
    }


}
