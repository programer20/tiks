package com.fon.tiks.service;

import java.util.List;
import java.util.Map;

import com.fon.tiks.domain.Invoice;
import com.fon.tiks.service.dto.InvoiceSearchRequest;

public interface GeneralService {
	
	public Map<String, Object> insert1000(Map<String, Object> data);

	public Map<String, Object> insert30000(Map<String, Object> data);

	public Map<String, Object> insert100000(Map<String, Object> data);
	
	public List<Invoice> search1000(InvoiceSearchRequest invoiceSearchRequest);
	
	public List<Invoice> search30000(InvoiceSearchRequest invoiceSearchRequest);

	public List<Invoice> search100000(InvoiceSearchRequest invoiceSearchRequest);
	
	public void update1000(double amount);

	public void update30000(double amount);
	
	public void update100000(double amount);
	
	public void delete1000();
	
	public void delete30000();
	
	public void delete100000();

}
