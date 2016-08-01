package fr.coffee;

import fr.coffee.integration.BeverageQuantityChecker;
import fr.coffee.integration.EmailNotifier;
import fr.coffee.command.BeverageCommand;
import fr.coffee.command.BeverageType;
import fr.coffee.history.CommandEvent;
import fr.coffee.history.History;
import fr.coffee.maker.CoffeeMakerDriver;


public class CoffeeMachineService {

    private CoffeeMakerDriver coffeeMakerDriver;
    private BeverageQuantityChecker beverageQuantityChecker;
    private EmailNotifier emailNotifier;

    private CoffeeMakerAdapter adapter = new CoffeeMakerAdapter();
    private History history = new History();
    private int moneyInCents = 0;

    public void command(BeverageCommand command) {

        if (missingBeverage(command.getBeverageType())) {
            emailNotifier.notifyMissingDrink(command.getBeverageType().getMakerCode());
            return;
        }
        if (missingMoneyFor(command.getBeverageType())) {
            coffeeMakerDriver.display("missing money");
            return;
        }
        coffeeMakerDriver.command(command);
        addToHistory(command);

    }

    public void receiveCoin(int centimes) {
        this.moneyInCents += centimes;
    }

    public void displayReport() {
        coffeeMakerDriver.display(adapter.adapt(history));
    }

    private boolean missingBeverage(BeverageType beverageType) {
        return beverageQuantityChecker.isEmpty(beverageType.getMakerCode());
    }

    private void addToHistory(BeverageCommand command) {
        history.add(new CommandEvent(command.getBeverageType().getMakerCode(), command.getBeverageType().getPriceInCents()));
    }

    private boolean missingMoneyFor(BeverageType beverageType) {
        return beverageType.costMoreThan(moneyInCents);
    }
}
