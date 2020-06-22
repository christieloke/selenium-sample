package com.arvato.webedi.tests.bdd;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;

public class StandaloneSelenium {
    public static void main(String[] args) throws IOException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        options.addArguments("start-maximized");
        options.setExperimentalOption("useAutomationExtension", false);
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get("https://www.google.com");
            WebElement searchEditBox = driver.findElement(By.name("q"));
            searchEditBox.sendKeys("arvato system malaysia");
            searchEditBox.sendKeys(Keys.RETURN);
            getScreenshot(driver);
        }finally {
            driver.quit();
        }
    }

    private static void getScreenshot(WebDriver driver) throws IOException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("screenshot.png"));
    }
}
