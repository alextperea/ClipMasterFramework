package org.selenium.pom.apis.catalog;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.selenium.pom.headers.CustomHeaders;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.selenium.pom.utils.PropertyLoader.loadProperties;

public class GetCategories {
    private String accessToken;
    private Properties properties;
    public GetCategories(String accessToken){
        this.accessToken = accessToken;
        properties = loadProperties("config.properties");
    }

    public Response getCategories(){
        String baseUri = properties.getProperty("categories.baseURI");
        String endpoint = properties.getProperty("categories.endpoint");

        Response response =
                given()
                        .filter(new AllureRestAssured()) //This adds some filters to Allure report.
                        .baseUri(baseUri)
                        .basePath(endpoint)
                        .headers(CustomHeaders.getCategoriesHeaders(accessToken)).log().headers()
                .when()
                        .get()
                .then()
                        .statusCode(200).log().everything()
                        .extract().response();

        return response;
    }

    public void validateCategoriesResponse(Response response) {
        // Valida los campos de la sección "meta"
        Assertions.assertThat(response.jsonPath().getString("meta.type")).isEqualTo("category");

        // Valida los campos de la sección "data"
        List<Map<String, Object>> categories = response.jsonPath().getList("data");

        for (Map<String, Object> category : categories) {
            Assertions.assertThat(category.get("category_id")).isNotNull();
            Assertions.assertThat(category.get("category_name")).isNotNull();

            // Valida que las categorías tengan los nombres esperados
            if (category.get("category_name").equals("Fitness")) {
                Assertions.assertThat(category.get("category_name")).isEqualTo("Finess"); //vamos a pushear este cambio para ver el error en el pipeline de Github Actions
            } else if (category.get("category_name").equals("Home")) {
                Assertions.assertThat(category.get("category_name")).isEqualTo("Home");
            } else {
                throw new AssertionError("La categoría no tiene el nombre esperado");
            }

            // Valida los campos de la sección "products" dentro de cada categoría
            List<Map<String, Object>> products = (List<Map<String, Object>>) category.get("products");

            for (Map<String, Object> product : products) {
                Assertions.assertThat(product.get("product_id")).isNotNull();
                Assertions.assertThat(product.get("product_name")).isNotNull();
            }
        }
    }
}
