package com.fon.tiks.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Invoice {

	@Id
	private String mongoId;
	private Long id;
	@JsonProperty("created_on")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class, as = LocalDateTime.class)
	private LocalDateTime createdOn;
	@JsonProperty("total_amount")
	private double totalAmount;
	@JsonProperty("payment_type")
	private String paymentType;
	private List<InvoiceItem> items;
	
	public Invoice() {
	}

	public Invoice(String mongoId, Long id, LocalDateTime createdOn, double totalAmount, String paymentType,
			List<InvoiceItem> items) {
		super();
		this.mongoId = mongoId;
		this.id = id;
		this.createdOn = createdOn;
		this.totalAmount = totalAmount;
		this.paymentType = paymentType;
		this.items = items;
	}

	public String getMongoId() {
		return mongoId;
	}

	public void setMongoId(String mongoId) {
		this.mongoId = mongoId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public List<InvoiceItem> getItems() {
		return items;
	}

	public void setItems(List<InvoiceItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Invoice [mongoId=" + mongoId + ", id=" + id + ", createdOn=" + createdOn + ", totalAmount="
				+ totalAmount + ", paymentType=" + paymentType + ", items=" + items + "]";
	}
	
}
