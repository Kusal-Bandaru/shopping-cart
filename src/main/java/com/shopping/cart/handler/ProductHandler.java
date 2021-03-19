package com.shopping.cart.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.shopping.cart.constant.ResponseConstants;
import com.shopping.cart.entity.Product;
import com.shopping.cart.exception.DataNotValidException;
import com.shopping.cart.exception.ProductDoesNotExistException;
import com.shopping.cart.service.ProductService;
import com.shopping.cart.validator.ProductRequestValidator;

/**
 * CartHandler class to handle the requests and response to cart operations.
 * 
 * @author Kusal
 *
 */
@Component
public class ProductHandler {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(ProductHandler.class);

	/**
	 * Autowiring ProductService dependency for consumption
	 */
	@Autowired
	private ProductService productService;

	/**
	 * Validator class to verify the requests
	 */
	@Autowired
	private ProductRequestValidator productRequestValidator;

	/**
	 * Get all the product list
	 * 
	 * @return ResponseEntity
	 */
	public ResponseEntity<Map<String, Object>> getAllProducts() {
		logger.info("Received a request to getAllProducts");
		Map<String, Object> responseMap = new HashMap<>();
		List<Product> productList = new ArrayList<>();
		try {
			productList = productService.getAllProducts();
			responseMap.put(ResponseConstants.RESPONSE, productList);
		} catch (Exception e) {
			logger.error("Exception occurred in ProductHandler::getAllProducts - Message {}", e.getMessage());
			responseMap.put(ResponseConstants.RESPONSE, e.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("Fetched the Product list successfully");
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}

	/**
	 * Get a product by id
	 * 
	 * @param id
	 * @return ResponseEntity
	 */
	public ResponseEntity<Map<String, Object>> getProductById(int id) {
		logger.info("Received request to fetch the Product by id = {}", id);
		Map<String, Object> responseMap = new HashMap<>();
		Product product;
		try {
			productRequestValidator.validateGetProductByIdRequest(id);
			product = productService.getProductById(id);
			responseMap.put(ResponseConstants.RESPONSE, product);
		} catch (DataNotValidException de) {
			logger.error("DataNotValidException occurred in ProductHandler::getProductById. Message - {}",
					de.getMessage());
			responseMap.put(ResponseConstants.RESPONSE, de.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.BAD_REQUEST);
		} catch (ProductDoesNotExistException pe) {
			logger.error("ProductDoesNotExistException in ProductHandler::getProductById. Message - {}",
					pe.getMessage());
			responseMap.put(ResponseConstants.RESPONSE, pe.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.PRECONDITION_FAILED);
		} catch (Exception e) {
			logger.error("Exception in ProductHandler::getProductById - {}", e.getMessage());
			responseMap.put(ResponseConstants.RESPONSE, e.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("Fetched product successfully for id = {}", id);
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}

	/**
	 * Get a product list by name
	 * 
	 * @param name
	 * @return ResponseEntity
	 */
	public ResponseEntity<Map<String, Object>> findProductByName(String name) {
		logger.info("Received request to findProductByName - {}", name);
		Map<String, Object> responseMap = new HashMap<>();
		List<Product> productList = new ArrayList<>();
		try {
			productRequestValidator.validateGetProductByNameRequest(name);
			productList = productService.findProductByName(name);
			responseMap.put(ResponseConstants.RESPONSE, productList);
		} catch (DataNotValidException de) {
			logger.error("DataNotValidException occurred in ProductHandler::findProductByName. Message - {}",
					de.getMessage());
			responseMap.put(ResponseConstants.RESPONSE, de.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.BAD_REQUEST);
		} catch (ProductDoesNotExistException pe) {
			logger.error("ProductDoesNotExistException in ProductHandler::findProductByName. Message - {}",
					pe.getMessage());
			responseMap.put(ResponseConstants.RESPONSE, pe.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.PRECONDITION_FAILED);
		} catch (Exception e) {
			logger.error("Exception in ProductHandler::findProductByCategory. Message - {}", e.getMessage());
			responseMap.put(ResponseConstants.RESPONSE, e.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("Fetched the product successfully by name - {}", name);
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}

	/**
	 * Get product list by category
	 * 
	 * @param category
	 * @return ResponseEntity
	 */
	public ResponseEntity<Map<String, Object>> findProductByCategory(String category) {
		logger.info("Received request to findProductByCategory - {}", category);
		Map<String, Object> responseMap = new HashMap<>();
		List<Product> productList = new ArrayList<>();
		try {
			productRequestValidator.validateGetProductByCategoryRequest(category);
			productList = productService.findProductByCategory(category);
			responseMap.put(ResponseConstants.RESPONSE, productList);
		} catch (DataNotValidException de) {
			logger.error("DataNotValidException occurred in ProductHandler::findProductByCategory. Message - {}",
					de.getMessage());
			responseMap.put(ResponseConstants.RESPONSE, de.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.BAD_REQUEST);
		} catch (ProductDoesNotExistException pe) {
			logger.error("ProductDoesNotExistException in ProductHandler::findProductByCategory. Message - {}",
					pe.getMessage());
			responseMap.put(ResponseConstants.RESPONSE, pe.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.PRECONDITION_FAILED);
		} catch (Exception e) {
			logger.error("Exception in ProductHandler::findProductByCategory. Message - {}", e.getMessage());
			responseMap.put(ResponseConstants.RESPONSE, e.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("Fetched the product successfully by category - {}", category);
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}
}
