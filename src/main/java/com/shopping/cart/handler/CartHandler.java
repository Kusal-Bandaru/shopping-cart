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

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(CartHandler.class);

	/**
	 * Autowiring CartService dependency for consumption
	 */
	@Autowired
	CartService cartService;

	/**
	 * Autowiring CartRequestValidator dependency for validation of requests
	 */
	@Autowired
	CartRequestValidator cartRequestValidator;

	/**
	 * Handler method to fetch the cartItem list based on cart Id
	 * 
	 * @param cartId
	 * @return ResponseEntity
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
				// Returning a user friendly response if cart is empty.
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

	/**
	 * Handler method to add an item to the cart
	 * 
	 * @param cartItem
	 * @return ResponseEntity
	 */
	public ResponseEntity<Map<String, Object>> addItem(CartItem cartItem) {
		Map<String, Object> responseMap = new HashMap<>();
		CartItem createdCartItem = null;
		try {
			logger.info("Request received to add an item to the cart - {}", cartItem);
			cartRequestValidator.validateAddItemRequest(cartItem);
			createdCartItem = cartService.addItem(cartItem);
			responseMap.put(ResponseConstants.RESPONSE, ResponseConstants.ADD_ITEM_SUCCESS);
		} catch (DataNotValidException de) {
			logger.error("DataNotValidException occurred in CartHandler::addItem. Message - {}", de.getMessage());
			responseMap.put(ResponseConstants.RESPONSE, de.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.BAD_REQUEST);
		} catch (CartNotAssociatedException | ProductDoesNotExistException ce) {
			logger.error(
					"CartNotAssociatedException/ProductDoesNotExistException occurred in CartHandler::addItem. Message - {}",
					ce.getMessage());
			responseMap.put(ResponseConstants.RESPONSE, ce.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.PRECONDITION_FAILED);
		} catch (Exception e) {
			logger.error("Exception in CartHandler::addItem. Message - {}", e.getMessage());
			responseMap.put(ResponseConstants.RESPONSE, e.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("CartItem added successfully to the cart, cartItemId - {}", createdCartItem.getId());
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.CREATED);
	}

	/**
	 * Handler method to update an existing item in the cart
	 * 
	 * @param cartItem
	 * @return ResponseEntity
	 */
	public ResponseEntity<Map<String, Object>> updateItem(CartItem cartItem) {
		Map<String, Object> responseMap = new HashMap<>();
		CartItem updatedCartItem = null;
		try {
			logger.info("Request received to update an existing item in the cart - {}", cartItem);
			cartRequestValidator.validateUpdateItemRequest(cartItem);
			updatedCartItem = cartService.updateItem(cartItem);
			responseMap.put(ResponseConstants.RESPONSE, ResponseConstants.UPDATE_ITEM_SUCCESS);
		} catch (DataNotValidException de) {
			logger.error("DataNotValidException occurred in CartHandler::updateItem. Message - {}", de.getMessage());
			responseMap.put(ResponseConstants.RESPONSE, de.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.BAD_REQUEST);
		} catch (CartNotAssociatedException | ProductDoesNotExistException | CartItemDoesNotExistException ce) {
			logger.error(
					"CartNotAssociatedException/ProductDoesNotExistException/CartItemDoesNotExistException occurred in CartHandler::updateItem. Message - {}",
					ce.getMessage());
			responseMap.put(ResponseConstants.RESPONSE, ce.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.PRECONDITION_FAILED);
		} catch (Exception e) {
			logger.error("Exception in CartHandler::updateItem. Message - {}", e.getMessage());
			responseMap.put(ResponseConstants.RESPONSE, e.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("CartItem updated successfully in the cart, cartItemId - {}", updatedCartItem.getId());
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}

	/**
	 * Handler method to delete an existing item from the cart
	 * 
	 * @param cartId
	 * @return ResponseEntity
	 */
	public ResponseEntity<Map<String, Object>> deleteItem(int cartItemId) {
		Map<String, Object> responseMap = new HashMap<>();
		try {
			logger.info("Request received to delete an item from the cart, cartItemId - {}", cartItemId);
			cartRequestValidator.validateDeleteCartItemRequest(cartItemId);
			cartService.deleteItem(cartItemId);
			responseMap.put(ResponseConstants.RESPONSE, ResponseConstants.DELETE_ITEM_SUCCESS);
		} catch (DataNotValidException de) {
			logger.error("DataNotValidException occurred in CartHandler::deleteItem. Message - {}", de.getMessage());
			responseMap.put(ResponseConstants.RESPONSE, de.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error("Exception in CartHandler::deleteItem. Message - {}", e.getMessage());
			responseMap.put(ResponseConstants.RESPONSE, e.getMessage());
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("CartItem deleted successfully from the cart, cartItemId - {}", cartItemId);
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}
}
