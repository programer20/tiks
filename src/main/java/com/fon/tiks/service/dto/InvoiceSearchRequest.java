package com.fon.tiks.service.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class InvoiceSearchRequest {
	
	private double min;
	private double max;
	private String paymentType;

}
