package com.shopping.cart.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.cart.entity.Product;
import com.shopping.cart.repository.ProductRepository;
import com.shopping.cart.service.ProductService;

/**
 * @author Kusal
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product getProductById(Long id) {

		Product product = null;
		Optional<Product> productOptional = productRepository.findById(id);
		if (productOptional.isPresent()) {
			product = productOptional.get();
		}
		return product;
	}

	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
}
