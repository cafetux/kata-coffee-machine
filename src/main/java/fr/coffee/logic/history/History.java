package fr.coffee.logic.history;

import fr.coffee.logic.command.BeverageType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cafetux on 31/07/2016.
 */
public class History {

    private List<CommandEvent> events = new ArrayList<>();

    public long count(BeverageType type) {
        return events.stream().filter(x->type.getMakerCode().equals(x.getBeverage())).count();
    }

    public double getAmountEarnedInCents() {
        return events.stream().mapToDouble(CommandEvent::getPrice).sum();
    }

    public void add(CommandEvent commandEvent) {
        events.add(commandEvent);
    }
}
