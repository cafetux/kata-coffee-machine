package fr.coffee.maker;

import fr.coffee.integration.CoffeeMaker;
import fr.coffee.logic.command.BeverageCommand;
import fr.coffee.logic.command.BeverageType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static fr.coffee.logic.command.BeverageType.*;
import static org.mockito.Mockito.only;

@RunWith(MockitoJUnitRunner.class)
public class CoffeeMakerDriverTest {

    @InjectMocks
    private CoffeeMakerDriver driver=new CoffeeMakerDriver();
    @Mock
    private CoffeeMaker maker;

    @Test
    public void should_prefix_user_message_by_m(){
        driver.display("coucou");
        Mockito.verify(maker).send("M:coucou");
    }

    @Test
    public void should_command_coffee_without_stick_when_no_sugar(){
        when_we_command(COFFEE, with_no_sugar());
        then_maker_receive_command("C::");
    }

    @Test
    public void should_command_tea_without_stick_when_no_sugar(){
        when_we_command(TEA, with_no_sugar());
        then_maker_receive_command("T::");
    }

    @Test
    public void should_command_chocolate_without_stick_when_no_sugar(){
        when_we_command(CHOCOLATE, with_no_sugar());
        then_maker_receive_command("H::");
    }

    @Test
    public void should_can_add_one_sugar_to_coffee_and_that_add_stick(){
        when_we_command(COFFEE, with_one_sugar());
        then_maker_receive_command("C:1:0");
    }

    @Test
    public void should_can_add_two_sugar_to_tea_and_that_add_stick(){
        when_we_command(TEA, with_two_sugars());
        then_maker_receive_command("T:2:0");
    }

    @Test
    public void should_can_order_orange_juice(){
        when_we_command(ORANGE_JUICE, with_two_sugars());
        then_maker_receive_command("O:2:0");
    }

    @Test
    public void should_can_order_extra_hot_coffee(){
       when_we_command_extra_hot(COFFEE, with_two_sugars());
        then_maker_receive_command("Ch:2:0");
    }

    @Test
    public void should_can_order_extra_hot_chocolate(){
        when_we_command_extra_hot(CHOCOLATE, with_no_sugar());
        then_maker_receive_command("Hh::");
    }

    @Test
    public void should_can_order_extra_hot_tea(){
       when_we_command_extra_hot(TEA, with_one_sugar());
        then_maker_receive_command("Th:1:0");
    }

    private void when_we_command(BeverageType beverageType, int nbSugar) {
        driver.command(new BeverageCommand(beverageType, nbSugar,false));
    }
    private void when_we_command_extra_hot(BeverageType beverageType, int nbSugar) {
        driver.command(new BeverageCommand(beverageType, nbSugar,true));
    }
    private void then_maker_receive_command(String expected) {
        Mockito.verify(maker, only()).send(expected);
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

}