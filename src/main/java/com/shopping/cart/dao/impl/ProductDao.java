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
 * @author Kusal
 *
 */
@Component
public class ProductDao implements Dao<Product> {

	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
	}

	@Override
	public Optional<Product> get(int id) {
		return productRepository.findById(id);
	}

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product update(Product product) {
		Product existingProduct = productRepository.getOne(product.getId());
		BeanUtils.copyProperties(product, existingProduct, "id");
		return productRepository.save(existingProduct);
	}

	@Override
	public void delete(int id) {
		productRepository.deleteById(id);
	}
	
	public List<Product> findByName(String name){
		return productRepository.findByName(name);
	}
	
	public List<Product> findByCategory(String category){
		return productRepository.findByCategory(category);
	}

}
