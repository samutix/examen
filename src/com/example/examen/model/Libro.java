package com.example.examen.model;
import java.io.Serializable;

public class Libro implements Serializable {
	
	public static final long serialVersionUID = 7526472295622776147L;


	
	private long id;
	private String titulo;
	private String author;
	private String isbn;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	
}
