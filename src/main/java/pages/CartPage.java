package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "product_details_1sAZl")
    public WebElement cartProduct;

    @FindBy(id = "continue_step_btn")
    public WebElement continueStepBtn;

    public boolean isProductInCart(){

        return cartProduct.isDisplayed();
    }
}
