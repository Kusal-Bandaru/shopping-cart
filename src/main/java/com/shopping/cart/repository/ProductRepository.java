package com.shopping.cart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.cart.entity.Product;

/**
 * @author Kusal
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findByName(String name);
	List<Product> findByCategory(String category);
}
