package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utilities.BaseTest;
import utilities.DriverFactory;

import java.time.Duration;

public class ProductPurchaseTest extends BaseTest {

    @Test
    public void productSearchAddToCartTest() throws InterruptedException {

        var driver = DriverFactory.getDriver();

        HomePage homePage = new HomePage(driver);
        SearchResultsPage resultsPage = new SearchResultsPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.navigate().refresh();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // 1 -> search
        homePage.searchProduct("Laptop");
        wait.until(ExpectedConditions.urlContains("Laptop"));

        // 2 -> results visible
        Assert.assertTrue(resultsPage.isResultsDisplayed(), "Results not displayed");

        // Cookie click
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button#onetrust-accept-btn-handler")));
        homePage.cookies.click();
        js.executeScript("window.scrollBy(0, 450)");

        // 3 -> filter
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("markalar")));
        resultsPage.applyFilter();

        // 4 -> select product
        resultsPage.selectFirstProduct();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[contains(@class,'productListContent')]//a)[1]")));

        // 5 -> verify product page
        Assert.assertTrue(productPage.isProductPageOpened(), "Product page not opened");

        // 6 -> add to cart
        productPage.addToCart();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'ProductOnBasketHeader')]//button[text()='Sepete git']")));

        // 7 -> go cart
        productPage.goToCartBtn.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("continue_step_btn")));

        // 8 -> verify cart
        Assert.assertTrue(cartPage.isProductInCart(), "Product not found in cart");
    }
}
