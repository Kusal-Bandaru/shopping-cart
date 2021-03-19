package com.shopping.cart.validator;

import org.springframework.stereotype.Component;

import com.shopping.cart.constant.ExceptionConstansts;
import com.shopping.cart.entity.CartItem;
import com.shopping.cart.exception.DataNotValidException;

/**
 * CartRequestValidator validates the requests received for cart related
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
			throw new DataNotValidException(ExceptionConstansts.CART_ID_INVALID_GET_REQUEST);
		}
	}

	/**
	 * Verifies if the request for cartItem contains both the valid cart id and
	 * product id are present.
	 * 
	 * @param cartId
	 * @throws DataNotValidException
	 */
	public void validateAddItemRequest(CartItem cartItemRequest) throws DataNotValidException {

		if (cartItemRequest == null) {
			throw new DataNotValidException(ExceptionConstansts.CART_ITEM_MISSING_IN_REQUEST);
		}

		if (cartItemRequest.getCart() == null || cartItemRequest.getCart().getId() <= 0) {
			throw new DataNotValidException(ExceptionConstansts.CART_MISSING_IN_CART_ITEM_REQUEST);
		}

		if (cartItemRequest.getProduct() == null || cartItemRequest.getProduct().getId() <= 0) {
			throw new DataNotValidException(ExceptionConstansts.PRODUCT_MISSING_IN_CART_ITEM_REQUEST);
		}
		
		if(cartItemRequest.getQuantity()<0) {
			throw new DataNotValidException(ExceptionConstansts.INVALID_QUANTITY_IN_ADD_REQUEST);
		}
	}

	/**
	 * Verifies if either valid cartItemId or CartId & ProductId are present in the
	 * request
	 * 
	 * @param cartItemRequest
	 * @throws DataNotValidException
	 */
	public void validateUpdateItemRequest(CartItem cartItemRequest) throws DataNotValidException {

		if (cartItemRequest == null) {
			throw new DataNotValidException(ExceptionConstansts.CART_ITEM_MISSING_IN_REQUEST);
		}

		if (cartItemRequest.getId() <= 0) {
			if (cartItemRequest.getCart() == null || cartItemRequest.getCart().getId() <= 0
					|| cartItemRequest.getProduct() == null || cartItemRequest.getProduct().getId() <= 0) {
				throw new DataNotValidException(ExceptionConstansts.CART_ITEM_UPDATE_MISSING_REQUEST);
			}
		}
		
		if(cartItemRequest.getQuantity()<=0) {
			throw new DataNotValidException(ExceptionConstansts.INVALID_QUANTITY_IN_UPDATE_REQUEST);
		}
	}

	/**
	 * Verifes if a valid cartItemId is presen in the request
	 * 
	 * @param cartItemId
	 * @throws DataNotValidException
	 */
	public void validateDeleteCartItemRequest(int cartItemId) throws DataNotValidException {
		if (cartItemId <= 0) {
			throw new DataNotValidException(ExceptionConstansts.CART_ID_INVALID_DELETE_REQUEST);
		}
	}
}
