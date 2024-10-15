package com.example.desempeo_1p;

public class Pelea {
    private String peleador1;
    private String peleador2;
    private String fecha;
    private String horario;

    public Pelea(String peleador1, String peleador2, String fecha, String horario) {
        this.peleador1 = peleador1;
        this.peleador2 = peleador2;
        this.fecha = fecha;
        this.horario = horario;
    }

    public String getPeleador1() {
        return peleador1;
    }

    public String getPeleador2() {
        return peleador2;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHorario() {
        return horario;
    }
}