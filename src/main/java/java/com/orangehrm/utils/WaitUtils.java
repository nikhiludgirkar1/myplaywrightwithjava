package java.com.orangehrm.utils;

import com.microsoft.playwright.Frame;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.aeonbits.owner.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public final class WaitUtils {

    private static final String SKELETON_LOADER_BONE = "div[class='v-skeleton-loader__text v-skeleton-loader__bone']";
    private static final String BUSY_PROGRESS_BAR = "html[class='nprogress-busy']";
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void waitForPageLoad(Page page) {
        PropertiesConfig propertiesConfig = ConfigFactory.create(PropertiesConfig.class);
        page.waitForLoadState();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.waitForSelector(BUSY_PROGRESS_BAR,
                new Page.WaitForSelectorOptions().setStrict(false)
                        .setState(WaitForSelectorState.DETACHED)
                        .setTimeout(propertiesConfig.maxPageLoadTimeoutInSec() * 1000));
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.waitForSelector(SKELETON_LOADER_BONE,
                new Page.WaitForSelectorOptions().setStrict(false)
                        .setState(WaitForSelectorState.DETACHED)
                        .setTimeout(propertiesConfig.maxPageLoadTimeoutInSec() * 1000));
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }

    public static void waitForFrameLoad(Frame frame) {
        PropertiesConfig propertiesConfig = ConfigFactory.create(PropertiesConfig.class);
        frame.waitForLoadState();
        frame.waitForLoadState(LoadState.NETWORKIDLE);
        frame.waitForSelector(BUSY_PROGRESS_BAR,
                new Frame.WaitForSelectorOptions().setStrict(false)
                        .setState(WaitForSelectorState.DETACHED)
                        .setTimeout(propertiesConfig.maxPageLoadTimeoutInSec() * 1000));
        frame.waitForLoadState(LoadState.NETWORKIDLE);
        frame.waitForSelector(SKELETON_LOADER_BONE,
                new Frame.WaitForSelectorOptions().setStrict(false)
                        .setState(WaitForSelectorState.DETACHED)
                        .setTimeout(propertiesConfig.maxPageLoadTimeoutInSec() * 1000));
        frame.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }

    public void waitForFormToLoad(Page page, String apiCallCompletionToWaitFor) {
        try {
            Response response1 = page.waitForResponse(
                    response -> response.url().contains(apiCallCompletionToWaitFor) && response.finished() == null,
                    () -> {

                    });
        } catch (TimeoutError te) {
            logger.debug("Time out Error occurred", te.getMessage());
        }
    }

    public void waitForFormToLoad(Page page) {
        this.waitForFormToLoad(page, "toggles");
    }
}

