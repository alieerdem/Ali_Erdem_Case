package helper;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static base.Base.driver;
import static base.Base.wait;

public class HelperMethods {

    public static void clickWhenClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public static void waitForElementClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static boolean isElementPresent(By locator) {
        try {
            WebDriverWait localWait = new WebDriverWait(driver, Duration.ofMillis(300));
            localWait.until(ExpectedConditions.elementToBeClickable(locator));
            WebElement element = driver.findElement(locator);
            return element.isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public static String switchToNewTabAndGetUrl(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        String originalWindow = driver.getWindowHandle();

        wait.until(d -> d.getWindowHandles().size() > 1);

        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.equals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        return driver.getCurrentUrl();
    }

    public static ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notification");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.addArguments("--ignore-certificate-errors-spki-list");
        chromeOptions.addArguments("--suppress-message-center-popups");

        chromeOptions.setAcceptInsecureCerts(true);
        return chromeOptions;
    }

}
