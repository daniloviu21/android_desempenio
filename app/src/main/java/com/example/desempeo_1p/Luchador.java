package com.example.desempeo_1p;

public class Luchador {
    private String nombre;
    private String fecha;
    private String planeta;
    private String universo;
    private String nivel;

    public Luchador(String nombre, String fecha, String planeta, String universo, String nivel) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.planeta = planeta;
        this.universo = universo;
        this.nivel = nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public String getPlaneta() {
        return planeta;
    }

    public String getUniverso() {
        return universo;
    }

    public String getNivel() {
        return nivel;
    }
}