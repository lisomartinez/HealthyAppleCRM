package ar.com.healthyapple.crm_web.model.Sale.ServiceCommand;

import ar.com.healthyapple.crm_web.model.Computer.HackintoshComputer;
import ar.com.healthyapple.crm_web.model.Computer.HardDrive;

public class ChangeHardDriveHackintoshComputerServiceCommand extends HackintoshComputerServiceCommand {

    private HardDrive hardDrive;

    public ChangeHardDriveHackintoshComputerServiceCommand(HackintoshComputer computer) {
        super(computer);
    }

    @Override
    public void execute() {
    }
}
