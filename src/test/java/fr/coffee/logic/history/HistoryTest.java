package fr.coffee.logic.history;

import org.junit.Test;

import static fr.coffee.logic.command.BeverageType.*;
import static org.assertj.core.api.Assertions.assertThat;

public class HistoryTest {

    History history=new History();

    @Test
    public void should_count_nb_product_is_0_when_not_history(){
        assertThat(history.count(COFFEE)).isEqualTo(0);
        assertThat(history.count(CHOCOLATE)).isEqualTo(0);
        assertThat(history.count(TEA)).isEqualTo(0);
        assertThat(history.count(ORANGE_JUICE)).isEqualTo(0);
    }

    @Test
    public void should_have_total_amount_of_money_earned_is_0_when_not_history(){
        assertThat(history.getAmountEarnedInCents()).isEqualTo(0.0);
    }

    @Test
    public void should_add_and_count_orders_events(){
        assertThat(history.count(COFFEE)).isEqualTo(0);
        history.add(new CommandEvent(COFFEE.getMakerCode(), COFFEE.getPriceInCents()));
        assertThat(history.count(COFFEE)).isEqualTo(1);
    }

    @Test
    public void should_add_and_count_only_asked_orders_events(){

        assertThat(history.count(TEA)).isEqualTo(0);
        history.add(new CommandEvent(TEA.getMakerCode(), TEA.getPriceInCents()));
        history.add(new CommandEvent(TEA.getMakerCode(), TEA.getPriceInCents()));
        history.add(new CommandEvent(COFFEE.getMakerCode(), COFFEE.getPriceInCents()));
        history.add(new CommandEvent(TEA.getMakerCode(), TEA.getPriceInCents()));
        assertThat(history.count(TEA)).isEqualTo(3);

    }

    @Test
    public void should_sum_amount_earned_with_all_commands(){

        assertThat(history.getAmountEarnedInCents()).isEqualTo(0);
        history.add(new CommandEvent(TEA.getMakerCode(), TEA.getPriceInCents()));
        history.add(new CommandEvent(TEA.getMakerCode(), TEA.getPriceInCents()));
        history.add(new CommandEvent(COFFEE.getMakerCode(), COFFEE.getPriceInCents()));
        history.add(new CommandEvent(TEA.getMakerCode(), TEA.getPriceInCents()));
        assertThat(history.getAmountEarnedInCents()).isEqualTo(180);

    }


}