package com.shopping.cart.dao;

import java.util.List;
import java.util.Optional;

/**
 * @author Kusal
 *
 */
public interface Dao<T> {

	List<T> getAll();

	Optional<T> get(int id);

	T save(T t);

	T update(T t);

	void delete(int id);
}
