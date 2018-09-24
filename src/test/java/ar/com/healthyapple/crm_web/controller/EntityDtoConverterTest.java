package ar.com.healthyapple.crm_web.controller;

import ar.com.healthyapple.crm_web.dto.Computer.MacComputerDto;
import ar.com.healthyapple.crm_web.model.Computer.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class EntityDtoConverterTest {
/*
    private MacComputer mac;
    private MacComputerDto macDto;
    private EntityDtoConverter entityDtoConverter;

    private final String MODEL_IDENTIFIER = "AAA1234";
    private final String SERIAL_NUMBER = "123";
    private final String PREINSTALLED_MACOS = "Mavericks";
    private final Double DISPLAY_SIZE = 15.0;

    @Before
    public void setUp() throws Exception {
        mac = new MacComputer(
                new MotherBoard(),
                new Processor(),
                new Memory(),
                new HardDrive(),
                MODEL_IDENTIFIER,
                SERIAL_NUMBER,
                LocalDate.of(2010,8,4),
                PREINSTALLED_MACOS,
                DISPLAY_SIZE
        );
        entityDtoConverter = new EntityDtoConverter(new ModelMapper());
    }

    @Test
    @Ignore
    public void converComputerDtoToEntity() throws Exception{
       MacComputer mac1 = new MacComputer(
                new MotherBoard("Gigabyte", "B85M","AAA","775"),
                new Processor("Intel", "i3", "AAA",4, "775", 1000),
                new Memory("Kingston", "Detonator","AAA", "DDR4",1600,8),
                new HardDrive("WD", "caviar black", "AAA","ssd", "5000"),
                MODEL_IDENTIFIER,
                SERIAL_NUMBER,
                LocalDate.of(2010,8,4),
                PREINSTALLED_MACOS,
                DISPLAY_SIZE
        );
        macDto = entityDtoConverter.convertToDto(mac1, MacComputerDto.class);

    }





    @Test
    @Ignore
    public void convertToDto() {
    }*/
}