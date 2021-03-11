/**
 * 
 */
package com.shopping.cart.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.shopping.cart.constant.ResponseConstants;
import com.shopping.cart.entity.CartItem;
import com.shopping.cart.exception.CartNotAssociatedException;
import com.shopping.cart.exception.ProductDoesNotExistException;
import com.shopping.cart.service.CartService;;

/**
 * @author Kusal
 *
 */
public class CartHandler {

	@Autowired
	CartService cartService;

	public ResponseEntity<Map<String, Object>> getCartItemList(long cartId) {
		Map<String, Object> responseMap = new HashMap<>();
		Set<CartItem> cartItemList;
		try {
			cartItemList = cartService.getCartItemList(cartId);
			if (cartItemList != null && cartItemList.size() > 0) {
				responseMap.put(ResponseConstants.RESPONSE, cartItemList);
			} else {
				responseMap.put(ResponseConstants.RESPONSE, ResponseConstants.EMPTY_CART);
			}
		} catch (CartNotAssociatedException e) {
			responseMap.put(ResponseConstants.RESPONSE, e.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.BAD_REQUEST);
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

	public ResponseEntity<Map<String, Object>> deleteItem(long itemId) {
		Map<String, Object> responseMap = new HashMap<>();
		cartService.deleteItem(itemId);
		responseMap.put(ResponseConstants.RESPONSE, ResponseConstants.DELETE_ITEM_SUCCESS);
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}
}
