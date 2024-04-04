package com.inditex;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.inditex.repositories.*;
import com.inditex.entities.*;


@DataJdbcTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
class PedidoJdbcUnitTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    LockerRepository lockerRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    private Producto producto1;
    private Producto producto2;

    private Cliente cliente1;
    private Cliente cliente2;

    private Locker locker1;
    private Locker locker2;

    private String clienteInsertInto = "INSERT INTO clientes (id, nombre, direccionx, direcciony) VALUES (?, ?, ?, ? )";
    private String productoInsertInto = "INSERT INTO productos (id, nombre, stock) VALUES (?, ?, ? )";
    private String lockerInsertInto = "INSERT INTO lockers (id, direccionx, direcciony) VALUES (?, ?, ? )";
    private String pedidoInsertInto = "INSERT INTO pedidos (id, productoId, clienteId, lockerId) VALUES (?, ?, ?, ? )";

    @BeforeEach
    void setUp(){
        producto1 = new Producto("Camiseta", 3);
        producto2 = new Producto("Bolso", 12);
	producto1.setId(1);
	producto2.setId(2);

        cliente1 = new Cliente("Paco", 1, 1);
        cliente2 = new Cliente("Lucia", 2, 3);
	cliente1.setId(1);
	cliente2.setId(2);

        locker1 = new Locker(1, 2);
        locker2 = new Locker(2, 4);
	locker1.setId(1);
	locker2.setId(2);

	List<Producto> productoLista = new ArrayList<Producto>();
	List<Cliente> clienteLista = new ArrayList<Cliente>();
	List<Locker> lockerLista = new ArrayList<Locker>();

	productoLista.add(producto1);
	productoLista.add(producto2);

	clienteLista.add(cliente1);
	clienteLista.add(cliente2);

	lockerLista.add(locker1);
	lockerLista.add(locker2);
	for (Producto producto : productoLista){
	    jdbcTemplate.update(productoInsertInto, producto.getId(),  producto.getNombre(), producto.getStock());
	}
	for (Cliente cliente : clienteLista){
	    jdbcTemplate.update(clienteInsertInto, cliente.getId(), cliente.getNombre(), cliente.getDireccionx(), cliente.getDirecciony());
	}
	for (Locker locker : lockerLista){
	    jdbcTemplate.update(lockerInsertInto, locker.getId(),  locker.getDireccionx(), locker.getDirecciony());
	}
    }


    @Test
    void should_find_no_pedidos_if_repository_is_empty(){
        Iterable<Pedido> pedidos = pedidoRepository.findAll();
        assertThat(pedidos).isEmpty();
    }

    @Test
    void should_create_a_pedido(){
        Pedido pedido1 = new Pedido(producto1.getId(), cliente1.getId(), locker1.getId());
	pedido1.setId(1);

        assertThat(pedido1)
            .hasFieldOrPropertyWithValue("id", pedido1.getId())
            .hasFieldOrPropertyWithValue("productoid", producto1.getId())
            .hasFieldOrPropertyWithValue("clienteid", cliente1.getId())
            .hasFieldOrPropertyWithValue("lockerid", locker1.getId());
    }

    @Test
    void should_find_all_pedidos(){

        Pedido pedido1 = new Pedido(producto1.getId(), cliente1.getId(), locker1.getId());
        Pedido pedido2 = new Pedido(producto1.getId(), cliente2.getId(), locker1.getId());
        Pedido pedido3 = new Pedido(producto2.getId(), cliente2.getId(), locker1.getId());
	pedido1.setId(1);
	pedido2.setId(2);
	pedido3.setId(3);

	List<Pedido> pedidoList = new ArrayList<Pedido>();
	pedidoList.add(pedido1);
	pedidoList.add(pedido2);
	pedidoList.add(pedido3);

	for (Pedido pedido: pedidoList){
	    jdbcTemplate.update(pedidoInsertInto, pedido.getId(), pedido.getProductoid(), pedido.getClienteid(), pedido.getLockerid());
	}

        List<Pedido> pedidos = pedidoRepository.findAll();

        assertThat(pedidos).hasSize(3).containsAll(pedidoList);
    }

    @Test
    void should_find_pedido_by_id(){
        Pedido pedido1 = new Pedido(producto1.getId(), cliente1.getId(), locker1.getId());
        Pedido pedido2 = new Pedido(producto1.getId(), cliente2.getId(), locker1.getId());
	pedido1.setId(1);
	pedido2.setId(2);
	List<Pedido> pedidoList = new ArrayList<Pedido>();
	pedidoList.add(pedido1);
	pedidoList.add(pedido2);

	for (Pedido pedido: pedidoList){
	    jdbcTemplate.update(pedidoInsertInto, pedido.getId(), pedido.getProductoid(), pedido.getClienteid(), pedido.getLockerid());
	}

        Pedido foundPedido = pedidoRepository.findById(pedido2.getId()).get();

        assertThat(foundPedido).isEqualTo(pedido2);
    }

    @Test
    void should_find_pedido_by_nombre_producto(){

        Pedido pedido1 = new Pedido(producto1.getId(), cliente1.getId(), locker1.getId());
        Pedido pedido2 = new Pedido(producto1.getId(), cliente2.getId(), locker1.getId());
        Pedido pedido3 = new Pedido(producto2.getId(), cliente2.getId(), locker1.getId());
	pedido1.setId(1);
	pedido2.setId(2);
	pedido3.setId(3);
	List<Pedido> pedidoList = new ArrayList<Pedido>();
	pedidoList.add(pedido1);
	pedidoList.add(pedido2);
	pedidoList.add(pedido3);

	for (Pedido pedido: pedidoList){
	    jdbcTemplate.update(pedidoInsertInto, pedido.getId(), pedido.getProductoid(), pedido.getClienteid(), pedido.getLockerid());
	}

        List<Pedido> foundPedidos = pedidoRepository.findByNombreProducto(producto1.getNombre());
	assertThat(foundPedidos).hasSize(2).contains(pedido1, pedido2);

        foundPedidos = pedidoRepository.findByNombreProducto(producto2.getNombre());
	assertThat(foundPedidos).hasSize(1).contains(pedido3);

        foundPedidos = pedidoRepository.findByNombreProducto("DoesNotExist");
	assertThat(foundPedidos).hasSize(0);
    }

    @Test
    void should_find_pedido_by_nombre_cliente(){
        Pedido pedido1 = new Pedido(producto1.getId(), cliente1.getId(), locker1.getId());
        Pedido pedido2 = new Pedido(producto1.getId(), cliente2.getId(), locker2.getId());
        Pedido pedido3 = new Pedido(producto2.getId(), cliente2.getId(), locker1.getId());
	pedido1.setId(1);
	pedido2.setId(2);
	pedido3.setId(3);
	List<Pedido> pedidoList = new ArrayList<Pedido>();
	pedidoList.add(pedido1);
	pedidoList.add(pedido2);
	pedidoList.add(pedido3);

	for (Pedido pedido: pedidoList){
	    jdbcTemplate.update(pedidoInsertInto, pedido.getId(), pedido.getProductoid(), pedido.getClienteid(), pedido.getLockerid());

	}

	List<Pedido> foundPedidos = pedidoRepository.findByNombreCliente(cliente2.getNombre());
	assertThat(foundPedidos).hasSize(2).contains(pedido2, pedido3);

	foundPedidos = pedidoRepository.findByNombreCliente(cliente1.getNombre());
	assertThat(foundPedidos).hasSize(1).contains(pedido1);

	foundPedidos = pedidoRepository.findByNombreCliente("DoesNotExist");
	assertThat(foundPedidos).hasSize(0);
    }

    @Test
    void should_find_pedido_by_nombre_cliente_and_nombre_producto(){
        Pedido pedido1 = new Pedido(producto1.getId(), cliente1.getId(), locker1.getId());
        Pedido pedido2 = new Pedido(producto1.getId(), cliente2.getId(), locker2.getId());
        Pedido pedido3 = new Pedido(producto2.getId(), cliente2.getId(), locker1.getId());
	pedido1.setId(1);
	pedido2.setId(2);
	pedido3.setId(3);
	List<Pedido> pedidoList = new ArrayList<Pedido>();
	pedidoList.add(pedido1);
	pedidoList.add(pedido2);
	pedidoList.add(pedido3);

	for (Pedido pedido: pedidoList){
	    jdbcTemplate.update(pedidoInsertInto, pedido.getId(), pedido.getProductoid(), pedido.getClienteid(), pedido.getLockerid());

	}

	List<Pedido> foundPedidos = pedidoRepository.findByNombreClienteAndNombreProducto(cliente2.getNombre(), producto1.getNombre());
	assertThat(foundPedidos).hasSize(1).contains(pedido2);

	foundPedidos = pedidoRepository.findByNombreClienteAndNombreProducto(cliente2.getNombre(), producto2.getNombre());
	assertThat(foundPedidos).hasSize(1).contains(pedido3);

	foundPedidos = pedidoRepository.findByNombreClienteAndNombreProducto(cliente1.getNombre(), producto2.getNombre());
	assertThat(foundPedidos).hasSize(0);
    }

}
