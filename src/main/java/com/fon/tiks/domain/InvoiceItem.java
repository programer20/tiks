package com.fon.tiks.domain;

import org.springframework.data.annotation.Id;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class InvoiceItem {
	
	@Id
	private String mongoId;
	private Long id;
	private int quantity;
	private double amount;
	private Product product;
	private Long invoiceId;
	
	public InvoiceItem() {
	}

	public InvoiceItem(String mongoId, Long id, int quantity, double amount, Product product, Long invoiceId) {
		super();
		this.mongoId = mongoId;
		this.id = id;
		this.quantity = quantity;
		this.amount = amount;
		this.product = product;
		this.invoiceId = invoiceId;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	@Override
	public String toString() {
		return "InvoiceItem [mongoId=" + mongoId + ", id=" + id + ", quantity=" + quantity + ", amount=" + amount
				+ ", product=" + product + ", invoiceId=" + invoiceId + "]";
	}
	
}
