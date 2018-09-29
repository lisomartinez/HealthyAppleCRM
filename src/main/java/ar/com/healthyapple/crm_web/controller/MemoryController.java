package ar.com.healthyapple.crm_web.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value= Uris.MEMORY)
public class MemoryController {

//
//    private MemoryService memoryService;
//
//    private EntityDtoConverter entityDtoConverter;
//
//    @Autowired
//    public MemoryController(MemoryService memoryService, EntityDtoConverter entityDtoConverter) {
//        this.memoryService = memoryService;
//        this.entityDtoConverter = entityDtoConverter;
//    }
//
//    @PostMapping()
//    @ResponseStatus(HttpStatus.CREATED)
//    public MemoryDto create(@RequestBody MemoryDto memoryDto) throws AlreadyExistException {
//        Memory memory = memoryService.create(entityDtoConverter.convertToEntity(memoryDto, Memory.class));
//        return entityDtoConverter.convertToDto(memory, MemoryDto.class);
//    }
//
//    @GetMapping(Uris.ID)
//    @ResponseStatus(HttpStatus.OK)
//    public MemoryDto read(@PathVariable String id) throws NotFoundException {
//        return entityDtoConverter.convertToDto(memoryService.read(Long.parseLong(id)), MemoryDto.class);
//    }
//
//    @PutMapping()
//    @ResponseStatus(HttpStatus.OK)
//    public MemoryDto update(@RequestBody MemoryDto memoryDto) throws NotFoundException {
//        Memory memory = entityDtoConverter.convertToEntity(memoryDto, Memory.class);
//        return entityDtoConverter.convertToDto(memoryService.update(memory), MemoryDto.class);
//    }
//
//    @DeleteMapping(Uris.ID)
//    @ResponseStatus(HttpStatus.OK)
//    public void deleteById(@PathVariable String id) throws NotFoundException {
//        memoryService.deleteById(Long.parseLong(id));
//    }
//
//    @DeleteMapping()
//    @ResponseStatus(HttpStatus.OK)
//    public void delete(@RequestBody MemoryDto memoryDto) throws NotFoundException {
//        memoryService.delete(entityDtoConverter.convertToEntity(memoryDto, Memory.class));
//    }
//
//
//    @GetMapping(Uris.SEARCH)
//    @ResponseStatus(HttpStatus.OK)
//    public MemoryDto findByBrandAndModel(@RequestParam (required = true) String brand, @RequestParam (required = true) String model) throws NotFoundException {
//        return  entityDtoConverter.convertToDto(memoryService.findMemoryByBrandAndModel(brand, model), MemoryDto.class);
//    }
//
//    @GetMapping()
//    @ResponseStatus(HttpStatus.OK)
//    public List<MemoryDto> findAll() {
//        return memoryService.findAll().stream()
//                .map(memory -> entityDtoConverter.convertToDto(memory, MemoryDto.class))
//                .collect(Collectors.toList());
//    }

}

