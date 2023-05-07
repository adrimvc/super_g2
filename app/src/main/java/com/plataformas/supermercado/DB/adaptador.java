package com.plataformas.supermercado.DB;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.plataformas.supermercado.ModificarProducto;
import com.plataformas.supermercado.NuevoProducto;
import com.plataformas.supermercado.R;
import com.plataformas.supermercado.modelo.Producto;

public class adaptador extends BaseAdapter {

    private ArrayList<Producto> lista;
    private Producto p;
    private Context contexto;
    private int R_layout_IdView;
    private Activity a;

    private DBmanager _db;

    public adaptador(Activity a, ArrayList<Producto> lista, DBmanager db ) {
        super();
        this.lista = lista;
        this.a=a;
        this._db=db;
        this.contexto = a.getApplicationContext();
    }

    public adaptador(Context contexto, int R_layout_IdView, ArrayList<Producto> lista) {
        super();
        this.contexto = contexto;
        this.lista = lista;
        this.R_layout_IdView = R_layout_IdView;
    }

    @Override
    public View getView(int posicion, View view, ViewGroup pariente) {
        View v= view;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.item_prod, null);
        }

        p=lista.get(posicion);
        TextView nombre = (TextView) v.findViewById(R.id.textView_nombre);
        nombre.setText(p.getNombre());

        TextView precio = (TextView) v.findViewById(R.id.textView_precio);
        precio.setText(""+p.getPrecio());

        ImageButton editar = (ImageButton) v.findViewById(R.id.btnEditar);

        ImageButton eliminar = (ImageButton) v.findViewById(R.id.btnEliminar);

        editar.setTag(posicion);
        eliminar.setTag(posicion);
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p=lista.get((Integer) v.getTag());
                Intent intent = new Intent(contexto, ModificarProducto.class);
                intent.putExtra("idProducto", p.getId());
                contexto.startActivity(intent);
            }
        });
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return v;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Producto getItem(int posicion) {
        p= lista.get(posicion);
        return p;
    }

    @Override
    public long getItemId(int posicion) {
        p= lista.get(posicion);
        return p.getId();
    }



}
