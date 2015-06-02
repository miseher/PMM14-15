package com.example.miguel.proyecto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class DataBaseAdapter {
    static final String DATABASE_NAME = "gestion";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table " + "LOGIN" +
            "( " + "ID" + " integer primary key autoincrement," + "USERNAME  text,PASSWORD text); ";

    static final String DATABASE_CREATE2 = "create table " + "PARTES" +
            "( " + "ParteID" + " integer primary key," +"FOREIGN KEY (Login_ID)REFERENCES DATABASE_CREATE(ID),"+ "AULA  text,DESCRIPCION text); ";

     //FOREIGN KEY (Login_ID)REFERENCES DATABASE_CREATE(ID)
    // instancia de SQLiteDatabase
    public SQLiteDatabase db;
    // Context
    private final Context context;

    // Database open/upgrade helper
    private DataBaseHelper dbHelper;

    public DataBaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public DataBaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    //METODOS PARA LA GESTION DEL LOGIN

    public void insertarEntrada(String userName, String password) {
        ContentValues newValues = new ContentValues();
        // Assigna Valores
        newValues.put("USERNAME", userName);
        newValues.put("PASSWORD", password);

        // Inserta la fila en la tabla
        db.insert("LOGIN", null, newValues);


    }

    public int borrarEntrada(String UserName) {
        //String id=String.valueOf(ID);
        String where = "USERNAME=?";
        int numberOFEntriesDeleted = db.delete("LOGIN", where, new String[]{UserName});

        return numberOFEntriesDeleted;
    }

    public String obtenerEntrada(String userName) {
        //Devuelve la contraseña del usuario
        Cursor cursor = db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if (cursor.getCount() < 1) // Nombre no existe
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }

    public void actualizaEntrada(String userName, String password) {
        // Actualiza el contenido de las filas
        ContentValues valoresActualizados = new ContentValues();
        // Asigna Valores
        valoresActualizados.put("USERNAME", userName);
        valoresActualizados.put("PASSWORD", password);

        String where = "USERNAME = ?";
        db.update("LOGIN", valoresActualizados, where, new String[]{userName});
    }

    //METODOS PARA LA GESTION DE LOS PARTES


    public long InsertarDatos(String strParteID, String strAula, String strDescripcion) {
        // TODO Auto-generated method stub

        try {
            db = dbHelper.getWritableDatabase();


            //A put() method initializes values to be inserted into the database

            ContentValues Val = new ContentValues();
            Val.put("ParteID", strParteID);
            Val.put("AULA", strAula);
            Val.put("DESCRIPCION", strDescripcion);

            long rows = db.insert("PARTES", null, Val);

            db.close();
            return rows;

        } catch (Exception e) {
            return -1;
        }
    }
    public long borrarIncidencia(String strParteID) {
        // TODO Auto-generated method stub

        try {

            db = dbHelper.getWritableDatabase();



            long rows = db.delete("PARTES", "ParteID = ?",
                    new String[] { String.valueOf(strParteID) });

            db.close();
            return rows; // return rows deleted.

        } catch (Exception e) {
            return -1;
        }

    }



    public String[] SeleccionarDatos(String strParteID) {
        // TODO Auto-generated method stub

        try {
            String arrData[] = null;

            db = dbHelper.getReadableDatabase();

            Cursor cursor = db.query("PARTES", new String[] { "*" },
                    "ParteID=?",
                    new String[] { String.valueOf(strParteID) }, null, null, null, null);

            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getColumnCount()];

                    arrData[0] = cursor.getString(0);
                    arrData[1] = cursor.getString(1);
                    arrData[2] = cursor.getString(2);
                }
            }
            cursor.close();
            db.close();
            return arrData;

        } catch (Exception e) {
            return null;
        }

    }


    public ArrayList<HashMap<String, String>> seleccionarTodos() {
        // TODO Auto-generated method stub
        try {

            ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;


            db = dbHelper.getReadableDatabase();

            String strSQL = "SELECT  * FROM " + "PARTES";
            Cursor cursor = db.rawQuery(strSQL, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        map = new HashMap<String, String>();
                        map.put("ParteID", cursor.getString(0));
                        map.put("AULA", cursor.getString(1));
                        map.put("DESCRIPCION", cursor.getString(2)); //Tel
                        MyArrList.add(map);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            db.close();
            return MyArrList;

        } catch (Exception e) {
            return null;
        }


    }
    public long actualizarDatos(String strParteID, String strAula, String strDesc) {
        // TODO Auto-generated method stub

        try {


            db = dbHelper.getWritableDatabase();// Write Data


            ContentValues Val = new ContentValues();
            Val.put("AULA", strAula);
            Val.put("DESCRIPCION", strDesc);

            long rows = db.update("PARTES", Val, " ParteID = ?",
                    new String[] { String.valueOf(strParteID) });

            db.close();
            return rows; // return rows updated.

        } catch (Exception e) {
            return -1;
        }

    }



}


