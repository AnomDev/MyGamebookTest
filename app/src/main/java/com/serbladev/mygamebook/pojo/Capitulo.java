package com.serbladev.mygamebook.pojo;

import java.util.ArrayList;

public class Capitulo {

    private int numeroCapitulo;
    private ArrayList<String> textoElecciones = new ArrayList<>();
    private ArrayList<Integer> botonElecciones = new ArrayList<>();
    private ArrayList<Objeto> objetosEncontrados = new ArrayList<>();

    public Capitulo(int numeroCapitulo, ArrayList<String> textoElecciones, ArrayList<Integer> botonElecciones, ArrayList<Objeto> objetosEncontrados) {
        this.numeroCapitulo = numeroCapitulo;
        this.textoElecciones = textoElecciones;
        this.botonElecciones = botonElecciones;
        this.objetosEncontrados = objetosEncontrados;
    }

    public ArrayList<String> getTextoElecciones() {
        return textoElecciones;
    }

    public void setTextoElecciones(ArrayList<String> textoElecciones) {
        this.textoElecciones = textoElecciones;
    }

    public ArrayList<Integer> getBotonEleccion() {
        return botonElecciones;
    }

    public void setBotonElecciones(ArrayList<Integer> botonElecciones) {
        this.botonElecciones = botonElecciones;
    }

    public int getNumeroCapitulo() {
        return numeroCapitulo;
    }

    public void setNumeroCapitulo(int numeroCapitulo) {
        this.numeroCapitulo = numeroCapitulo;
    }

    public ArrayList<Objeto> getObjetosEncontrados() {
        return objetosEncontrados;
    }

    public void setObjetosEncontrados(ArrayList<Objeto> objetosEncontrados) {
        this.objetosEncontrados = objetosEncontrados;
    }
}


