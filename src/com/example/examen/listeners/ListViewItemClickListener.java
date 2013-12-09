package com.example.examen.listeners;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.example.examen.APP2Activity;
import com.example.examen.APP3Activity;
import com.example.examen.model.Libro;


public class ListViewItemClickListener implements AdapterView.OnItemClickListener {

	private Activity activity;
	
	public ListViewItemClickListener(Activity activity) {
		this.activity = activity;
	}
	
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Libro l = (Libro) parent.getItemAtPosition(position);
		
		if(l != null)
		{
			Intent i = new Intent(this.activity, APP2Activity.class);
			i.putExtra("libro", l);
			this.activity.startActivityForResult(i, APP3Activity.REQUEST_CODE_ADD_LIBRO);			
		}
	}
}
