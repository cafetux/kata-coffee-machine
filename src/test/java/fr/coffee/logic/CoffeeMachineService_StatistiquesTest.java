package fr.coffee.logic;

import fr.coffee.integration.CoffeeMaker;
import fr.coffee.logic.command.BeverageCommand;
import fr.coffee.logic.command.BeverageType;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.Random;

import static fr.coffee.logic.command.BeverageType.*;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Mockito.atLeastOnce;

@RunWith(MockitoJUnitRunner.class)
public class CoffeeMachineService_StatistiquesTest {

    @InjectMocks
    private CoffeeMachineService service;
    @Mock
    private CoffeeMaker maker;
    private Random random = new Random();
    private ArgumentCaptor<String> captor = forClass(String.class);

    @Test
    public void should_retrieve_empty_statistiques_when_nothing_bought(){
        given_no_command_today();
        when_we_retrieve_report();
        then_we_have_display("M:C:0 T:0 H:0 O:0 €:0.0");
    }

    @Test
    public void should_retrieve_statistiques_when_somethings_was_bought(){
        given_a_command_of(COFFEE);
        given_a_command_of(COFFEE);
        given_a_command_of(ORANGE_JUICE);
        given_a_command_of(TEA);
        given_a_command_of(CHOCOLATE);
        given_a_command_of(TEA);
        given_a_command_of(COFFEE);

        when_we_retrieve_report();

         // 3*0.60 + 2*0.40 + 1*0.50 + 1*0.60 = 3.7
        then_we_have_display("M:C:3 T:2 H:1 O:1 €:3.7");
    }

    private void then_we_have_display(String reportMessage) {
        Mockito.verify(maker, atLeastOnce()).send(captor.capture());
        List<String> commands = captor.getAllValues();
        Assertions.assertThat(commands).endsWith(reportMessage);
    }

    private void when_we_retrieve_report() {
       service.displayReport();
    }

    private void given_a_command_of(BeverageType beverage) {
        service.receiveCoin(beverage.getPriceInCents());
        service.command(new BeverageCommand(beverage, random.nextBoolean()?0:2, random.nextBoolean()));
    }

    private void given_no_command_today() {

    }

}