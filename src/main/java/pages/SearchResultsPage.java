package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.WaitUtils;

import java.time.Duration;
import java.util.List;

public class SearchResultsPage extends BasePage {

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "li[class*='productListContent']")
    public List<WebElement> productList;

    @FindBy(xpath = "(//li[contains(@class,'productListContent')]//a)[1]")
    public WebElement firstProduct;

    @FindBy(xpath = "//*[@id='markalar']//input[@placeholder='Filtrele']")
    public WebElement brandFilterBox;

    @FindBy(xpath = "(//div[@id='markalar']//label[contains(.,'HP')])[1]")
    public WebElement hpSelect;

    public boolean isResultsDisplayed(){
        return productList.size() > 0;
    }

    public void applyFilter() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1 -> Focus on the filter box and type HP.
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", brandFilterBox);
        brandFilterBox.clear();
        brandFilterBox.sendKeys("HP");

        // 2 -> By locator for loading the dynamic list (to prevent staleElement errors).
        By hpLocator = By.xpath("(//div[@id='markalar']//label[contains(.,'HP')])[1]");
        WebElement tazeHpElement = wait.until(ExpectedConditions.elementToBeClickable(hpLocator));

        int attempts = 0;
        while (attempts < 3) {
            try {
                // Center the element and click.
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", tazeHpElement);
                js.executeScript("arguments[0].click();", tazeHpElement);

                // Wait for the URL to change. (?markalar=hp)
                wait.until(ExpectedConditions.urlContains("markalar=hp"));

                // If the URL changes but the products remain the same, reload the page with the new URL.
                driver.get(driver.getCurrentUrl());
                break;
            } catch (Exception e) {
                attempts++;
                tazeHpElement = driver.findElement(hpLocator);
            }
        }

        // Confirm that the filter is visible.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test-id='filterbox-item-display-name']")));
    }

    public void selectFirstProduct(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", firstProduct);
        WaitUtils.waitForVisible(By.xpath("(//li[contains(@class,'productListContent')]//a)[1]"));

        firstProduct.click();
    }
}
