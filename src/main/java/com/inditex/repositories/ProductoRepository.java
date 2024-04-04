package com.inditex.repositories;

import java.util.List;
import java.util.Optional;

import com.inditex.entities.Producto;

import org.springframework.data.repository.Repository;

public interface ProductoRepository extends Repository<Producto, Long> {
    List<Producto> findAll();
    Optional<Producto> findById(long id);
    Producto save(Producto prod);
    void delete(Producto prod);
    void deleteById(long id);
    void deleteAll();
}
