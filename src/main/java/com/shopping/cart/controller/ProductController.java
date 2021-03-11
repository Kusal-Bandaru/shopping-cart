package com.shopping.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.cart.entity.Product;
import com.shopping.cart.service.ProductService;

/**
 * @author Kusal
 *
 */
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/getAll")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/find/{id}")
	public Product getProductById(@PathVariable Long id) {
		return productService.getProductById(id);
	}

	@PostMapping("/new")
	public Product addProduct(@RequestBody Product product) {
		return productService.saveProduct(product);
	}

}
