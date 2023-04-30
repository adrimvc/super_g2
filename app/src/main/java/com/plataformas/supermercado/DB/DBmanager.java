package com.plataformas.supermercado.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import android.util.Log;

import com.plataformas.supermercado.modelo.Producto;

public class DBmanager {

    public static final String TABLA_PRODUCTOS="producto";
    private static final String ID_PRODUCTO="id";
    private static final String NOMBRE_PRODUCTO="nombre";
    private static final String PRECIO="precio";

    public static final String PRODUCTO_CREATE=
            "create table producto (id integer not null, nombre text not null, precio integer not null);";

    public DBconexion _conexion;
    public SQLiteDatabase _db;

    public DBmanager(Context context) {
        _conexion = new DBconexion(context);
        Log.d("constructor","DBmanager constructor");
    }

    public DBmanager open() throws SQLException {
        Log.d("open","DBmanager open");
        _db = _conexion.getWritableDatabase();

        return this;
    }

    public void close() {
        _conexion.close();
    }

    public void insertarProducto(int id, String nombre, int precio) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_PRODUCTO,id);
        contentValues.put(NOMBRE_PRODUCTO,nombre);
        contentValues.put(PRECIO,precio);

        this._db.insert(TABLA_PRODUCTOS,null,contentValues);
        Log.d("insert","Registro insertado "+nombre);
    }

    public boolean insertarModeloProducto(Producto p) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_PRODUCTO,p.getId());
        contentValues.put(NOMBRE_PRODUCTO,p.getNombre());
        contentValues.put(PRECIO,p.getPrecio());

        long resultado = _db.insert(TABLA_PRODUCTOS,null,contentValues);

        if(resultado==-1){
            Log.d("insert","Error al insertar "+p.getNombre());
            return false;
        }
        Log.d("insert","Registro insertado "+p.getNombre());
        return true;
    }

}
