package com.example.demo.application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import java.util.function.Function;

/**
 * Created by DavidArango on 9/20/19.
 */

@Component
public class ClienteController {

    @Bean("downFiles")
    public Function<String, String> downFiles() {
        return value -> {
            System.out.println("Hello: " + value);
            return "Hello: " + value;
        };
    }

}


