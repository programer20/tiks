package com.fon.tiks.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fon.tiks.domain.Product;

@Mapper
public interface ProductMapper {
	
	public int insert(Product product);
	
	public int update(Product product);
	
	public Product getById(@Param("id") Long id);
	
	public List<Product> getAll();
	
	public int deleteById(@Param("id") Long id);

	public int deleteAll();

}
