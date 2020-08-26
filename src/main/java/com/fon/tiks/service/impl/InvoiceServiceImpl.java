package com.fon.tiks.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fon.tiks.domain.Invoice;
import com.fon.tiks.mapper.InvoiceItemMapper;
import com.fon.tiks.mapper.InvoiceMapper;
import com.fon.tiks.service.InvoiceService;
import com.fon.tiks.service.dto.InvoiceSearchRequest;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {
	
	@Autowired
	InvoiceMapper invoiceMapper;
	
	@Autowired
	InvoiceItemMapper invoiceItemMapper;

	@Override
	public Invoice insert(Invoice invoice) {
		invoiceMapper.insert(invoice);
		invoice.getItems().forEach(i -> {
			i.setInvoiceId(invoice.getId());
			invoiceItemMapper.insert(i);
		});
		return invoice;
	}

	@Override
	public Invoice update(Invoice invoice) {
		invoiceMapper.update(invoice);
		invoice.getItems().forEach(i -> {
			invoiceItemMapper.update(i);
		});
		return invoice;
	}

	@Override
	public int updateByAmount(double amount) {
		return invoiceMapper.updateByAmount(amount);
	}

	@Override
	public Invoice getById(Long id) {
		return invoiceMapper.getById(id);
	}

	@Override
	public List<Invoice> getAll() {
		return invoiceMapper.getAll();
	}
	
	@Override
	public List<Invoice> search(InvoiceSearchRequest invoiceSearchRequest) {
		return invoiceMapper.search(invoiceSearchRequest);
	}

	@Override
	public int deleteById(Long id) {
		invoiceItemMapper.deleteByInvoiceId(id);
		return invoiceMapper.deleteById(id);
	}

	@Override
	public void deleteAll() {
		invoiceItemMapper.deleteAll();
		invoiceMapper.deleteAll();
	}

}
