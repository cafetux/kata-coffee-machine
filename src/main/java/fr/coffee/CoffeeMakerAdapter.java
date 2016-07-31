package fr.coffee;

import fr.coffee.command.BeverageCommand;
import fr.coffee.history.History;

import static fr.coffee.command.BeverageType.*;

/**
 * adapt business objects to maker command
 */
public class CoffeeMakerAdapter {

    /**
     * Adapt command object to maker order
     * @param command
     * @return the srting to command
     */
    public String adapt(BeverageCommand command){
        StringBuilder makerInstruction = new StringBuilder();
        makerInstruction.append(command.getBeverageType().getMakerCode());
        if(command.isNeedExtraHot()){
            makerInstruction.append("h");
        }
        makerInstruction.append(":");
        if(command.getNbSugars()>0) {
            makerInstruction.append(command.getNbSugars());
            makerInstruction.append(":");
            makerInstruction.append("0");
        } else{
            makerInstruction.append(":");
        }
        return makerInstruction.toString();
    }

    public String adapt(History history){
        return "C:"+history.count(COFFEE)+" T:"+history.count(TEA)+" H:"+history.count(CHOCOLATE)+" O:"+history.count(ORANGE_JUICE)+" €:"+history.getAmountEarnedInCents()/100;
    }

}
