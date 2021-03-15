package com.shopping.cart.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.cart.dao.impl.CartDao;
import com.shopping.cart.dao.impl.CartItemDao;
import com.shopping.cart.entity.Cart;
import com.shopping.cart.entity.CartItem;
import com.shopping.cart.entity.Product;
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
			throw new CartNotAssociatedException("User is not associated to cart or Requested cart is not present.");
		}
	}

	@Override
	public CartItem addItem(CartItem cartItem) throws CartNotAssociatedException, ProductDoesNotExistException {

		Optional<Cart> cart = cartDao.get(cartItem.getCart().getId());
		Optional<Product> product = productRepository.findById(cartItem.getProduct().getId());
		if (cart.isPresent() && product.isPresent()) {
			Optional<CartItem> existingCartItemOptional = cartItemDao.fetchIfItemExists(cart.get(), product.get());
			if (existingCartItemOptional.isPresent()) {
				CartItem existingCartItem = existingCartItemOptional.get();
				existingCartItem.setQuantity(existingCartItem.getQuantity() + 1);
				return updateItem(existingCartItem);
			} else {
				cartItem.setCart(cart.get());
				cartItem.setProduct(product.get());
			}
		} else if (!cart.isPresent()) {
			throw new CartNotAssociatedException("User is not associated to cart or Requested cart is not present.");
		} else {
			throw new ProductDoesNotExistException("Requested product does not exist");
		}
		return cartItemDao.save(cartItem);
	}

	@Override
	public CartItem updateItem(CartItem cartItem) {
		return cartItemDao.update(cartItem);
	}

	@Override
	public void deleteItem(int itemId) {
		cartItemDao.delete(itemId);
	}

}
