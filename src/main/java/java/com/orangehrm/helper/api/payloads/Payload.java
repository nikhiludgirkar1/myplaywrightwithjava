package java.com.orangehrm.helper.api.payloads;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.com.orangehrm.helper.api.OrangeConstants;
import java.com.orangehrm.utils.JsonUtil;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Payload {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static String getRequest(String requestJsonFileName, String... values) {
        logger.debug("Request JSON is :: " + requestJsonFileName);
        String requestJson = JsonUtil
                .readJsonFromResource(OrangeConstants.PROPERTIES_CONFIG.requestJsonPath() + requestJsonFileName);
        logger.debug("Request JSON is :: " + requestJson);
        try {
            Class<?> stringClass = String.class;
            Class[] argTypes = new Class[] { String.class, Object[].class };
            Method method = stringClass.getMethod("format", argTypes);
            String[] methodArgs = Arrays.copyOfRange(values, 0, values.length);
            logger.debug("invoking %s.format()%n", stringClass.getName());
            Object requestJsonObj = method.invoke(null, requestJson, (Object) methodArgs);
            requestJson = requestJsonObj.toString();
            logger.debug("Request JSON is :: " + requestJsonObj.toString());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return requestJson;
    }

    public static String getRequest(String requestJsonFileName, Object... values) {
        logger.debug("Request JSON is :: " + requestJsonFileName);
        String requestJson = JsonUtil
                .readJsonFromResource(OrangeConstants.PROPERTIES_CONFIG.requestJsonPath() + requestJsonFileName);
        logger.debug("Request JSON is :: " + requestJson);
        try {
            Class<?> stringClass = String.class;
            Class[] argTypes = new Class[] { String.class, Object[].class };
            Method method = stringClass.getMethod("format", argTypes);
            Object[] methodArgs = Arrays.copyOfRange(values, 0, values.length);
            logger.debug("invoking %s.format()%n", stringClass.getName());
            Object requestJsonObj = method.invoke(null, requestJson, (Object) methodArgs);
            requestJson = requestJsonObj.toString();
            logger.debug("Request JSON is :: " + requestJsonObj.toString());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return requestJson;
    }

    public static String getRequest(String requestJsonFileName) {
        logger.debug("Request JSON is :: " + requestJsonFileName);
        String requestJson = JsonUtil
                .readJsonFromResource(OrangeConstants.PROPERTIES_CONFIG.requestJsonPath() + requestJsonFileName);
        logger.debug("Request JSON is :: " + requestJson);
        return requestJson;
    }

}
