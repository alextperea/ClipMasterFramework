package org.selenium.pom.apis;

import io.restassured.response.Response;
import org.selenium.pom.bodies.InvoiceRequestBody;

import java.util.Properties;
import static org.selenium.pom.utils.PropertyLoader.loadProperties;

public class InvoiceInformationEndpoint {
    private Properties properties;
    public InvoiceInformationEndpoint(){
        properties = loadProperties("config.properties");
    }

    public Response AddInvoiceInfo(){
        String baseUrl = properties.getProperty("baseURI");
        String endpoint = properties.getProperty("invoiceEndpoint");

        String taxPayerId = properties.getProperty("tax_payer_id");
        String legalEntityName = properties.getProperty("legal_entity_name");
        String legalEntityLastName = properties.getProperty("legal_entity_last_name");
        String legalEntitySecondLastName = properties.getProperty("legal_entity_second_last_name");
        String entityType = properties.getProperty("entity_type");
        String fiscalAddressPostalCode = properties.getProperty("fiscal_address_postal_code");
        String satTaxRegimeCode = properties.getProperty("sat_tax_regime_code");
        String satInvoiceUseCode = properties.getProperty("sat_invoice_use_code");

        //Construir el objeto del Body Request
        InvoiceRequestBody.InvoicingDetails invoiceRequestBodyObj = new InvoiceRequestBody.InvoicingDetails();
        invoiceRequestBodyObj.setTaxPayerId(taxPayerId);
        invoiceRequestBodyObj.setLegalEntityName(legalEntityName);
        invoiceRequestBodyObj.setLegalEntityLastName("");
        invoiceRequestBodyObj.setFiscalAddressPostalCode(fiscalAddressPostalCode);
        invoiceRequestBodyObj.setSatTaxRegimeCode(satTaxRegimeCode);
        invoiceRequestBodyObj.setSatInvoiceUseDescription();




    }
}
