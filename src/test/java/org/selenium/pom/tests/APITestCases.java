package org.selenium.pom.tests;


import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.selenium.pom.apis.catalog.CreateSingleProduct;
import org.selenium.pom.apis.catalog.DeleteProduct;
import org.selenium.pom.apis.catalog.GetCategories;
import org.selenium.pom.apis.login.Login;
import org.testng.annotations.Test;
import java.io.IOException;

public class APITestCases {
    @Description("This endpoint should login and get a 200OK HTTP code as well as store the access token fo future uses")
    @Test(description = "Should be able to login and get access token")
    public void LoginExec() throws IOException {
        Login login = new Login();

        Response response =
                            login.login();
                            login.validateLoginResponse(response);
    }
    @Description("This test should be able to Login, store an access_token and then get the categories in the Catalog")
    @Test(description = "Get Catalog Categories")
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
    @Description("This test should be able to Login, create a single product in the catalog and then delete that same product | this follows CRUD pattern")
    @Test(description = "Make a CRUD for Catalog single product")
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
