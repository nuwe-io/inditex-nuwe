package com.inditex.repositories;

import java.util.List;
import java.util.Optional;

import com.inditex.entities.Pedido;
import com.inditex.entities.Locker;
import com.inditex.entities.Cliente;
import com.inditex.entities.Producto;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;

public interface PedidoRepository extends CrudRepository<Pedido, Long> {
    // TODO: Fase 1 -> Implementar las consultas SQL en los siguientes metodos.

    @Query("")
    List<Pedido> findAll();
    @Query("")
    Optional<Pedido> findById(@Param("id") long id);
    Pedido save(@Param("pedido") Pedido pedido);
    void delete(Pedido pedido);
    void deleteAll();

    @Query("")
    List<Pedido> findByNombreProducto(@Param("nombre") String nombre);

    @Query("")
    List<Pedido> findByNombreCliente(@Param("nombre") String nombre);

    @Query("")
    List<Pedido> findByNombreClienteAndNombreProducto(@Param("nombreCliente") String nombreCliente, @Param("nombreProducto") String nombreProducto);

}
