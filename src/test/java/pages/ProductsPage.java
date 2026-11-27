package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {
    WebDriver driver;

    private final By titleLocator = By.className("title");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean titleIsVisible() {
        return driver.findElement(titleLocator).isDisplayed();
    }

    public String getTitleText() {
        return driver.findElement(titleLocator).getText();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
