package com.fon.tiks.repository.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.fon.tiks.domain.Invoice;
import com.fon.tiks.repository.InvoiceCustomRepository;

public class InvoiceCustomRepositoryImpl implements InvoiceCustomRepository {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public void updateByAmount(double amount) {
		Query select = Query.query(Criteria.where("totalAmount").gt(amount));
		Update update = new Update();
		update.set("createdOn", LocalDateTime.now());
		mongoTemplate.updateMulti(select, update, Invoice.class);
	}

}
