package ar.com.healthyapple.crm_web.controller;

import ar.com.healthyapple.crm_web.dto.Computer.ProcessorDto;
import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Computer.Processor;
import ar.com.healthyapple.crm_web.service.Computer.ProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(Uris.PROCESSORS)
public class ProcessorController {

    private ProcessorService processorService;

    private EntityDtoConverter entityDtoConverter;

    @Autowired
    public ProcessorController(ProcessorService processorService, EntityDtoConverter entityDtoConverter) {
        this.processorService = processorService;
        this.entityDtoConverter = entityDtoConverter;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProcessorDto create(@RequestBody ProcessorDto processorDto) throws AlreadyExistException {
        Processor processor = processorService.create(entityDtoConverter.convertToEntity(processorDto, Processor.class));
        return entityDtoConverter.convertToDto(processor, ProcessorDto.class);
    }

    @GetMapping(Uris.ID)
    @ResponseStatus(HttpStatus.OK)
    public ProcessorDto read(@PathVariable String id ) throws NotFoundException {
        Processor processor = processorService.read(Long.parseLong(id));
        return entityDtoConverter.convertToDto(processor, ProcessorDto.class);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ProcessorDto update(@RequestBody ProcessorDto processorDto) throws NotFoundException {
        Processor processor = processorService.update(entityDtoConverter.convertToEntity(processorDto, Processor.class));
        return entityDtoConverter.convertToDto(processor, ProcessorDto.class);
    }

    @DeleteMapping(Uris.ID)
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable String id) throws NotFoundException {
        processorService.deleteById(Long.parseLong(id));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestBody ProcessorDto processorDto) throws NotFoundException {
        processorService.delete(entityDtoConverter.convertToEntity(processorDto, Processor.class));
    }
    @GetMapping(Uris.SEARCH)
    @ResponseStatus(HttpStatus.OK)
    public ProcessorDto findByBrandAndModel(@RequestParam(required = true) String brand, @RequestParam(required = true) String model) throws NotFoundException {
        Processor processor = processorService.findByBrandAndModel(brand, model);
        return entityDtoConverter.convertToDto(processor, ProcessorDto.class);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProcessorDto> findAll() {
        return processorService.findAll().stream()
                .map(processor -> entityDtoConverter.convertToDto(processor, ProcessorDto.class))
                .collect(Collectors.toList());
    }


}
