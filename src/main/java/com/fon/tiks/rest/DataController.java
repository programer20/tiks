package com.fon.tiks.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fon.tiks.domain.Invoice;
import com.fon.tiks.domain.Product;
import com.fon.tiks.service.GeneralService;
import com.fon.tiks.service.dto.InvoiceSearchRequest;
import com.fon.tiks.util.InvoiceSetup;
import com.fon.tiks.util.ProductCsvReader;

@RestController
public class DataController {
	
	@Autowired
	@Qualifier("oracle")
	GeneralService oracleService;
	
	@Autowired
	@Qualifier("mongodb")
	GeneralService mongoDBService;
	
	@Autowired
	@Qualifier("existdb")
	GeneralService existDBService;
	
	private final InvoiceSearchRequest invoiceSearchRequest = InvoiceSearchRequest.builder().min(100).max(150).paymentType("CREDIT CARD").build();
	private final double amount = 700;
	
	@GetMapping("/all")
	public ResponseEntity<?> all() {
		crud1000();
		crud30000();
		crud100000();
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/1000")
	public ResponseEntity<?> crud1000() {
		insert1000();
		update1000();
		search1000();
		delete1000();
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/30000")
	public ResponseEntity<?> crud30000() {
		insert30000();
		update30000();
		search30000();
		delete30000();
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/100000")
	public ResponseEntity<?> crud100000() {
		insert100000();
		update100000();
		search100000();
		delete100000();
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/insert1000")
	public ResponseEntity<?> insert1000() {
		Map<String, Object> data = new HashMap<>();
		List<Product> products = ProductCsvReader.load1000();
		List<Invoice> invoices = InvoiceSetup.loadData(1000, products);
		data.put("products", products);
		data.put("invoices", invoices);
		oracleService.insert1000(data);
		mongoDBService.insert1000(data);
		existDBService.insert1000(data);
		
		return ResponseEntity.ok(data);
	}

	@PostMapping("/insert30000")
	public ResponseEntity<?> insert30000() {
		Map<String, Object> data = new HashMap<>();
		List<Product> products = ProductCsvReader.loadDataSet();
		List<Invoice> invoices = InvoiceSetup.loadData(30000, products);
		data.put("products", products);
		data.put("invoices", invoices);
		oracleService.insert30000(data);
		mongoDBService.insert30000(data);
		existDBService.insert30000(data);
		
		return ResponseEntity.ok(data);
	}

	@PostMapping("/insert100000")
	public ResponseEntity<?> insert100000() {
		Map<String, Object> data = new HashMap<>();
		List<Product> products = ProductCsvReader.load100000();
		List<Invoice> invoices = InvoiceSetup.loadData(100000, products);
		data.put("products", products);
		data.put("invoices", invoices);
		oracleService.insert100000(data);
		mongoDBService.insert100000(data);
		existDBService.insert100000(data);
		
		return ResponseEntity.ok(data);
	}
	
	@GetMapping("/search1000")
	public ResponseEntity<?> search1000() {
		List<Invoice> invoices = oracleService.search1000(invoiceSearchRequest);
		mongoDBService.search1000(invoiceSearchRequest);
		existDBService.search1000(invoiceSearchRequest);

		return ResponseEntity.ok(invoices);
	}
	
	@GetMapping("/search30000")
	public ResponseEntity<?> search30000() {
		List<Invoice> invoices = oracleService.search30000(invoiceSearchRequest);
		mongoDBService.search30000(invoiceSearchRequest);
		existDBService.search30000(invoiceSearchRequest);

		return ResponseEntity.ok(invoices);
	}

	@GetMapping("/search100000")
	public ResponseEntity<?> search100000() {
		List<Invoice> invoices = oracleService.search100000(invoiceSearchRequest);
		mongoDBService.search100000(invoiceSearchRequest);
		existDBService.search100000(invoiceSearchRequest);

		return ResponseEntity.ok(invoices);
	}
	
	@PutMapping("/update1000")
	public ResponseEntity<?> update1000() {
		oracleService.update1000(amount);
		mongoDBService.update1000(amount);
		existDBService.update1000(amount);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/update30000")
	public ResponseEntity<?> update30000() {
		oracleService.update30000(amount);
		mongoDBService.update30000(amount);
		existDBService.update30000(amount);

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/update100000")
	public ResponseEntity<?> update100000() {
		oracleService.update100000(amount);
		mongoDBService.update100000(amount);
		existDBService.update100000(amount);

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/delete1000")
	public ResponseEntity<?> delete1000() {
		oracleService.delete1000();
		mongoDBService.delete1000();
		existDBService.delete1000();
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/delete30000")
	public ResponseEntity<?> delete30000() {
		oracleService.delete30000();
		mongoDBService.delete30000();
		existDBService.delete30000();

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/delete100000")
	public ResponseEntity<?> delete100000() {
		oracleService.delete100000();
		mongoDBService.delete100000();
		existDBService.delete100000();

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
