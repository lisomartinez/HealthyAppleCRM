package ar.com.healthyapple.crm_web.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Uris.COMPUTERS)
public class ComputerController {

//    private ComputerService computerService;
//
//    private EntityDtoConverter entityDtoConverter;
//
//    @Autowired
//    public ComputerController(ComputerService computerService, EntityDtoConverter entityDtoConverter) {
//        this.computerService = computerService;
//        this.entityDtoConverter = entityDtoConverter;
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public ComputerDto create(@RequestBody ComputerDto computerDto) {
//        Computer computer = computerService.create(entityDtoConverter.convertToEntity(computerDto, Computer.class));
//        return entityDtoConverter.convertToDto(computer, ComputerDto.class);
//    }
//
//    @GetMapping(Uris.ID)
//    @ResponseStatus(HttpStatus.OK)
//    public ComputerDto read(@PathVariable String id) throws NotFoundException {
//        Computer computer = computerService.read(Long.parseLong(id));
//        return entityDtoConverter.convertToDto(computer, ComputerDto.class);
//    }
//
//    @PutMapping
//    @ResponseStatus(HttpStatus.OK)
//    public ComputerDto update(@RequestBody ComputerDto computerDto) throws NotFoundException {
//        Computer computer = computerService.update(entityDtoConverter.convertToEntity(computerDto, Computer.class));
//        return entityDtoConverter.convertToDto(computer, ComputerDto.class);
//    }
//
//    @DeleteMapping(Uris.ID)
//    @ResponseStatus(HttpStatus.OK)
//    public void deleteById(@PathVariable String id) throws NotFoundException {
//        computerService.deleteById(Long.parseLong(id));
//    }
//
//    @DeleteMapping
//    @ResponseStatus(HttpStatus.OK)
//    public void delete(@RequestBody ComputerDto computerDto) throws NotFoundException {
//        computerService.delete(entityDtoConverter.convertToEntity(computerDto, Computer.class));
//    }
//
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<ComputerDto> findAll() {
//         return computerService.findAll().stream()
//                    .map(computer -> entityDtoConverter.convertToDto(computer, ComputerDto.class))
//                    .collect(Collectors.toList());
//    }

}
