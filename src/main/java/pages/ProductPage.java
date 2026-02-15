package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.WaitUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(tagName = "h1")
    public WebElement title;

    @FindBy(xpath = "(//button[@data-test-id='addToCart'])[1]")
    public WebElement addToCartBtn;

    @FindBy(xpath = "//div[contains(@class,'ProductOnBasketHeader')]//button[text()='Sepete git']")
    public WebElement goToCartBtn;

    public boolean isProductPageOpened(){
        return title.isDisplayed();
    }

    public void addToCart(){
        WaitUtils.waitForPageLoad();
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());

        // If you have multiple tabs open, switch to the most recently opened tab.
        if (tabs.size() > 1) {
            driver.switchTo().window(tabs.get(tabs.size() - 1));
        }

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@data-test-id='addToCart'])[1]")));

        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", addToCartBtn);
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));

        try {
            addToCartBtn.click();
        } catch (Exception e) {
            // If you're still getting the 'Intercepted' error, JS Click is the most reliable option.
            js.executeScript("arguments[0].click();", addToCartBtn);
        }
    }
}
