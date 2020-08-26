package com.fon.tiks.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fon.tiks.domain.Invoice;
import com.fon.tiks.setup.InvoiceSetup;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class InvoiceMapperTest extends BaseMapperTest {

	@Autowired
	InvoiceMapper invoiceMapper;

	@Autowired
	InvoiceItemMapper invoiceItemMapper;
	
	@Autowired
	ProductMapper productMapper;

	@Autowired
	InvoiceSetup invoiceSetup;

	@Test
	void testCrud() {

		log.info("Getting invoices setup");
		List<Invoice> invoices = invoiceSetup.getSetup();

		log.info("Get all invoices");
		invoices = invoiceMapper.getAll();
		assertEquals(2, invoices.size());
		assertEquals(2, invoices.get(0).getItems().size());
		assertEquals(3, invoices.get(1).getItems().size());
		invoices.get(0).getItems().forEach(i -> {
			assertNotNull(i.getProduct());
		});
		
		log.info("Delete all");
		invoices.forEach(i -> {
			invoiceItemMapper.deleteByInvoiceId(i.getId());
			invoiceMapper.deleteById(i.getId());
		});
		productMapper.deleteAll();
		
		assertEquals(0, invoiceMapper.getAll().size());
		assertEquals(0, invoiceItemMapper.getAll().size());
		assertEquals(0, productMapper.getAll().size());
	}

}
