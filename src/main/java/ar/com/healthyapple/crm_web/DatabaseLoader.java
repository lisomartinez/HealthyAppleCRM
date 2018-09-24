package ar.com.healthyapple.crm_web;

import ar.com.healthyapple.crm_web.model.Computer.HardDrive;

import ar.com.healthyapple.crm_web.model.Computer.Memory;
import ar.com.healthyapple.crm_web.model.Computer.MotherBoard;
import ar.com.healthyapple.crm_web.model.Computer.Processor;
import ar.com.healthyapple.crm_web.repository.Computer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements ApplicationRunner {

    private HardDriveRepository hardDriveRepository;

    private MemoryRepository memoryRepository;

    private MotherBoardRepository motherBoardRepository;

    private PcCaseRepository pcCaseRepository;

    private PowerSupplyRepository powerSupplyRepository;

    private ProcessorRepository processorRepository;

    private VideoCardRepository videoCardRepository;

    @Autowired
    public DatabaseLoader(HardDriveRepository hardDriveRepository, MemoryRepository memoryRepository, MotherBoardRepository motherBoardRepository, PcCaseRepository pcCaseRepository, PowerSupplyRepository powerSupplyRepository, ProcessorRepository processorRepository, VideoCardRepository videoCardRepository) {
        this.hardDriveRepository = hardDriveRepository;
        this.memoryRepository = memoryRepository;
        this.motherBoardRepository = motherBoardRepository;
        this.pcCaseRepository = pcCaseRepository;
        this.powerSupplyRepository = powerSupplyRepository;
        this.processorRepository = processorRepository;
        this.videoCardRepository = videoCardRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createHardDrives();
        createMemory();
        createProcessor();
        createMotherBoard();
        createPcCase();
        createPowerSupply();
        createVideoCard();
        createMacComputer();
        createHackintoshComputer();
    }


    private void createHardDrives() {
        hardDriveRepository.save(new HardDrive("WD",  "Caviar Black","123", "HD", "500GB"));
        hardDriveRepository.save(new HardDrive("WD",  "Caviar Blue", "234","HD", "2TB"));
        hardDriveRepository.save(new HardDrive("WD",  "Caviar Red",  "566","HD", "4TB"));
        hardDriveRepository.save(new HardDrive("WD",  "Caviar Green","987", "HD", "1TB"));
        hardDriveRepository.save(new HardDrive("Samsung",  "860 EVO", "7567","SSD", "250GB"));
        hardDriveRepository.save(new HardDrive("Samsung",  "860 EVO", "2345345", "SSD", "500GB"));
    }

    private void createMemory() {
        memoryRepository.save(new Memory("Kingston", "ValueRam", "12313AAA", "DDR3", 1333, 16));
        memoryRepository.save(new Memory("Kingston", "ValueRam", "AAA123", "DDR4", 2133, 16));
        memoryRepository.save(new Memory("GSkill", "HyperX", "BNB234", "DDR4", 3200, 32));
        memoryRepository.save(new Memory("Kingston", "HyperX", "ZZZD23", "DDR4", 1600, 8));
        memoryRepository.save(new Memory("Corsair", "Savage", "123111", "DDR3", 2400, 32));
        memoryRepository.save(new Memory("Crucial", "Savage", "1444AAA", "DDR2", 1333, 4));
    }

    private void createProcessor() {
        processorRepository.save(new Processor("Intel", "i7 4790k", "AAA3123", 4, "1150", 4000));
        processorRepository.save(new Processor("Intel", "i7 4770k", "SSS3123", 4, "1151", 3800));
        processorRepository.save(new Processor("Intel", "i5 4690k", "ASD413", 4, "1150", 3200));
        processorRepository.save(new Processor("Intel", "i3 3100", "a1AD23", 2, "1150", 2800));
    }

    private void createMotherBoard() {
        motherBoardRepository.save(new MotherBoard("Gigabyte", "Z97", "GA-Z97X-UD7 TH", "1150"));
    }

    private void createPcCase() {

    }

    private void createPowerSupply() {

    }

    private void createVideoCard() {

    }

    private void createMacComputer() {

    }

    private void createHackintoshComputer() {

    }

}
