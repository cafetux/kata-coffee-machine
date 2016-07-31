package fr.coffee.logic;

import fr.coffee.logic.history.CommandEvent;
import fr.coffee.logic.history.History;
import fr.coffee.maker.CoffeeMaker;

/**
 * transform GUI commands on coffeeMachine protocol command
 */
public class CoffeeMachineLogicService {

    private CoffeeMakerAdapter adapter = new CoffeeMakerAdapter();
    private CoffeeMaker maker;
    private History history = new History();
    private int moneyInCents = 0;

    public void command(BeverageCommand command) {

        if (hasEnoughtMoneyFor(command)) {
            make(command);
            addToHistory(command);
        } else {
            sendMessage("missing money");
        }
    }

    private void addToHistory(BeverageCommand command) {
        history.add(new CommandEvent(command.getBeverageType().getMakerCode(),command.getBeverageType().getPriceInCents()));
    }

    private boolean hasEnoughtMoneyFor(BeverageCommand command) {
        return !command.getBeverageType().costMoreThan(moneyInCents);
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

    public void displayReport() {
        sendMessage(adapter.adapt(history));
    }
}
