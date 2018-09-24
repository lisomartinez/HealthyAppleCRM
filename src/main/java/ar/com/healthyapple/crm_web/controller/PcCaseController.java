package ar.com.healthyapple.crm_web.controller;

import ar.com.healthyapple.crm_web.dto.Computer.PcCaseDto;
import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Computer.PcCase;
import ar.com.healthyapple.crm_web.service.Computer.PcCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(Uris.PC_CASE)
public class PcCaseController {

    private PcCaseService pcCaseService;

    private EntityDtoConverter entityDtoConverter;

    @Autowired
    public PcCaseController(PcCaseService pcCaseService, EntityDtoConverter entityDtoConverter) {
        this.pcCaseService = pcCaseService;
        this.entityDtoConverter = entityDtoConverter;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PcCaseDto create (@RequestBody PcCaseDto pcCaseDto) throws AlreadyExistException {

        PcCase pcCase = pcCaseService.create(entityDtoConverter.convertToEntity(pcCaseDto, PcCase.class));
        return entityDtoConverter.convertToDto(pcCase, PcCaseDto.class);
    }

    @GetMapping(Uris.ID)
    @ResponseStatus(HttpStatus.OK)
    public PcCaseDto read (@PathVariable String id) throws NotFoundException {
        PcCase pcCase = pcCaseService.read(Long.parseLong(id));
        return entityDtoConverter.convertToDto(pcCase, PcCaseDto.class);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public PcCaseDto update (@RequestBody PcCaseDto pcCaseDto) throws NotFoundException {
        PcCase pcCase = pcCaseService.update(entityDtoConverter.convertToEntity(pcCaseDto, PcCase.class));
        return entityDtoConverter.convertToDto(pcCase, PcCaseDto.class);
    }

    @DeleteMapping(Uris.ID)
    @ResponseStatus(HttpStatus.OK)
    public void deleteByID (@PathVariable String id) throws NotFoundException {
        pcCaseService.deleteById(Long.parseLong(id));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void delete (@RequestBody PcCaseDto pcCaseDto) throws NotFoundException{
        pcCaseService.delete(entityDtoConverter.convertToEntity(pcCaseDto, PcCase.class));
    }

    @GetMapping(Uris.SEARCH)
    @ResponseStatus(HttpStatus.OK)
    public PcCaseDto findByBrandAndModel (@RequestParam(required = true) String brand, @RequestParam(required = true) String model) throws NotFoundException {
        PcCase pcCase = pcCaseService.findByBrandAndModel(brand, model);
        return entityDtoConverter.convertToDto(pcCase, PcCaseDto.class);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PcCaseDto> findAll() {
        return pcCaseService.findAll().stream()
                .map(pcCase -> entityDtoConverter.convertToDto(pcCase, PcCaseDto.class))
                .collect(Collectors.toList());
    }
}
