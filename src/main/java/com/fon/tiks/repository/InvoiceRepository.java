package com.fon.tiks.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fon.tiks.domain.Invoice;

public interface InvoiceRepository extends MongoRepository<Invoice, String>, InvoiceCustomRepository {
	
	public List<Invoice> findByTotalAmountBetweenAndPaymentType(double min, double max, String paymentType);

}
