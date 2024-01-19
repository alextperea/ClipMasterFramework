package org.selenium.pom.headers;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.selenium.pom.apis.Login;
import org.selenium.pom.apis.LoginEP;
import org.selenium.pom.utils.PropertyLoader;

import java.util.Properties;

public class CustomHeaders {

    public static Headers getLoginHeaders() {
            Properties properties = PropertyLoader.loadProperties("config.properties");

            return Headers.headers(
                    new Header("authority", properties.getProperty("login.authorityHeader")),
                    new Header("accept", properties.getProperty("login.acceptHeader")),
                    new Header("accept-language", properties.getProperty("login.acceptLanguageHeader")),
                    new Header("content-type", properties.getProperty("login.contentTypeHeader"))
            );
        }

    public static Headers getInvoiceHeaders(Login login) {
        //Login login = new Login(); //creamos una instancia de la clase LoginEP para poder usar el access_token
        //String accessToken = login.getAccessToken(); // guardamos el access_token en una variable
        Properties properties = PropertyLoader.loadProperties("config.properties");

        return Headers.headers(
                new Header("accept", properties.getProperty("invoice.AcceptHeader")),
                new Header("accept-language", properties.getProperty("invoice.Accept-language")),
                new Header("authorization", "Bearer " + login.getAccessToken()), // aca ya podemos entonces utilizar el access token. el Bearer es parte del OAUTH2
                new Header("content-type", properties.getProperty("login.contentTypeHeader"))
        );
    }

    public static Headers getCategoriesHeaders(Login login) {
        Properties properties = PropertyLoader.loadProperties("config.properties");

        return Headers.headers(
                new Header("accept", properties.getProperty("categories.accept")),
                new Header("accept-language", properties.getProperty("categories.accept-language")),
                new Header("authorization", "Bearer " + login.getAccessToken()) // aca ya podemos entonces utilizar el access token. el Bearer es parte del OAUTH2
        );
    }
    }
