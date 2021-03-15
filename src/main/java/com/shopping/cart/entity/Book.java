package com.shopping.cart.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * @author Kusal
 *
 */
@Entity
@PrimaryKeyJoinColumn(name = "book_id")
public class Book extends Product {

//	@Id
//	@Column(name = "book_id")
//	private int id;

	private String genre;

	private String author;

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
