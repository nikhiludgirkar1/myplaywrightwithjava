package com.orangehrm.definitions;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestPaths {
    public static void main(String[] args) throws IOException, URISyntaxException {
        String appSchemaPath = "data/";
        URI filePath = Thread.currentThread().getContextClassLoader().getResource(appSchemaPath + "vin.js").toURI();
        String jsonString = new String(Files.readAllBytes(Paths.get(filePath)));
        jsonString = jsonString.replace("_343545454", "_420");
        System.out.println(jsonString);
        Path path2 = Paths.get("./target/test-classes/data/vin.js");
        Files.write(path2, jsonString.getBytes(StandardCharsets.UTF_8));
        String data = new String(Files.readAllBytes(Paths.get("src/test/resources/data/TextFile.txt")));
        System.out.println("Printing with absolute path " + data);
    }
}
