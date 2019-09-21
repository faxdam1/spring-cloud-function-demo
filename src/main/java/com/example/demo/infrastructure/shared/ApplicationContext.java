package com.example.demo.infrastructure.shared;

import com.example.demo.domain.service.ClienteService;
import com.example.demo.infrastructure.persistence.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by DavidArango on 9/20/19.
 */
@Configuration
public class ApplicationContext {

    @Autowired
    private ClienteRepository clienteRepository;

    @Bean
    public ClienteService getClienteService() {
        return new ClienteService(clienteRepository);
    }

}
