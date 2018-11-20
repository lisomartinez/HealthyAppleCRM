package ar.com.healthyapple.crm_web.controller;

import ar.com.healthyapple.crm_web.controller.DtoConverter.ComponentDtoConverter;
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

    private ComponentDtoConverter componentDtoConverter;


    @Autowired
    public ComponentController(ComponentService componentService,  ComponentDtoConverter componentDtoConverter) {
        this.componentService = componentService;
        this.componentDtoConverter = componentDtoConverter;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ComponentDto createComponent(@RequestBody ComponentDto componentDto) throws AlreadyExistException {
        Component component = componentService.create(componentDtoConverter.convertToEntity(componentDto));
        return componentDtoConverter.convertToDto(component);
    }

    @GetMapping(Uris.ID)
    @ResponseStatus(HttpStatus.OK)
    public ComponentDto readComponent(@PathVariable Long id) throws NotFoundException {
        return componentDtoConverter.convertToDto(componentService.read(id));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ComponentDto updateComponent(@RequestBody ComponentDto componentDto) throws NotFoundException {
        Component component = componentService.update(componentDtoConverter.convertToEntity(componentDto));
        return componentDtoConverter.convertToDto(component);
    }

    @DeleteMapping(Uris.ID)
    @ResponseStatus(HttpStatus.OK)
    public void deleteComponentById(@PathVariable Long id) throws NotFoundException {
        componentService.deleteById(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteComponent(@RequestBody ComponentDto componentDto) throws NotFoundException {
        componentService.delete(componentDtoConverter.convertToEntity(componentDto));
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ComponentDto> findAllComponents(Pageable pageable) throws PageDoesNotExistException {

        Page<Component> componentPage = this.componentService.findAll(pageable);

        List<ComponentDto> componentDtos = componentPage.getContent().stream()
                .map(component -> componentDtoConverter.convertToDto(component))
                .collect(Collectors.toList());

        Page<ComponentDto> componentDtoPage = componentPage.map(component -> componentDtoConverter.convertToDto(component));

        return componentDtoPage;
    }


}
