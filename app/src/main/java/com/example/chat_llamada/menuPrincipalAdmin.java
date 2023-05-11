package com.example.chat_llamada;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

public class menuPrincipalAdmin extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal_admin);

        // Declarar botones
        Button buttonProductos = findViewById(R.id.buttonProductosAdmin);
        Button buttonContacto = findViewById(R.id.buttonInventario);
        Button buttonCursos = findViewById(R.id.buttonCursosAdmin);
        Button buttonChat = findViewById(R.id.buttonChatAdmin);
        Button buttonVideollamada = findViewById(R.id.buttonVideollamadaAdmin);
        Button buttonFacturas = findViewById(R.id.buttonFacturasAdmin);
        // Agregar ClickListeners a los botones
        buttonProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Productos.class);
                startActivity(intent);
            }
        });

        buttonContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InventarioActivity.class);
                startActivity(intent);
            }
        });

        buttonCursos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cursos.class);
                startActivity(intent);
            }
        });

        buttonChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        buttonVideollamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Videollamada.class);
                startActivity(intent);
            }
        });
        buttonFacturas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Facturas.class);
                startActivity(intent);
            }
        });
    }
}
