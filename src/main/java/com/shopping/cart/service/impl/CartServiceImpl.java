package com.shopping.cart.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.cart.constant.ExceptionConstansts;
import com.shopping.cart.dao.impl.CartDao;
import com.shopping.cart.dao.impl.CartItemDao;
import com.shopping.cart.entity.Cart;
import com.shopping.cart.entity.CartItem;
import com.shopping.cart.entity.Product;
import com.shopping.cart.exception.CartItemDoesNotExistException;
import com.shopping.cart.exception.CartNotAssociatedException;
import com.shopping.cart.exception.ProductDoesNotExistException;
import com.shopping.cart.repository.ProductRepository;
import com.shopping.cart.service.CartService;

/**
 * @author Kusal
 *
 */
@Service
@Transactional
public class CartServiceImpl implements CartService {

	Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

	@Autowired
	CartDao cartDao;

	@Autowired
	CartItemDao cartItemDao;

	@Autowired
	ProductRepository productRepository;

	@Override
	public Cart getCartItemList(int cartId) throws CartNotAssociatedException {
		logger.info("In cartSrvImpl {}", cartId);
		Optional<Cart> cartOptional = cartDao.get(cartId);
		logger.info("received cart optional {}", cartOptional);
		if (cartOptional.isPresent()) {
			Cart cart = cartOptional.get();
			logger.info("received cart {}", cart);
			return cart;
		} else {
			throw new CartNotAssociatedException(ExceptionConstansts.CART_MISSING);
		}
	}

	@Override
	public CartItem addItem(CartItem cartItem) throws CartNotAssociatedException, ProductDoesNotExistException {
		logger.info("Received request to add cartItem: {}", cartItem);
		Optional<Cart> cart = cartDao.get(cartItem.getCart().getId());
		Optional<Product> product = productRepository.findById(cartItem.getProduct().getId());
		if (cart.isPresent() && product.isPresent()) {
			Optional<CartItem> existingCartItemOptional = cartItemDao.fetchIfItemExists(cart.get(), product.get());
			if (existingCartItemOptional.isPresent()) {
				CartItem existingCartItem = existingCartItemOptional.get();
				existingCartItem.setQuantity(existingCartItem.getQuantity() + 1);
				return cartItemDao.update(existingCartItem);
			} else {
				cartItem.setCart(cart.get());
				cartItem.setProduct(product.get());
				if (cartItem.getQuantity() == 0)
					cartItem.setQuantity(1);
			}
		} else if (!cart.isPresent()) {
			throw new CartNotAssociatedException(ExceptionConstansts.CART_MISSING);
		} else if (!product.isPresent()) {
			throw new ProductDoesNotExistException(ExceptionConstansts.PRODUCT_MISSING);
		}
		return cartItemDao.save(cartItem);
	}

	@Override
	public CartItem updateItem(CartItem cartItem)
			throws CartNotAssociatedException, ProductDoesNotExistException, CartItemDoesNotExistException {
		CartItem existingCartItem = null;
		if (cartItem.getId() == 0) {
			Optional<Cart> cart = cartDao.get(cartItem.getCart().getId());
			Optional<Product> product = productRepository.findById(cartItem.getProduct().getId());
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

	@Override
	public void deleteItem(int itemId) {
		cartItemDao.delete(itemId);
	}

}
