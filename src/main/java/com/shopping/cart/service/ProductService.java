package com.shopping.cart.service;

import java.util.List;

import com.shopping.cart.entity.Product;

/**
 * @author Kusal
 *
 */
public interface ProductService {

	public List<Product> getAllProducts();

	public Product getProductById(Long id);

	public Product saveProduct(Product product);
}
