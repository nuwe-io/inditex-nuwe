package com.inditex.controllers;

import com.inditex.repositories.*;
import com.inditex.entities.Locker;

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
public class LockerController {

    @Autowired
    LockerRepository lockerRepository;

    @GetMapping("/lockers")
    public ResponseEntity<List<Locker>> getAllLockers(){
        List<Locker> lockers = new ArrayList<>();

        lockerRepository.findAll().forEach(lockers::add);

        if (lockers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(lockers, HttpStatus.OK);
    }

    @GetMapping("/lockers/{id}")
    public ResponseEntity<Locker> getLockerById(@PathVariable("id") long id){
        Optional<Locker> locker = lockerRepository.findById(id);
        if (! locker.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(locker.get(),HttpStatus.OK);
    }

    @PostMapping("/locker")
    public ResponseEntity<Locker> createLocker(@RequestBody Locker locker){
        Locker l = new Locker(locker.getDireccionx(), locker.getDirecciony());
        lockerRepository.save(l);
        return new ResponseEntity<>(l, HttpStatus.CREATED);
    }

    @DeleteMapping("/lockers/{id}")
    public ResponseEntity<HttpStatus> deleteLocker(@PathVariable("id") long id){
        Optional<Locker> locker = lockerRepository.findById(id);

        if (! locker.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        lockerRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/lockers")
    public ResponseEntity<HttpStatus> deleteAllLockers(){
        lockerRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
