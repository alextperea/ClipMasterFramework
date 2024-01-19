package org.selenium.pom.headers;

import io.restassured.http.Header;
import io.restassured.http.Headers;
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
    }
