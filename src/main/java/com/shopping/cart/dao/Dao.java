package com.shopping.cart.dao;

import java.util.List;
import java.util.Optional;

/**
 * Dao interface which provides operations to perform on repository
 * 
 * @author Kusal
 *
 */
public interface Dao<T> {
	/**
	 * Method to get all the list of items
	 * 
	 * @return List<T>
	 */

	List<T> getAll();

	/**
	 * Method to return an optional from the id
	 * 
	 * @param id
	 * @return Optional<T>
	 */
	Optional<T> get(int id);

	/**
	 * Method to save an entity.
	 * 
	 * @param t
	 * @return T
	 */
	T save(T t);

	/**
	 * Method to update an existing entity
	 * 
	 * @param t
	 * @return T
	 */
	T update(T t);

	/**
	 * Method to delete an existing entity
	 * 
	 * @param id
	 */
	void delete(int id);
}
