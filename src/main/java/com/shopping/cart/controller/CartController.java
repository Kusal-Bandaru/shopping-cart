package com.shopping.cart.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.cart.entity.CartItem;
import com.shopping.cart.handler.CartHandler;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Kusal
 *
 */
@RestController
@RequestMapping("/api/v1/cart")
@Api(value = "CartController", tags = { "API for performing operations on cart" }) // API info for swagger
public class CartController {

	@Autowired
	CartHandler cartHandler;

	@GetMapping
	@ApiOperation(value = "Welcome message", httpMethod = "GET") // API Operation info for swagger
	public String welcomeMessage() {
		return "Welcome to your cart. Add some items.";
	}

	@GetMapping("/itemList/{cartId}")
	@ApiOperation(value = "Get item list from the cart", httpMethod = "GET")
	public ResponseEntity<Map<String, Object>> getCartItemList(@PathVariable int cartId) {
		return cartHandler.getCartItemList(cartId);
	}

	@PostMapping("/addItem")
	@ApiOperation(value = "Add a item to the cart", httpMethod = "POST", consumes = "application/json")
	public ResponseEntity<Map<String, Object>> addItem(@RequestBody CartItem cartItem) {
		return cartHandler.addItem(cartItem);
	}

	@PostMapping("/updateItem")
	@ApiOperation(value = "Update an existing item in the cart", httpMethod = "POST", consumes = "application/json")
	public ResponseEntity<Map<String, Object>> updateItem(@RequestBody CartItem cartItem) {
		return cartHandler.updateItem(cartItem);
	}

	@DeleteMapping("/deleteItem")
	@ApiOperation(value = "Delete an existing item from the cart", httpMethod = "DELETE", consumes = "application/json")
	public ResponseEntity<Map<String, Object>> deleteItem(int itemId) {
		return cartHandler.deleteItem(itemId);
	}
}
