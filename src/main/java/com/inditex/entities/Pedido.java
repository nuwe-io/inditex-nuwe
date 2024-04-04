package com.inditex.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Table(name="PEDIDOS")
public class Pedido{
    @Id
    private long id;

    private long productoid;
    private long clienteid;
    private long lockerid;

    public Pedido(){
	super();
    }
 
    public Pedido( long productoid, long clienteid, long lockerid) {
	    this.productoid = productoid;
	    this.clienteid = clienteid;
	    this.lockerid = lockerid;
    }

    public long getId(){
	return this.id;
    }

    public void setId(long id){
	this.id = id;
    }

    public void setProductoid(long id) {
	this.productoid = id;
    }

    public void setClienteid(long id) {
	this.clienteid = id;
    }

    public void setLockerid(long id) {
	this.lockerid = id;
    }

    public long getProductoid(){
	return this.productoid;
    }

    public long getClienteid(){
	return this.clienteid;
    }

    public long getLockerid(){
	return this.lockerid;
    }

    public static double distanciaEntreClienteLocker(Cliente c, Locker l){
        // TODO: Fase 2. Completar la entidad de Pedido.
	return distancia;
    }

    @Override
    public boolean equals(Object obj) {
	Pedido pedido = (Pedido) obj;
	return pedido.getId() == this.getId() &&
        pedido.getProductoid() == this.getProductoid() &&
        pedido.getClienteid() == this.getClienteid() &&
        pedido.getLockerid() == this.getLockerid();
    }

    @Override
    public int hashCode() {
	return Objects.hash(productoid, clienteid, lockerid);
    }
}
