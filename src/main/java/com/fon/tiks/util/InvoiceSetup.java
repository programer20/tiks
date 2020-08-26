package com.fon.tiks.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fon.tiks.domain.Invoice;
import com.fon.tiks.domain.InvoiceItem;
import com.fon.tiks.domain.Product;

public class InvoiceSetup {

	private static final List<String> payments = Arrays.asList("CASH", "DEBIT CARD", "CREDIT CARD", "CHEQUE");

	public static List<Invoice> loadData(int dataSize, List<Product> products) {
		List<Invoice> invoices = new ArrayList<>();

		for (int i = 0; i < dataSize; i++) {
			List<InvoiceItem> invoiceItems = new ArrayList<>();
			int itemsCount = (int) ((Math.random() * 4) + 1);
			double totalAmount = 0;
			for (int j = 0; j < itemsCount; j++) {
				Product product = products.get((int) ((Math.random() * (products.size()-1)) + 1));
				int quantity = (int) ((Math.random() * 4) + 1);
				double amount = quantity * product.getPrice();
				totalAmount += amount;
				invoiceItems.add(InvoiceItem.builder().product(product).quantity(quantity).amount(amount).build());
			}
			invoices.add(Invoice.builder().createdOn(LocalDateTime.now()).totalAmount(totalAmount)
					.paymentType(payments.get((int) ((Math.random() * 3) + 1))).items(invoiceItems).build());
		}

		return invoices;
	}

}
