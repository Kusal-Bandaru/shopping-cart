package com.shopping.cart.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.cart.handler.ProductHandler;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * ProductController class contains API endpoints for product related operations
 * 
 * @author Kusal
 *
 */
@RestController
@RequestMapping("/api/v1/product")
@Api(value = "ProductController", tags = { "API for performing operations on products" }) // API info for swagger
public class ProductController {

	/**
	 * Autowiring ProductHandler class to handle the request and response
	 */
	@Autowired
	ProductHandler productHandler;

	/**
	 * Endpoint to fetch all the product list
	 * 
	 * @return ResponseEntity
	 */
	@GetMapping("/getAll")
	@ApiOperation(value = "Get all product list", httpMethod = "GET") // API info for swagger
	public ResponseEntity<Map<String, Object>> getAllProducts() {
		return productHandler.getAllProducts();
	}

	/**
	 * Endpoint to find the product by it's id
	 * 
	 * @param id
	 * @return ResponseEntity
	 */
	@PostMapping("/findById")
	@ApiOperation(value = "Find product by id", httpMethod = "POST")
	public ResponseEntity<Map<String, Object>> getProductById(int id) {
		return productHandler.getProductById(id);
	}

	/**
	 * Endpoint to find the product by it's name
	 * 
	 * @param name
	 * @return ResponseEntity
	 */
	@PostMapping("/findByName")
	@ApiOperation(value = "Find product by name", httpMethod = "POST")
	public ResponseEntity<Map<String, Object>> findProductByName(String name) {
		return productHandler.findProductByName(name);
	}

	/**
	 * Endpoint to find the product by it's category
	 * 
	 * @param category
	 * @return ResponseEntity
	 */
	@PostMapping("/findByCategory")
	@ApiOperation(value = "Find product by category", httpMethod = "POST")
	public ResponseEntity<Map<String, Object>> findProductByCategory(String category) {
		return productHandler.findProductByCategory(category);
	}
}
