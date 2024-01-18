package org.selenium.pom.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.selenium.pom.apis.LoginEP;
import org.selenium.pom.apis.LoginEndpoint;
import org.testng.annotations.Test;

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

        Response response = login.login();
        login.validateLoginResponse(response);
    }

}
