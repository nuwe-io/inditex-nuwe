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

import com.inditex.repositories.LockerRepository;
import com.inditex.entities.Locker;


@DataJdbcTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
class LockerJdbcUnitTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    LockerRepository repository;

    String insertInto = "INSERT INTO lockers(id, direccionx, direcciony) VALUES (?, ?, ? )";

    @Test
    void should_find_no_lockers_if_repository_is_empty(){
        Iterable<Locker> lockers = repository.findAll();
        assertThat(lockers).isEmpty();
    }

    @Test
    void should_create_a_locker(){
        Locker locker1 = new Locker( 1, 3);
	locker1.setId(1);

        assertThat(locker1)
            .hasFieldOrPropertyWithValue("id",1L)
            .hasFieldOrPropertyWithValue("direccionx", 1)
            .hasFieldOrPropertyWithValue("direcciony", 3);
    }

    @Test
    void should_find_all_lockers(){

	List<Locker> lockerList = new ArrayList<Locker>();
        Locker locker1 = new Locker( 1 , 1);
        Locker locker2 = new Locker( 2, 3);
        Locker locker3 = new Locker( 3, 2);

	locker1.setId(1);
	locker2.setId(2);
	locker3.setId(3);
	lockerList.add(locker1);
	lockerList.add(locker2);
	lockerList.add(locker3);

	for (Locker locker: lockerList){
	    jdbcTemplate.update(insertInto, locker.getId(),  locker.getDireccionx(), locker.getDirecciony());
	}

        List<Locker> lockers = repository.findAll();

        assertThat(lockers).hasSize(3).containsAll(lockerList);
    }

    @Test
    void should_find_locker_by_id(){
        Locker locker1 = new Locker(  1, 1);
        Locker locker2 = new Locker(  2, 3);
	List<Locker> lockerList = new ArrayList<Locker>();
	locker1.setId(1);
	locker2.setId(2);

	lockerList.add(locker1);
	lockerList.add(locker2);

	for (Locker locker: lockerList){
	    jdbcTemplate.update(insertInto, locker.getId(),  locker.getDireccionx(), locker.getDirecciony());
	}

        Optional<Locker> optFoundLocker = repository.findById(locker2.getId());
	Locker foundLocker = optFoundLocker.get();

        assertThat(foundLocker).isEqualTo(locker2);
    }

    @Test
    void should_delete_locker(){
        Locker locker1 = new Locker(  1, 1);
        Locker locker2 = new Locker(  2, 3);
        Locker locker3 = new Locker( 3, 2);

	locker1.setId(1);
	locker2.setId(2);
	locker3.setId(3);

	List<Locker> lockerList = new ArrayList<Locker>();

	lockerList.add(locker1);
	lockerList.add(locker2);
	lockerList.add(locker3);

	for (Locker locker: lockerList){
	    jdbcTemplate.update(insertInto, locker.getId(), locker.getDireccionx(), locker.getDirecciony());
	}

        repository.deleteById(locker2.getId());

        Iterable<Locker> lockers = repository.findAll();

        assertThat(lockers).hasSize(2).contains(locker1, locker3);
    }

    @Test
    void should_delete_all_lockers(){
        Locker locker1 = new Locker(  1, 1);
        Locker locker2 = new Locker(  2, 3);
        Locker locker3 = new Locker( 3, 2);

	locker1.setId(1);
	locker2.setId(2);
	locker3.setId(3);

	List<Locker> lockerList = new ArrayList<Locker>();

	lockerList.add(locker1);
	lockerList.add(locker2);
	lockerList.add(locker3);

	for (Locker locker: lockerList){
	    jdbcTemplate.update(insertInto, locker.getId(), locker.getDireccionx(), locker.getDirecciony());
	}

        repository.deleteAll();
        assertThat(repository.findAll()).isEmpty();
    }
    
}
