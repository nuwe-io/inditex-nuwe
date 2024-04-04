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

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> getAllClientes(){
        List<Cliente> clientes = new ArrayList<>();

        clienteRepository.findAll().forEach(clientes::add);

        if (clientes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") long id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (! cliente.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(cliente.get(),HttpStatus.OK);
    }

    @GetMapping("/clientes/x/{direccionx}")
    public ResponseEntity<List<Cliente>> getClienteByX(@PathVariable("direccionx") int direccionx) {
        List<Cliente> clientes = new ArrayList<>();
        clienteRepository.findByDireccionx(direccionx).forEach(clientes::add);

        if (clientes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/clientes/y/{direcciony}")
    public ResponseEntity<List<Cliente>> getClienteByY(@PathVariable("direcciony") int direcciony) {
        List<Cliente> clientes = new ArrayList<>();
        clienteRepository.findByDirecciony(direcciony).forEach(clientes::add);

        if (clientes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @PostMapping("/cliente")
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente client){
        Cliente c = new Cliente(client.getNombre(), client.getDireccionx(), client.getDirecciony());
        clienteRepository.save(c);
        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }

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
