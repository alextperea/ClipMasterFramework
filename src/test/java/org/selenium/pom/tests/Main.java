package org.selenium.pom.tests;

import org.junit.Test;
import org.selenium.pom.apis.LoginEndpoint;

public class Main {

    @Test
    public void APIExecution(){

        LoginEndpoint loginEndpoint = new LoginEndpoint();
        loginEndpoint.login();


    }
}
