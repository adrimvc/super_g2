package com.plataformas.supermercado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.plataformas.supermercado.DB.DBmanager;
import com.plataformas.supermercado.modelo.Producto;

import java.util.ArrayList;

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

        ArrayList<Producto> listadoProductos=dBmanager.leerProductos();

        if (listadoProductos.size()<4){
            //INSERCION DIRECTA
            dBmanager.insertarProducto(1,"Leche",6300);
            dBmanager.insertarProducto(2,"Galletas Saltín",3500);
            dBmanager.insertarProducto(3,"Chocolate",5500);

            //INSERCION MODELO
            dBmanager.insertarModeloProducto(new Producto(4,"Pan Tajado",4500));
        }else{
            //limpiar todos los productos insertados en las pruebas!
            //dBmanager.limpiarProductos();
        }

        dBmanager.close();

    }
}