package com.orangehrm.hooks;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;   
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.com.orangehrm.helper.api.OrangeConstants;
import java.com.orangehrm.hooks.DriverFactory;
import java.lang.invoke.MethodHandles;

public class Hooks {
    private Page page;
    private Page newTab;
    private BrowserContext browserContext;
    private Scenario currentScenario;
    private String scenarioName;
    private static String featureName = "";
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @Before
    public void setup(Scenario scenario) {
        currentScenario = scenario;
        this.scenarioName = scenario.getName();
        String currentFeatureName = scenario.getUri().toString();
        featureName = currentFeatureName;
        this.browserContext = new DriverFactory().createBrowserContext();
        this.page = this.browserContext.newPage();
        this.page.setDefaultTimeout(30000);
    }

    @After
    public void tearDown(Scenario scenario) {
        logger.info("Hooks.tearDown");
        featureName = scenario.getUri().toString();
        logger.info("Scenario name is :: {}", scenario.getName());
        if (scenario.isFailed()) {
            byte[] scenarioShot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
            scenario.attach(scenarioShot, "image/png", scenario.getName());
            if (this.newTab != null) {
                byte[] newTabScenarioShot = newTab.screenshot(new Page.ScreenshotOptions().setFullPage(true));
                scenario.attach(newTabScenarioShot, "image/png", scenario.getName() + "_newTabScreenShot");
            }
        }

        if (!OrangeConstants.PROPERTIES_CONFIG.keepBrowserOpen()) {
            page.close();
            if (newTab != null) {
                newTab.close();
            }
        }
        this.browserContext.close();
    }

    public BrowserContext getBrowserContext() {
        return browserContext;
    }

    public Page getNewTab() {
        if (newTab == null) {
            newTab = browserContext.newPage();
        }
        return newTab;
    }

    public void setNewTab(Page newTab) {
        this.newTab = newTab;
    }

}
