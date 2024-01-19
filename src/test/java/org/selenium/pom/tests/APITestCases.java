package org.selenium.pom.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.selenium.pom.apis.*;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

public class APITestCases {

    @Test
    public void APIExecution(){

        LoginEndpoint loginEndpoint = new LoginEndpoint();
        loginEndpoint.login();
    }

    @Test
    public void LoginEPExecution() throws JsonProcessingException {
        LoginEP login = new LoginEP();

        Response response =
                            login.login();
                            login.validateLoginResponse(response);
    }

    @Test
    public void LoginExec() throws IOException {
        Login login = new Login();

        Response response =
                            login.login();
                            login.validateLoginResponse(response);
    }

    @Test
    public void InvoiceEPExecution() throws IOException, InterruptedException {
        Login login = new Login();
        Response responseLogin =
                                login.login();
                                login.validateLoginResponse(responseLogin);

        InvoiceInformationEndpoint invoiceInformationEndpoint = new InvoiceInformationEndpoint();
        Response responseInvoice =
                                invoiceInformationEndpoint.AddInvoiceInfo(login);
                                invoiceInformationEndpoint.validateInvoicingResponse(responseInvoice);
    }

    @Test
    public void CategoriesAPIExecution() throws IOException {
        Login login = new Login();
        Response responseLogin =
                                login.login();
                                login.validateLoginResponse(responseLogin);

        GetCategories getCategories = new GetCategories();
        Response responseCategories =
                                     getCategories.getCategories(login);
                                     getCategories.validateCategoriesResponse(responseCategories);

    }


}
