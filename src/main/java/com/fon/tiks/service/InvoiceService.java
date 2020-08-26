package com.fon.tiks.service;

import java.util.List;

import com.fon.tiks.domain.Invoice;
import com.fon.tiks.service.dto.InvoiceSearchRequest;

public interface InvoiceService {

	public Invoice insert(Invoice invoice);

	public Invoice update(Invoice invoice);
	
	public int updateByAmount(double amount);

	public Invoice getById(Long id);

	public List<Invoice> getAll();
	
	public List<Invoice> search(InvoiceSearchRequest invoiceSearchRequest);

	public int deleteById(Long id);
	
	public void deleteAll();

}
