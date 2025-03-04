package java.com.orangehrm.pageobjects;

import com.microsoft.playwright.Page;

public class FirstStepsPage {
    private static final String IMAGE_LINK = "//a[contains(.,'Images')]";
    private Page page;
    public FirstStepsPage(Page page) {
        this.page = page;
    }

    public void clickImage() {
        page.locator(IMAGE_LINK).click();
    }

    public boolean isImagesPageLoaded() {
        return page.locator("//img[@alt='Google Images']").isVisible();
    }
}
