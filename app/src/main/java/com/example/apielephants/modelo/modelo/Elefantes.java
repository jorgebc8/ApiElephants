package com.example.apielephants.modelo.modelo;

import java.io.Serializable;

public class Elefantes implements Serializable{


    private String nombre;
    private String especie;
    private String imagenId;


    public Elefantes(String nombre,String especie,String imagenId) {
        this.nombre = nombre;
        this.especie = especie;
        this.imagenId = imagenId;
    }

    public Elefantes() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }


    public String getImagenId() {
        return imagenId;
    }

    public void setImagenId(String imagenId) {
        this.imagenId = imagenId;
    }
}
