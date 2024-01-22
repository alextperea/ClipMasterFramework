package org.selenium.pom.apis.catalog;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.SoftAssertions;
import org.selenium.pom.headers.CustomHeaders;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import static io.restassured.RestAssured.given;
import static org.selenium.pom.utils.PropertyLoader.loadProperties;

public class CreateSingleProduct {
    private String accessToken;
    private String productId;
    private Properties properties;
    public CreateSingleProduct(String accessToken){
        this.accessToken = accessToken;
        properties = loadProperties("config.properties");
    }
    public String getProductId(){
        return productId;
    }

    public Response createSingleProduct() throws IOException {
        String baseUri = properties.getProperty("singleProduct.baseURI");
        String endpoint = properties.getProperty("singleProduct.endpoint");

        // Lee el contenido del archivo JSON
        String requestBody = new String(Files.readAllBytes(Paths.get("src/test/resources/singleProductRequest.json")));

        // Genera valores aleatorios para los campos
        int price = (int) (Math.random() * 100); // Genera un precio aleatorio entre 0 y 100
        requestBody = requestBody.replaceAll("\"price\": null", "\"price\": " + price);

        Response response =
                given()
                        .filter(new AllureRestAssured())
                        .baseUri(baseUri)
                        .basePath(endpoint)
                        .headers(CustomHeaders.getSingleProductHeaders(accessToken)).log().headers()
                        .body(requestBody).log().body()
                .when()
                        .post()
                .then()
                        .statusCode(200).log().everything()
                        .extract().response();

        productId = response.jsonPath().getString("data.product_id");

        return response;
    }
    public void validateSingleProductCreation(Response response) {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(response.jsonPath().getString("data.product_name")).isEqualTo("Test product");
        softly.assertThat(response.jsonPath().getString("data.description")).isEqualTo("Test description for product");
        softly.assertThat(response.jsonPath().getDouble("data.price")).isBetween(0.00, 100.00);

        softly.assertAll(); // Lanza las excepciones si alguna aserción falla
    }

}
