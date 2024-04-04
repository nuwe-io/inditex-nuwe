package com.inditex;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.inditex.repositories.ProductoRepository;
import com.inditex.entities.Producto;


@DataJdbcTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
class ProductoJdbcUnitTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ProductoRepository repository;

    String insertInto = "INSERT INTO productos(id, Nombre, stock) VALUES (?, ?, ? )";

    @Test
    void should_find_no_productos_if_repository_is_empty(){
        Iterable<Producto> productos = repository.findAll();
        assertThat(productos).isEmpty();
    }

    @Test
    void should_create_a_producto(){
        Producto producto1 = new Producto( "Producto1" , 3);
	producto1.setId(1);

        assertThat(producto1)
            .hasFieldOrPropertyWithValue("id",1L)
            .hasFieldOrPropertyWithValue("nombre","Producto1")
            .hasFieldOrPropertyWithValue("stock", 3);
    }

    @Test
    void should_find_all_productos(){

	List<Producto> productoList = new ArrayList<Producto>();
        Producto producto1 = new Producto( "Producto1" , 1);
        Producto producto2 = new Producto( "Producto2" , 3);
        Producto producto3 = new Producto( "Producto3" , 2);

	producto1.setId(1);
	producto2.setId(2);
	producto3.setId(3);
	productoList.add(producto1);
	productoList.add(producto2);
	productoList.add(producto3);

	for (Producto producto: productoList){
	    jdbcTemplate.update(insertInto, producto.getId(),  producto.getNombre(), producto.getStock());
	}

        List<Producto> productos = repository.findAll();

        assertThat(productos).hasSize(3).containsAll(productoList);
    }

    @Test
    void should_find_producto_by_id(){
        Producto producto1 = new Producto( "Producto1" , 1);
        Producto producto2 = new Producto( "Producto2" , 3);
	List<Producto> productoList = new ArrayList<Producto>();
	producto1.setId(1);
	producto2.setId(2);

	productoList.add(producto1);
	productoList.add(producto2);

	for (Producto producto: productoList){
	    jdbcTemplate.update(insertInto, producto.getId(),  producto.getNombre(), producto.getStock());
	}

        Optional<Producto> optFoundProducto = repository.findById(producto2.getId());
	Producto foundProducto = optFoundProducto.get();

        assertThat(foundProducto).isEqualTo(producto2);
    }

    @Test
    void should_delete_producto(){
        Producto producto1 = new Producto( "Producto1" , 1);
        Producto producto2 = new Producto( "Producto2" , 3);
        Producto producto3 = new Producto( "Producto3" , 2);

	producto1.setId(1);
	producto2.setId(2);
	producto3.setId(3);

	List<Producto> productoList = new ArrayList<Producto>();

	productoList.add(producto1);
	productoList.add(producto2);
	productoList.add(producto3);

	for (Producto producto: productoList){
	    jdbcTemplate.update(insertInto, producto.getId(), producto.getNombre(), producto.getStock());
	}

        repository.deleteById(producto2.getId());

        Iterable<Producto> productos = repository.findAll();

        assertThat(productos).hasSize(2).contains(producto1, producto3);
    }

    @Test
    void should_delete_all_productos(){
        Producto producto1 = new Producto( "Producto1" , 1);
        Producto producto2 = new Producto( "Producto2" , 3);
        Producto producto3 = new Producto( "Producto3" , 2);

	producto1.setId(1);
	producto2.setId(2);
	producto3.setId(3);

	List<Producto> productoList = new ArrayList<Producto>();

	productoList.add(producto1);
	productoList.add(producto2);
	productoList.add(producto3);

	for (Producto producto: productoList){
	    jdbcTemplate.update(insertInto, producto.getId(), producto.getNombre(), producto.getStock());
	}

        repository.deleteAll();
        assertThat(repository.findAll()).isEmpty();
    }
    
}
