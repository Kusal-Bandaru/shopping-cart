/**
 * 
 */
package com.shopping.cart.handler;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.shopping.cart.constant.ResponseConstants;
import com.shopping.cart.entity.Cart;
import com.shopping.cart.entity.CartItem;
import com.shopping.cart.exception.CartNotAssociatedException;
import com.shopping.cart.exception.ProductDoesNotExistException;
import com.shopping.cart.service.CartService;;

/**
 * @author Kusal
 *
 */
@Component
public class CartHandler {

	Logger logger = LoggerFactory.getLogger(CartHandler.class);
	
	@Autowired
	CartService cartService;

	public ResponseEntity<Map<String, Object>> getCartItemList(int cartId) {
		Map<String, Object> responseMap = new HashMap<>();
		Cart cart;
		try {
			logger.info(">>>>>In Cart Handler");
			cart = cartService.getCartItemList(cartId);
			logger.info("received the cart {}", cart);
			if (cart.getCartItem() != null && cart.getCartItem().size() > 0) {
				responseMap.put(ResponseConstants.RESPONSE, cart);
			} else {
				responseMap.put(ResponseConstants.RESPONSE, ResponseConstants.EMPTY_CART);
			}
		} catch (CartNotAssociatedException e) {
			responseMap.put(ResponseConstants.RESPONSE, e.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.BAD_REQUEST);
		} catch(Exception e) {
			logger.error(">>> Exception in cartItemList {}", e.getMessage());
			e.printStackTrace();
			responseMap.put(ResponseConstants.RESPONSE, e.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}

	public ResponseEntity<Map<String, Object>> addItem(CartItem cartItem) {
		Map<String, Object> responseMap = new HashMap<>();
		try {
			cartService.addItem(cartItem);
			responseMap.put(ResponseConstants.RESPONSE, ResponseConstants.ADD_ITEM_SUCCESS);
		} catch (CartNotAssociatedException | ProductDoesNotExistException e) {
			responseMap.put(ResponseConstants.RESPONSE, e.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.CREATED);
	}

	public ResponseEntity<Map<String, Object>> updateItem(CartItem cartItem) {
		Map<String, Object> responseMap = new HashMap<>();
		cartService.updateItem(cartItem);
		responseMap.put(ResponseConstants.RESPONSE, ResponseConstants.UPDATE_ITEM_SUCCESS);
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}

	public ResponseEntity<Map<String, Object>> deleteItem(int itemId) {
		Map<String, Object> responseMap = new HashMap<>();
		cartService.deleteItem(itemId);
		responseMap.put(ResponseConstants.RESPONSE, ResponseConstants.DELETE_ITEM_SUCCESS);
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}
}
