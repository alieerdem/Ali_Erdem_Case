import base.Base;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;
import pages.CareersPage;
import pages.HomePage;
import pages.OpenPositionsPage;

import static helper.HelperMethods.switchToNewTabAndGetUrl;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class InsiderTest extends Base {

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
    public void verifyQaJobsHaveCorrectListing() {
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
    public void testJobApplication() {
        openPositionsPage.goToOpenPositionsPage();

        openPositionsPage.clickSeeAllQaJobsButton();
        openPositionsPage.filterJobs();

        openPositionsPage.clickViewRole();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        String openedPageUrl = switchToNewTabAndGetUrl(driver);
        assertTrue(openedPageUrl.contains("lever"), "URL does not contain 'lever'");
    }

}
