package com.inditex.repositories;

import java.util.List;
import java.util.Optional;

import com.inditex.entities.Locker;

import org.springframework.data.repository.Repository;

public interface LockerRepository extends Repository<Locker, Long> {
    List<Locker> findAll();
    Optional<Locker> findById(long id);
    Locker save(Locker locker);
    void delete(Locker locker);
    void deleteById(long id);
    void deleteAll();
}
