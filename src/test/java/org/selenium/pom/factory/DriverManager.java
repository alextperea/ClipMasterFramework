package org.selenium.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {

    public WebDriver initializeDriver(String browser){
        WebDriver driver;
        browser = System.getProperty("browser", browser); // this is for maven command line (mvn clean test -Dbrowser=FIREFOX) and also for xml file
        switch (DriverType.valueOf(browser)){
            case CHROME:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalStateException("Invalid browser name: "+ browser);
        }
        driver.manage().window().maximize();
        return driver;
    }
}
