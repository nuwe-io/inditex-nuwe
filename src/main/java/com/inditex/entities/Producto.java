package com.inditex.entities;

import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name="PRODUCTOS")
public class Producto {

    @Id
    private long id;

    private String nombre;
    private int stock;

    public Producto(){
	super();
    }
 
    public Producto(String nombre,int stock){
        this.nombre = nombre;
        this.stock = stock;
    }

    public long getId(){
	return this.id;
    }

    public String getNombre(){
	return this.nombre;
    }

    public int getStock(){
	return this.stock;
    }

    public void setStock(int stock){
	this.stock = stock;
    }

    public void setNombre(String nombre){
	this.nombre= nombre;
    }

    public void setId(long id){
	this.id= id;
    }

    @Override
    public boolean equals(Object obj) {
        Producto producto = (Producto) obj;
        return producto.getId() == this.getId() &&
            producto.getNombre() == this.getNombre() &&
            producto.getStock() == this.getStock();
    }

    @Override
    public int hashCode() {
	return Objects.hash(this.nombre, this.stock);
    }
    
}
