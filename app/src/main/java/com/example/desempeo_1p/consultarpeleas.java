package com.example.desempeo_1p;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class consultarpeleas extends AppCompatActivity {

    private List<Luchador> luchadores;
    private List<Pelea> peleas;
    private TextView textViewPeleas;
    private TextView textViewIndicativo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_consultarpeleas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textViewPeleas = findViewById(R.id.textViewPeleas);
        textViewIndicativo = findViewById(R.id.textViewIndicativo);
        luchadores = cargarLuchadores();
        peleas = new ArrayList<>();

        // Inicialmente, el TextView de peleas debe estar vacío
        textViewPeleas.setText(""); // Asegúrate de que no muestre nada al inicio
    }

    private List<Luchador> cargarLuchadores() {
        SharedPreferences sharedPreferences = getSharedPreferences("Luchadores", MODE_PRIVATE);
        LuchadorManager luchadorManager = new LuchadorManager(sharedPreferences);
        List<Luchador> luchadores = luchadorManager.cargarLuchadores();

        // Si no hay luchadores, agregar luchadores iniciales
        if (luchadores.isEmpty()) {
            luchadores = luchadorManager.agregarLuchadoresIniciales();
            luchadorManager.guardarLuchadores(luchadores);
        }

        return luchadores;
    }

    public void regresar(View view) {
        Intent intent = new Intent(consultarpeleas.this, MainActivity.class);
        startActivity(intent);
    }

    public void generarPeleas(View view) {
        if (luchadores.size() < 2) {
            Toast.makeText(this, "Se necesitan al menos 2 luchadores para generar peleas.", Toast.LENGTH_SHORT).show();
            return;
        }

        peleas.clear(); // Limpiar peleas anteriores
        Random random = new Random();
        for (int i = 0; i < luchadores.size() - 1; i += 2) {
            String peleador1 = luchadores.get(i).getNombre();
            String peleador2 = luchadores.get(i + 1).getNombre();
            String fecha = "Fecha: " + "01/01/2023"; // Puedes cambiar esto a la fecha actual o a una fecha específica
            String horario = "Horario: " + (random.nextInt(24) + 1) + ":00"; // Generar un horario aleatorio

            Pelea pelea = new Pelea(peleador1, peleador2, fecha, horario);
            peleas.add(pelea);
        }

        mostrarPeleas();
        textViewIndicativo.setVisibility(View.GONE); // Ocultar el mensaje indicativo
    }

    private void mostrarPeleas() {
        StringBuilder infoPeleas = new StringBuilder();
        for (Pelea pelea : peleas) {
            infoPeleas.append(pelea.getPeleador1()).append(" vs ").append(pelea.getPeleador2()).append("\n")
                      .append(pelea.getFecha()).append("\n")
                      .append(pelea.getHorario()).append("\n\n");
        }
        textViewPeleas.setText(infoPeleas.toString());
    }
}
