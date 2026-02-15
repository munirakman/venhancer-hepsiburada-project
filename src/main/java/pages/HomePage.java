package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utilities.DriverFactory;
import utilities.WaitUtils;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "button#onetrust-accept-btn-handler")
    public WebElement cookies;

    public By searchBoxLocator = By.cssSelector("input[data-test-id='search-bar-input']");

    public void searchProduct(String product){

        WebDriver driver = DriverFactory.getDriver();

        WaitUtils.waitForPageLoad();

        WebElement box = WaitUtils.waitForClickable(searchBoxLocator);

        // Following the occasional occurrence of the StaleElementReferenceException, real user behavior was implemented.
        Actions actions = new Actions(driver);

        actions
                .moveToElement(box)
                .click()
                .pause(300)
                .sendKeys(product)
                .pause(300)
                .sendKeys(Keys.ENTER)
                .perform();
    }

}
