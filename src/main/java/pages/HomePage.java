package pages;

import base.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static helper.HelperMethods.waitForElementClickable;

public class HomePage extends Base
{

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[normalize-space()='Company']")
    private WebElement companyDropdown;

    @FindBy(linkText = "Careers")
    private WebElement careersLink;

    public void openHomePage() {
        driver.get(BASE_URL);
    }

    public void clickToCareersLink() {
        waitForElementClickable(companyDropdown);
        companyDropdown.click();
        waitForElementClickable(careersLink);
        careersLink.click();
    }
}
