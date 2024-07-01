package ru.homework.cargo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
@EnableStateMachineFactory
public class TelegramBotStateMachineConfig extends StateMachineConfigurerAdapter<String, String> {

    @Override
    public void configure(StateMachineStateConfigurer<String, String> states) throws Exception {
        states
                .withStates()
                .initial("IDLE")
                .states(new HashSet<>(Arrays.asList("IDLE", "MENU", "PROCESSING", "COMPLETED")));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<String, String> transitions) throws Exception {
        transitions
                .withExternal()
                .source("IDLE")
                .target("MENU")
                .event("start")
                .and()
                .withExternal()
                .source("MENU")
                .target("PROCESSING")
                .event("process")
                .and()
                .withExternal()
                .source("PROCESSING")
                .target("COMPLETED")
                .event("complete");
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<String, String> config) throws Exception {
        config
                .withConfiguration()
                .autoStartup(true);
    }
}