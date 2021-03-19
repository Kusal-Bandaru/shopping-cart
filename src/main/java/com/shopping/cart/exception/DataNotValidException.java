/**
 * 
 */
package com.shopping.cart.exception;

/**
 * DataNotValidException to be thrown if any invalid data is encountered
 * 
 * @author Kusal
 *
 */
public class DataNotValidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public DataNotValidException(String message) {
		super(message);
	}

}
