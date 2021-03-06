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
import javax.persistence.Transient;

import io.swagger.annotations.ApiModelProperty;

/**
 * Entity class for Cart/ Shopping cart
 * 
 * @author Kusal
 *
 */
@Entity
public class Cart implements Comparable<Cart> {

	/**
	 * Primary key id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_id")
	private int id;

	/**
	 * User entity that is associated with this cart
	 */
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	@ApiModelProperty(required = false, hidden = true)
	private User user;

	/**
	 * Set of Cartitems present in the cart
	 */
	@OneToMany(mappedBy = "cart")
	@ApiModelProperty(required = false, hidden = true)
	private Set<CartItem> cartItem;

	/**
	 * Total price of all the items present in the cart
	 */
	@Transient
	@ApiModelProperty(required = false, hidden = true)
	private float totalPrice;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
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

	/**
	 * @return the totalPrice
	 */
	public float getTotalPrice() {
		return this.cartItem == null ? 0f
				: this.getCartItem().stream().map(CartItem::getSubPrice).reduce(0f, Float::sum);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + Float.floatToIntBits(totalPrice);
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		if (id != other.id)
			return false;
		if (Float.floatToIntBits(totalPrice) != Float.floatToIntBits(other.totalPrice))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", user=" + user + ", cartItem=" + cartItem + ", totalPrice=" + getTotalPrice() + "]";
	}

	@Override
	public int compareTo(Cart o) {
		return Float.compare(this.getTotalPrice(), o.getTotalPrice());
	}

}
