package com.shopping.cart.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.cart.constant.ExceptionConstansts;
import com.shopping.cart.dao.impl.CartDao;
import com.shopping.cart.dao.impl.CartItemDao;
import com.shopping.cart.dao.impl.ProductDao;
import com.shopping.cart.entity.Cart;
import com.shopping.cart.entity.CartItem;
import com.shopping.cart.entity.Product;
import com.shopping.cart.exception.CartItemDoesNotExistException;
import com.shopping.cart.exception.CartNotAssociatedException;
import com.shopping.cart.exception.ProductDoesNotExistException;
import com.shopping.cart.service.CartService;

/**
 * CartServiceImpl class contains business logic for operations on the cart.
 * 
 * @author Kusal
 *
 */
@Service
@Transactional
public class CartServiceImpl implements CartService {

	/**
	 * Autowiring Dao dependencies to perform DB operations
	 */
	@Autowired
	CartDao cartDao;

	@Autowired
	CartItemDao cartItemDao;

	@Autowired
	ProductDao productDao;

	/**
	 * Get the cart items list based on cart id.
	 * 
	 * @param cartId
	 * @return Cart
	 * @throws CartNotAssociatedException
	 */
	@Override
	public Cart getCartItemList(int cartId) throws CartNotAssociatedException {
		Optional<Cart> cartOptional = cartDao.get(cartId);
		if (cartOptional.isPresent()) {
			Cart cart = cartOptional.get();
			return cart;
		} else {
			throw new CartNotAssociatedException(ExceptionConstansts.CART_MISSING);
		}
	}

	/**
	 * Add an item to the cart
	 * 
	 * @param cartItem
	 * @return CartItem
	 * @throws CartNotAssociatedException
	 * @throws ProductDoesNotExistException
	 */
	@Override
	public CartItem addItem(CartItem cartItem) throws CartNotAssociatedException, ProductDoesNotExistException {
		Optional<Cart> cart = cartDao.get(cartItem.getCart().getId());
		Optional<Product> product = productDao.get(cartItem.getProduct().getId());
		if (cart.isPresent() && product.isPresent()) {
			Optional<CartItem> existingCartItemOptional = cartItemDao.fetchIfItemExists(cart.get(), product.get());
			if (existingCartItemOptional.isPresent()) { // checking if cartItem to add is already present in the cart
				CartItem existingCartItem = existingCartItemOptional.get();
				existingCartItem.setQuantity(existingCartItem.getQuantity() + 1); // incrementing quantity for existing
																					// items
				return cartItemDao.update(existingCartItem);
			} else {
				cartItem.setCart(cart.get());
				cartItem.setProduct(product.get());
				if (cartItem.getQuantity() == 0) // setting the quantity to 1 for new items
					cartItem.setQuantity(1);
			}
		} else if (!cart.isPresent()) {
			throw new CartNotAssociatedException(ExceptionConstansts.CART_MISSING);
		} else if (!product.isPresent()) {
			throw new ProductDoesNotExistException(ExceptionConstansts.PRODUCT_MISSING);
		}
		return cartItemDao.save(cartItem);
	}

	/**
	 * Update an existing item in the cart
	 * 
	 * @param cartItem
	 * @return CartItem
	 * @throws CartNotAssociatedException
	 * @throws ProductDoesNotExistException
	 * @throws CartItemDoesNotExistException
	 */
	@Override
	public CartItem updateItem(CartItem cartItem)
			throws CartNotAssociatedException, ProductDoesNotExistException, CartItemDoesNotExistException {
		CartItem existingCartItem = null;
		if (cartItem.getId() == 0) {
			Optional<Cart> cart = cartDao.get(cartItem.getCart().getId());
			Optional<Product> product = productDao.get(cartItem.getProduct().getId());
			if (cart.isPresent() && product.isPresent()) {
				Optional<CartItem> existingCartItemOptional = cartItemDao.fetchIfItemExists(cart.get(), product.get());
				if (existingCartItemOptional.isPresent()) {
					existingCartItem = existingCartItemOptional.get();
					existingCartItem.setQuantity(cartItem.getQuantity());
				} else {
					throw new CartItemDoesNotExistException(ExceptionConstansts.CART_ITEM_MISSING);
				}
			} else if (!cart.isPresent()) {
				throw new CartNotAssociatedException(ExceptionConstansts.CART_MISSING);
			} else if (!product.isPresent()) {
				throw new ProductDoesNotExistException(ExceptionConstansts.PRODUCT_MISSING);
			}
			cartItem.getCart().getId();
		} else {
			Optional<CartItem> existingCartItemOptional = cartItemDao.get(cartItem.getId());
			if (existingCartItemOptional.isPresent()) {
				existingCartItem = existingCartItemOptional.get();
				existingCartItem.setQuantity(cartItem.getQuantity());
			} else {
				throw new CartItemDoesNotExistException(ExceptionConstansts.CART_ITEM_MISSING);
			}
		}
		return cartItemDao.update(existingCartItem);
	}

	/**
	 * Delete an item from the cart
	 * 
	 * @param itemId
	 */
	@Override
	public void deleteItem(int itemId) {
		cartItemDao.delete(itemId);
	}

}
