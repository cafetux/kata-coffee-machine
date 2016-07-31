package fr.coffee.maker;

import fr.coffee.command.BeverageCommand;

/**
 * Command some beverages to coffeeMaker
 */
public interface BeverageMaker {

    void command(BeverageCommand command);

}
