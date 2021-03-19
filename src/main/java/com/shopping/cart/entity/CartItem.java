package com.shopping.cart.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.swagger.annotations.ApiModelProperty;

/**
 * Entity class for Cart items
 * 
 * @author Kusal
 *
 */
@Entity
@Table(name = "cart_item")
// Query to fetch the cart item based on product and cart
@NamedQuery(name = "CartItem.fetchIfItemExists", query = "FROM CartItem ci WHERE ci.cart = :cart AND ci.product = :product")
public class CartItem {

	/**
	 * Primary key id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_item_id")
	private int id;

	/**
	 * Cart entity that is associated with this cartItem
	 */
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Cart cart;

	/**
	 * Product entity that is associated with this cartItem
	 */
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "product_id", referencedColumnName = "product_id")
	private Product product;

	/**
	 * quantity of the product in the cartItem
	 */
	private int quantity;

	/**
	 * Subprice for this cartItem alone
	 */
	@Transient
	@ApiModelProperty(required = false, hidden = true)
	private float subPrice;

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
	 * @return the cart
	 */
	public Cart getCart() {
		return cart;
	}

	/**
	 * @param cart the cart to set
	 */
	public void setCart(Cart cart) {
		this.cart = cart;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the subPrice
	 */
	public float getSubPrice() {
		return this.product.getPrice() * this.quantity;
	}

	@Override
	public String toString() {
		return "CartItem [id=" + id + ", cartId=" + cart.getId() + ", productId=" + product + ", quantity=" + quantity
				+ ", subPrice=" + getSubPrice() + "]";
	}

}
