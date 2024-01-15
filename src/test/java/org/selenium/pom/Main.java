package org.selenium.pom;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Main {

    @Test
    public void dummyTest(){
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.google.com");

    }
}
