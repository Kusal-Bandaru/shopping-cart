package com.shopping.cart.service;

import java.util.List;

import com.shopping.cart.entity.Product;
import com.shopping.cart.exception.ProductDoesNotExistException;

/**
 * ProductService Interface for business logic related to product operations
 * 
 * @author Kusal
 *
 */
public interface ProductService {

	/**
	 * Get all the product list
	 * 
	 * @return List<Product>
	 */
	public List<Product> getAllProducts();

	/**
	 * Fetch a product by id
	 * 
	 * @param id
	 * @return Product
	 * @throws ProductDoesNotExistException
	 */
	public Product getProductById(int id) throws ProductDoesNotExistException;

	/**
	 * Fetch product list by name
	 * 
	 * @param name
	 * @return List<Product>
	 * @throws ProductDoesNotExistException
	 */
	public List<Product> findProductByName(String name) throws ProductDoesNotExistException;

	/**
	 * Fetch product list by category
	 * 
	 * @param category
	 * @return List<Product>
	 * @throws ProductDoesNotExistException
	 */
	public List<Product> findProductByCategory(String category) throws ProductDoesNotExistException;

}
