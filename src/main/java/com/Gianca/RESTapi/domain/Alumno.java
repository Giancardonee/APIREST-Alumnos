package com.Gianca.RESTapi.domain;

public class Alumno {
    private int id;
    private String nombre;
    private int edad;
    private String curso;
    private String email;

    public Alumno(int id, String curso, int edad, String nombre,String email) {
        this.id = id;
        this.curso = curso;
        this.edad = edad;
        this.nombre = nombre;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
