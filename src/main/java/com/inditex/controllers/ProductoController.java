package com.inditex.controllers;

import com.inditex.repositories.*;
import com.inditex.entities.Producto;

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
public class ProductoController {

    @Autowired
    ProductoRepository productoRepository;

    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> getAllProductos(){
        List<Producto> productos = new ArrayList<>();

        productoRepository.findAll().forEach(productos::add);

        if (productos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable("id") long id){
        Optional<Producto> producto = productoRepository.findById(id);
        if (! producto.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(producto.get(),HttpStatus.OK);
    }

    @PostMapping("/producto")
    public ResponseEntity<Producto> createProducto(@RequestBody Producto prod){
        Producto p = new Producto(prod.getNombre(), prod.getStock());
        productoRepository.save(p);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<HttpStatus> deleteProducto(@PathVariable("id") long id){
        Optional<Producto> producto = productoRepository.findById(id);

        if (! producto.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        productoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/productos")
    public ResponseEntity<HttpStatus> deleteAllProductos(){
        productoRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
