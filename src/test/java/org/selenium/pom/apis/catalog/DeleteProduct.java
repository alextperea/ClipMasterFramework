package org.selenium.pom.apis.catalog;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.selenium.pom.headers.CustomHeaders;
import java.util.Properties;
import static io.restassured.RestAssured.given;
import static org.selenium.pom.utils.PropertyLoader.loadProperties;

public class DeleteProduct {
    private Properties properties;
    private String productId;
    private String accessToken;
    public DeleteProduct(String accessToken, String productId) {
        this.accessToken = accessToken;
        this.productId =productId;
        properties = loadProperties("config.properties");
    }

    public Response deleteCatalogProduct(){
        String baseUri = properties.getProperty("deleteProduct.baseURI");
        String endpoint = properties.getProperty("deleteProduct.endpoint");

        // Comprueba que el productId no esté vacío
        if (productId == null || productId.isEmpty()) {
            throw new IllegalArgumentException("Product ID no debe estar vacío");
        }

        Response response =
                given()
                        .filter(new AllureRestAssured())
                        .baseUri(baseUri)
                        .basePath(endpoint + productId).log().body()
                        .headers(CustomHeaders.getDeleteProduct(accessToken)).log().headers()
                .when()
                        .delete()
                .then()
                        .statusCode(204).log().everything()
                        .extract().response();

        return response;

    }
}
