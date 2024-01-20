package org.selenium.pom.apis.login;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.selenium.pom.headers.CustomHeaders;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import static io.restassured.RestAssured.given;
import static org.selenium.pom.utils.PropertyLoader.loadProperties;

public class Login {
    private Properties properties;
    public String accessToken;
    public String getAccessToken(){
        return accessToken;
    }
    public Login(){
        properties = loadProperties("config.properties");
    }

    public Response login() throws IOException {

        String baseUri = properties.getProperty("login.baseURI");
        String endpoint = properties.getProperty("login.endpoint");

        // Lee el contenido del archivo JSON
        String requestBody = new String(Files.readAllBytes(Paths.get("src/test/resources/loginRequest.json")));

        Response response =
                given()
                        .baseUri(baseUri)
                        .basePath(endpoint)
                        .headers(CustomHeaders.getLoginHeaders()).log().headers()
                        .body(requestBody).log().body()
                .when()
                        .post()
                .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON).log().all()
                        .extract()
                        .response();

        // Guardar el access_token
        accessToken = response.jsonPath().getString("access_token");

        return response;
    }
    public void validateLoginResponse(Response response) {
        SoftAssertions softAssertions = new SoftAssertions(); //los softAssertions

        softAssertions.assertThat(response.jsonPath().getString("access_token")).isNotEmpty();
        softAssertions.assertThat(response.jsonPath().getString("country")).isEqualTo("MX");
        softAssertions.assertThat(response.jsonPath().getString("app_version_check.status")).isEqualTo("ALLOWED");

        softAssertions.assertAll();  // Esto asegura que todas las aserciones se verifiquen incluso si una falla.
    }
}
