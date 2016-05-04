package com.loopme.reporting.test;

import org.junit.Test;

import javax.json.*;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class JacksonTest {



    @Test
    public void test() throws Exception {
        JsonReader reader = Json.createReader(load("topN.json"));
        JsonArray results = reader.readArray()
                .getJsonObject(0)
                .getJsonArray("result");
        for (JsonObject result : results.getValuesAs(JsonObject.class)) {
            System.out.println(result);
        }
    }


    protected InputStream load(String file) {
        try {
            return Files.newInputStream(Paths.get(JacksonTest.class.getClassLoader().getResource(file).toURI()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
