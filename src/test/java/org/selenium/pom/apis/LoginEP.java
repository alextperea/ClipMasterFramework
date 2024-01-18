package org.selenium.pom.apis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.selenium.pom.bodies.LoginRequestBody;
import org.selenium.pom.headers.CustomHeaders;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Properties;
import static io.restassured.RestAssured.given;
import static org.selenium.pom.utils.PropertyLoader.loadProperties;

public class LoginEP {
    private Properties properties;
    public LoginEP() {
        properties = loadProperties("config.properties");
    }

    public Response login() throws JsonProcessingException {
        String baseUrl = properties.getProperty("baseURI");
        String endpoint = properties.getProperty("loginEndpoint");
        String email = properties.getProperty("email");
        String password = properties.getProperty("password");


        // Construir el objeto del Body Request
        LoginRequestBody requestBodyObj = new LoginRequestBody();
        requestBodyObj.setEmail(email);
        requestBodyObj.setPassword(password);
        requestBodyObj.setType("LOGIN");
        requestBodyObj.setSource("M_DASHBOARD");

        // Serializar el objeto a JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(requestBodyObj);


        return
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
    }

    public void validateLoginResponse(Response response) {
        String accessToken = response.jsonPath().getString("access_token");
        String country = response.jsonPath().getString("country");
        String appVersionCheckStatus = response.jsonPath().getString("app_version_check.status");

        assertThat(accessToken).isNotEmpty();
        assertThat(country).isEqualTo("MX");
        assertThat(appVersionCheckStatus).isEqualTo("ALLOWED");
    }
}
