package com.inditex.controllers;

import com.inditex.repositories.*;
import com.inditex.entities.Obstaculo;

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
public class ObstaculoController {

    @Autowired
    ObstaculoRepository obstaculoRepository;

    @GetMapping("/obstaculos")
    public ResponseEntity<List<Obstaculo>> getAllObstaculos(){
        List<Obstaculo> obstaculos = new ArrayList<>();

        obstaculoRepository.findAll().forEach(obstaculos::add);

        if (obstaculos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(obstaculos, HttpStatus.OK);
    }


    @GetMapping("/obstaculos/id")
    public ResponseEntity<Obstaculo> getObstaculoByXY(@RequestParam int x, @RequestParam int y) {
        Optional<Obstaculo> obstaculo = obstaculoRepository.findByDireccionxAndDirecciony(x,y);
        if (!obstaculo.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(obstaculo.get(), HttpStatus.OK);

    }

    @GetMapping("/obstaculos/x/{direccionx}")
    public ResponseEntity<List<Obstaculo>> getObstaculoByx(@PathVariable("direccionx") int direccionx) {
        List<Obstaculo> obstaculos = new ArrayList<>();
        obstaculoRepository.findByDireccionx(direccionx).forEach(obstaculos::add);

        if (obstaculos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(obstaculos, HttpStatus.OK);
    }

    @GetMapping("/obstaculos/y/{direcciony}")
    public ResponseEntity<List<Obstaculo>> getObstaculoByY(@PathVariable("direcciony") int direcciony) {
        List<Obstaculo> obstaculos = new ArrayList<>();
        obstaculoRepository.findByDirecciony(direcciony).forEach(obstaculos::add);

        if (obstaculos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(obstaculos, HttpStatus.OK);
    }

    @PostMapping("/obstaculo")
    public ResponseEntity<Obstaculo> createObstaculo(@RequestBody Obstaculo obstaculo){
        Obstaculo obs = new Obstaculo(obstaculo.getDireccionx(), obstaculo.getDirecciony());
        obstaculoRepository.save(obs);
        return new ResponseEntity<>(obs, HttpStatus.CREATED);
    }

    @DeleteMapping("/obstaculos/{direccionx}/{direcciony}")
    public ResponseEntity<HttpStatus> deleteObstaculo(@PathVariable("direccionx") int direccionx, @PathVariable("direcciony") int direcciony){
        Optional<Obstaculo> obstaculo = obstaculoRepository.findByDireccionxAndDirecciony(direccionx, direcciony);

        if (! obstaculo.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        obstaculoRepository.delete(obstaculo.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/obstaculos")
    public ResponseEntity<HttpStatus> deleteAllObstaculos(){
        obstaculoRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
