package com.arvato.webedi.tests.bdd;

import com.arvato.selenium.SeleniumElementRepository;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(Cucumber.class)
@CucumberOptions(
        stepNotifications = true,
        features = "src/test/resources/features/",
        plugin = {"pretty",
                "io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm",
                "json:target/jsonReports/report.json",
                "html:target/cucumber-html-reports"},
        glue = "com.arvato.webedi.tests.bdd.glue")
public class CucumberBddRunnerTest {
    @BeforeClass
    public static void beforeClass() throws IOException {
        SeleniumElementRepository.getInstance().initialize("src/test/resources/elements");
    }
}