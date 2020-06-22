package com.arvato.selenium.model;

import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.By;

public class SeleniumElement {
    private String findBy;
    private String value;
    private String name;

    public SeleniumElement() {
    }

    public String getFindBy() {
        return findBy;
    }

    public void setFindBy(String findBy) {
        this.findBy = findBy;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public By getLocator() {
        switch (findBy) {
            case "id":
                return By.id(value);
            case "linkText":
                return By.linkText(value);
            case "partialLinkText":
                return By.partialLinkText(value);
            case "name":
                return By.name(value);
            case "tagName":
                return By.tagName(value);
            case "xpath":
                return By.xpath(value);
            case "className":
                return By.className(value);
            case "cssSelector":
                return By.cssSelector(value);
            default:
                throw new ArvatoSeleniumException("Unrecognized findBy type: " + findBy);
        }
    }
}
