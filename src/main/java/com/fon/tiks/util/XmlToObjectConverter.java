package com.fon.tiks.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fon.tiks.domain.Invoice;

public class XmlToObjectConverter {
	
	public static Invoice unmarshal(String invoiceXml) {
		Invoice invoice = null;
	    try {
	    	XmlMapper xmlMapper = new XmlMapper();
			invoice = xmlMapper.readValue(invoiceXml, Invoice.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return invoice;
	}

}
