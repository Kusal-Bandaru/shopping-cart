package com.shopping.cart.exception;

/**
 * @author Kusal
 *
 */
public class CartNotAssociatedException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public CartNotAssociatedException(String message) {
		super(message);
	}

}
