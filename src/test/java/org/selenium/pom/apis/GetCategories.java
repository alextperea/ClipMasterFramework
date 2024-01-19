package org.selenium.pom.apis;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.selenium.pom.headers.CustomHeaders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.selenium.pom.utils.PropertyLoader.loadProperties;

public class GetCategories {

    private Properties properties;
    private String accessToken;
    public String getAccessToken(){
        return accessToken;
    }
    public GetCategories(){
        properties = loadProperties("config.properties");
    }

    public Response getCategories(Login login) throws IOException {

        String baseUrl = properties.getProperty("invoice.BaseURI");
        String endpoint = properties.getProperty("categories.endpoint");

        Response response =
                given()
                        .baseUri(baseUrl)
                        .basePath(endpoint)
                        .headers(CustomHeaders.getCategoriesHeaders(login))
                .when()
                        .get()
                .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .extract().response();

        return response;
    }
    public void validateCategoriesResponse(Response response){
        SoftAssertions softly = new SoftAssertions();

        // Validaciones para cada elemento de "data"
        List<Map<String, Object>> data = response.jsonPath().getList("data");
        data.forEach(category -> {
            softly.assertThat(category.get("category_id")).isNotNull();
            softly.assertThat(category.get("category_name")).isIn("Fitness", "Home");

            List<Map<String, Object>> products = (List<Map<String, Object>>) category.get("products");
            softly.assertThat(products).isNotEmpty();

            // Validaciones para cada producto en la lista de productos
            products.forEach(product -> {
                softly.assertThat(product.get("product_id")).isNotNull();
                softly.assertThat(product.get("product_name")).isEqualTo("Producto C");
            });
        });

        softly.assertThat(response.jsonPath().getString("meta.type")).isEqualTo("category");

        // Asegúrate de que todas las aserciones se verifiquen incluso si una falla
        softly.assertAll();
    }
}
