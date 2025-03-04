package java.com.orangehrm.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Map;

public class JsonUtil {
    public static final String ENCODING_TYPE = "UTF-8";
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private JsonUtil() {
    }

    public static String readAll(final Reader reader) throws IOException {
        final StringBuilder build = new StringBuilder();
        int pointer;
        while ((pointer = reader.read()) != -1) {
            build.append((char) pointer);
        }
        return build.toString();
    }

    public static String readJsonFromResource(String fileName) {
        String requestJson = null;
        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            System.out.println("Input Stream " + inputStream);
            requestJson = JsonUtil.readJsonFromFile(inputStream);
        } catch (final IOException e) {
            logger.error(e.getMessage());
        }
        return requestJson;
    }

    public static String readJsonFromFile(final FileInputStream file) throws IOException {
        try (InputStream inp = file) {
            return readFromInputStream(inp);
        }
    }

    public static String readJsonFromFile(final InputStream inputStream) throws IOException {
        try (inputStream) {
            return readFromInputStream(inputStream);
        }
    }

    private static String readFromInputStream(InputStream inputStream) throws IOException {
        final BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, Charset.forName(JsonUtil.ENCODING_TYPE)));
        return JsonUtil.readAll(reader);
    }

    public static Map asMap(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Map.class);
    }
}
