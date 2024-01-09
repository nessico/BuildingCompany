package com.solvd.buildingcompany.util.parser;

import com.solvd.buildingcompany.model.Supplier;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class SupplierSAXParserUtil {

    public static List<Supplier> parseSuppliers(String filePath) {
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            SupplierSAXHandler handler = new SupplierSAXHandler();
            reader.setContentHandler(handler);

            reader.parse(new InputSource(new FileReader(filePath)));

            return handler.getSuppliers();
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
