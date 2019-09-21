package com.example.demo.domain.service;

import com.example.demo.domain.service.repository.IClienteRepository;
import com.example.demo.domain.model.Cliente;

/**
 * Created by DavidArango on 9/20/19.
 */
public class ClienteService {

    private IClienteRepository clienteRepository;

    public ClienteService(IClienteRepository clienteRepository){
         this.clienteRepository=clienteRepository;
    }

    public Cliente getCliente(Cliente cliente){
        return clienteRepository.getCliente(cliente);
    }


}
