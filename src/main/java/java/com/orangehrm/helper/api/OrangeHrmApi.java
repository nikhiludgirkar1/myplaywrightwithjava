package java.com.orangehrm.helper.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.ImmutableMap;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.com.orangehrm.helper.api.payloads.Payload;
import java.com.orangehrm.utils.JsonUtil;
import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OrangeHrmApi implements AutoCloseable {
    private final Playwright playwright;
    private final String baseUri;
    private static APIRequestContext request;
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private Map<String, String> headers = new HashMap<>();

    protected OrangeHrmApi(String baseUri, Map<String, String> requestHeaders) {
        playwright = Playwright.create();
        this.baseUri = baseUri;
        headers = requestHeaders;
        logger.debug("Headers set *** " + headers);
    }

    public OrangeHrmApi(String baseUri) {
        this(baseUri, (Map<String, String>) null);
    }


    public OrangeHrmApi(String baseUri, String authToken) {
        this(baseUri, authToken == null ? null : ImmutableMap.of("Authorization", authToken));
    }

    protected APIResponse post(String endpoint, Object body) {
        return post(endpoint, Optional.empty(), Optional.of(body));
    }

    public APIResponse post(String endPoint, String authJsonFileName) {
        Map<String, Object> requestBody = new HashMap<>();
        try {
            requestBody = JsonUtil.asMap(Payload.getRequest(authJsonFileName));
        } catch (final JsonProcessingException e) {
            logger.error(e.getMessage());
        }
        APIResponse response = post(endPoint, requestBody);
        return response;
    }

    private APIResponse post(String endpoint, Optional<Map<String, String>> queryParams, Optional<Object> body) {
        request = getRequestContext();
        APIResponse response;
        RequestOptions requestOptions = RequestOptions.create();
        setQueryParametersIntoBody(queryParams, body, requestOptions);
        response = request.post(endpoint, requestOptions);
        return response;
    }

    public APIResponse post(String endPoint, String authJsonFileName, Object... values) {
        Map<String, Object> requestBody = new HashMap<>();
        try {
            requestBody = JsonUtil.asMap(Payload.getRequest(authJsonFileName, values));
        } catch (final JsonProcessingException e) {
            logger.error(e.getMessage());
        }
        APIResponse response = post(endPoint, requestBody);
        return response;
    }

    protected APIResponse put(String endpoint, Object body) {
        return put(endpoint, Optional.empty(), Optional.of(body));
    }

    private APIResponse put(String endpoint, Optional<Map<String, String>> queryParams, Optional<Object> body) {
        request = getRequestContext();
        APIResponse response;
        RequestOptions requestOptions = RequestOptions.create();

        setQueryParametersIntoBody(queryParams, body, requestOptions);
        response = request.put(endpoint, requestOptions);
        return response;
    }

    public APIResponse put(String endPoint, String authJsonFileName, Object... values) {
        Map<String, Object> requestBody = new HashMap<>();
        try {
            requestBody = JsonUtil.asMap(Payload.getRequest(authJsonFileName, values));
        } catch (final JsonProcessingException e) {
            logger.error(e.getMessage());
        }
        APIResponse response = put(endPoint, requestBody);
        return response;
    }

    public APIResponse delete(String endpoint) {
        return this.delete(endpoint, Optional.empty());
    }

    public APIResponse delete(String endpoint, Optional<Map<String, String>> queryParams) {
        request = getRequestContext();
        APIResponse response;
        RequestOptions requestOptions = RequestOptions.create();
        setQueryParameters(queryParams, requestOptions);
        response = request.delete(endpoint, requestOptions);
        return response;
    }

    private APIResponse patch(String endpoint, Optional<Map<String, String>> queryParams, Optional<Object> body) {
        request = getRequestContext();
        APIResponse response;
        RequestOptions requestOptions = RequestOptions.create();

        setQueryParametersIntoBody(queryParams, body, requestOptions);
        response = request.patch(endpoint, requestOptions);
        return response;
    }

    public APIResponse patch(String endPoint, String requestFileName, Object... values) {
        Map<String, Object> requestBody = new HashMap<>();
        try {
            requestBody = JsonUtil.asMap(Payload.getRequest(requestFileName, values));
        } catch (final JsonProcessingException e) {
            logger.error(e.getMessage());
        }
        APIResponse response = patch(endPoint, Optional.empty(), Optional.of(requestBody));
        return response;
    }

    public APIResponse get(String endpoint) {
        return this.get(endpoint, Optional.empty());
    }

    public APIResponse get(String endpoint, Optional<Map<String, String>> queryParams) {
        request = getRequestContext();
        APIResponse response;
        RequestOptions requestOptions = RequestOptions.create();
        setQueryParameters(queryParams, requestOptions);
        response = request.get(endpoint, requestOptions);
        return response;
    }

    public boolean isValidStatus(int code) {
        return code >= 200 && code < 300;
    }

    private APIRequestContext getRequestContext() {
        APIRequest.NewContextOptions requestOptions = new APIRequest.NewContextOptions();
        logger.debug("Baseuri :: {}, headers: :: {}", baseUri, headers);
        requestOptions.setBaseURL(baseUri).setExtraHTTPHeaders(headers);
        logger.debug("Headers: {}", requestOptions.extraHTTPHeaders);
        return playwright.request().newContext(requestOptions);
    }

    private void setQueryParameters(Optional<Map<String, String>> queryParams, RequestOptions requestOptions) {
        if (queryParams.isPresent() && queryParams.get().size() > 0) {
            queryParams.get().forEach(requestOptions::setQueryParam);
        }
    }

    private void setQueryParametersIntoBody(Optional<Map<String, String>> queryParams, Optional<Object> body, RequestOptions requestOptions) {
        setQueryParameters(queryParams, requestOptions);
        body.ifPresent(requestOptions::setData);
    }


    @Override
    public void close() throws Exception {
        try {
            request.dispose();
            playwright.close();
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong while disposing the request and playwright object");
        }
    }
}
