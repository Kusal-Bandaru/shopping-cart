package com.shopping.cart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * Entity class for Apparel, subclass of Product
 * 
 * @author Kusal
 *
 */
@Entity
@PrimaryKeyJoinColumn(name = "product_id")
public class Apparel extends Product {

	/**
	 * Primary key id
	 */
	@Id
	@Column(name = "apparel_id")
	private int id;

	/**
	 * Type of the apparel
	 */
	@Column(name = "apparel_type")
	private String type;

	/**
	 * Brand of the apparel
	 */
	private String brand;

	/**
	 * Design of the apparel
	 */
	private String design;

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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @return the design
	 */
	public String getDesign() {
		return design;
	}

	/**
	 * @param design the design to set
	 */
	public void setDesign(String design) {
		this.design = design;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((design == null) ? 0 : design.hashCode());
		result = prime * result + id;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Apparel other = (Apparel) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (design == null) {
			if (other.design != null)
				return false;
		} else if (!design.equals(other.design))
			return false;
		if (id != other.id)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Apparel [id=" + id + ", type=" + type + ", brand=" + brand + ", design=" + design + "]";
	}

}
