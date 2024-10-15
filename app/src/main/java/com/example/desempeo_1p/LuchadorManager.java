package com.example.desempeo_1p;

import android.content.SharedPreferences;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LuchadorManager {

    private SharedPreferences sharedPreferences;
    private Gson gson;

    public LuchadorManager(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        this.gson = new Gson();
    }

    public List<Luchador> cargarLuchadores() {
        String json = sharedPreferences.getString("luchadoresList", null);
        Type type = new TypeToken<List<Luchador>>() {}.getType();
        List<Luchador> luchadores = gson.fromJson(json, type);

        return luchadores != null ? luchadores : new ArrayList<>();
    }

    public void guardarLuchadores(List<Luchador> luchadores) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = gson.toJson(luchadores);
        editor.putString("luchadoresList", json);
        editor.apply();
    }

    public List<Luchador> agregarLuchadoresIniciales() {
        List<Luchador> luchadores = new ArrayList<>();
        luchadores.add(new Luchador("Goku", "16/04/1989", "Tierra", "Universo 7", "9000"));
        luchadores.add(new Luchador("Vegeta", "15/08/1989", "Planeta Vegeta", "Universo 7", "8500"));
        luchadores.add(new Luchador("Piccolo", "05/05/1989", "Namek", "Universo 7", "8000"));
        luchadores.add(new Luchador("Krillin", "29/10/1989", "Tierra", "Universo 7", "7000"));
        luchadores.add(new Luchador("Gohan", "18/05/1990", "Tierra", "Universo 7", "7500"));
        luchadores.add(new Luchador("Trunks", "10/06/1990", "Tierra", "Universo 7", "7200"));
        luchadores.add(new Luchador("Frieza", "01/01/1989", "Planeta Frieza", "Universo 7", "10000"));
        luchadores.add(new Luchador("Cell", "01/01/1990", "Tierra", "Universo 7", "9500"));

        return luchadores;
    }
}