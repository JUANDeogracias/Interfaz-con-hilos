package org.example;

public class Usuario {

    private int id;
    private String nombre;

    public Usuario(int id, String nombre, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String contrasena;


}
