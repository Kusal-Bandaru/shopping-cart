package com.shopping.cart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * Entity class for Book, subclass of Product
 * 
 * @author Kusal
 *
 */
@Entity
@PrimaryKeyJoinColumn(name = "product_id")
public class Book extends Product {

	/**
	 * Primary key id
	 */
	@Id
	@Column(name = "book_id")
	private int id;

	/**
	 * Genre of the book
	 */
	private String genre;

	/**
	 * Author of the book
	 */
	private String author;

	/**
	 * publications that published this book
	 */
	private String publications;

	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the publications
	 */
	public String getPublications() {
		return publications;
	}

	/**
	 * @param publications the publications to set
	 */
	public void setPublications(String publications) {
		this.publications = publications;
	}

	@Override
	public String toString() {
		return "Book [genre=" + genre + ", author=" + author + ", publications=" + publications + "]";
	}

}
