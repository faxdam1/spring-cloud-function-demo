package com.example.demo.application;

import com.example.demo.domain.model.Cliente;
import com.example.demo.domain.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;


import java.util.function.Function;

/**
 * Created by DavidArango on 9/20/19.
 */

@Component
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @Bean("guardar")
    public Function<Message<Cliente>, Message<Cliente>> guardarCliente() {

        return value -> MessageBuilder.
                createMessage(
                        clienteService.getCliente(value.getPayload()),value.getHeaders()
                );
    }




}


