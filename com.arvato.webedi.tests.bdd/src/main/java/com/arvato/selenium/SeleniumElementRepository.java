package com.arvato.selenium;

import com.arvato.selenium.model.SeleniumElement;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SeleniumElementRepository {
    private static SeleniumElementRepository instance;
    private Map<String, SeleniumElement> elements = new HashMap<>();

    public static SeleniumElementRepository getInstance() {
        if (instance == null) {
            instance = new SeleniumElementRepository();
        }

        return instance;
    }

    public void initialize(String elementPath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File[] files = new File(elementPath).listFiles();
        for (File file: files) {
            List<SeleniumElement> tmpElements = mapper.readValue(file,  new TypeReference<List<SeleniumElement>>(){});
            elements.putAll(tmpElements.stream().collect(Collectors.toMap(x -> x.getName(), x -> x)));
        }
    }

    public SeleniumElement getLookUp(String name) {
        return elements.get(name);
    }
}
