package com.shopping.cart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.cart.entity.Product;

/**
 * ProductRepository for operations on Product entities in DB
 * 
 * @author Kusal
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	/**
	 * Derived query method to find Product list by name
	 * 
	 * @param name
	 * @return List<Product>
	 */
	List<Product> findByName(String name);

	/**
	 * Derived query method to find Product list by category
	 * 
	 * @param name
	 * @return List<Product>
	 */
	List<Product> findByCategory(String category);
}
