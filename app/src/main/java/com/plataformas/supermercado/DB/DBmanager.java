package com.plataformas.supermercado.DB;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.plataformas.supermercado.modelo.Producto;

import java.util.ArrayList;

public class DBmanager {

    public static final String TABLA_PRODUCTOS = "producto";
    private static final String ID_PRODUCTO = "id";
    private static final String NOMBRE_PRODUCTO = "nombre";
    private static final String PRECIO = "precio";

    ArrayList<Producto> lista;

    public static final String PRODUCTO_CREATE =
            "create table producto (id integer not null, nombre text not null, precio integer not null);";

    public DBconexion _conexion;
    public SQLiteDatabase _db;

    public DBmanager(Context context) {
        _conexion = new DBconexion(context);
        Log.d("constructor", "DBmanager constructor");
    }

    public DBmanager open() throws SQLException {
        Log.d("open", "DBmanager open");
        _db = _conexion.getWritableDatabase();

        return this;
    }

    public void close() {
        _conexion.close();
    }

    public ArrayList<Producto> leerProductos () {
        //lista.clear();
        Cursor cursor=_db.rawQuery("select * from producto", null);
        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                lista.add(new Producto( cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2)
                ));
            }while (cursor.moveToNext());
        }
        return lista;
    }

    public void insertarProducto(int id, String nombre, int precio) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_PRODUCTO, id);
        contentValues.put(NOMBRE_PRODUCTO, nombre);
        contentValues.put(PRECIO, precio);

        this._db.insert(TABLA_PRODUCTOS, null, contentValues);
        Log.d("insert", "Registro insertado " + nombre);
    }

    public boolean insertarModeloProducto(Producto p) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_PRODUCTO, p.getId());
        contentValues.put(NOMBRE_PRODUCTO, p.getNombre());
        contentValues.put(PRECIO, p.getPrecio());

        long resultado = _db.insert(TABLA_PRODUCTOS, null, contentValues);

        if (resultado == -1) {
            Log.d("insert", "Error al insertar " + p.getNombre());
            return false;
        }
        Log.d("insert", "Registro insertado " + p.getNombre());
        return true;
    }

    public void actualizarModeloProducto(Producto p) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NOMBRE_PRODUCTO, p.getNombre());
        contentValues.put(PRECIO, p.getPrecio());

        SQLiteDatabase db = this._db;
        db.update(TABLA_PRODUCTOS, contentValues, ID_PRODUCTO + "=?", new String[]{String.valueOf(p.getId())});
        db.close();
    }

    @SuppressLint("Range")
    public Producto obtenerProductoPorId(int idProducto) {
        Producto producto = null;
        SQLiteDatabase db = this._db;
        String selectQuery = "SELECT * FROM " + TABLA_PRODUCTOS + " WHERE " + ID_PRODUCTO + " = " + idProducto;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            producto = new Producto(cursor.getInt(cursor.getColumnIndex(ID_PRODUCTO)),
                    cursor.getString(cursor.getColumnIndex(NOMBRE_PRODUCTO)),
                    cursor.getInt(cursor.getColumnIndex(PRECIO)));
        }
        cursor.close();
        db.close();
        return producto;
    }
}