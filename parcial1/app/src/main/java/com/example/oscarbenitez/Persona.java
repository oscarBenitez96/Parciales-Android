package com.example.oscarbenitez;

import java.io.Serializable;

public class Persona implements Serializable {

        private Integer id;
        private Integer imageUrl;
        private String nombre;
        private String categoria;
        private String detalle;
        private Integer year;


        public Persona(){}
        public Persona(Integer id, Integer imageUrl, String nombre, String categoria, String detalle, Integer year, Integer poster){
            this.id = id;
            this.imageUrl = imageUrl;
            this.nombre = nombre;
            this.categoria = categoria;
            this.detalle = detalle;
            this.year = year;

        }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Integer getImageUrl() { return imageUrl; }

    public void setImageUrl(Integer imageUrl) { this.imageUrl = imageUrl; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCategoria() { return categoria; }

    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getDetalle() { return detalle; }

    public void setDetalle(String detalle) { this.detalle = detalle; }

    public Integer getYear() { return year; }

    public void setYear(Integer year) { this.year = year; }
}
