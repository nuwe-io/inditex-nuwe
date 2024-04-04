package com.inditex.repositories;

import java.util.List;
import java.util.Optional;

import com.inditex.entities.Cliente;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
    @Query("SELECT * FROM clientes;")
    List<Cliente> findAll();
    Optional<Cliente> findById(long id);
    List<Cliente> findByDireccionx(int direccionx);
    List<Cliente> findByDirecciony(int direcciony);
    Cliente save(Cliente client);
    void delete(Cliente client);
    void deleteById(long id);
}
