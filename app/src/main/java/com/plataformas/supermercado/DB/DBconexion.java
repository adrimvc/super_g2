package com.plataformas.supermercado.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBconexion extends SQLiteOpenHelper {

    private static final String name="dbsuper";
    private static final int version=1;

    public DBconexion(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DBmanager.PRODUCTO_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ DBmanager.TABLA_PRODUCTOS);
    }

}
