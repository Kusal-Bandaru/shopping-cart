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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Kusal
 *
 */
@RestController
@RequestMapping("/api/v1/product")
@Api(value = "ProductController", tags = { "API for performing operations on products" }) // API info for swagger
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/getAll")
	@ApiOperation(value = "Get all product list", httpMethod = "GET") // API info for swagger
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/find/{id}")
	@ApiOperation(value = "Find a product by name", httpMethod = "GET")
	public Product getProductById(@PathVariable Long id) {
		return productService.getProductById(id);
	}

	@PostMapping("/new")
	@ApiOperation(value = "Add a new product", httpMethod = "POST", consumes = "application/json")
	public Product addProduct(@RequestBody Product product) {
		return productService.saveProduct(product);
	}

}
