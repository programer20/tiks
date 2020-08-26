package com.fon.tiks.service;

import java.util.List;

import com.fon.tiks.domain.InvoiceItem;

public interface InvoiceItemService {

	public InvoiceItem insert(InvoiceItem invoiceItem);

	public InvoiceItem update(InvoiceItem invoiceItem);

	public List<InvoiceItem> getByInvoiceId(Long id);

	public List<InvoiceItem> getAll();

	public int deleteByInvoiceId(Long id);

}
