package org.selenium.pom.apis;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.selenium.pom.utils.PropertyLoader;

import java.util.Properties;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.selenium.pom.utils.PropertyLoader.loadProperties;

public class LoginEndpoint {

    public void login(){
        // Cargar propiedades desde el archivo de configuración
        Properties properties = loadProperties("config.properties");
        String baseUrl = properties.getProperty("baseURI");
        String endpoint = "/api/login";

        // Datos de la solicitud
        String email = properties.getProperty("email");
        String password = properties.getProperty("password");
        String requestBody = String.format("{\"email\": \"%s\", \"password\": \"%s\", \"type\": \"LOGIN\", \"source\": \"M_DASHBOARD\"}", email, password);

        // Headers de la solicitud
        String authorityHeader = "api-gateway.payclip.com";
        String acceptHeader = "application/vnd.com.payclip.v1+json";
        String acceptLanguageHeader = "es-US,es;q=0.9";
        String contentTypeHeader = "application/json";
        String cookieHeader = "__cf_bm=DynqhLzviHexmJ7krVH0F3oNp9.pD78N.1PCEb1my7g-1705360027-1-AbYG2EmgBEuAswxsMDTaNCrdfLQoYOJxiEyYDlGi/vIjBkYPrmBEFYyVSMGChcHh4JudmRu5FKJGzWQrP7FUrrU=";

        // Realizar la solicitud
        Response response = RestAssured.
                 given()
                        .baseUri(baseUrl)
                        .basePath(endpoint)
                        .headers("authority", authorityHeader, "accept", acceptHeader, "accept-language", acceptLanguageHeader, "content-type", contentTypeHeader, "Cookie", cookieHeader)
                        .body(requestBody)
                .when()
                        .post()
                .then()
                        .statusCode(200)  // Código de respuesta esperado
                        .contentType(ContentType.JSON)  // Tipo de contenido esperado
                        .extract()
                        .response();

        // Validar la respuesta
        String accessToken = response.jsonPath().getString("access_token");
        String country = response.jsonPath().getString("country");
        String appVersionCheckStatus = response.jsonPath().getString("app_version_check.status");

        // Validaciones adicionales
        assert accessToken != null && !accessToken.isEmpty() : "Access token válido";
        assert "MX".equals(country) : "País válido";
        assert "ALLOWED".equals(appVersionCheckStatus) : "Estado de verificación de versión de la aplicación válido";

    }
}


