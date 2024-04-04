package com.inditex.entities;

import java.io.Serializable;

import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name="OBSTACULOS")
public class Obstaculo {

    @Id
    private long id;

    private int direccionx;
    private int direcciony;

    public Obstaculo(){
	super();
    }
 
    public Obstaculo(int direccionx, int direcciony){
        this.direccionx = direccionx;
        this.direcciony = direcciony;
    }

    public int getDireccionx(){
	return this.direccionx;
    }

    public int getDirecciony(){
	return this.direcciony;
    }

    public long getId(){
	return this.id;
    }

    public void setDireccionx(int direccionx){
	this.direccionx= direccionx;
    }

    public void setDirecciony(int direcciony){
	this.direcciony= direcciony;
    }

    public void setId(long id){
	this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        Obstaculo obstaculo = (Obstaculo) obj;
        return obstaculo.getId() == this.getId() &&
            obstaculo.getDireccionx() == this.getDireccionx() &&
            obstaculo.getDirecciony() == this.getDirecciony();
    }

    @Override
    public int hashCode() {
	return Objects.hash(direccionx, direcciony);
    }
}

