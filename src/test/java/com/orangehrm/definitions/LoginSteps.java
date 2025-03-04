package com.orangehrm.definitions;

import com.microsoft.playwright.Page;
import com.orangehrm.hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.com.orangehrm.helper.api.OrangeConstants;
import java.com.orangehrm.pageobjects.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {
    private final Page page;
    private final LoginPage loginPage;

    public LoginSteps(Hooks hooks) {
        page = hooks.getPage();
        loginPage = new LoginPage(page);
    }

    @Given("User is on orangehrm portal")
    public void userIsOnOrangeHrmPortal() {
        page.setDefaultNavigationTimeout(OrangeConstants.PROPERTIES_CONFIG.maxPageLoadTimeoutInSec() * 1000);
        String url = OrangeConstants.PROPERTIES_CONFIG.orangeHrmPortalUrl();
        page.navigate(url);
    }

    @When("User performs login using valid credentials with username {string} and password {string}")
    public void userEntersValidUserName(String userName, String password) {
        loginPage.login(userName, password);
    }

    @Then("welcome page is displayed for {string}")
    public void menuIsDisplayed(String userName) {
        assertTrue(loginPage.isWelcomePageDisplayedForUsername(userName), "Welcome page not displayed");
    }
}
