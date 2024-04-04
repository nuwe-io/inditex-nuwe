package com.inditex.entities;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name="CLIENTES")
public class Cliente{

    @Id
    private long id;

    private String nombre;
    private int direccionx;
    private int direcciony;

    public Cliente(){
	super();
    }
 
    public Cliente(String nombre, int direccionx, int direcciony){
        this.nombre = nombre;
        this.direccionx = direccionx;
        this.direcciony = direcciony;
    }

    public long getId(){
	return this.id;
    }
    public String getNombre(){
	return this.nombre;
    }

    public int getDireccionx(){
	return this.direccionx;
    }

    public int getDirecciony(){
	return this.direcciony;
    }

    public void setId(long id){
	this.id = id;
    }

    public void setNombre(String nombre){
	this.nombre= nombre;
    }

    public void setDireccionx(int direccionx){
	this.direccionx= direccionx;
    }

    public void setDirecciony(int direcciony){
	this.direcciony= direcciony;
    }

    @Override
    public boolean equals(Object co) {
        Cliente c = (Cliente) co;
        return c.getId() == this.getId() &&
            c.getNombre() == this.getNombre() &&
            c.getDireccionx() == this.getDireccionx() &&
            c.getDirecciony() == this.getDirecciony();
    }

    @Override
    public int hashCode() {
	return Objects.hash(nombre, direccionx, direcciony);
    }
    
}
