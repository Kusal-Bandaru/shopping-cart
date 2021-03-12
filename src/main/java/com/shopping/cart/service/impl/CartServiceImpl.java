package com.shopping.cart.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

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

	@Autowired
	CartDao cartDao;

	@Autowired
	CartItemDao cartItemDao;

	@Autowired
	ProductRepository productRepository;

	@Override
	public Cart getCartItemList(Long cartId) throws CartNotAssociatedException {
		Optional<Cart> cartOptional = cartDao.get(cartId);
		if (cartOptional.isPresent()) {
			Cart cart = cartOptional.get();
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
			cartItem.setCart(cart.get());
			cartItem.setProduct(product.get());
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
	public void deleteItem(Long itemId) {
		cartItemDao.delete(itemId);
	}

}
