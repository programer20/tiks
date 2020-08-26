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
import com.fon.tiks.repository.InvoiceRepository;
import com.fon.tiks.service.GeneralService;
import com.fon.tiks.service.dto.InvoiceSearchRequest;

@Service
@Transactional
@Qualifier("mongodb")
public class MongoDBServiceImpl implements GeneralService {
	
	@Autowired
	InvoiceRepository invoiceRepository;

	@Monitoring(database = Database.MONGODB, dataSize = DataSize.SMALL, operation = Operation.INSERT)
	@Override
	public Map<String, Object> insert1000(Map<String, Object> data) {
		@SuppressWarnings("unchecked")
		List<Invoice> invoices = (List<Invoice>) data.get("invoices");
		invoiceRepository.saveAll(invoices);
		return data;
	}

	@Monitoring(database = Database.MONGODB, dataSize = DataSize.MEDIUM, operation = Operation.INSERT)
	@Override
	public Map<String, Object> insert30000(Map<String, Object> data) {
		@SuppressWarnings("unchecked")
		List<Invoice> invoices = (List<Invoice>) data.get("invoices");
		invoiceRepository.saveAll(invoices);
		return data;
	}

	@Monitoring(database = Database.MONGODB, dataSize = DataSize.LARGE, operation = Operation.INSERT)
	@Override
	public Map<String, Object> insert100000(Map<String, Object> data) {
		@SuppressWarnings("unchecked")
		List<Invoice> invoices = (List<Invoice>) data.get("invoices");
		invoiceRepository.saveAll(invoices);
		return data;
	}

	@Monitoring(database = Database.MONGODB, dataSize = DataSize.SMALL, operation = Operation.SELECT)
	@Override
	public List<Invoice> search1000(InvoiceSearchRequest invoiceSearchRequest) {
		double min = invoiceSearchRequest.getMin();
		double max = invoiceSearchRequest.getMax();
		String paymentType = invoiceSearchRequest.getPaymentType();
		return invoiceRepository.findByTotalAmountBetweenAndPaymentType(min, max, paymentType);
	}

	@Monitoring(database = Database.MONGODB, dataSize = DataSize.MEDIUM, operation = Operation.SELECT)
	@Override
	public List<Invoice> search30000(InvoiceSearchRequest invoiceSearchRequest) {
		double min = invoiceSearchRequest.getMin();
		double max = invoiceSearchRequest.getMax();
		String paymentType = invoiceSearchRequest.getPaymentType();
		return invoiceRepository.findByTotalAmountBetweenAndPaymentType(min, max, paymentType);
	}

	@Monitoring(database = Database.MONGODB, dataSize = DataSize.LARGE, operation = Operation.SELECT)
	@Override
	public List<Invoice> search100000(InvoiceSearchRequest invoiceSearchRequest) {
		double min = invoiceSearchRequest.getMin();
		double max = invoiceSearchRequest.getMax();
		String paymentType = invoiceSearchRequest.getPaymentType();
		return invoiceRepository.findByTotalAmountBetweenAndPaymentType(min, max, paymentType);
	}
	
	@Monitoring(database = Database.MONGODB, dataSize = DataSize.SMALL, operation = Operation.UPDATE)
	@Override
	public void update1000(double amount) {
		invoiceRepository.updateByAmount(amount);
	}

	@Monitoring(database = Database.MONGODB, dataSize = DataSize.MEDIUM, operation = Operation.UPDATE)
	@Override
	public void update30000(double amount) {
		invoiceRepository.updateByAmount(amount);
	}

	@Monitoring(database = Database.MONGODB, dataSize = DataSize.LARGE, operation = Operation.UPDATE)
	@Override
	public void update100000(double amount) {
		invoiceRepository.updateByAmount(amount);
	}

	@Monitoring(database = Database.MONGODB, dataSize = DataSize.SMALL, operation = Operation.DELETE)
	@Override
	public void delete1000() {
		invoiceRepository.deleteAll();
	}

	@Monitoring(database = Database.MONGODB, dataSize = DataSize.MEDIUM, operation = Operation.DELETE)
	@Override
	public void delete30000() {
		invoiceRepository.deleteAll();
	}

	@Monitoring(database = Database.MONGODB, dataSize = DataSize.LARGE, operation = Operation.DELETE)
	@Override
	public void delete100000() {
		invoiceRepository.deleteAll();
	}
	
}
