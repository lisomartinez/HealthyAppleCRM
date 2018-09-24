package ar.com.healthyapple.crm_web.model.Sale.ServiceCommand;

import ar.com.healthyapple.crm_web.model.Computer.HackintoshComputer;

public abstract class HackintoshComputerServiceCommand extends ServiceCommand {
    protected HackintoshComputer computer;

    public HackintoshComputerServiceCommand(HackintoshComputer computer) {
        this.computer = computer;
    }

//    public abstract void execute();
}
