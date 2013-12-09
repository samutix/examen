package com.example.examen;


import com.example.examen.db.MyAppDataSource;
import com.example.examen.model.Libro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class APP2Activity extends Activity {
	private MyAppDataSource ds;
	private Libro libroToUpdate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_app2);
		setupActionBar();
		
		ds = new MyAppDataSource(this);
	    ds.open();
	    
	    Intent i = this.getIntent();
	    
	    if(i.hasExtra(APP3Activity.EXTRA_LIBRO))
	    {
	    	Libro l = (Libro) i.getSerializableExtra(APP3Activity.EXTRA_LIBRO);
	    	
	    	EditText tituloField = (EditText) this.findViewById(R.id.tituloField);
	    	tituloField.setText(l.getTitulo());
			
			EditText authorField = (EditText) this.findViewById(R.id.authorField);
			authorField.setText(l.getAuthor());
			
			EditText isbnField = (EditText) this.findViewById(R.id.isbnField);
			isbnField.setText(l.getIsbn());
			
			Button guardarButton = (Button) this.findViewById(R.id.guardarButton);
			guardarButton.setText("Actualizado");
			
			Button deleteButton = (Button) this.findViewById(R.id.deleteButton);
			deleteButton.setVisibility(Button.VISIBLE);
			
			this.setTitle("Actualizar Libro");
			
			this.libroToUpdate = l;
	    }
	    else
	    {
	    	Button guardarButton = (Button) this.findViewById(R.id.guardarButton);
	    	guardarButton.setText("Create");
	    	
	    	Button deleteButton = (Button) this.findViewById(R.id.deleteButton);
	    	deleteButton.setVisibility(Button.VISIBLE);
	    	
	    	this.setTitle("Create Person");
	    	
	    	this.libroToUpdate = null;
	    }
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_libro, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onSaveButtonClicked(View view) {
		EditText tituloField = (EditText) this.findViewById(R.id.tituloField);
		String tituloName = tituloField.getText().toString();
		
		EditText authorField = (EditText) this.findViewById(R.id.authorField);
		String authorName = authorField.getText().toString();
		
		EditText isbnField = (EditText) this.findViewById(R.id.isbnField);
		String isbnName = isbnField.getText().toString();
		
		if(tituloName.isEmpty() || authorName.isEmpty() || isbnName.isEmpty())
		{
			Toast.makeText(this, "Complete el formulario antes de guardar", Toast.LENGTH_LONG).show();
			return;
		}
		
		Libro l = null;
		
		if(this.libroToUpdate != null)
		{
			l = ds.updateLibro(this.libroToUpdate, tituloName, authorName, isbnName);
		}
		else
		{
			l = ds.createPerson(tituloName, authorName, isbnName);
		}
		
		Intent i = new Intent();
		i.putExtra(APP3Activity.EXTRA_LIBRO, l);
		i.putExtra(APP3Activity.EXTRA_REMOVE, false);
		this.setResult(RESULT_OK, i);
		
		this.finish();
	}
	
	public void onDeleteButtonClicked(View view) {
		
		Libro l = ds.deleteLibro(this.libroToUpdate);
		
		Intent i = new Intent();
		i.putExtra(APP3Activity.EXTRA_LIBRO, l);
		i.putExtra(APP3Activity.EXTRA_REMOVE, true);
		this.setResult(RESULT_OK, i);
		
		this.finish();
	}
	
	@Override
	protected void onResume() {
		ds.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		ds.close();
		super.onPause();
	}
}