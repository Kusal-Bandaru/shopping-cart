package com.shopping.cart.exception;

/**
 * @author Kusal
 *
 */
public class CartItemDoesNotExistException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public CartItemDoesNotExistException(String message) {
		super(message);
	}

}
