package com.example.demo.infrastructure.persistence;

import com.example.demo.domain.service.repository.IClienteRepository;
import com.example.demo.domain.model.Cliente;
import org.springframework.stereotype.Repository;

/**
 * Created by DavidArango on 9/20/19.
 */
@Repository
public class ClienteRepository implements IClienteRepository {

    public Cliente getCliente(Cliente cliente){
        cliente.setName("Name consultado");
        cliente.setValue("Valor consultado");
        return cliente;
    }

}
