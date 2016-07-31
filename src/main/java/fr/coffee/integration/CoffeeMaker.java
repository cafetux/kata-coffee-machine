package fr.coffee.integration;

/**
 * interface of the CoffeeMaker
 */
public interface CoffeeMaker {

    /**
     * send comma,d to the coffee maker
     * @param command the command to send
     */
    void send(String command);
}
