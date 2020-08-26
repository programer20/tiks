package com.fon.tiks.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fon.tiks.domain.Product;
import com.fon.tiks.mapper.ProductMapper;
import com.fon.tiks.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductMapper productMapper;

	@Override
	public Product insert(Product product) {
		productMapper.insert(product);
		return product;
	}

	@Override
	public Product update(Product product) {
		productMapper.update(product);
		return product;
	}

	@Override
	public Product getById(Long id) {
		return productMapper.getById(id);
	}

	@Override
	public List<Product> getAll() {
		return productMapper.getAll();
	}

	@Override
	public int deleteById(Long id) {
		return productMapper.deleteById(id);
	}

	@Override
	public int deleteAll() {
		return productMapper.deleteAll();
	}

}
