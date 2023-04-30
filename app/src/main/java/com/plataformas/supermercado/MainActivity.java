package com.plataformas.supermercado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.plataformas.supermercado.DB.DBmanager;
import com.plataformas.supermercado.modelo.Producto;

public class MainActivity extends AppCompatActivity {

    Button botonIngresar;

    private DBmanager dBmanager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonIngresar=findViewById(R.id.btnIngresar);
        botonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProductosActivity.class));
            }
        });


        //crear db inicial
        dBmanager = new DBmanager(getApplicationContext());
        dBmanager.open();

        //INSERCION DIRECTA
        dBmanager.insertarProducto(1,"Leche",6300);
        dBmanager.insertarProducto(2,"Galletas Saltín",3500);
        dBmanager.insertarProducto(3,"Chocolate",5500);

        //INSERCION MODELO
        dBmanager.insertarModeloProducto(new Producto(4,"Pan Tajado",4500));

        dBmanager.close();

    }
}