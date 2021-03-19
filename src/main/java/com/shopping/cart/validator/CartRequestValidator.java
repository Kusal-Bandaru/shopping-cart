/**
 * 
 */
package com.shopping.cart.validator;

import org.springframework.stereotype.Component;

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

	public void validateGetCartItemListRequest(int cartId) throws DataNotValidException {
		if(cartId <= 0) {
			throw new DataNotValidException("Please enter a valid Cart Id to fetch the items");
		}
	}
}
