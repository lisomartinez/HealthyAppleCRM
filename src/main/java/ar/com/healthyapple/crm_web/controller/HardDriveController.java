package ar.com.healthyapple.crm_web.controller;

import org.springframework.web.bind.annotation.*;


//TODO:AddValidation to ID field
@RestController
@RequestMapping(value = Uris.HARD_DRIVES)
public class HardDriveController {

//
//    private EntityDtoConverter entityDtoConverter;
//
//    private HardDiskService hardDiskService;
//
//    @Autowired
//    public HardDriveController(EntityDtoConverter entityDtoConverter, HardDiskService hardDiskService) {
//        this.entityDtoConverter = entityDtoConverter;
//        this.hardDiskService = hardDiskService;
//    }
//
//    @GetMapping(value = Uris.ID)
//    @ResponseStatus(HttpStatus.OK)
//    public HardDriveDto get(@PathVariable String id) throws NotFoundException{
//        HardDrive hardDrive = hardDiskService.readById(Long.parseLong(id));
//        HardDriveDto hardDriveDto = entityDtoConverter.convertToDto(hardDrive, HardDriveDto.class);
//        return hardDriveDto;
//        }
//
//
//    @PostMapping()
//    @ResponseStatus(HttpStatus.CREATED)
//    public HardDriveDto create(@RequestBody HardDriveDto hardDrive) throws AlreadyExistException {
//        HardDrive hd = hardDiskService.create(entityDtoConverter.convertToEntity(hardDrive, HardDrive.class));
//        return entityDtoConverter.convertToDto(hd, HardDriveDto.class);
//    }
//
//    @GetMapping()
//    @ResponseStatus(HttpStatus.OK)
//    public List<HardDriveDto> findAll() {
//        List<HardDrive> hardDriveList = hardDiskService.findAll();
//        List<HardDriveDto> hardDriveDtoList = hardDriveList.stream()
//                .map(hd -> entityDtoConverter.convertToDto(hd, HardDriveDto.class))
//                .collect(Collectors.toList());
//
//        return hardDriveDtoList;
//    }
//
//    @DeleteMapping(value = Uris.ID)
//    @ResponseStatus(HttpStatus.OK)
//    public void deleteById(@PathVariable String id) throws NotFoundException {
//         hardDiskService.deleteById(Long.parseLong(id));
//    }
//
//    @DeleteMapping()
//    @ResponseStatus(HttpStatus.OK)
//    public void delete(@RequestBody HardDriveDto hardDriveDto) throws NotFoundException{
//        hardDiskService.delete(entityDtoConverter.convertToEntity(hardDriveDto, HardDrive.class));
//    }
//
//    @PutMapping()
//    @ResponseStatus(HttpStatus.OK)
//    public HardDriveDto update(@RequestBody HardDriveDto hardDrive) throws NotFoundException {
//        HardDrive hd = entityDtoConverter.convertToEntity(hardDrive, HardDrive.class);
//        return entityDtoConverter.convertToDto(hardDiskService.update(hd), HardDriveDto.class);
//    }
//
//    @GetMapping(value = Uris.SEARCH)
//    @ResponseStatus(HttpStatus.OK)
//    public HardDriveDto findByBrandAndModel(@RequestParam(required = true) String brand, @RequestParam(required = true) String model) throws NotFoundException{
//        return entityDtoConverter.convertToDto(hardDiskService.findHardDriveByBrandAndModel(brand,model), HardDriveDto.class);
//    }

}
