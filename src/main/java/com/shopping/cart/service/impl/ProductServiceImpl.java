package com.shopping.cart.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.cart.constant.ExceptionConstansts;
import com.shopping.cart.dao.impl.ProductDao;
import com.shopping.cart.entity.Product;
import com.shopping.cart.exception.ProductDoesNotExistException;
import com.shopping.cart.service.ProductService;

/**
 * ProductServiceImpl class contains business logic for operations on the
 * product.
 * 
 * @author Kusal
 *
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	/**
	 * Autowiring Dao dependencies to perform DB operations
	 */
	@Autowired
	ProductDao productDao;

	/**
	 * Get all the product list
	 * 
	 * @return List<Product>
	 */
	@Override
	public List<Product> getAllProducts() {
		return productDao.getAll();
	}

	/**
	 * Fetch a product by id
	 * 
	 * @param id
	 * @return Product
	 * @throws ProductDoesNotExistException
	 */
	@Override
	public Product getProductById(int id) throws ProductDoesNotExistException {
		Optional<Product> productOptional = productDao.get(id);
		if (productOptional.isPresent()) {
			return productOptional.get();
		} else {
			throw new ProductDoesNotExistException(ExceptionConstansts.PRODUCT_MISSING);
		}
	}

	/**
	 * Fetch product list by name
	 * 
	 * @param name
	 * @return List<Product>
	 * @throws ProductDoesNotExistException
	 */
	@Override
	public List<Product> findProductByName(String name) throws ProductDoesNotExistException {

		List<Product> productList = productDao.findByName(name);
		if (productList != null && !productList.isEmpty()) {
			return productList;
		} else {
			throw new ProductDoesNotExistException(ExceptionConstansts.PRODUCT_MISSING);
		}
	}

	/**
	 * Fetch product list by category
	 * 
	 * @param category
	 * @return List<Product>
	 * @throws ProductDoesNotExistException
	 */
	@Override
	public List<Product> findProductByCategory(String category) throws ProductDoesNotExistException {
		List<Product> productList = productDao.findByCategory(category);
		if (productList != null && !productList.isEmpty()) {
			return productList;
		} else {
			throw new ProductDoesNotExistException(ExceptionConstansts.PRODUCT_MISSING);
		}
	}

}
