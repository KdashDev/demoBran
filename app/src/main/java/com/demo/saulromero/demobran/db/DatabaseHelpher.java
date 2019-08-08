package com.demo.saulromero.demobran.db;

/**
 * Created by Admincetes on 11/09/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.demo.saulromero.demobran.model.EmpleadoModel;

import java.util.ArrayList;

/**
 * Created by PRABHU on 11/12/2015.
 */
public class DatabaseHelpher extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="demo";
    private static final int DATABASE_VERSION = 1;
    private static final String Empleados_TABLE = "empleados";
    private static final String Emp_TABLE = "create table "+ Empleados_TABLE +"(id_emp INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT ,fecha DATETIME)";

    Context context;

    public DatabaseHelpher(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Emp_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Empleados_TABLE);

        // Create tables again
        onCreate(db);
    }
    /* Insert into database*/

    /* Insert into database*/
    public void insertIntoEmpDB(String nombre,String puesto, String fecha){
        Log.d("insert", "before insert");

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        //values.put("name", name);
        values.put("nombre", !nombre.equals("")?nombre:"");
        values.put("fecha", !fecha.equals("")?fecha:"");
        values.put("puesto", !puesto.equals("")?puesto:"");


        // 3. insert
        db.insert(Empleados_TABLE, null, values);
        // 4. close
        db.close();
        //Toast.makeText(context, "insert value", Toast.LENGTH_LONG);
        Log.i("insert into DB", "After insert");
    }





    /* Retrive  data from database */
    public ArrayList<EmpleadoModel> getDataNotiFromDB(String q, String tabla) {

        String query = q;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<EmpleadoModel> modelList = new ArrayList<EmpleadoModel>();
        if (cursor.moveToFirst()) {
            do {
                EmpleadoModel model = new EmpleadoModel();
                model.setNombre(cursor.getString(0));
                model.setFecha(cursor.getString(1));
                model.setPuesto(cursor.getString(2));
                modelList.add(model);

            } while (cursor.moveToNext());


        }
        return modelList;
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
