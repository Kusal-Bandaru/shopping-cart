package com.shopping.cart.service;

import java.util.List;

import com.shopping.cart.entity.Product;
import com.shopping.cart.exception.ProductDoesNotExistException;

/**
 * @author Kusal
 *
 */
public interface ProductService {

	public List<Product> getAllProducts();

	public Product getProductById(int id) throws ProductDoesNotExistException;
	
	public List<Product> findProductByName(String name) throws ProductDoesNotExistException;
	
	public List<Product> findProductByCategory(String category) throws ProductDoesNotExistException;

}
