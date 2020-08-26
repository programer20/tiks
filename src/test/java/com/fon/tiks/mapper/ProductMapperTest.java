package com.fon.tiks.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fon.tiks.domain.Product;
import com.fon.tiks.setup.ProductSetup;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class ProductMapperTest extends BaseMapperTest {
	
	@Autowired
	ProductMapper productMapper;
	
	@Autowired
	ProductSetup productSetup;

	@Test
	void testCrud() {

		log.info("Getting product setup");
		List<Product> products = productSetup.getSetup();
		
		log.info("Get all products");
		products = productMapper.getAll();
		assertEquals(2, products.size());
		
		log.info("Delete all products");
		products.forEach(p -> {
			productMapper.deleteById(p.getId());
		});
		
		assertEquals(0, productMapper.getAll().size());
	}

}
