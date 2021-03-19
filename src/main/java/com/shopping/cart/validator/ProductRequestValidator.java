package com.shopping.cart.validator;

import org.springframework.stereotype.Component;

import com.shopping.cart.constant.ExceptionConstansts;
import com.shopping.cart.exception.DataNotValidException;

/**
 * ProductRequestValidator validates the requests received for product related
 * operations
 * 
 * @author Kusal
 *
 */
@Component
public class ProductRequestValidator {

	/**
	 * Verifies if the product id is valid
	 * 
	 * @param id
	 * @throws DataNotValidException
	 */
	public void validateGetProductByIdRequest(int id) throws DataNotValidException {
		if (id <= 0) {
			throw new DataNotValidException(ExceptionConstansts.INVALID_PRODUCT_ID_IN_REQUEST);
		}
	}

	/**
	 * Verifies if the name given is not empty
	 * 
	 * @param name
	 * @throws DataNotValidException
	 */
	public void validateGetProductByNameRequest(String name) throws DataNotValidException {
		if (name == null || name.isEmpty()) {
			throw new DataNotValidException(ExceptionConstansts.INVALID_PRODUCT_NAME_IN_REQUEST);
		}
	}

	/**
	 * Verifies if the category given is not empty
	 * 
	 * @param category
	 * @throws DataNotValidException
	 */
	public void validateGetProductByCategoryRequest(String category) throws DataNotValidException {
		if (category == null || category.isEmpty()) {
			throw new DataNotValidException(ExceptionConstansts.INVALID_PRODUCT_CATEGORY_IN_REQUEST);
		}
	}
}
