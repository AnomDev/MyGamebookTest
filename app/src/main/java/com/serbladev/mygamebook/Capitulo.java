package com.serbladev.mygamebook;

import java.util.ArrayList;

public class Capitulo {

    private int numcapitulo;
    private ArrayList<String> textoOpciones = new ArrayList<>();
    private ArrayList<Integer> opciones = new ArrayList<>();
    private ArrayList<Objeto> objetosEncontrados = new ArrayList<>();

    public Capitulo(int numcapitulo, ArrayList<String> textoOpciones, ArrayList<Integer> opciones, ArrayList<Objeto> objetosEncontrados) {
        this.numcapitulo = numcapitulo;
        this.textoOpciones = textoOpciones;
        this.opciones = opciones;
        this.objetosEncontrados = objetosEncontrados;
    }

    public ArrayList<String> getTextoOpciones() {
        return textoOpciones;
    }

    public void setTextoOpciones(ArrayList<String> textoOpciones) {
        this.textoOpciones = textoOpciones;
    }

    public ArrayList<Integer> getOpciones() {
        return opciones;
    }

    public void setOpciones(ArrayList<Integer> opciones) {
        this.opciones = opciones;
    }

    public int getNumcapitulo() {
        return numcapitulo;
    }

    public void setNumcapitulo(int numcapitulo) {
        this.numcapitulo = numcapitulo;
    }

    public ArrayList<Objeto> getObjetosEncontrados() {
        return objetosEncontrados;
    }

    public void setObjetosEncontrados(ArrayList<Objeto> objetosEncontrados) {
        this.objetosEncontrados = objetosEncontrados;
    }
}


