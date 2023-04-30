package com.plataformas.supermercado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.plataformas.supermercado.DB.DBmanager;
import com.plataformas.supermercado.modelo.Producto;

public class ModificarProducto extends AppCompatActivity {

    EditText nombreEdittext;
    EditText precioEdittext;
    Button guardarButton;

    Producto producto;

    private DBmanager dBmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_producto);

        nombreEdittext = findViewById(R.id.edittextonombre);
        precioEdittext = findViewById(R.id.edittextoprecio);
        guardarButton = findViewById(R.id.btnadd);

        // Instanciar DBmanager
        dBmanager = new DBmanager(getApplicationContext());
        dBmanager.open();

        int idProducto = getIntent().getIntExtra("idProducto", -1);
        if (idProducto != -1) {
            // Obtener producto por ID
            producto = dBmanager.obtenerProductoPorId(idProducto);
        } else {
            // Crear nuevo producto si no se ha pasado un ID válido
            producto = new Producto();
        }

        nombreEdittext.setText(producto.getNombre());
        precioEdittext.setText(String.valueOf(producto.getPrecio()));

        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                producto.setNombre(nombreEdittext.getText().toString());
                producto.setPrecio(Integer.parseInt(precioEdittext.getText().toString()));

                dBmanager.actualizarModeloProducto(producto);

                // Cerrar DBmanager
                dBmanager.close();

                finish();
            }
        });
    }
}
