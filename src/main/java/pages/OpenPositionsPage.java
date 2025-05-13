package pages;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static helper.HelperMethods.*;

public class OpenPositionsPage extends Base {
    public OpenPositionsPage(WebDriver driver) {
        super(driver);
    }

    private static final String OPEN_POSITIONS_PAGE_URL = BASE_URL + "careers/quality-assurance/";

    @FindBy(xpath = "//a[.='See all QA jobs']")
    private WebElement seeAllQaJobsButton;

    @FindBy(css = "span[aria-labelledby='select2-filter-by-location-container']")
    private WebElement locationFilter;

    @FindBy(css = ".job-item")
    private List<WebElement> jobList;

    @FindBy(xpath = "//li[@role='option' and normalize-space(text())='Istanbul, Turkiye']")
    private WebElement istanbulOption;

    private By istanbulLocator = By.xpath("//li[@role='option' and normalize-space(text())='Istanbul, Turkiye']");

    @FindBy(xpath = "//a[text()='View Role']")
    private WebElement viewRoleButton;

    @FindBy(id = "resultCounter")
    private WebElement resultCounter;

    private By positionOfJobListings = By.cssSelector(".position");
    private By departmentOfJobListings = By.cssSelector(".department");
    private By locationOfJobListings = By.cssSelector(".location");

    public void goToOpenPositionsPage() {
        driver.get(OPEN_POSITIONS_PAGE_URL);
    }

    public void clickSeeAllQaJobsButton() {
        clickWhenClickable(seeAllQaJobsButton);
    }

    public void filterJobs() {

        //The loop added since locations are usually not parsed when the page is opened, and that causes tests to fail.
        // This solution ignores a static long sleep and prevents time loss.
        for(int attempts = 0; attempts < 20; attempts++) {
            clickWhenClickable(locationFilter);

                if (isElementPresent(istanbulLocator)){
                    try{
                        istanbulOption.click();
                        break;
                    } catch (Exception e){
                        //Continue in the loop to try to click the element in case something went wrong
                    }
                } else {
                    //If Istanbul is not present, click the location filter again so the loop can start over
                    locationFilter.click();
                }
        }
    }

    public boolean doesAllJobsHaveCorrectDetails(String position, String department, String location) {
        return jobList.stream().allMatch(job -> {
            String jobPosition = job.findElement(positionOfJobListings).getText();
            String jobDepartment = job.findElement(departmentOfJobListings).getText();
            String jobLocation = job.findElement(locationOfJobListings).getText();

            return jobPosition.contains(position) &&
                    jobDepartment.contains(department) &&
                    jobLocation.contains(location);
        });
    }

    public void clickViewRole() {

        // Scroll to the result counter
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", resultCounter);

        // Wait for the "View Role" button to be clickable, used refresh since elements are being rendered
        wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.elementToBeClickable(viewRoleButton)
        ));

        // Perform a hover action on the "View Role" button since it's necessary to be hovered to click
        Actions actions = new Actions(driver);
        actions.moveToElement(viewRoleButton).perform();

        clickWhenClickable(viewRoleButton);
        }
}
