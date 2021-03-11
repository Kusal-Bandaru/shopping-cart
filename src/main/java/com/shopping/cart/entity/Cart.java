package com.shopping.cart.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * @author Kusal
 *
 */
@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_id")
	private long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;

	@OneToMany(mappedBy = "cart")
	private Set<CartItem> cartItem;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the cartItem
	 */
	public Set<CartItem> getCartItem() {
		return cartItem;
	}

	/**
	 * @param cartItem the cartItem to set
	 */
	public void setCartItem(Set<CartItem> cartItem) {
		this.cartItem = cartItem;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", user=" + user + ", cartItem=" + cartItem + "]";
	}

}
