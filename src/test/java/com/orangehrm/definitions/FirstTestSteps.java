package com.orangehrm.definitions;

import com.microsoft.playwright.Page;
import com.orangehrm.hooks.Hooks;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.com.orangehrm.pageobjects.FirstStepsPage;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FirstTestSteps {

    private final FirstStepsPage firstStepsPage;
    private final Page page;


    public FirstTestSteps(Hooks hooks) {
        page = hooks.getPage();
        firstStepsPage = new FirstStepsPage(page);
    }

    @When("user performs operations")
    public void userPerformsOperations() throws URISyntaxException, IOException {

    }

    @When("user navigates to google.com")
    public void userNavigates() {
        page.navigate("https://www.google.com/");
    }

    @When("user clicks on images")
    public void userClicksOnImages() {
        firstStepsPage.clickImage();
    }

    @Then("images page is loaded")
    public void imagesPageIsLoaded() {
        assertTrue(firstStepsPage.isImagesPageLoaded());
    }


}
