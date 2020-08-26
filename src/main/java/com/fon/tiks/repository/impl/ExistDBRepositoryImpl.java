package com.fon.tiks.repository.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

import com.fon.tiks.domain.Invoice;
import com.fon.tiks.domain.InvoiceItem;
import com.fon.tiks.repository.ExistDBRepository;
import com.fon.tiks.service.dto.InvoiceSearchRequest;
import com.fon.tiks.util.ExistDBConnection;
import com.fon.tiks.util.XmlToObjectConverter;

@Repository
public class ExistDBRepositoryImpl implements ExistDBRepository {

	@Override
	public void insert(Invoice invoice) {
		try {
			Collection col = ExistDBConnection.getInstance().getOrCreateCollection();
			XMLResource res = (XMLResource) col.createResource(null, "XMLResource");
			String invoiceXML = 
					"<invoice>"
						+ "<created_on>" + invoice.getCreatedOn() + "</created_on>"
						+ "<total_amount>" + invoice.getTotalAmount() + "</total_amount>"
						+ "<payment_type>" + invoice.getPaymentType() + "</payment_type>"
						+ "<items>";

			for (InvoiceItem item : invoice.getItems()) {
				invoiceXML = invoiceXML 
						+ "<invoice_item>"
						+ 	"<quantity>" + item.getQuantity() + "</quantity>"
						+ 	"<amount>" + item.getAmount() + "</amount>"
						+ 	"<product>" 
						+		"<name>" + item.getProduct().getName() + "</name>"
						+		"<price>" + item.getProduct().getPrice() + "</price>"
						+		"<brand>" + item.getProduct().getBrand() + "</brand>"
						+		"<category>" + item.getProduct().getCategory() + "</category>"
						+		"<description>" + item.getProduct().getDescription() + "</description>"
						+   "</product>"
						+ "</invoice_item>";
			}
			
			invoiceXML = invoiceXML 
						+ "</items>"
					+ "</invoice>";
			
			
			res.setContent(invoiceXML);
			col.storeResource(res);
			col.close();
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Invoice> getAll() {
		List<Invoice> invoices = new ArrayList<>();
		try {
			Collection col = ExistDBConnection.getInstance().getOrCreateCollection();
			XPathQueryService xpqs = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            xpqs.setProperty("indent", "yes");
            
            ResourceSet result = xpqs.query("//invoice");
            ResourceIterator i = result.getIterator();
            while(i.hasMoreResources()) {
            	Resource res = i.nextResource();
            	invoices.add(XmlToObjectConverter.unmarshal(res.getContent().toString()));
            }
    		col.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return invoices;
	}
	
	@Override
	public List<Invoice> search(InvoiceSearchRequest invoiceSearchRequest) {
		List<Invoice> invoices = new ArrayList<>();
		try {
			Collection col = ExistDBConnection.getInstance().getOrCreateCollection();
			XPathQueryService xpqs = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            xpqs.setProperty("indent", "yes");
            
            ResourceSet result = xpqs.query("for $x in //invoice " + 
            		"where $x/total_amount>" + invoiceSearchRequest.getMin() + 
            		" and $x/total_amount<" + invoiceSearchRequest.getMax() + 
            		" and $x/payment_type=\"" + invoiceSearchRequest.getPaymentType() + "\"" + 
            		" return $x");
            ResourceIterator i = result.getIterator();
            while(i.hasMoreResources()) {
            	Resource res = i.nextResource();
            	invoices.add(XmlToObjectConverter.unmarshal(res.getContent().toString()));
            }
    		col.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return invoices;
	}
	
	@Override
	public void updateByAmount(double amount) {
		try {
			Collection col = ExistDBConnection.getInstance().getOrCreateCollection();
			XPathQueryService xpqs = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            xpqs.setProperty("indent", "yes");
            
            xpqs.query("for $x in //invoice"
            		+ " where $x/total_amount>" + amount
            		+ " return update replace $x//created_on with <created_on>" + LocalDateTime.now() + "</created_on>");
    		col.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void deleteAll() {
		try {
			Collection col = ExistDBConnection.getInstance().getOrCreateCollection();
			List<String> resources = Arrays.asList(col.listResources());
			for (String id : resources) {
				Resource resource = col.getResource(id);
				col.removeResource(resource);
			}
			col.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

}
