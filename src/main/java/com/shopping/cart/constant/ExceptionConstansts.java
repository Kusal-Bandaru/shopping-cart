package com.shopping.cart.constant;

/**
 * Constants class for Exception messages
 * 
 * @author Kusal
 *
 */
public class ExceptionConstansts {

	/**
	 * Exception message when a product is missing
	 */
	public static final String PRODUCT_MISSING = "Requested product does not exist";

	/**
	 * Exception message when cart is missing
	 */
	public static final String CART_MISSING = "User is not associated to cart or Requested cart is not present";

	/**
	 * Exception message when a cart item is missing
	 */
	public static final String CART_ITEM_MISSING = "Requested cart item does not exist. Please add it to the cart before updation";

	/**
	 * Exception message when an invalid cart id is given in the request
	 */
	public static final String CART_ID_INVALID_GET_REQUEST = "Please enter a valid Cart Id to fetch the items";

	/**
	 * Exception message when an invalid CartItem is given in the request
	 */
	public static final String CART_ITEM_MISSING_IN_REQUEST = "Please provide a valid CartItem request to add/update the item";

	/**
	 * Exception message when an invalid cart id is given in the request
	 */
	public static final String CART_MISSING_IN_CART_ITEM_REQUEST = "Please provide a valid Cart Id to add the item";
	/**
	 * Exception message when an invalid product id is given in the request
	 */
	public static final String PRODUCT_MISSING_IN_CART_ITEM_REQUEST = "Please provide a valid Product Id to add to the cart";

	/**
	 * Exception message when both cartItemId and cartId & productId are missing in
	 * request
	 */
	public static final String CART_ITEM_UPDATE_MISSING_REQUEST = "Please provide either a valid CartItemId or valid CartId & ProductId "
			+ "to update the item";

	/**
	 * Exception message when cart id is invalid for delete request
	 */
	public static final String CART_ID_INVALID_DELETE_REQUEST = "Please enter a valid cartItemId to delete";

	/**
	 * Exception message when invalid product id is given in the request to fetch
	 * the product
	 */
	public static final String INVALID_PRODUCT_ID_IN_REQUEST = "Please enter a valid Product Id to fetch the product";
	/**
	 * Exception message when invalid product name is given in the request to fetch
	 * the products
	 */
	public static final String INVALID_PRODUCT_NAME_IN_REQUEST = "Please enter a valid Product Name to fetch the product";

	/**
	 * Exception message when invalid product category is given in the request to
	 * fetch the products
	 */
	public static final String INVALID_PRODUCT_CATEGORY_IN_REQUEST = "Please enter a valid Product Category to fetch the product";

	/**
	 * Exception message when invalid quantity is provided in add cartItem request
	 */
	public static final String INVALID_QUANTITY_IN_ADD_REQUEST = "Invalid quantity in request. Please provide a valid quantity or "
			+ "do not provide a value for quantity(single item will be added by default).";

	/**
	 * Exception message when invalid quantity is provided in update cartItem
	 * request
	 */
	public static final String INVALID_QUANTITY_IN_UPDATE_REQUEST = "Please enter quantity greater than 0 to update the item. "
			+ "Use delete if the quantity needs to be updated to 0";
}
