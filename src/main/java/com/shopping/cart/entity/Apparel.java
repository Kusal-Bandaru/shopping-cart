package com.shopping.cart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * @author Kusal
 *
 */
@Entity
@PrimaryKeyJoinColumn(name = "apparel_id")
public class Apparel extends Product {

//	@Id
//	@Column(name = "apparel_id")
//	private int id;
	
	@Column(name = "apparel_type")
	private String type;

	private String brand;

	private String design;

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
	public String toString() {
		return "Apparel [type=" + type + ", brand=" + brand + ", design=" + design + "]";
	}

}