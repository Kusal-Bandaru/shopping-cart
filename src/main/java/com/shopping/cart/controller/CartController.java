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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.cart.entity.CartItem;
import com.shopping.cart.handler.CartHandler;

/**
 * @author Kusal
 *
 */
@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

	@Autowired
	CartHandler cartHandler;

	@GetMapping
	public String welcomeMessage() {
		return "Welcome to your cart. Add some items.";
	}

	@GetMapping("/itemList/{cartId}")
	public ResponseEntity<Map<String, Object>> getCartItemList(@PathVariable Long cartId) {
		return cartHandler.getCartItemList(cartId);
	}

	@PostMapping("/addItem")
	public ResponseEntity<Map<String, Object>> addItem(@RequestBody CartItem cartItem) {
		return cartHandler.addItem(cartItem);
	}

	@PostMapping("/updateItem")
	public ResponseEntity<Map<String, Object>> updateItem(@RequestBody CartItem cartItem) {
		return cartHandler.updateItem(cartItem);
	}

	@DeleteMapping("/deleteItem")
	public ResponseEntity<Map<String, Object>> deleteItem(@RequestParam Long itemId) {
		return cartHandler.deleteItem(itemId);
	}
}
