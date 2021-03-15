package com.shopping.cart.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product getProductById(Product productRequest) {
		Product product = null;
		Optional<Product> productOptional = null;
		if(productRequest.getId() > 0) {
			productOptional = productRepository.findById(productRequest.getId());
//		}else if(productRequest.getName() != null) {
//			productOptional = productRepository.findByName(productRequest.getName());
//		}else if(productRequest.getCategory() != null) {
//			productOptional = productRepository.findByCategory(productRequest.getCategory());
		}
		if (productOptional.isPresent()) {
			product = productOptional.get();
		}
		return product;
	}

	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
}
