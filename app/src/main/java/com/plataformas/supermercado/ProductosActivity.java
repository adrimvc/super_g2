package com.plataformas.supermercado;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.plataformas.supermercado.DB.DBmanager;
import com.plataformas.supermercado.DB.adaptador;
import com.plataformas.supermercado.modelo.Producto;

import java.util.ArrayList;


public class ProductosActivity extends AppCompatActivity {

    ImageButton botonNuevoProd;
    ListView listadoProd;

    private DBmanager dBmanager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        botonNuevoProd = findViewById(R.id.btnNuevoProd);

        //crear conexion
        dBmanager = new DBmanager(getApplicationContext());
        dBmanager.open();

        listadoProd =  (ListView) findViewById(R.id.listado);
        ArrayList<Producto> listadoProductos=dBmanager.leerProductos();
        listadoProd.setAdapter(new adaptador(this, listadoProductos, dBmanager));

        botonNuevoProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NuevoProducto.class));
            }
        });



    }
}