/**
 * 
 */
package com.shopping.cart.validator;

import org.springframework.stereotype.Component;

import com.shopping.cart.entity.CartItem;
import com.shopping.cart.exception.DataNotValidException;

/**
 * CartRequestValidatorn validates the request received for cart related
 * operations
 * 
 * @author Kusal
 *
 */
@Component
public class CartRequestValidator {

	/**
	 * Verifies if the cartId is valid
	 * 
	 * @param cartId
	 * @throws DataNotValidException
	 */
	public void validateGetCartItemListRequest(int cartId) throws DataNotValidException {
		if (cartId <= 0) {
			throw new DataNotValidException("Please enter a valid Cart Id to fetch the items");
		}
	}

	/**
	 * Checks the request for cartItem that needs to be added.
	 * 
	 * @param cartId
	 * @throws DataNotValidException
	 */
	public void validateAddItemRequest(CartItem cartItemRequest) throws DataNotValidException {

		if (cartItemRequest == null) {
			throw new DataNotValidException("Please provide a valid CartItem request to add the item");
		}

		if (cartItemRequest.getCart() == null || cartItemRequest.getCart().getId() <= 0) {
			throw new DataNotValidException("Please provide a valid Cart Id to add the item");
		}

		if (cartItemRequest.getProduct() == null || cartItemRequest.getProduct().getId() <= 0) {
			throw new DataNotValidException("Please provide a valid Product Id to add to the cart");
		}
	}

	/**
	 * 
	 * @param cartItemRequest
	 * @throws DataNotValidException
	 */
	public void validateUpdateItemRequest(CartItem cartItemRequest) throws DataNotValidException {

	}

	/**
	 * 
	 * @param cartItemId
	 * @throws DataNotValidException
	 */
	public void validateDeleteCartItemRequest(int cartItemId) throws DataNotValidException {
		if (cartItemId <= 0) {
			throw new DataNotValidException("Please enter a valid cartItemId to delete");
		}
	}
}
