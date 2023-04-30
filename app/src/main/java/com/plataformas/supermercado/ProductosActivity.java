package com.plataformas.supermercado;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.plataformas.supermercado.DB.DBmanager;
import com.plataformas.supermercado.modelo.Producto;

import java.util.ArrayList;


public class ProductosActivity extends AppCompatActivity {

    ImageButton botonNuevoProd;
    private DBmanager dBmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        botonNuevoProd = findViewById(R.id.btnNuevoProd);

        //crear conexion
        dBmanager = new DBmanager(getApplicationContext());
        dBmanager.open();
       // ArrayList<Producto> listadoProductos=dBmanager.leerProductos();

        botonNuevoProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NuevoProducto.class));
            }
        });



    }
}