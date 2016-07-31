package fr.coffee.logic;

import fr.coffee.integration.BeverageQuantityChecker;
import fr.coffee.integration.CoffeeMaker;
import fr.coffee.integration.EmailNotifier;
import fr.coffee.logic.command.BeverageCommand;
import fr.coffee.logic.command.BeverageType;
import fr.coffee.logic.history.CommandEvent;
import fr.coffee.logic.history.History;

/**
 * transform GUI commands on coffeeMachine protocol command
 */
public class CoffeeMachineService {

    private CoffeeMaker maker;
    private BeverageQuantityChecker beverageQuantityChecker;
    private EmailNotifier emailNotifier;

    private CoffeeMakerAdapter adapter = new CoffeeMakerAdapter();
    private History history = new History();
    private int moneyInCents = 0;

    public void command(BeverageCommand command) {

        if (hasEnougthBeverage(command.getBeverageType()) && hasEnoughtMoneyFor(command)) {
            make(command);
            addToHistory(command);
        }
    }

    private boolean hasEnougthBeverage(BeverageType beverageType) {
        if(beverageQuantityChecker.isEmpty(beverageType.getMakerCode())){
            emailNotifier.notifyMissingDrink(beverageType.getMakerCode());
            return false;
        }
        return true;
    }

    private void addToHistory(BeverageCommand command) {
        history.add(new CommandEvent(command.getBeverageType().getMakerCode(),command.getBeverageType().getPriceInCents()));
    }

    private boolean hasEnoughtMoneyFor(BeverageCommand command) {
        if(command.getBeverageType().costMoreThan(moneyInCents)){
            sendMessage("missing money");
            return false;
        }
        return true;
    }

    private void make(BeverageCommand beverageToCommand) {
        maker.send(adapter.adapt(beverageToCommand));
    }

    private void sendMessage(final String message) {
        maker.send("M:" + message);
    }


    public void receiveCoin(int centimes) {
        this.moneyInCents+=centimes;
    }

    public void displayReport() {
        sendMessage(adapter.adapt(history));
    }
}
