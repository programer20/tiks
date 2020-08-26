package com.fon.tiks.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fon.tiks.domain.InvoiceItem;

@Mapper
public interface InvoiceItemMapper {
	
	public int insert(InvoiceItem invoiceItem);

	public int update(InvoiceItem invoiceItem);
	
	public List<InvoiceItem> getAll();

	public List<InvoiceItem> getByInvoiceId(@Param("id") Long id);

	public int deleteByInvoiceId(@Param("id") Long id);
	
	public int deleteAll();

}
