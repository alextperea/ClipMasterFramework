package org.selenium.pom.apis.catalog;

import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.selenium.pom.headers.CustomHeaders;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import static io.restassured.RestAssured.given;
import static org.selenium.pom.utils.PropertyLoader.loadProperties;

public class CreateSingleProduct {
    private String accessToken;
    private Properties properties;
    public CreateSingleProduct(String accessToken){
        this.accessToken = accessToken;
        properties = loadProperties("config.properties");
    }

    public Response createSingleProduct() throws IOException {
        String baseUri = properties.getProperty("singleProduct.baseURI");
        String endpoint = properties.getProperty("singleProduct.endpoint");

        // Lee el contenido del archivo JSON
        String requestBody = new String(Files.readAllBytes(Paths.get("src/test/resources/singleProductRequest.json")));

        // Genera valores aleatorios para los campos
        String productName = RandomStringUtils.randomAlphabetic(10); // Genera un nombre de producto de 10 caracteres
        String description = RandomStringUtils.randomAlphabetic(20); // Gener una descripcion de producto de 20 caracteres
        int price = (int) (Math.random() * 100); // Genera un precio aleatorio entre 0 y 100

        // Agrega los valores aleatorios a los campos del JSON
        requestBody = requestBody.replaceAll("\"product_name\": null", "\"product_name\": \"" + productName + "\"");
        requestBody = requestBody.replaceAll("\"description\": null", "\"description\": \"" + description + "\"");
        requestBody = requestBody.replaceAll("\"price\": null", "\"price\": " + price);

        Response response =
                given()
                        .baseUri(baseUri)
                        .basePath(endpoint)
                        .headers(CustomHeaders.getSingleProductHeaders(accessToken)).log().headers()
                        .body(requestBody).log().body()
                .when()
                        //.log().all()
                        .post()
                .then()
                        //.log().all()
                        .statusCode(200).log().everything()
                        .extract().response();
        return response;
    }
}
