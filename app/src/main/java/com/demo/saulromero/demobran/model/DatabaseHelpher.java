package com.demo.saulromero.demobran.model;

/**
 * Created by Admincetes on 11/09/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by PRABHU on 11/12/2015.
 */
public class DatabaseHelpher extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="huasteapp";
    private static final int DATABASE_VERSION = 3;
    private static final String NOTIFICATION_TABLE = "notification";
    private static final String ITINERARIO_TABLE = "itinerario";
    private static final String NOT_TABLE = "create table "+ NOTIFICATION_TABLE  +"(id_notifications INTEGER PRIMARY KEY AUTOINCREMENT,titulo TEXT ,data TEXT,url TEXT,descuento TEXT)";
    private static final String EVT_TABLE = "create table "+ ITINERARIO_TABLE  +"(id_itinerario INTEGER PRIMARY KEY AUTOINCREMENT,evento TEXT ,fecha DATETIME,hora_inicio TEXT,hora_final TEXT,descripcion TEXT)";

    Context context;

    public DatabaseHelpher(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(NOT_TABLE);
        db.execSQL(EVT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + NOTIFICATION_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ITINERARIO_TABLE);

        // Create tables again
        onCreate(db);
    }
    /* Insert into database*/
    public void insertIntoNotiDB(String tabla,String titulo, String data, String url, String descuento){
        Log.d("insert", "before insert");

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        //values.put("name", name);
        values.put("titulo", !titulo.equals("")?titulo:"");
        values.put("data", !data.equals("")?data:"");
        values.put("url", !url.equals("")?url:"");
        values.put("descuento", !descuento.equals("")?descuento:"");

        // 3. insert
        db.insert(tabla, null, values);
        // 4. close
        db.close();
        //Toast.makeText(context, "insert value", Toast.LENGTH_LONG);
        Log.i("insert into DB", "After insert");
    }

    /* Insert into database*/
    public void insertIntoItiDB(String tabla,String evento,String fecha, String hora_inicio, String hora_final, String descripcion){
        Log.d("insert", "before insert");

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        //values.put("name", name);
        values.put("evento", !evento.equals("")?evento:"");
        values.put("fecha", !fecha.equals("")?fecha:"");
        values.put("hora_inicio", !hora_inicio.equals("")?hora_inicio:"");
        values.put("hora_final", !hora_final.equals("")?hora_final:"");
        values.put("descripcion", !descripcion.equals("")?descripcion:"");

        // 3. insert
        db.insert(tabla, null, values);
        // 4. close
        db.close();
        //Toast.makeText(context, "insert value", Toast.LENGTH_LONG);
        Log.i("insert into DB", "After insert");
    }



    /*delete a row from database*/

    public void deleteARow(String id,String tabla, String campo ){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(tabla, campo + " = ?", new String[] { id });
        db.close();
    }
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

}
