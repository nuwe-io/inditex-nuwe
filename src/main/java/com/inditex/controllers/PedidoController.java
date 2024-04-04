package com.inditex.controllers;

import com.inditex.repositories.*;
import com.inditex.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class PedidoController {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    LockerRepository lockerRepository;

    @GetMapping("/pedidos")
    public ResponseEntity<List<Pedido>> getAllPedidos(){
	// TODO: Fase 2
    }

    @GetMapping("/pedidos/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable("id") long id){
	// TODO: Fase 2
    }

    @PostMapping("/pedido")
	public ResponseEntity<List<Pedido>> createPedido(@RequestParam long productoid, @RequestParam long clienteid ){
    }
	// TODO: Fase 2


    @DeleteMapping("/pedidos")
    public ResponseEntity<HttpStatus> deleteAllPedidos(){
	// TODO: Fase 2
    }

}
