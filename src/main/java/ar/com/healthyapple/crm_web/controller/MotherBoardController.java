package ar.com.healthyapple.crm_web.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Uris.MOTHERBOARDS)
public class MotherBoardController {
//
//    private MotherBoardService motherBoardService;
//
//    private EntityDtoConverter entityDtoConverter;
//
//    @Autowired
//    public MotherBoardController(MotherBoardService motherBoardService, EntityDtoConverter entityDtoConverter) {
//        this.motherBoardService = motherBoardService;
//        this.entityDtoConverter = entityDtoConverter;
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public MotherBoardDto create(@RequestBody MotherBoardDto motherBoardDto) throws AlreadyExistException {
//        MotherBoard motherBoard = motherBoardService.create(entityDtoConverter.convertToEntity(motherBoardDto, MotherBoard.class));
//        return entityDtoConverter.convertToDto(motherBoard, MotherBoardDto.class);
//    }
//
//    @GetMapping(Uris.ID)
//    @ResponseStatus(HttpStatus.OK)
//    public MotherBoardDto read(@PathVariable String id) throws NotFoundException {
//        MotherBoard motherBoard = motherBoardService.read(Long.parseLong(id));
//        return entityDtoConverter.convertToDto(motherBoard, MotherBoardDto.class);
//    }
//
//    @PutMapping()
//    @ResponseStatus(HttpStatus.OK)
//    public MotherBoardDto update(@RequestBody MotherBoardDto motherBoardDto) throws NotFoundException {
//        MotherBoard motherBoard = motherBoardService.update(entityDtoConverter.convertToEntity(motherBoardDto, MotherBoard.class));
//        return entityDtoConverter.convertToDto(motherBoard, MotherBoardDto.class);
//    }
//
//    @DeleteMapping(Uris.ID)
//    @ResponseStatus(HttpStatus.OK)
//    public void deleteByID(@PathVariable String id) throws NotFoundException {
//        motherBoardService.deleteById(Long.parseLong(id));
//    }
//
//    @DeleteMapping()
//    @ResponseStatus(HttpStatus.OK)
//    public void delete(@RequestBody MotherBoardDto motherBoardDto) throws NotFoundException {
//        motherBoardService.delete(entityDtoConverter.convertToEntity(motherBoardDto, MotherBoard.class));
//    }
//
//    @GetMapping(Uris.SEARCH)
//    @ResponseStatus(HttpStatus.OK)
//    public MotherBoardDto findByBrandAndModel(@RequestParam(required = true) String brand, @RequestParam(required = true) String model) throws NotFoundException {
//        MotherBoard motherBoard = motherBoardService.findByBrandAndModel(brand, model);
//        return entityDtoConverter.convertToDto(motherBoard, MotherBoardDto.class);
//    }
//
//    @GetMapping()
//    @ResponseStatus(HttpStatus.OK)
//    public List<MotherBoardDto> findAll() {
//        return motherBoardService.findAll().stream()
//                .map(motherBoard -> entityDtoConverter.convertToDto(motherBoard, MotherBoardDto.class))
//                .collect(Collectors.toList());
//    }
}
