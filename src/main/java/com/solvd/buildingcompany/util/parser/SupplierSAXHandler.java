package com.solvd.buildingcompany.util.parser;

import com.solvd.buildingcompany.model.Supplier;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SupplierSAXHandler extends DefaultHandler {

    private List<Supplier> suppliers;
    private Supplier supplier;
    private StringBuilder data;

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("supplier")) {
            supplier = new Supplier();
            supplier.setSupplierId(Integer.parseInt(attributes.getValue("supplierId")));
        }
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("supplier")) {
            if (suppliers == null) {
                suppliers = new ArrayList<>();
            }
            suppliers.add(supplier);
        } else if (qName.equalsIgnoreCase("supplierName")) {
            supplier.setSupplierName(data.toString());
        } else if (qName.equalsIgnoreCase("contactInfo")) {
            supplier.setContactInfo(data.toString());
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }
}
