package com.example.examen.db;

import android.provider.BaseColumns;

public final class MyAppContract {

	public MyAppContract() {
		// this empty constructor prevent accidentally instantiating the contract class
	}
	
	public static abstract class Libreria implements BaseColumns {
		public static final String TABLE_NAME = "biblioteca";
		public static final String COLUMN_NAME_TITULO = "titulo";
		public static final String COLUMN_NAME_AUTHOR = "author";
		public static final String COLUMN_NAME_ISBN = "isbn";
	}
}
