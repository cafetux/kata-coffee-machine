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

@RunWith(MockitoJUnitRunner.class)
public class CoffeeMachineAdapterTest {

    @InjectMocks
    private CoffeeMachineAdapter service;
    @Mock
    private CoffeeMaker maker;
    private BeverageType beverageType;
    private int nbSugar;

    @Test
    public void should_command_coffee_without_stick_when_no_sugar(){

        given_a_command_of(COFFEE);
        with_no_sugar();
        when_we_command();
        then_maker_receive_command("C::");
    }

    @Test
    public void should_command_tea_without_stick_when_no_sugar(){
        given_a_command_of(TEA);
        with_no_sugar();
        when_we_command();
        then_maker_receive_command("T::");
    }

    @Test
    public void should_command_chocolate_without_stick_when_no_sugar(){
        given_a_command_of(CHOCOLATE);
        with_no_sugar();
        when_we_command();
        then_maker_receive_command("H::");
    }

    @Test
    public void should_can_add_one_sugar_to_coffee_and_that_add_stick(){
        given_a_command_of(COFFEE);
        with_one_sugar();
        when_we_command();
        then_maker_receive_command("C:1:0");
    }

    @Test
    public void should_can_add_two_sugar_to_tea_and_that_add_stick(){
        given_a_command_of(TEA);
        with_two_sugars();
        when_we_command();
        then_maker_receive_command("T:2:0");
   }

    private void given_a_command_of(BeverageType type) {
        beverageType = type;
    }

    private void with_no_sugar() {
        nbSugar = 0;
    }
    private void with_one_sugar() {
        nbSugar = 1;
    }
    private void with_two_sugars() {
        nbSugar = 2;
    }

    private void then_maker_receive_command(String expected) {
        Mockito.verify(maker).make(expected);
    }

    private void when_we_command() {
        service.command(beverageType, nbSugar);
    }

}