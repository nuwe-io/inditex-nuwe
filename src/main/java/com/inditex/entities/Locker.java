package com.inditex.entities;


import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name="LOCKERS")
public class Locker{

    @Id
    private long id;

    private int direccionx;
    private int direcciony;

    public Locker(){
	super();
    }
 
    public Locker(int direccionx, int direcciony){
        this.direccionx = direccionx;
        this.direcciony = direcciony;
    }

    public long getId(){
	return this.id;
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

    public void setDireccionx(int direccionx){
	this.direccionx= direccionx;
    }

    public void setDirecciony(int direcciony){
	this.direcciony= direcciony;
    }

    @Override
    public boolean equals(Object obj) {
        Locker locker = (Locker) obj;
        return locker.getId() == this.getId() &&
            locker.getDireccionx() == this.getDireccionx() &&
            locker.getDirecciony() == this.getDirecciony();
    }

    @Override
    public int hashCode() {
	return Objects.hash(direccionx, direcciony);
    }
}
