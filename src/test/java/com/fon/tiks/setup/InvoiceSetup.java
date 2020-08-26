package com.fon.tiks.setup;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fon.tiks.domain.Invoice;
import com.fon.tiks.domain.InvoiceItem;
import com.fon.tiks.domain.Product;
import com.fon.tiks.mapper.InvoiceItemMapper;
import com.fon.tiks.mapper.InvoiceMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class InvoiceSetup {

	@Autowired
	InvoiceMapper invoiceMapper;
	
	@Autowired
	InvoiceItemMapper invoiceItemMapper;
	
	@Autowired
	ProductSetup productSetup;
	
	public List<Invoice> getSetup() {
		List<Invoice> setup = new ArrayList<>();
		
		log.info("Getting products setup");
		List<Product> products = productSetup.getSetup();
		
		Invoice invoice1 = Invoice.builder().totalAmount(35000).paymentType("CASH").build();
		invoiceMapper.insert(invoice1);
		List<InvoiceItem> items1 = new ArrayList<>();
		items1.add(InvoiceItem.builder().quantity(2).amount(1000).product(products.get(0)).invoiceId(invoice1.getId()).build());
		items1.add(InvoiceItem.builder().quantity(3).amount(2000).product(products.get(1)).invoiceId(invoice1.getId()).build());
		invoice1.setItems(items1);
		invoice1.getItems().forEach(ii -> {
			invoiceItemMapper.insert(ii);
		});
		
		Invoice invoice2 = Invoice.builder().totalAmount(50000).paymentType("CARD").build();
		invoiceMapper.insert(invoice2);
		List<InvoiceItem> items2 = new ArrayList<>();
		items2.add(InvoiceItem.builder().quantity(4).amount(4000).product(products.get(0)).invoiceId(invoice2.getId()).build());
		items2.add(InvoiceItem.builder().quantity(1).amount(2000).product(products.get(1)).invoiceId(invoice2.getId()).build());
		items2.add(InvoiceItem.builder().quantity(2).amount(3000).product(products.get(0)).invoiceId(invoice2.getId()).build());
		invoice2.setItems(items2);
		invoice2.getItems().forEach(ii -> {
			invoiceItemMapper.insert(ii);
		});
		
		setup.add(invoice1);
		setup.add(invoice2);
		
		return setup;
	}
	
	
}
