package com.example.desempeo_1p;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.List;

public class consultarluchadores extends AppCompatActivity {

    private TextView textViewLuchadores;
    private ImageView imageView;
    private List<Luchador> luchadores;
    private LuchadorManager luchadorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_consultarluchadores);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textViewLuchadores = findViewById(R.id.textViewLuchadores);
        imageView = findViewById(R.id.imageView);

        SharedPreferences sharedPreferences = getSharedPreferences("Luchadores", MODE_PRIVATE);
        luchadorManager = new LuchadorManager(sharedPreferences);

        // Inicializar luchadores
        luchadores = luchadorManager.cargarLuchadores();
        if (luchadores.isEmpty()) {
            luchadores = luchadorManager.agregarLuchadoresIniciales();
            luchadorManager.guardarLuchadores(luchadores); // Guardar luchadores iniciales
        }

        Log.d("Consultarluchadores", "Luchadores loaded: " + luchadores.size());

    }

    private void mostrarLuchadores() {
        StringBuilder infoLuchadores = new StringBuilder();
        for (Luchador luchador : luchadores) {
            infoLuchadores.append("Nombre: ").append(luchador.getNombre()).append("\n")
                          .append("Fecha de Nacimiento: ").append(luchador.getFecha()).append("\n")
                          .append("Planeta: ").append(luchador.getPlaneta()).append("\n")
                          .append("Universo: ").append(luchador.getUniverso()).append("\n")
                          .append("Nivel: ").append(luchador.getNivel()).append("\n\n");
        }
        textViewLuchadores.setText(infoLuchadores.toString());
    }

    public void consultarLuchadores(View view) {
        mostrarLuchadores();
        Log.d("Consultarluchadores", "Consultar button clicked");
    }

    public void regresar(View view) {
        Intent intent = new Intent(consultarluchadores.this, MainActivity.class);
        startActivity(intent);
    }
}
