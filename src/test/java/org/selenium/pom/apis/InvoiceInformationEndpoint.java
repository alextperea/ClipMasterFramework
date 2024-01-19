package org.selenium.pom.apis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.selenium.pom.bodies.InvoiceRequestBody;
import org.selenium.pom.headers.CustomHeaders;
import static org.assertj.core.api.Assertions.assertThat;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import static io.restassured.RestAssured.given;
import static org.selenium.pom.utils.PropertyLoader.loadProperties;

public class InvoiceInformationEndpoint {
    private Properties properties;
    public InvoiceInformationEndpoint(){
        properties = loadProperties("config.properties");
    }

    public Response AddInvoiceInfo(Login login) throws IOException {

        String baseUrl = properties.getProperty("invoice.BaseURI");
        String endpoint = properties.getProperty("invoice.Endpoint");

        // Lee el contenido del archivo JSON
        String requestBody = new String(Files.readAllBytes(Paths.get("src/test/resources/invoiceRequest.json")));

            Response response =
                given()
                        .baseUri(baseUrl)
                        .basePath(endpoint)
                        .headers(CustomHeaders.getInvoiceHeaders(login))
                        .body(requestBody)
                .when()
                        .post()

                .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .extract()
                        .response();

            return response;
    }

    public void validateInvoicingResponse(Response response) {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(response.jsonPath().getString("tax_payer_id")).isNotEmpty();
        softly.assertThat(response.jsonPath().getString("legal_entity_name")).isEqualTo("ALEJANDRO TOPETE PEREA");
        softly.assertThat(response.jsonPath().getString("legal_entity_last_name")).isEmpty();
        softly.assertThat(response.jsonPath().getString("legal_entity_second_last_name")).isEmpty();
        softly.assertThat(response.jsonPath().getString("fiscal_address_postal_code")).isEqualTo("44210");
        softly.assertThat(response.jsonPath().getString("sat_tax_regime_code")).isEqualTo("626");
        softly.assertThat(response.jsonPath().getString("sat_tax_regime_description")).isEqualTo("Régimen Simplificado de Confianza");
        softly.assertThat(response.jsonPath().getString("sat_invoice_use_code")).isEqualTo("G03");
        softly.assertThat(response.jsonPath().getString("sat_invoice_use_description")).isEqualTo("Gastos en general");
        softly.assertThat(response.jsonPath().getString("entity_type")).isEqualToIgnoringCase("PHYSICAL");

        softly.assertAll();  // Esto asegura que todas las aserciones se verifiquen incluso si una falla.
    }
    }


