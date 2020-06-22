package com.arvato.webedi.tests.bdd.glue;

import com.arvato.selenium.SeleniumElementRepository;
import com.arvato.selenium.model.SeleniumElement;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class InteractionGlue {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox"); // Bypass OS security model
        options.addArguments("--disable-dev-shm-usage");  // overcome limited resource problem
        options.addArguments("--headless");
//        options.addArguments("--remote-debugging-port=9222");
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--disable-extensions");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);

        // wait for 30 seconds for elements to load before start failing
        driver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30L, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 30L);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("^open '(.+)'$")
    public void navigate(String url) {
        driver.get(url);
    }

    @And("^type '(.+)' into '(.+)'$")
    public void sendKeys(String text, String elementName) {
        WebElement element = getElement(elementName);
        element.sendKeys(text);
    }

    @And("^click '(.+)'$")
    public void click(String elementName) {
        WebElement element = getElement(elementName);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    @And("^get screenshot$")
    public void getScreenshot() throws IOException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("screenshot" + getTimestamp() + ".png"));
    }

    private WebElement getElement(String name) {
        SeleniumElement lookUp = SeleniumElementRepository.getInstance().getLookUp(name);
        return driver.findElement(lookUp.getLocator());
    }

    private static String getTimestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    }
}
