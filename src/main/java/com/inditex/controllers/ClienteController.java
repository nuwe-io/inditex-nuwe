package com.inditex.controllers;

import com.inditex.repositories.*;
import com.inditex.entities.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class ClienteController {
    // Implementa aquí la solución requerida

    // Métodos de ejemplo
    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<HttpStatus> deleteCliente(@PathVariable("id") long id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (! cliente.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        clienteRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/clientes")
    public ResponseEntity<HttpStatus> deleteAllClientes(){
        clienteRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
