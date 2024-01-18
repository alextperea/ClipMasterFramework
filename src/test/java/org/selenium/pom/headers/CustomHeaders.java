package org.selenium.pom.headers;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.selenium.pom.utils.PropertyLoader;

import java.util.Properties;

public class CustomHeaders {

    public static Headers getLoginHeaders() {
            Properties properties = PropertyLoader.loadProperties("config.properties");

            return Headers.headers(
                    new Header("authority", properties.getProperty("authorityHeader")),
                    new Header("accept", properties.getProperty("acceptHeader")),
                    new Header("accept-language", properties.getProperty("acceptLanguageHeader")),
                    new Header("content-type", properties.getProperty("contentTypeHeader")),
                    new Header("Cookie", properties.getProperty("cookieHeader"))
            );
        }

    public static Headers getInvoiceHeaders() {
        Properties properties = PropertyLoader.loadProperties("config.properties");

        return Headers.headers(
                new Header("authority", properties.getProperty("authorityHeader")),
                new Header("accept", properties.getProperty("invoiceAcceptHeader")),
                new Header("accept-language", properties.getProperty("acceptLanguageHeader")),
                new Header("content-type", properties.getProperty("contentTypeHeader")),
                new Header("Cookie", properties.getProperty("cookieHeader"))
        );
    }
    }
