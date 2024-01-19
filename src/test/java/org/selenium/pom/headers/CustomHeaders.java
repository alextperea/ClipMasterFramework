package org.selenium.pom.headers;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.selenium.pom.utils.PropertyLoader;
import java.util.Properties;

public class CustomHeaders {
    static Properties properties = PropertyLoader.loadProperties("config.properties");
    public static Headers getLoginHeaders() {

            return Headers.headers(
                    new Header("authority", properties.getProperty("login.authorityHeader")),
                    new Header("accept", properties.getProperty("login.acceptHeader")),
                    new Header("accept-language", properties.getProperty("login.acceptLanguageHeader")),
                    new Header("content-type", properties.getProperty("login.contentTypeHeader"))
            );
        }
    public static Headers getCategoriesHeaders(String accessToken){

            return Headers.headers(
                    new Header("accept", properties.getProperty("categories.acceptHeader")),
                    new Header("accept-language", properties.getProperty("categories.acceptLanguage")),
                    new Header("authorization", accessToken)
            );
    }
    public static Headers getSingleProductHeaders(String accessToken){
            return Headers.headers(
                    new Header("accept", properties.getProperty("singleProduct.acceptHeader")),
                    new Header("accept-language", properties.getProperty("singleProduct.acceptLanguage")),
                    new Header("content-type", properties.getProperty("singleProduct.contentType")),
                    new Header("authorization", accessToken)
            );
    }
    }
