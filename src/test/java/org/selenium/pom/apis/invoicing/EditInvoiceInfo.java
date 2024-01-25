package org.selenium.pom.apis.invoicing;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.selenium.pom.headers.CustomHeaders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.selenium.pom.utils.PropertyLoader.loadProperties;

public class EditInvoiceInfo {
    private String accessToken;
    private Properties properties;
    public EditInvoiceInfo(String accessToken){
        this.accessToken=accessToken;
        properties = loadProperties("config.properties");
    }

    public Response editInvoiceInfo() throws IOException {
        String baseUri = properties.getProperty("invoice.baseURI");
        String endpoint = properties.getProperty("invoice.endpoint");

        // Lee el contenido del archivo JSON
        String requestBody = new String(Files.readAllBytes(Paths.get("src/test/resources/invoiceRequest.json")));

        Response response =
                given()
                        .filter(new AllureRestAssured())
                        .baseUri(baseUri)
                        .basePath(endpoint)
                        .headers(CustomHeaders.getInvoiceHeaders(accessToken))
                        .body(requestBody).log().body()
                .when()
                        .post()
                .then()
                        .statusCode(200).log().everything()
                        .extract().response();

        return response;
    }

    }

