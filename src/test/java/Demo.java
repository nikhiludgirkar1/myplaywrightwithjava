import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Demo {
    private static Playwright playwright;

    public static void main(String[] args) {
        playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));
        Page page = browser.newPage();
        page.navigate("https://www.google.com/");
        page.locator("//a[contains(.,'Images')]").click();
        page.close();
        playwright.close();

    }

}
