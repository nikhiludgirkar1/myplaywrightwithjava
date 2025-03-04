package java.com.orangehrm.pageobjects;

import com.microsoft.playwright.Page;

public class AddEmployeePage {
    private Page page;
    public AddEmployeePage(Page page) {
        this.page = page;
    }

    public void clickAddEmployee() {
        page.locator("//a[@id='menu_pim_viewPimModule']").hover();
        page.locator("//a[@id='menu_pim_addEmployee']").click();
    }
}
