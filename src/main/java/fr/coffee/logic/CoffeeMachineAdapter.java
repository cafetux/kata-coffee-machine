package fr.coffee.logic;

import fr.coffee.maker.CoffeeMaker;

/**
 * transform GUI commands on coffeeMachine protocol command
 */
public class CoffeeMachineAdapter {

    private CoffeeMaker maker;

    public void command(BeverageType beverageType, int nbSugar) {
        String makerInstruction = translateCommand(beverageType, nbSugar);
        maker.make(makerInstruction);
    }

    private String translateCommand(BeverageType beverageType, int nbSugar) {
        StringBuilder makerInstruction = new StringBuilder();
        makerInstruction.append(getBeverageCodeLetter(beverageType));
        makerInstruction.append(":");
        if(nbSugar>0) {
            makerInstruction.append(nbSugar);
            makerInstruction.append(":");
            makerInstruction.append("0");
        }else{
            makerInstruction.append(":");
        }
        return makerInstruction.toString();
    }

    private String getBeverageCodeLetter(BeverageType beverageType) {
        switch (beverageType) {
            case COFFEE:
                return "C";
            case TEA:
                return "T";
            case CHOCOLATE:
                return "H";
            default:
                throw new IllegalArgumentException("unknown beverage type "+beverageType);
        }
    }
}
