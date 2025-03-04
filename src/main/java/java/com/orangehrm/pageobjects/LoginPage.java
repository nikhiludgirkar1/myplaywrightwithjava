package java.com.orangehrm.pageobjects;

import com.microsoft.playwright.Page;

public class LoginPage {
    private Page page;
    private static final String USERNAME_TEXTBOX = "//input[@id='txtUsername']";
    private static final String PASSWORD_TEXTBOX = "//input[@id='txtPassword']";
    private static final String LOGIN_BUTTON = "//input[@id='btnLogin']";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void login(String userName, String password) {
        page.locator(USERNAME_TEXTBOX).fill(userName);
        page.locator(PASSWORD_TEXTBOX).fill(password);
        page.locator(LOGIN_BUTTON).click();
    }

    public boolean isWelcomePageDisplayedForUsername(String userName) {
        String locator = "//a[contains(.,'Welcome %s')]";
        return page.locator(String.format(locator, userName)).isVisible();
    }
}
