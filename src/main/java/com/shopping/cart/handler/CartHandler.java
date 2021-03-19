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
import com.shopping.cart.exception.CartItemDoesNotExistException;
import com.shopping.cart.exception.CartNotAssociatedException;
import com.shopping.cart.exception.DataNotValidException;
import com.shopping.cart.exception.ProductDoesNotExistException;
import com.shopping.cart.service.CartService;
import com.shopping.cart.validator.CartRequestValidator;;

/**
 * CartHandler class to handle the requests and response to cart operations.
 * 
 * @author Kusal
 *
 */
@Component
public class CartHandler {

	Logger logger = LoggerFactory.getLogger(CartHandler.class);

	@Autowired
	CartService cartService;

	@Autowired
	CartRequestValidator cartRequestValidator;

	/**
	 * method to fetch the cartItem list based on cart Id
	 * 
	 * @param cartId
	 * @return
	 */
	public ResponseEntity<Map<String, Object>> getCartItemList(int cartId) {
		Map<String, Object> responseMap = new HashMap<>();
		Cart cart;
		try {
			logger.info("Request received to fetch CartItems list for cartId - {}", cartId);
			cartRequestValidator.validateGetCartItemListRequest(cartId);
			cart = cartService.getCartItemList(cartId);
			if (cart.getCartItem() != null && cart.getCartItem().size() > 0) {
				responseMap.put(ResponseConstants.RESPONSE, cart);
			} else {
				responseMap.put(ResponseConstants.RESPONSE, ResponseConstants.EMPTY_CART);
			}
		} catch (DataNotValidException de) {
			logger.error("DataNotValidException occurred in CartHandler::getCartItemList. Message - {}",
					de.getMessage());
			responseMap.put(ResponseConstants.RESPONSE, de.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.BAD_REQUEST);
		} catch (CartNotAssociatedException ce) {
			logger.error("CartNotAssociatedException occurred in CartHandler::getCartItemList. Message - {}",
					ce.getMessage());
			responseMap.put(ResponseConstants.RESPONSE, ce.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.PRECONDITION_FAILED);
		} catch (Exception e) {
			logger.error("Exception in CartHandler::getCartItemList. Message - {}", e.getMessage());
			responseMap.put(ResponseConstants.RESPONSE, e.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("CartItem list is fetched successfully for cartId - {}", cartId);
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}

	public ResponseEntity<Map<String, Object>> addItem(CartItem cartItem) {
		Map<String, Object> responseMap = new HashMap<>();
		try {
			logger.info("addItem handler: {}", cartItem.getCart());
			logger.info("addItem handler: {}", cartItem.getProduct());
			cartService.addItem(cartItem);
			responseMap.put(ResponseConstants.RESPONSE, ResponseConstants.ADD_ITEM_SUCCESS);
		} catch (CartNotAssociatedException | ProductDoesNotExistException e) {
			responseMap.put(ResponseConstants.RESPONSE, e.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.PRECONDITION_FAILED);
		} catch (Exception e) {
			logger.error("Exception in addItem {}", e.getMessage());
			responseMap.put(ResponseConstants.RESPONSE, e.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.CREATED);
	}

	public ResponseEntity<Map<String, Object>> updateItem(CartItem cartItem) {
		Map<String, Object> responseMap = new HashMap<>();
		try {
			cartService.updateItem(cartItem);
			responseMap.put(ResponseConstants.RESPONSE, ResponseConstants.UPDATE_ITEM_SUCCESS);
		} catch (CartNotAssociatedException | ProductDoesNotExistException | CartItemDoesNotExistException e) {
			responseMap.put(ResponseConstants.RESPONSE, e.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.PRECONDITION_FAILED);
		} catch (Exception e) {
			logger.error("Exception in updateItem {}", e.getMessage());
			responseMap.put(ResponseConstants.RESPONSE, e.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}

	public ResponseEntity<Map<String, Object>> deleteItem(int itemId) {
		Map<String, Object> responseMap = new HashMap<>();
		cartService.deleteItem(itemId);
		responseMap.put(ResponseConstants.RESPONSE, ResponseConstants.DELETE_ITEM_SUCCESS);
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}
}
