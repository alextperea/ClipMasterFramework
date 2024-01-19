package org.selenium.pom.apis;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.selenium.pom.headers.CustomHeaders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.selenium.pom.utils.PropertyLoader.loadProperties;

public class Login {

    private Properties properties;
    private String accessToken;
    public String getAccessToken(){
        return accessToken;
    }
    public Login(){
        properties = loadProperties("config.properties");
    }

    public Response login() throws IOException {

        String baseUrl = properties.getProperty("baseURI");
        String endpoint = properties.getProperty("login.Endpoint");

        // Lee el contenido del archivo JSON
        String requestBody = new String(Files.readAllBytes(Paths.get("src/test/resources/loginRequest.json")));

        Response response =
                given()
                        .baseUri(baseUrl)
                        .basePath(endpoint)
                        .headers(CustomHeaders.getLoginHeaders())
                        .body(requestBody)
                .when()
                        .post()
                .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .extract()
                        .response();

        // Guardar el access_token
        accessToken = response.jsonPath().getString("access_token");

        return response;
    }
    public void validateLoginResponse(Response response) {
//        String accessToken = response.jsonPath().getString("access_token");
//        String country = response.jsonPath().getString("country");
//        String appVersionCheckStatus = response.jsonPath().getString("app_version_check.status");
//
//        assertThat(accessToken).isNotEmpty();
//        assertThat(country).isEqualTo("MX");
//        assertThat(appVersionCheckStatus).isEqualTo("ALLOWED");

        SoftAssertions softAssertions = new SoftAssertions(); //los softAssertions

        softAssertions.assertThat(response.jsonPath().getString("access_token")).isNotEmpty();
        softAssertions.assertThat(response.jsonPath().getString("country")).isEqualTo("MX");
        softAssertions.assertThat(response.jsonPath().getString("app_version_check.status")).isEqualTo("ALLOWED");

        softAssertions.assertAll();  // Esto asegura que todas las aserciones se verifiquen incluso si una falla.
    }


}
