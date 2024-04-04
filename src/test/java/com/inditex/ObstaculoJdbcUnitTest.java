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

import com.inditex.repositories.ObstaculoRepository;
import com.inditex.entities.Obstaculo;


@DataJdbcTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
class ObstaculoJdbcUnitTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ObstaculoRepository repository;

    String insertInto = "INSERT INTO obstaculos(id, direccionx, direcciony) VALUES (?, ?, ? )";

    @Test
    void should_find_no_obstaculos_if_repository_is_empty(){
        Iterable<Obstaculo> obstaculos = repository.findAll();
        assertThat(obstaculos).isEmpty();
    }

    @Test
    void should_create_a_obstaculo(){
        Obstaculo obstaculo1 = new Obstaculo( 1, 3);
	obstaculo1.setId(1);

        assertThat(obstaculo1)
            .hasFieldOrPropertyWithValue("id",1L)
            .hasFieldOrPropertyWithValue("direccionx", 1)
            .hasFieldOrPropertyWithValue("direcciony", 3);
    }

    @Test
    void should_find_all_obstaculos(){

	List<Obstaculo> obstaculoList = new ArrayList<Obstaculo>();
        Obstaculo obstaculo1 = new Obstaculo( 1 , 1);
        Obstaculo obstaculo2 = new Obstaculo( 2, 3);
        Obstaculo obstaculo3 = new Obstaculo( 3, 2);

	obstaculo1.setId(1);
	obstaculo2.setId(2);
	obstaculo3.setId(3);
	obstaculoList.add(obstaculo1);
	obstaculoList.add(obstaculo2);
	obstaculoList.add(obstaculo3);

	for (Obstaculo obstaculo: obstaculoList){
	    jdbcTemplate.update(insertInto, obstaculo.getId(),  obstaculo.getDireccionx(), obstaculo.getDirecciony());
	}

        List<Obstaculo> obstaculos = repository.findAll();

        assertThat(obstaculos).hasSize(3).containsAll(obstaculoList);
    }

    @Test
    void should_find_obstaculo_by_id(){
        Obstaculo obstaculo1 = new Obstaculo(  1, 1);
        Obstaculo obstaculo2 = new Obstaculo(  2, 3);
	List<Obstaculo> obstaculoList = new ArrayList<Obstaculo>();
	obstaculo1.setId(1);
	obstaculo2.setId(2);

	obstaculoList.add(obstaculo1);
	obstaculoList.add(obstaculo2);

	for (Obstaculo obstaculo: obstaculoList){
	    jdbcTemplate.update(insertInto, obstaculo.getId(),  obstaculo.getDireccionx(), obstaculo.getDirecciony());
	}

        Optional<Obstaculo> optFoundObstaculo = repository.findById(obstaculo2.getId());
	Obstaculo foundObstaculo = optFoundObstaculo.get();

        assertThat(foundObstaculo).isEqualTo(obstaculo2);
    }

    @Test
    void should_delete_obstaculo(){
        Obstaculo obstaculo1 = new Obstaculo(  1, 1);
        Obstaculo obstaculo2 = new Obstaculo(  2, 3);
        Obstaculo obstaculo3 = new Obstaculo( 3, 2);

	obstaculo1.setId(1);
	obstaculo2.setId(2);
	obstaculo3.setId(3);

	List<Obstaculo> obstaculoList = new ArrayList<Obstaculo>();

	obstaculoList.add(obstaculo1);
	obstaculoList.add(obstaculo2);
	obstaculoList.add(obstaculo3);

	for (Obstaculo obstaculo: obstaculoList){
	    jdbcTemplate.update(insertInto, obstaculo.getId(), obstaculo.getDireccionx(), obstaculo.getDirecciony());
	}

        repository.deleteById(obstaculo2.getId());

        Iterable<Obstaculo> obstaculos = repository.findAll();

        assertThat(obstaculos).hasSize(2).contains(obstaculo1, obstaculo3);
    }

    @Test
    void should_delete_all_obstaculos(){
        Obstaculo obstaculo1 = new Obstaculo(  1, 1);
        Obstaculo obstaculo2 = new Obstaculo(  2, 3);
        Obstaculo obstaculo3 = new Obstaculo( 3, 2);

	obstaculo1.setId(1);
	obstaculo2.setId(2);
	obstaculo3.setId(3);

	List<Obstaculo> obstaculoList = new ArrayList<Obstaculo>();

	obstaculoList.add(obstaculo1);
	obstaculoList.add(obstaculo2);
	obstaculoList.add(obstaculo3);

	for (Obstaculo obstaculo: obstaculoList){
	    jdbcTemplate.update(insertInto, obstaculo.getId(), obstaculo.getDireccionx(), obstaculo.getDirecciony());
	}

        repository.deleteAll();
        assertThat(repository.findAll()).isEmpty();
    }
    
}
