package com.example.examen.db;

import com.example.examen.db.MyAppContract.Libreria;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MyAppDbHelper extends SQLiteOpenHelper {

	// If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "App.db";
    
//  private static final String NULL_TYPE = " NULL";
	private static final String TEXT_TYPE = " TEXT";
//	private static final String INTEGER_TYPE = " INTEGER";
//	private static final String REAL_TYPE = " REAL";
//	private static final String BLOB_TYPE = " BLOB";
	
	private static final String COMMA_SEPARATOR = ",";
	
	private static final String SQL_CREATE_PEOPLE =
		    "CREATE TABLE " + Libreria.TABLE_NAME + " (" +
		    Libreria._ID + " INTEGER PRIMARY KEY," +
		    Libreria.COLUMN_NAME_TITULO + TEXT_TYPE + COMMA_SEPARATOR +
		    Libreria.COLUMN_NAME_AUTHOR + TEXT_TYPE + COMMA_SEPARATOR +
		    Libreria.COLUMN_NAME_ISBN + TEXT_TYPE +
		    " )";
	
	private static final String SQL_DROP_PEOPLE =
		    "DROP TABLE IF EXISTS " + Libreria.TABLE_NAME;
    
    public MyAppDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PEOPLE);
    }
    
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	Log.w(
    			MyAppDbHelper.class.getName(),
    			"Actualización de la base de datos de la versión " + oldVersion + " a " + newVersion + ", que destruirá todos los datos antiguos"
    	);

    	db.execSQL(SQL_DROP_PEOPLE);
    	onCreate(db);
    }
    
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
