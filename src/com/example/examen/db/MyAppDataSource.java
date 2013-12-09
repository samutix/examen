package com.example.examen.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.examen.db.MyAppContract.Libreria;
import com.example.examen.model.Libro;

public class MyAppDataSource {

	private MyAppDbHelper dbHelper;
	private SQLiteDatabase db;
	
	private String[] allColumns = {
		    Libreria._ID,
		    Libreria.COLUMN_NAME_TITULO,
		    Libreria.COLUMN_NAME_AUTHOR,
		    Libreria.COLUMN_NAME_ISBN
		    };

	public MyAppDataSource(Context context) {
		this.dbHelper = new MyAppDbHelper(context);
	}
	
	public void open() throws SQLException {
		this.db = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}

	public Libro createPerson(String tituloName, String authorName, String isbnName) {
		ContentValues values = new ContentValues();
		values.put(Libreria.COLUMN_NAME_TITULO, tituloName);
		values.put(Libreria.COLUMN_NAME_AUTHOR, authorName);
		values.put(Libreria.COLUMN_NAME_ISBN, isbnName);
		
	    long insertId = db.insert(Libreria.TABLE_NAME, null, values);
	    
	    Cursor c = db.query(
	    		Libreria.TABLE_NAME,
	    		this.allColumns, Libreria._ID + " = " + insertId, 
	    		null,
	    		null, 
	    		null, 
	    		null
	    	);
	    c.moveToFirst();
	    
	    Libro l = cursorToLibro(c);
	    c.close();
	    
	    return l;
	}
	
	public Libro updateLibro(Libro l, String tituloName, String authorName, String isbnName) {
		ContentValues values = new ContentValues();
		values.put(Libreria.COLUMN_NAME_TITULO, tituloName);
		values.put(Libreria.COLUMN_NAME_AUTHOR, authorName);
		values.put(Libreria.COLUMN_NAME_ISBN, isbnName);
		
	    db.update(Libreria.TABLE_NAME, values, Libreria._ID + " = " + l.getId(), null);
	    
	    l.setTitulo(tituloName);
	    l.setAuthor(authorName);
	    l.setIsbn(isbnName);
	    
	    return l;
	}
	
	public List<Libro> getLibreria() {
	    List<Libro> libreria = new ArrayList<Libro>();
	    
	    String sortOrder = Libreria.COLUMN_NAME_TITULO + " DESC";
	    
	    Cursor c = db.query(
			    Libreria.TABLE_NAME,	// The table to query
			    this.allColumns,			// The columns to return
			    null,				// The columns for the WHERE clause
			    null,				// The values for the WHERE clause
			    null,				// don't group the rows
			    null,				// don't filter by row groups
			    sortOrder			// The sort order
		    );

	    c.moveToFirst();
	    while (!c.isAfterLast()) {
	      Libro l = cursorToLibro(c);
	      libreria.add(l);
	      c.moveToNext();
	    }
	    
	    // make sure to close the cursor
	    c.close();
	    
	    return libreria;
	}
	
	public Libro deleteLibro(Libro l) {
	    long id = l.getId();
	    db.delete(Libreria.TABLE_NAME, Libreria._ID + " = " + id, null);
	    l.setId(0);
	    return l;
	}

	
	private Libro cursorToLibro(Cursor cursor) {
		Libro l = new Libro();
	    l.setId(cursor.getLong(0));
	    l.setTitulo(cursor.getString(1));
	    l.setAuthor(cursor.getString(2));
	    l.setIsbn(cursor.getString(3));
	    return l;
	}
}
