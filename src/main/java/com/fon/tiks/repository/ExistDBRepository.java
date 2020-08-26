package com.fon.tiks.repository;

import java.util.List;

import com.fon.tiks.domain.Invoice;
import com.fon.tiks.service.dto.InvoiceSearchRequest;

public interface ExistDBRepository {
	
	public void insert(Invoice invoice);
	
	public List<Invoice> getAll();
	
	public List<Invoice> search(InvoiceSearchRequest invoiceSearchRequest);
	
	public void updateByAmount(double amount);
	
	public void deleteAll();

}
