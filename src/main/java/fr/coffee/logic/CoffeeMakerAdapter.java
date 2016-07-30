package fr.coffee.logic;

/**
 * Created by cafetux on 30/07/2016.
 */
public class CoffeeMakerAdapter {

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
            }else{
                makerInstruction.append(":");
            }
            return makerInstruction.toString();
    }

}
