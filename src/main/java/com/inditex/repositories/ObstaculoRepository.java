package com.inditex.repositories;

import java.util.List;
import java.util.Optional;

import com.inditex.entities.Obstaculo;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;

public interface ObstaculoRepository extends Repository<Obstaculo, Long> {
    List<Obstaculo> findAll();
    Optional<Obstaculo> findByDireccionxAndDirecciony(int direccionx, int direcciony);
    List<Obstaculo> findByDireccionx(int direccionx);
    List<Obstaculo> findByDirecciony(int direcciony);
    Optional<Obstaculo> findById(long id);
    Obstaculo save(Obstaculo obs);
    void delete(Obstaculo obs);
    void deleteById(long id);
    void deleteAll();
}
