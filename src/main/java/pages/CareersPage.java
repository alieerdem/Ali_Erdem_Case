package pages;

import base.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static helper.HelperMethods.waitForElementVisible;

public class CareersPage extends Base
{
    public CareersPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div.career-load-more")
    private WebElement teamsBlock;

    @FindBy(id = "location-slider")
    private WebElement locationsBlock;

    @FindBy(xpath = "//h2[.='Life at Insider']")
    private WebElement lifeAtInsiderBlock;

    public boolean areAllBlocksDisplayed() {
        waitForElementVisible(teamsBlock);
        waitForElementVisible(locationsBlock);
        waitForElementVisible(lifeAtInsiderBlock);

        return locationsBlock.isDisplayed() &&
                teamsBlock.isDisplayed() &&
                lifeAtInsiderBlock.isDisplayed();
    }
}
