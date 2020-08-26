package com.fon.tiks.service;

import java.util.List;

import com.fon.tiks.domain.Product;

public interface ProductService {

	public Product insert(Product product);

	public Product update(Product product);

	public Product getById(Long id);

	public List<Product> getAll();

	public int deleteById(Long id);
	
	public int deleteAll();
	
}
