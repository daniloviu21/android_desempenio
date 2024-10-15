package com.example.desempeo_1p;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class agregarluchador extends AppCompatActivity {

    private EditText editTextNombre, editTextFecha, editTextPlaneta, editTextUniverso, editTextNivel;
    private List<Luchador> luchadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregarluchador);

        editTextNombre = findViewById(R.id.editTextText);
        editTextFecha = findViewById(R.id.editTextDate);
        editTextPlaneta = findViewById(R.id.editTextText2);
        editTextUniverso = findViewById(R.id.editTextNumber);
        editTextNivel = findViewById(R.id.editTextNumber2);

        luchadores = cargarLuchadores();
    }

    private List<Luchador> cargarLuchadores() {
        SharedPreferences sharedPreferences = getSharedPreferences("Luchadores", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("luchadoresList", null);
        Type type = new TypeToken<List<Luchador>>() {}.getType();
        List<Luchador> luchadores = gson.fromJson(json, type);

        return luchadores != null ? luchadores : new ArrayList<>();
    }

    public void agregarLuchador(View view) {
        String nombre = editTextNombre.getText().toString();
        String fecha = editTextFecha.getText().toString();
        String planeta = editTextPlaneta.getText().toString();
        String universo = editTextUniverso.getText().toString();
        String nivel = editTextNivel.getText().toString();

        Luchador nuevoLuchador = new Luchador(nombre, fecha, planeta, universo, nivel);
        luchadores.add(nuevoLuchador);

        guardarLuchadores();

        Intent intent = new Intent(agregarluchador.this, MainActivity.class);
        startActivity(intent);
    }

    private void guardarLuchadores() {
        SharedPreferences sharedPreferences = getSharedPreferences("Luchadores", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(luchadores);
        editor.putString("luchadoresList", json);
        editor.apply();
    }

    public void regresar(View view) {
        Intent intent = new Intent(agregarluchador.this, MainActivity.class);
        startActivity(intent);
    }
}
