package com.shopping.cart.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shopping.cart.dao.Dao;
import com.shopping.cart.entity.Product;
import com.shopping.cart.repository.ProductRepository;

/**
 * ProductDao class to perform operations on ProductRepository
 * 
 * @author Kusal
 *
 */
@Component
public class ProductDao implements Dao<Product> {

	/**
	 * Autowiring ProductRepository to perform operations
	 */
	@Autowired
	ProductRepository productRepository;

	/**
	 * Get all the list of Product entities
	 * 
	 * @return List<Product>
	 */
	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
	}

	/**
	 * Get an Optional of Product Entity based on the id
	 * 
	 * @param id
	 * @return Optional<Product>
	 */
	@Override
	public Optional<Product> get(int id) {
		return productRepository.findById(id);
	}

	/**
	 * Save a product entity
	 * 
	 * @param product
	 * @return Product
	 */

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	/**
	 * Update an existing product Entity
	 * 
	 * @param product
	 * @return Product
	 */
	@Override
	public Product update(Product product) {
		Product existingProduct = productRepository.getOne(product.getId());
		BeanUtils.copyProperties(product, existingProduct, "id");
		return productRepository.save(existingProduct);
	}

	/**
	 * Delete an existing Product entity
	 * 
	 * @param id
	 */
	@Override
	public void delete(int id) {
		productRepository.deleteById(id);
	}

	/**
	 * Method to fetch a product entities list by name
	 * 
	 * @param name
	 * @return List<Product>
	 */
	public List<Product> findByName(String name) {
		return productRepository.findByName(name);
	}

	/**
	 * Method to fetch a product entities list by category
	 * 
	 * @param category
	 * @return List<Product>
	 */
	public List<Product> findByCategory(String category) {
		return productRepository.findByCategory(category);
	}

}
