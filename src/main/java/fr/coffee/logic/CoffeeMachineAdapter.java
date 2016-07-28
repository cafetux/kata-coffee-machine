package fr.coffee.logic;

import fr.coffee.maker.CoffeeMaker;

import static fr.coffee.logic.BeverageType.CHOCOLATE;
import static fr.coffee.logic.BeverageType.COFFEE;
import static fr.coffee.logic.BeverageType.TEA;

/**
 * transform GUI commands on coffeeMachine protocol command
 */
public class CoffeeMachineAdapter {

    private CoffeeMaker maker;
    private int moneyInCents = 0;

    public void command(BeverageType beverageType, int nbSugar) {

        Beverage beverageToCommand= adapt(beverageType,nbSugar);

        if(beverageToCommand.costMoreThan(moneyInCents)){
            sendMessage("missing money");
        }else {
            make(beverageToCommand);
        }
    }

    private void make(Beverage beverageToCommand) {
        maker.make(beverageToCommand.toString());
    }

    private void sendMessage(final String message) {
        maker.make("M:" + message);
    }

    private Beverage adapt(BeverageType beverageType, int nbSugar) {
        switch (beverageType) {
            case COFFEE:
                return new Beverage(COFFEE,60,"C",nbSugar);
            case TEA:
                return new Beverage(TEA,40,"T",nbSugar);
            case CHOCOLATE:
                return new Beverage(CHOCOLATE,50,"H",nbSugar);
            default:
                throw new IllegalArgumentException("unknown beverage type "+beverageType);
        }
    }

    public void receiveCoin(int centimes) {
        this.moneyInCents+=centimes;
    }
}
