package com.plataformas.supermercado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
public class ModificarProducto extends AppCompatActivity {

    EditText nombreEdittext;
    EditText precioEdittext;
    Button guardarButton;

    Producto producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_producto);

        nombreEdittext = findViewById(R.id.editTextNombre);
        precioEdittext = findViewById(R.id.editTextPrecio);
        guardarButton = findViewById(R.id.guardarButton);


        int idProducto = getIntent().getIntExtra("idProducto", -1);
        if (idProducto != -1) {
            producto = DBmanager.obtenerProductoPorId(idProducto);
        }

        nombreEdittext.setText(producto.getNombre());
        precioEdittext.setText(String.valueOf(producto.getPrecio()));

        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                producto.setNombre(nombreEdittext.getText().toString());
                producto.setPrecio(Integer.parseInt(precioEdittext.getText().toString()));

                DBmanager.actualizarModeloProducto(producto);

                finish();
            }
        });
    }
}
