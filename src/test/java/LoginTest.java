import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    public void autorizationWithStandartValidUsername() {
        var username = "standard_user";
        var password = "secret_sauce";
        driver.findElement(usernameLocator).sendKeys(username);
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("inventory.html"));

        var expectedResult = "Products";
        var actualResult = driver.findElement(titleLocator).getText();
        assertEquals(expectedResult, actualResult, "Текст заголовка не совпадает с ожидаемым");
    }

    @Test
    public void authorizationWithLockedOutUserTest() {
        var username = "locked_out_user";
        var password = "secret_sauce";
        driver.findElement(usernameLocator).sendKeys(username);
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();

        assertTrue(driver.findElement(errorMessageLocator).isDisplayed());

        var expectedResult = "Epic sadface: Sorry, this user has been locked out.";
        var actualResult = driver.findElement(errorMessageLocator).getText();
        assertEquals(expectedResult, actualResult, "Неверный текст сообщения об ошибке");
    }

    @Test
    public void autorizationUsernameIsEmpty() {
        var password = "secret_sauce";
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();

        var expectedResult = "Epic sadface: Username is required";
        var actualResult = driver.findElement(errorMessageLocator).getText();
        assertEquals(expectedResult, actualResult, "Неверный текст сообщения об ошибке");
    }

    @Test
    public void autorizationPasswordIsEmpty() {
        var username = "standard_user";
        driver.findElement(usernameLocator).sendKeys(username);
        driver.findElement(loginButtonLocator).click();

        var expectedResult = "Epic sadface: Password is required";
        var actualResult = driver.findElement(errorMessageLocator).getText();
        assertEquals(expectedResult, actualResult, "Неверный текст сообщения об ошибке");
    }

    @Test
    public void autorizationUsernameAndPasswordIsEmpty() {
        driver.findElement(loginButtonLocator).click();

        var expectedResult = "Epic sadface: Username is required";
        var actualResult = driver.findElement(errorMessageLocator).getText();
        assertEquals(expectedResult, actualResult, "Неверный текст сообщения об ошибке");
    }

    @Test
    public void autorizationWithUsernameInCorrect() {
        var username = "test@gmail.com";
        var password = "secret_sauce";
        driver.findElement(usernameLocator).sendKeys(username);
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();

        var expectedResult = "Epic sadface: Username and password do not match any user in this service";
        var actualResult = driver.findElement(errorMessageLocator).getText();
        assertEquals(expectedResult, actualResult, "Неверный текст сообщения об ошибке");
    }

    @Test
    public void autorizationWithPasswordIsIncorrect() {
        var username = "standard_user";
        var password = "qwerty123";
        driver.findElement(usernameLocator).sendKeys(username);
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();

        var expectedResult = "Epic sadface: Username and password do not match any user in this service";
        var actualResult = driver.findElement(errorMessageLocator).getText();
        assertEquals(expectedResult, actualResult, "Неверный текст сообщения об ошибке");
    }
}