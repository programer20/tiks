package com.fon.tiks.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fon.tiks.domain.Invoice;
import com.fon.tiks.service.dto.InvoiceSearchRequest;

@Mapper
public interface InvoiceMapper {

	public int insert(Invoice invoice);

	public int update(Invoice invoice);
	
	public int updateByAmount(@Param("amount") double amount);

	public Invoice getById(@Param("id") Long id);

	public List<Invoice> getAll();
	
	public List<Invoice> search(@Param("request") InvoiceSearchRequest invoiceSearchRequest);

	public int deleteById(@Param("id") Long id);
	
	public int deleteAll();
	
}
