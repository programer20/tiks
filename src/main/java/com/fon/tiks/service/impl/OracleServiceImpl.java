package com.fon.tiks.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fon.tiks.annotation.Monitoring;
import com.fon.tiks.annotation.Monitoring.DataSize;
import com.fon.tiks.annotation.Monitoring.Database;
import com.fon.tiks.annotation.Monitoring.Operation;
import com.fon.tiks.domain.Invoice;
import com.fon.tiks.domain.Product;
import com.fon.tiks.service.GeneralService;
import com.fon.tiks.service.InvoiceService;
import com.fon.tiks.service.ProductService;
import com.fon.tiks.service.dto.InvoiceSearchRequest;

@Service
@Transactional
@Qualifier("oracle")
public class OracleServiceImpl implements GeneralService {
	
	@Autowired
	InvoiceService invoiceService;
	
	@Autowired
	ProductService productService;

	@Monitoring(database = Database.ORACLE, dataSize = DataSize.SMALL, operation = Operation.INSERT)
	@Override
	public Map<String, Object> insert1000(Map<String, Object> data) {
		@SuppressWarnings("unchecked")
		List<Product> products = (List<Product>) data.get("products");
		for (Product product : products) {
			productService.insert(product);
		}
		@SuppressWarnings("unchecked")
		List<Invoice> invoices = (List<Invoice>) data.get("invoices");
		for (Invoice invoice : invoices) {
			invoiceService.insert(invoice);
		}
		return data;
	}

	@Monitoring(database = Database.ORACLE, dataSize = DataSize.MEDIUM, operation = Operation.INSERT)
	@Override
	public Map<String, Object> insert30000(Map<String, Object> data) {
		@SuppressWarnings("unchecked")
		List<Product> products = (List<Product>) data.get("products");
		for (Product product : products) {
			productService.insert(product);
		}
		@SuppressWarnings("unchecked")
		List<Invoice> invoices = (List<Invoice>) data.get("invoices");
		for (Invoice invoice : invoices) {
			invoiceService.insert(invoice);
		}
		return data;
	}

	@Monitoring(database = Database.ORACLE, dataSize = DataSize.LARGE, operation = Operation.INSERT)
	@Override
	public Map<String, Object> insert100000(Map<String, Object> data) {
		@SuppressWarnings("unchecked")
		List<Product> products = (List<Product>) data.get("products");
		for (Product product : products) {
			productService.insert(product);
		}
		@SuppressWarnings("unchecked")
		List<Invoice> invoices = (List<Invoice>) data.get("invoices");
		for (Invoice invoice : invoices) {
			invoiceService.insert(invoice);
		}
		return data;
	}
	
	@Monitoring(database = Database.ORACLE, dataSize = DataSize.SMALL, operation = Operation.SELECT)
	@Override
	public List<Invoice> search1000(InvoiceSearchRequest invoiceSearchRequest) {
		return invoiceService.search(invoiceSearchRequest);
	}

	@Monitoring(database = Database.ORACLE, dataSize = DataSize.MEDIUM, operation = Operation.SELECT)
	@Override
	public List<Invoice> search30000(InvoiceSearchRequest invoiceSearchRequest) {
		return invoiceService.search(invoiceSearchRequest);
	}

	@Monitoring(database = Database.ORACLE, dataSize = DataSize.LARGE, operation = Operation.SELECT)
	@Override
	public List<Invoice> search100000(InvoiceSearchRequest invoiceSearchRequest) {
		return invoiceService.search(invoiceSearchRequest);
	}
	
	@Monitoring(database = Database.ORACLE, dataSize = DataSize.SMALL, operation = Operation.UPDATE)
	@Override
	public void update1000(double amount) {
		invoiceService.updateByAmount(amount);
	}
	
	@Monitoring(database = Database.ORACLE, dataSize = DataSize.MEDIUM, operation = Operation.UPDATE)
	@Override
	public void update30000(double amount) {
		invoiceService.updateByAmount(amount);
	}

	@Monitoring(database = Database.ORACLE, dataSize = DataSize.LARGE, operation = Operation.UPDATE)
	@Override
	public void update100000(double amount) {
		invoiceService.updateByAmount(amount);
	}

	@Monitoring(database = Database.ORACLE, dataSize = DataSize.SMALL, operation = Operation.DELETE)
	@Override
	public void delete1000() {
		invoiceService.deleteAll();
		productService.deleteAll();
	}
	
	@Monitoring(database = Database.ORACLE, dataSize = DataSize.MEDIUM, operation = Operation.DELETE)
	@Override
	public void delete30000() {
		invoiceService.deleteAll();
		productService.deleteAll();
	}

	@Monitoring(database = Database.ORACLE, dataSize = DataSize.LARGE, operation = Operation.DELETE)
	@Override
	public void delete100000() {
		invoiceService.deleteAll();
		productService.deleteAll();
	}

}
