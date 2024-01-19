package org.selenium.pom.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.selenium.pom.apis.login.Login;
import org.selenium.pom.apis.login.LoginEP;
import org.selenium.pom.apis.login.LoginEndpoint;
import org.testng.annotations.Test;

import java.io.IOException;

public class APITestCases {
    @Test
    public void LoginExec() throws IOException {
        Login login = new Login();

        Response response =
                            login.login();
                            login.validateLoginResponse(response);
    }
}
