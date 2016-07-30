package fr.coffee.logic;

import fr.coffee.maker.CoffeeMaker;

/**
 * transform GUI commands on coffeeMachine protocol command
 */
public class CoffeeMachineLogicService {

    private CoffeeMakerAdapter adapter = new CoffeeMakerAdapter();
    private CoffeeMaker maker;
    private int moneyInCents = 0;

    public void command(BeverageCommand command) {

        if(hasEnoughtMoneyFor(command)){
            sendMessage("missing money");
        } else {
            make(command);
        }
    }

    private boolean hasEnoughtMoneyFor(BeverageCommand command) {
        return command.getBeverageType().costMoreThan(moneyInCents);
    }

    private void make(BeverageCommand beverageToCommand) {
        maker.make(adapter.adapt(beverageToCommand));
    }

    private void sendMessage(final String message) {
        maker.make("M:" + message);
    }


    public void receiveCoin(int centimes) {
        this.moneyInCents+=centimes;
    }
}
