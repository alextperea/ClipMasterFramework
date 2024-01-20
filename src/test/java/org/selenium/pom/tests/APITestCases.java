package org.selenium.pom.tests;


import io.restassured.response.Response;
import org.selenium.pom.apis.catalog.CreateSingleProduct;
import org.selenium.pom.apis.catalog.DeleteProduct;
import org.selenium.pom.apis.catalog.GetCategories;
import org.selenium.pom.apis.login.Login;
import org.testng.annotations.Test;
import java.io.IOException;

public class APITestCases {
    @Test
    public void LoginExec() throws IOException {
        Login login = new Login();

        Response response =
                            login.login();
                            login.validateLoginResponse(response);
    }
    @Test
    public void CategoriesExec() throws IOException {
        Login login = new Login();
        Response responseLogin =
                                login.login();
                                login.validateLoginResponse(responseLogin);
                                String accessToken = login.getAccessToken();

        GetCategories getCategories = new GetCategories(accessToken);
        Response responseGetCategories =
                                        getCategories.getCategories();
                                        getCategories.validateCategoriesResponse(responseGetCategories);

    }
    @Test
    public void CRUDForCatalog() throws IOException {
        Login login = new Login();
        Response responseLogin =
                                login.login();
                                login.validateLoginResponse(responseLogin);
                                String accessToken = login.getAccessToken();

        CreateSingleProduct createSingleProduct = new CreateSingleProduct(accessToken);
        Response responseSingleProduct =
                                        createSingleProduct.createSingleProduct();
                                        createSingleProduct.validateSingleProductCreation(responseSingleProduct);
                                        String productId = createSingleProduct.getProductId();

        DeleteProduct deleteProduct = new DeleteProduct(accessToken, productId);
                                          deleteProduct.deleteCatalogProduct();
    }
}
