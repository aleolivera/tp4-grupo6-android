package com.example.tp4_grupo6.entidades;

public class Categoria{
    private Integer id;
    private String descripcion;

    public Categoria() { }
    public Categoria(String descripcion) {
        this.descripcion = descripcion;
    }
    public Categoria(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}