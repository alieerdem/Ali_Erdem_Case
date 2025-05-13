import base.Base;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.CareersPage;
import pages.HomePage;
import pages.OpenPositionsPage;

import java.io.File;
import java.io.IOException;

import static helper.HelperMethods.switchToNewTabAndGetUrl;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomePageTest extends Base {

    private HomePage homePage;
    private CareersPage careersPage;
    private OpenPositionsPage openPositionsPage;

    @BeforeMethod
    public void setupPages() {
        homePage = new HomePage(driver);
        careersPage = new CareersPage(driver);
        openPositionsPage = new OpenPositionsPage(driver);
    }

    @Test(priority = 1)
    public void verifyHomePageIsOpened() {
        homePage.openHomePage();
        assertEquals(driver.getTitle(), "#1 Leader in Individualized, Cross-Channel CX — Insider", "Home page is not opened");
    }

    @Test(priority = 2)
    public void verifyAllBlocksAreDisplayedInCareersPage() {
        homePage.openHomePage();
        homePage.clickToCareersLink();
        careersPage = new CareersPage(driver);

        assertTrue(careersPage.areAllBlocksDisplayed());
    }

    @Test(priority = 3)
    public void verifyQaJobsHaveCorrectListing() throws InterruptedException {
        openPositionsPage.goToOpenPositionsPage();

        openPositionsPage.clickSeeAllQaJobsButton();
        openPositionsPage.filterJobs();

        assertTrue(openPositionsPage.doesAllJobsHaveCorrectDetails(
                "Quality Assurance",
                "Quality Assurance",
                "Istanbul, Turkey"
        ));
    }

    @Test(priority = 4)
    public void testJobApplication() throws InterruptedException {
        openPositionsPage.clickViewRole();
        Thread.sleep(3000);
        String openedPageUrl = switchToNewTabAndGetUrl(driver);
        assertTrue(openedPageUrl.contains("lever"), "URL does not contain 'lever'");
    }

}
