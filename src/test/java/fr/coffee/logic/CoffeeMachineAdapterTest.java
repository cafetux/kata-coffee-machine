package fr.coffee.logic;

import fr.coffee.maker.CoffeeMaker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static fr.coffee.logic.BeverageType.CHOCOLATE;
import static fr.coffee.logic.BeverageType.COFFEE;
import static fr.coffee.logic.BeverageType.TEA;
import static org.mockito.Mockito.only;

@RunWith(MockitoJUnitRunner.class)
public class CoffeeMachineAdapterTest {

    @InjectMocks
    private CoffeeMachineAdapter service;
    @Mock
    private CoffeeMaker maker;

    @Test
    public void should_command_coffee_without_stick_when_no_sugar(){
        Given_enougth_money_for_COFFEE();
        when_we_command(COFFEE, with_no_sugar());
        then_maker_receive_command("C::");
    }

    @Test
    public void should_command_tea_without_stick_when_no_sugar(){
        Given_enougth_money_for_TEA();
        when_we_command(TEA, with_no_sugar());
        then_maker_receive_command("T::");
    }

    @Test
    public void should_command_chocolate_without_stick_when_no_sugar(){
        Given_enougth_money_for_CHOCOLATE();
        when_we_command(CHOCOLATE, with_no_sugar());
        then_maker_receive_command("H::");
    }

    @Test
    public void should_can_add_one_sugar_to_coffee_and_that_add_stick(){
        Given_enougth_money_for_COFFEE();
        when_we_command(COFFEE, with_one_sugar());
        then_maker_receive_command("C:1:0");
    }

    @Test
    public void should_can_add_two_sugar_to_tea_and_that_add_stick(){
        Given_enougth_money_for_TEA();
        when_we_command(TEA, with_two_sugars());
        then_maker_receive_command("T:2:0");
   }

    @Test
    public void should_reject_command_of_tea_when_not_enougth_money(){
        Given_not_enougth_money_for_TEA();
        when_we_command(TEA, with_two_sugars());
        then_maker_receive_command("M:missing money");
    }

    private void Given_not_enougth_money_for_CHOCOLATE() {
        service.receiveCoin(30);
    }

    private void Given_not_enougth_money_for_TEA() {
        service.receiveCoin(30);
    }

    private void Given_not_enougth_money_for_COFFEE() {
        service.receiveCoin(30);
    }
// One tea is 0,4 euro, a coffee is 0,6 euro, a chocolate is 0,5 euro.
    private void Given_enougth_money_for_CHOCOLATE() {
        service.receiveCoin(50);
    }

    private void Given_enougth_money_for_TEA() {
        service.receiveCoin(40);
    }

    private void Given_enougth_money_for_COFFEE() {
        service.receiveCoin(60);
    }

    private int with_no_sugar() {
        return 0;
    }
    private int with_one_sugar() {
        return 1;
    }
    private int with_two_sugars() {
        return 2;
    }

    private void then_maker_receive_command(String expected) {
        Mockito.verify(maker, only()).make(expected);
    }

    private void when_we_command(BeverageType beverageType, int nbSugar) {
        service.command(beverageType, nbSugar);
    }

}