package com.plataformas.supermercado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.plataformas.supermercado.DB.DBmanager;

public class NuevoProducto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_producto);
        TextView id=(TextView)findViewById(R.id.TextView3);
        TextView nombre=(TextView)findViewById(R.id.edittextonombre);
        TextView precio=(TextView)findViewById(R.id.edittextoprecio);
        Button btnInsert=(Button)findViewById(R.id.btnIngresar);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBmanager dbManager = new DBmanager(getApplicationContext());
                dbManager.open();
                Producto producto = new Producto(Integer.parseInt(id.getText().toString()), nombre.getText().toString(), Integer.parseInt(precio.getText().toString()));
                dbManager.insertarModeloProducto(producto);
                dbManager.close();
            }
        });
    }
}