package com.shopping.cart.service.impl;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.cart.dao.impl.CartDao;
import com.shopping.cart.entity.Cart;
import com.shopping.cart.entity.CartItem;
import com.shopping.cart.entity.Product;
import com.shopping.cart.exception.CartNotAssociatedException;
import com.shopping.cart.repository.CartItemRepository;
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
	CartItemRepository cartItemRepository;

	@Autowired
	ProductRepository productRepository;

	@Override
	public Set<CartItem> getCartItemList(Long cartId) throws CartNotAssociatedException {
		Optional<Cart> cartOptional = cartDao.get(cartId);
		if (cartOptional.isPresent()) {
			Cart cart = cartOptional.get();
			return cart.getCartItem();
		} else {
			throw new CartNotAssociatedException("User is not associated to cart or Requested cart is not present.");
		}
	}

	@Override
	public CartItem addItem(CartItem cartItem) {
		System.out.println(cartItem.toString());
		Optional<Cart> cart = cartDao.get(cartItem.getCart().getId());
		Optional<Product> product = productRepository.findById(cartItem.getProduct().getId());
		if (cart.isPresent() && product.isPresent()) {
			cartItem.setCart(cart.get());
			cartItem.setProduct(product.get());
		}
		return cartItemRepository.save(cartItem);
	}

	@Override
	public CartItem updateItem(CartItem cartItem) {
		return cartItemRepository.save(cartItem);
	}

	@Override
	public void deleteItem(Long itemId) {
		cartItemRepository.deleteById(itemId);
	}

}
