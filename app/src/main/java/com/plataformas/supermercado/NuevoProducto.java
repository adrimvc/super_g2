package com.plataformas.supermercado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.plataformas.supermercado.DB.DBmanager;
import com.plataformas.supermercado.modelo.Producto;

public class NuevoProducto extends AppCompatActivity {

    EditText idEdittext;
    EditText nombreEdittext;
    EditText precioEdittext;
    Button guardarButton;

    private DBmanager dBmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_producto);

        idEdittext = findViewById(R.id.edittextoid);
        nombreEdittext = findViewById(R.id.edittextonombre);
        precioEdittext = findViewById(R.id.edittextoprecio);
        guardarButton = findViewById(R.id.btnadd);

        // Instanciar DBmanager
        dBmanager = new DBmanager(getApplicationContext());

        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    Producto producto = new Producto(Integer.parseInt(idEdittext.getText().toString()), nombreEdittext.getText().toString(), Integer.parseInt(precioEdittext.getText().toString()));
                    dBmanager.open();
                    dBmanager.insertarModeloProducto(producto);
                    Toast.makeText(getApplication(), "Producto insertado!", Toast.LENGTH_SHORT).show();
                    // Cerrar DBmanager
                    dBmanager.close();

                }catch(Exception e){
                    Toast.makeText(getApplication(),"ERROR", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }
}
