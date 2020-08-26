package com.fon.tiks.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fon.tiks.domain.Invoice;
import com.fon.tiks.domain.InvoiceItem;
import com.fon.tiks.domain.Product;
import com.fon.tiks.mapper.BaseMapperTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class InvoiceRepositoryTest extends BaseMapperTest {
	
	@Autowired
	InvoiceRepository invoiceRepository;

	@Test
	void testCrud() {

		log.info("Insert invoices");

		List<Invoice> invoices = new ArrayList<>();

		List<Product> products = new ArrayList<>();
		products.add(Product.builder().name("Naziv1").price(2400).brand("Brend1").category("Kategorija1").description("Opis1").build());
		products.add(Product.builder().name("Naziv2").price(3500).brand("Brend2").category("Kategorija2").description("Opis2").build());

		Invoice invoice1 = Invoice.builder().createdOn(LocalDateTime.now()).totalAmount(35000).paymentType("CASH").build();
		List<InvoiceItem> items1 = new ArrayList<>();
		items1.add(InvoiceItem.builder().quantity(2).amount(1000).product(products.get(0)).invoiceId(invoice1.getId())
				.build());
		items1.add(InvoiceItem.builder().quantity(3).amount(2000).product(products.get(1)).invoiceId(invoice1.getId())
				.build());
		invoice1.setItems(items1);

		Invoice invoice2 = Invoice.builder().createdOn(LocalDateTime.now()).totalAmount(50000).paymentType("CARD").build();
		List<InvoiceItem> items2 = new ArrayList<>();
		items2.add(InvoiceItem.builder().quantity(4).amount(4000).product(products.get(0)).invoiceId(invoice2.getId())
				.build());
		items2.add(InvoiceItem.builder().quantity(1).amount(2000).product(products.get(1)).invoiceId(invoice2.getId())
				.build());
		items2.add(InvoiceItem.builder().quantity(2).amount(3000).product(products.get(0)).invoiceId(invoice2.getId())
				.build());
		invoice2.setItems(items2);

		invoices.add(invoice1);
		invoices.add(invoice2);

		invoiceRepository.insert(invoices);
		
		log.info("Get all");
		invoices = invoiceRepository.findAll();
		assertEquals(2, invoices.size());
		
		log.info("Delete all");
		invoiceRepository.deleteAll();
		assertEquals(0, invoiceRepository.findAll().size());
	}

}
