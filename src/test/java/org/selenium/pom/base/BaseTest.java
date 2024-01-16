package org.selenium.pom.base;

import org.openqa.selenium.WebDriver;
import org.selenium.pom.factory.DriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    private WebDriver driver;

    @BeforeClass
    public void startDriver(String browser){
        driver = new DriverManager().initializeDriver(browser);

    }

    @AfterClass
    public void quitDriver(){
        driver.quit();
    }
}
