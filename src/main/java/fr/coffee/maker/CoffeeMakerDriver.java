package fr.coffee.maker;

import fr.coffee.integration.CoffeeMaker;
import fr.coffee.logic.CoffeeMakerAdapter;
import fr.coffee.logic.command.BeverageCommand;

/**
 * Abstraction permettant d'interragir avec le coffeeMaker
 */
public class CoffeeMakerDriver implements BeverageMaker,Display {

    private CoffeeMakerAdapter adapter=new CoffeeMakerAdapter();
    private CoffeeMaker maker;

    @Override
    public void command(BeverageCommand command) {
        maker.send(adapter.adapt(command));
    }

    @Override
    public void display(String message) {
        maker.send("M:"+message);
    }
}
