package ar.com.healthyapple.crm_web.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Uris.POWER_SUPPLIES)
public class PowerSupplyController {
//
//    private PowerSupplyService powerSupplyService;
//
//    private EntityDtoConverter entityDtoConverter;
//
//    @Autowired
//    public PowerSupplyController(PowerSupplyService powerSupplyService, EntityDtoConverter entityDtoConverter) {
//        this.powerSupplyService = powerSupplyService;
//        this.entityDtoConverter = entityDtoConverter;
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public PowerSupplyDto create(@RequestBody PowerSupplyDto powerSupplyDto) throws AlreadyExistException {
//        PowerSupply powerSupply = powerSupplyService.create(entityDtoConverter.convertToEntity(powerSupplyDto, PowerSupply.class));
//        return entityDtoConverter.convertToDto(powerSupply, PowerSupplyDto.class);
//    }
//
//    @GetMapping(Uris.ID)
//    @ResponseStatus(HttpStatus.OK)
//    public PowerSupplyDto read (@PathVariable String id) throws NotFoundException {
//        PowerSupply powerSupply = powerSupplyService.read(Long.parseLong(id));
//        return entityDtoConverter.convertToDto(powerSupply, PowerSupplyDto.class);
//    }
//
//    @PutMapping()
//    @ResponseStatus(HttpStatus.OK)
//    public PowerSupplyDto update (@RequestBody PowerSupplyDto powerSupplyDto) throws NotFoundException {
//        PowerSupply powerSupply = powerSupplyService.update(entityDtoConverter.convertToEntity(powerSupplyDto, PowerSupply.class));
//        return entityDtoConverter.convertToDto(powerSupply, PowerSupplyDto.class);
//    }
//
//    @DeleteMapping(Uris.ID)
//    @ResponseStatus(HttpStatus.OK)
//    public void deleteById(@PathVariable String id) throws NotFoundException {
//        powerSupplyService.deleteById(Long.parseLong(id));
//    }
//
//    @DeleteMapping()
//    @ResponseStatus(HttpStatus.OK)
//    public void delete(@RequestBody PowerSupplyDto powerSupplyDto) throws NotFoundException {
//        powerSupplyService.delete(entityDtoConverter.convertToEntity(powerSupplyDto, PowerSupply.class));
//    }
//
//    @GetMapping(Uris.SEARCH)
//    @ResponseStatus(HttpStatus.OK)
//    public PowerSupplyDto findByBrandAndModel(@RequestParam (required = true) String brand, @RequestParam (required = true) String model) throws NotFoundException {
//        PowerSupply powerSupply = powerSupplyService.findByBrandAndModel(brand, model);
//        return entityDtoConverter.convertToDto(powerSupply, PowerSupplyDto.class);
//    }
//
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<PowerSupplyDto> findAll() {
//        return powerSupplyService.findAll().stream()
//                .map(powerSupply -> entityDtoConverter.convertToDto(powerSupply, PowerSupplyDto.class))
//                .collect(Collectors.toList());
//    }

}
