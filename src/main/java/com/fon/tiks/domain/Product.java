package com.fon.tiks.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
@Document(collation = "product")
public class Product {
	
	@Id
	private String mongoId;
	private Long id;
	private String name;
	private double price;
	private String brand;
	private String category;
	private String description;

}
