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
import org.springframework.web.bind.annotation.PathVariable;

import com.shopping.cart.entity.CartItem;
import com.shopping.cart.exception.CartNotAssociatedException;
import com.shopping.cart.service.CartService;

/**
 * @author Kusal
 *
 */
public class CartHandler {

	@Autowired
	CartService cartService;

	public ResponseEntity<Map<String, Object>> getCartItemList(@PathVariable Long cartId) {
		Map<String, Object> responseMap = new HashMap<>();
		Set<CartItem> cartItemList;
		try {
			cartItemList = cartService.getCartItemList(cartId);
			if (cartItemList != null && cartItemList.size() > 0) {
				responseMap.put("response", cartItemList);
			} else {
				responseMap.put("response", "Cart is empty! Add some items to cart.");
			}
		} catch (CartNotAssociatedException e) {
			responseMap.put("response", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}
}
