package com.fon.tiks.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fon.tiks.domain.Product;

public class ProductCsvReader {
	
	public static List<Product> loadDataSet() {
		List<Product> products = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/main/resources/static/amazon_com.csv"));
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				String name = values[0];
				Double price = Double.valueOf(values[1]);
				String brand = values[2];
				String category = values[3];
				String description = values[4];
				products.add(Product.builder().name(name).price(price).brand(brand).category(category)
						.description(description).build());
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return products;
	}

	public static List<Product> load1000() {
		return loadDataSet().subList(0, 1000);
	}
	
	public static List<Product> load100000() {
		List<Product> products = loadDataSet();
		products.addAll(loadDataSet());
		products.addAll(loadDataSet());
		products.addAll(loadDataSet().subList(0, 5164));
		return products;
	}

}
