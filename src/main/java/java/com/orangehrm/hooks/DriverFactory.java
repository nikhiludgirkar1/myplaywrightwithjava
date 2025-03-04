package java.com.orangehrm.hooks;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.com.orangehrm.helper.api.OrangeConstants;
import java.lang.invoke.MethodHandles;

public class DriverFactory {
    private Playwright playwright;
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public DriverFactory() {
    }

    public BrowserContext createBrowserContext() {
        logger.info("Inside create Browser context");
        playwright = Playwright.create();
        BrowserContext browserContext = null;
        if (OrangeConstants.PROPERTIES_CONFIG.browserType().equalsIgnoreCase("chrome")) {
            BrowserType browserType = Playwright.create().chromium();
            Browser browser = getPlaywrightBrowser(OrangeConstants.PROPERTIES_CONFIG.browserType(), browserType);
            browserContext = createNewBrowserContext(browser);
        }
        return browserContext;
    }
    private Browser getPlaywrightBrowser(String runInBrowserType, BrowserType browserType) {
        return browserType.launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(200).setChannel(runInBrowserType.toLowerCase()));
    }

    public BrowserContext createNewBrowserContext(Browser browser) {
        Browser.NewContextOptions newContextOptions = new Browser.NewContextOptions();
        newContextOptions.setAcceptDownloads(true);
        return browser.newContext(newContextOptions);
    }
}
