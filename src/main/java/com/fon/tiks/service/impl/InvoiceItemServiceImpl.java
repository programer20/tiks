package com.fon.tiks.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fon.tiks.domain.InvoiceItem;
import com.fon.tiks.mapper.InvoiceItemMapper;
import com.fon.tiks.service.InvoiceItemService;

@Service
@Transactional
public class InvoiceItemServiceImpl implements InvoiceItemService {
	
	@Autowired
	InvoiceItemMapper invoiceItemMapper;

	@Override
	public InvoiceItem insert(InvoiceItem invoiceItem) {
		invoiceItemMapper.insert(invoiceItem);
		return invoiceItem;
	}

	@Override
	public InvoiceItem update(InvoiceItem invoiceItem) {
		invoiceItemMapper.update(invoiceItem);
		return invoiceItem;
	}

	@Override
	public List<InvoiceItem> getByInvoiceId(Long id) {
		return invoiceItemMapper.getByInvoiceId(id);
	}

	@Override
	public List<InvoiceItem> getAll() {
		return invoiceItemMapper.getAll();
	}

	@Override
	public int deleteByInvoiceId(Long id) {
		return invoiceItemMapper.deleteByInvoiceId(id);
	}

}
