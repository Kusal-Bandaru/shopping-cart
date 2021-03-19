package com.shopping.cart.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.cart.constant.ExceptionConstansts;
import com.shopping.cart.dao.impl.ProductDao;
import com.shopping.cart.entity.Product;
import com.shopping.cart.exception.ProductDoesNotExistException;
import com.shopping.cart.service.ProductService;

/**
 * @author Kusal
 *
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	ProductDao productDao;

	@Override
	public List<Product> getAllProducts() {
		return productDao.getAll();
	}

	@Override
	public Product getProductById(int id) throws ProductDoesNotExistException {
		Optional<Product> productOptional = productDao.get(id);
		if (productOptional.isPresent()) {
			return productOptional.get();
		} else {
			throw new ProductDoesNotExistException(ExceptionConstansts.PRODUCT_MISSING);
		}
	}

	@Override
	public List<Product> findProductByName(String name) throws ProductDoesNotExistException {
		
		List<Product> productList = productDao.findByName(name);
		logger.info("Fetched product list from DB by name - {}, productList - {}", name, productList);
		if (productList != null && !productList.isEmpty()) {
			return productList;
		} else {
			throw new ProductDoesNotExistException(ExceptionConstansts.PRODUCT_MISSING);
		}
	}

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
