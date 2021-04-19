package com.serbladev.mygamebook.pojo;

import android.graphics.Bitmap;

import java.io.Serializable;

// Necesitamos que implemente de "Serializable" para poder posteriormente convertido a JSON.
public class Objeto implements Serializable {
    private String nombre;
    private String descripcion;
    private Bitmap imagen;

    public Objeto(String nombre, String descripcion, Bitmap imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
