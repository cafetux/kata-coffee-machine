package fr.coffee;

import fr.coffee.CoffeeMachineService;
import fr.coffee.integration.BeverageQuantityChecker;
import fr.coffee.integration.EmailNotifier;
import fr.coffee.command.BeverageCommand;
import fr.coffee.command.BeverageType;
import fr.coffee.maker.CoffeeMakerDriver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Random;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CoffeeMachineService_AdapterTest {

    @InjectMocks
    private CoffeeMachineService service;
    @Mock
    private CoffeeMakerDriver maker;

    @Mock
    private BeverageQuantityChecker beverageQuantityChecker;
    @Mock
    private EmailNotifier emailNotifier;
    private Random random = new Random();


    @Test
    public void should_make_command_when_enough_money_and_enough_beverage() {
        Given_enough_money();
        and_enough_beverage();
        when_we_command_beverage();
        then_maker_receive_command();;
    }

    @Test
    public void should_reject_command_of_tea_when_not_enough_money() {
        Given_not_enough_money();
        and_enough_beverage();
        when_we_command_beverage();
        then_maker_receive_message("missing money");
    }

    private void then_maker_receive_message(String expectedMessage) {
        verify(maker).display(expectedMessage);
    }

    @Test
    public void should_reject_command_when_not_enough_beverage_and_send_email() {
        Given_enough_money();
        and_not_enough_beverage();
        when_we_command_beverage();
        then_maker_not_receive_command();
        and_send_email_for_refill();
    }

    private void when_we_command_beverage() {
        service.command(randomCommand());
    }

    private BeverageCommand randomCommand() {
        return new BeverageCommand(BeverageType.values()[random.nextInt(4)], random.nextBoolean()?0:2,random.nextBoolean());
    }

    private void and_send_email_for_refill() {
        verify(emailNotifier).notifyMissingDrink(anyString());
    }


    private void and_enough_beverage() {
        when(beverageQuantityChecker.isEmpty(anyString())).thenReturn(false);
    }

    private void and_not_enough_beverage() {
        when(beverageQuantityChecker.isEmpty(anyString())).thenReturn(true);
    }

    private void Given_not_enough_money() {
        service.receiveCoin(3);
    }

    private void Given_enough_money() {
        service.receiveCoin(4000);
    }

    private void then_maker_receive_command() {
        Mockito.verify(maker, only()).command(any(BeverageCommand.class));
    }

    private void then_maker_not_receive_command() {
        Mockito.verify(maker, never()).command(any(BeverageCommand.class));
    }

}