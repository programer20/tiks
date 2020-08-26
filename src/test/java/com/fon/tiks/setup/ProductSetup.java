package com.fon.tiks.setup;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fon.tiks.domain.Product;
import com.fon.tiks.mapper.ProductMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProductSetup {

	@Autowired
	ProductMapper productMapper;

	public List<Product> getSetup() {
		List<Product> setup = new ArrayList<>();

		log.info("insert products");
		setup.add(Product.builder().name("Naziv1").price(2400).brand("Brend1").category("Kategorija1").description("Opis1").build());
		setup.add(Product.builder().name("Naziv2").price(3500).brand("Brend2").category("Kategorija2").description("Opis2").build());

		setup.forEach(p -> {
			productMapper.insert(p);
		});
		
		return setup;
	}

}
