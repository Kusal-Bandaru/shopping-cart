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
import com.shopping.cart.exception.ProductDoesNotExistException;
import com.shopping.cart.service.ProductService;

/**
 * @author Kusal
 *
 */
@Component
public class ProductHandler {

	public static Logger logger = LoggerFactory.getLogger(ProductHandler.class);
	
	@Autowired
	private ProductService productService;
	
	public  ResponseEntity<Map<String, Object>> getAllProducts() {
		Map<String, Object> responseMap = new HashMap<>();
		List<Product> productList = new ArrayList<>();
		try {
			productList =  productService.getAllProducts();
			responseMap.put(ResponseConstants.RESPONSE, productList);
		} catch (Exception e) {
			logger.error("Exception in ProductHandler::getAllProducts - {}", e.getMessage());
			responseMap.put(ResponseConstants.RESPONSE, e.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}

	public ResponseEntity<Map<String, Object>> getProductById(int id) {
		Map<String, Object> responseMap = new HashMap<>();
		Product product;
		try {
			product =  productService.getProductById(id);
			responseMap.put(ResponseConstants.RESPONSE, product);
		} catch (ProductDoesNotExistException e) {
			logger.error("ProductDoesNotExistException in ProductHandler::getProductById - {}", e.getMessage());
			responseMap.put(ResponseConstants.RESPONSE, e.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.PRECONDITION_FAILED);
		} catch (Exception e) {
			logger.error("Exception in ProductHandler::getProductById - {}", e.getMessage());
			responseMap.put(ResponseConstants.RESPONSE, e.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}

	public ResponseEntity<Map<String, Object>> findProductByName(String name){
		logger.info("Received request to findProductByName - {}", name);
		Map<String, Object> responseMap = new HashMap<>();
		List<Product> productList = new ArrayList<>();
		try {
			productList =  productService.findProductByName(name);
			responseMap.put(ResponseConstants.RESPONSE, productList);
		} catch (ProductDoesNotExistException e) {
			logger.error("ProductDoesNotExistException in ProductHandler::findProductByName - {}", e.getMessage());
			responseMap.put(ResponseConstants.RESPONSE, e.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.PRECONDITION_FAILED);
		} catch (Exception e) {
			logger.error("Exception in ProductHandler::findProductByCategory - {}", e.getMessage());
			responseMap.put(ResponseConstants.RESPONSE, e.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}
	
	public ResponseEntity<Map<String, Object>> findProductByCategory(String category){
		logger.info("Received request to findProductByCategory - {}", category);
		Map<String, Object> responseMap = new HashMap<>();
		List<Product> productList = new ArrayList<>();
		try {
			productList =  productService.findProductByCategory(category);
			responseMap.put(ResponseConstants.RESPONSE, productList);
		} catch (ProductDoesNotExistException e) {
			logger.error("ProductDoesNotExistException in ProductHandler::findProductByCategory - {}", e.getMessage());
			responseMap.put(ResponseConstants.RESPONSE, e.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.PRECONDITION_FAILED);
		} catch (Exception e) {
			logger.error("Exception in ProductHandler::findProductByCategory - {}", e.getMessage());
			responseMap.put(ResponseConstants.RESPONSE, e.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}
}
