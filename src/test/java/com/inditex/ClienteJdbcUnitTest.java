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

import com.inditex.repositories.ClienteRepository;
import com.inditex.entities.Cliente;


@DataJdbcTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
class ClienteJdbcUnitTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ClienteRepository repository;

    String insertInto = "INSERT INTO clientes (id, nombre, direccionx, direcciony) VALUES (?, ?, ?, ? )";

    @Test
    void should_find_no_clientes_if_repository_is_empty(){
        Iterable<Cliente> clientes = repository.findAll();
        assertThat(clientes).isEmpty();
    }

    @Test
    void should_create_a_cliente(){
        Cliente cliente1 = new Cliente("ClienteUno",  1, 3);
	cliente1.setId(1);

        assertThat(cliente1)
            .hasFieldOrPropertyWithValue("id",1L)
            .hasFieldOrPropertyWithValue("nombre", "ClienteUno")
            .hasFieldOrPropertyWithValue("direccionx", 1)
            .hasFieldOrPropertyWithValue("direcciony", 3);
    }

    @Test
    void should_find_all_clientes(){

	List<Cliente> cs = new ArrayList<Cliente>();
        Cliente cliente1 = new Cliente( "ClienteUno", 1 , 1);
        Cliente cliente2 = new Cliente( "ClienteDos", 2, 3);
        Cliente cliente3 = new Cliente( "ClienteTres", 3, 2);

	cliente1.setId(1);
	cliente2.setId(2);
	cliente3.setId(3);
	cs.add(cliente1);
	cs.add(cliente2);
	cs.add(cliente3);

	for (Cliente c: cs){
	    jdbcTemplate.update(insertInto, c.getId(), c.getNombre(), c.getDireccionx(), c.getDirecciony());
	}

        List<Cliente> clientes = repository.findAll();

        assertThat(clientes).hasSize(3).containsAll(cs);
    }

    @Test
    void should_find_cliente_by_id(){
        Cliente cliente1 = new Cliente( "ClienteUno",  1, 1);
        Cliente cliente2 = new Cliente( "ClienteDos",  2, 3);
	List<Cliente> cs = new ArrayList<Cliente>();
	cliente1.setId(1);
	cliente2.setId(2);

	cs.add(cliente1);
	cs.add(cliente2);

	for (Cliente c: cs){
	    jdbcTemplate.update(insertInto, c.getId(), c.getNombre(), c.getDireccionx(), c.getDirecciony());
	}

        Optional<Cliente> optFoundCliente = repository.findById(cliente2.getId());
	Cliente foundCliente = optFoundCliente.get();

        assertThat(foundCliente).isEqualTo(cliente2);
    }

    @Test
    void should_delete_cliente(){
        Cliente cliente1 = new Cliente( "ClienteUno",  1, 1);
        Cliente cliente2 = new Cliente( "ClienteDos",  2, 3);
        Cliente cliente3 = new Cliente( "ClienteTres", 3, 2);

	cliente1.setId(1);
	cliente2.setId(2);
	cliente3.setId(3);

	List<Cliente> cs = new ArrayList<Cliente>();

	cs.add(cliente1);
	cs.add(cliente2);
	cs.add(cliente3);

	for (Cliente c: cs){
	    jdbcTemplate.update(insertInto, c.getId(), c.getNombre(), c.getDireccionx(), c.getDirecciony());
	}

        repository.deleteById(cliente2.getId());

        Iterable<Cliente> clientes = repository.findAll();

        assertThat(clientes).hasSize(2).contains(cliente1, cliente3);
    }

    @Test
    void should_delete_all_clientes(){
        Cliente cliente1 = new Cliente("ClienteUno",  1, 1);
        Cliente cliente2 = new Cliente("ClienteDos",  2, 3);
        Cliente cliente3 = new Cliente("ClienteTres", 3, 2);

	cliente1.setId(1);
	cliente2.setId(2);
	cliente3.setId(3);

	List<Cliente> cs = new ArrayList<Cliente>();

	cs.add(cliente1);
	cs.add(cliente2);
	cs.add(cliente3);

	for (Cliente c: cs){
	    jdbcTemplate.update(insertInto, c.getId(), c.getNombre(), c.getDireccionx(), c.getDirecciony());
	}

        repository.deleteAll();
        assertThat(repository.findAll()).isEmpty();
    }
    
}
