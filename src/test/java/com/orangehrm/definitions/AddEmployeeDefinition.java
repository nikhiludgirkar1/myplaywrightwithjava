package com.orangehrm.definitions;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.orangehrm.hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.com.orangehrm.helper.api.OrangeConstants;
import java.com.orangehrm.helper.api.OrangeHrmApi;
import java.com.orangehrm.pageobjects.AddEmployeePage;
import java.lang.invoke.MethodHandles;
import java.util.List;

public class AddEmployeeDefinition {
    private final Page page;
    private Page newTab;
    private BrowserContext browserContext;
    private final AddEmployeePage addEmployeePage;
    Hooks localHooks;
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public AddEmployeeDefinition(Hooks hooks) {
        localHooks = hooks;
        page = hooks.getPage();
        addEmployeePage = new AddEmployeePage(page);
        browserContext = hooks.getBrowserContext();
    }

    @When("add employee button")
    public void userClicksOnAssignLeave() {
        addEmployeePage.clickAddEmployee();
    }

    @Given("the user opens a new browser tab with the copied link")
    public void theUserOpensANewBrowserTabWithTheCopiedLink() {
        newTab = browserContext.newPage();
        localHooks.setNewTab(newTab);
        newTab.navigate("https://www.google.com/");
        newTab.bringToFront();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String pageTitle = newTab.title();
        logger.debug("Page Title is :: " + pageTitle);
    }

    @When("user gets response for users")
    public void userGetsResponseForUsers() {
        OrangeHrmApi orangeHrmApi = new OrangeHrmApi(OrangeConstants.PROPERTIES_CONFIG.baseApiUrl());
        APIResponse apiResponse = orangeHrmApi.get("");
        logger.debug("" + apiResponse.status());
        logger.debug("" + apiResponse.text());
        DocumentContext documentContext = JsonPath.parse(apiResponse.text());
        List<String> idArray = documentContext.read("$..[?(@.gender == 'female')].name");
        logger.debug("id is " + idArray);
    }

    public String getAuthToken() {
        return "Bearer 18806c8605b08cabb3c9ce642cbc3a21e1a8942a96c3b908a7e0e27c3b5cf354";
    }

    @When("user creates another user with name {string} and email {string}")
    public void userCreatesAnotherUserWithNameAndEmail(String name, String email) {
        OrangeHrmApi orangeHrmApi = new OrangeHrmApi(OrangeConstants.PROPERTIES_CONFIG.baseApiUrl(), getAuthToken());
        APIResponse apiResponse = orangeHrmApi.post("", "createUser.json", name, email);
        DocumentContext documentContext = JsonPath.parse(apiResponse.text());
        logger.debug("" + apiResponse.status());
        logger.debug("" + apiResponse.text());
    }

}
