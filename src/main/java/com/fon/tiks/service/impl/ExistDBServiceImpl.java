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
import com.fon.tiks.repository.ExistDBRepository;
import com.fon.tiks.service.GeneralService;
import com.fon.tiks.service.dto.InvoiceSearchRequest;

@Service
@Transactional
@Qualifier("existdb")
public class ExistDBServiceImpl implements GeneralService {
	
	@Autowired
	ExistDBRepository existDBRepository;

	// TODO

	@Monitoring(database = Database.EXISTDB, dataSize = DataSize.SMALL, operation = Operation.INSERT)
	@Override
	public Map<String, Object> insert1000(Map<String, Object> data) {
		@SuppressWarnings("unchecked")
		List<Invoice> invoices = (List<Invoice>) data.get("invoices");
		for (Invoice invoice : invoices) {
			existDBRepository.insert(invoice);
		}
		return data;
	}

	@Monitoring(database = Database.EXISTDB, dataSize = DataSize.MEDIUM, operation = Operation.INSERT)
	@Override
	public Map<String, Object> insert30000(Map<String, Object> data) {
		@SuppressWarnings("unchecked")
		List<Invoice> invoices = (List<Invoice>) data.get("invoices");
		for (Invoice invoice : invoices) {
			existDBRepository.insert(invoice);
		}
		return data;
	}

	@Monitoring(database = Database.EXISTDB, dataSize = DataSize.LARGE, operation = Operation.INSERT)
	@Override
	public Map<String, Object> insert100000(Map<String, Object> data) {
		@SuppressWarnings("unchecked")
		List<Invoice> invoices = (List<Invoice>) data.get("invoices");
		for (Invoice invoice : invoices) {
			existDBRepository.insert(invoice);
		}
		return data;
	}

	@Monitoring(database = Database.EXISTDB, dataSize = DataSize.SMALL, operation = Operation.SELECT)
	@Override
	public List<Invoice> search1000(InvoiceSearchRequest invoiceSearchRequest) {
		return existDBRepository.search(invoiceSearchRequest);
	}

	@Monitoring(database = Database.EXISTDB, dataSize = DataSize.MEDIUM, operation = Operation.SELECT)
	@Override
	public List<Invoice> search30000(InvoiceSearchRequest invoiceSearchRequest) {
		return existDBRepository.search(invoiceSearchRequest);
	}

	@Monitoring(database = Database.EXISTDB, dataSize = DataSize.LARGE, operation = Operation.SELECT)
	@Override
	public List<Invoice> search100000(InvoiceSearchRequest invoiceSearchRequest) {
		return existDBRepository.search(invoiceSearchRequest);
	}
	
	@Monitoring(database = Database.EXISTDB, dataSize = DataSize.SMALL, operation = Operation.UPDATE)
	@Override
	public void update1000(double amount) {
		existDBRepository.updateByAmount(amount);
	}

	@Monitoring(database = Database.EXISTDB, dataSize = DataSize.MEDIUM, operation = Operation.UPDATE)
	@Override
	public void update30000(double amount) {
		existDBRepository.updateByAmount(amount);
	}

	@Monitoring(database = Database.EXISTDB, dataSize = DataSize.LARGE, operation = Operation.UPDATE)
	@Override
	public void update100000(double amount) {
		existDBRepository.updateByAmount(amount);
	}

	@Monitoring(database = Database.EXISTDB, dataSize = DataSize.SMALL, operation = Operation.DELETE)
	@Override
	public void delete1000() {
		existDBRepository.deleteAll();
	}

	@Monitoring(database = Database.EXISTDB, dataSize = DataSize.MEDIUM, operation = Operation.DELETE)
	@Override
	public void delete30000() {
		existDBRepository.deleteAll();
	}

	@Monitoring(database = Database.EXISTDB, dataSize = DataSize.LARGE, operation = Operation.DELETE)
	@Override
	public void delete100000() {
		existDBRepository.deleteAll();
	}
	
}
