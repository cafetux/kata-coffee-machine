package fr.coffee.history;

/**
 * Created by cafetux on 31/07/2016.
 */
public class CommandEvent {

    private String beverage;
    private int price;

    public CommandEvent(String beverage, int price) {
        this.beverage = beverage;
        this.price = price;
    }

    public String getBeverage() {
        return beverage;
    }

    public int getPrice() {
        return price;
    }
}
