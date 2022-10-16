package com.example.tp4_grupo6.entidades;

public class Articulo {
    private Integer id;
    private Categoria categoria;
    private String nombre;
    private Integer stock;

    public Articulo(){}
    public Articulo(Integer id, Categoria categoria, String nombre, int stock) {
        this.id = id;
        this.categoria = categoria;
        this.nombre = nombre;
        this.stock = stock;
    }
    public Articulo(Categoria categoria, String nombre, int stock) {
        this.categoria = categoria;
        this.nombre = nombre;
        this.stock = stock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Articulo: \n" +
                "id= " + id + "\n" +
                "categoria= " + categoria.toString() + "\n" +
                "nombre= '" + nombre + '\'' + "\n" +
                "stock= " + stock + "\n" ;
    }
}
