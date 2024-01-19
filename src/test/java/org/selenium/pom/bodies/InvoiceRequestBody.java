package org.selenium.pom.bodies;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InvoiceRequestBody {
    @JsonProperty("invoicing")
    private InvoicingDetails invoicing;

    public InvoiceRequestBody(InvoicingDetails invoicing) {
        this.invoicing = invoicing;
    }

    public static class InvoicingDetails {
        @JsonProperty("tax_payer_id")
        private String taxPayerId;
        @JsonProperty("legal_entity_name")
        private String legalEntityName;
        @JsonProperty("legal_entity_last_name")
        private String legalEntityLastName;
        @JsonProperty("legal_entity_second_last_name")
        private String legalEntitySecondLastName;
        @JsonProperty("entity_type")
        private String entityType;
        @JsonProperty("fiscal_address_postal_code")
        private String fiscalAddressPostalCode;
        @JsonProperty("sat_tax_regime_code")
        private String satTaxRegimeCode;
        @JsonProperty("sat_invoice_use_code")
        private String satInvoiceUseCode;


        // Constructor vacío necesario para la deserialización JSON
        public InvoicingDetails() {
        }

        // Constructor con parametros
        public InvoicingDetails(String taxPayerId, String legalEntityName, String legalEntityLastName,
                                  String legalEntitySecondLastName, String fiscalAddressPostalCode,
                                  String satTaxRegimeCode, String satInvoiceUseCode, String entityType) {
            this.taxPayerId = taxPayerId;
            this.legalEntityName = legalEntityName;
            this.legalEntityLastName = legalEntityLastName;
            this.legalEntitySecondLastName = legalEntitySecondLastName;
            this.entityType = entityType;
            this.fiscalAddressPostalCode = fiscalAddressPostalCode;
            this.satTaxRegimeCode = satTaxRegimeCode;
            this.satInvoiceUseCode = satInvoiceUseCode;
        }


        //Getters y Setters

        public String getTaxPayerId() {
            return taxPayerId;
        }

        public void setTaxPayerId(String taxPayerId) {
            this.taxPayerId = taxPayerId;
        }
        public String getLegalEntityName() {
            return legalEntityName;
        }

        public void setLegalEntityName(String legalEntityName) {
            this.legalEntityName = legalEntityName;
        }

        public String getLegalEntityLastName() {
            return legalEntityLastName;
        }

        public void setLegalEntityLastName(String legalEntityLastName) {
            this.legalEntityLastName = legalEntityLastName;
        }

        public String getLegalEntitySecondLastName() {
            return legalEntitySecondLastName;
        }

        public void setLegalEntitySecondLastName(String legalEntitySecondLastName) {
            this.legalEntitySecondLastName = legalEntitySecondLastName;
        }

        public String getEntityType() {
            return entityType;
        }

        public void setEntityType(String entityType) {
            this.entityType = entityType;
        }

        public String getFiscalAddressPostalCode() {
            return fiscalAddressPostalCode;
        }

        public void setFiscalAddressPostalCode(String fiscalAddressPostalCode) {
            this.fiscalAddressPostalCode = fiscalAddressPostalCode;
        }

        public String getSatTaxRegimeCode() {
            return satTaxRegimeCode;
        }

        public void setSatTaxRegimeCode(String satTaxRegimeCode) {
            this.satTaxRegimeCode = satTaxRegimeCode;
        }

        public String getSatInvoiceUseCode() {
            return satInvoiceUseCode;
        }

        public void setSatInvoiceUseCode(String satInvoiceUseCode) {
            this.satInvoiceUseCode = satInvoiceUseCode;
        }
    }
}

