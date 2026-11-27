package tests;

import org.junit.jupiter.api.Test;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    public void autorizationWithStandardValidUsername() {
        loginPage.open();
        loginPage.login(LoginPage.STANDARD_USER, LoginPage.PASSWORD);
        assertTrue(productsPage.titleIsVisible());
        assertEquals("Products", productsPage.getTitleText(), "Текст заголовка не совпадает с ожидаемым");
        assertTrue(productsPage.getCurrentUrl().contains("inventory.html"));
    }

    @Test
    public void authorizationWithLockedOutUserTest() {
        loginPage.open();
        loginPage.login(LoginPage.LOCKED_OUT_USER, LoginPage.PASSWORD);
        assertTrue(loginPage.errorMessageIsVisible());
        assertEquals("Epic sadface: Sorry, this user has been locked out.", loginPage.getErrorMessageText(), "Неверный текст сообщения об ошибке");
    }

    @Test
    public void autorizationUsernameIsEmpty() {
        loginPage.open();
        loginPage.login("", LoginPage.PASSWORD);
        assertTrue(loginPage.errorMessageIsVisible());
        assertEquals("Epic sadface: Username is required", loginPage.getErrorMessageText(), "Неверный текст сообщения об ошибке");
    }

    @Test
    public void autorizationPasswordIsEmpty() {
        loginPage.open();
        loginPage.login(LoginPage.STANDARD_USER, "");
        assertTrue(loginPage.errorMessageIsVisible());
        assertEquals("Epic sadface: Password is required", loginPage.getErrorMessageText(), "Неверный текст сообщения об ошибке");
    }

    @Test
    public void autorizationUsernameAndPasswordIsEmpty() {
        loginPage.open();
        loginPage.login("", "");
        assertTrue(loginPage.errorMessageIsVisible());
        assertEquals("Epic sadface: Username is required", loginPage.getErrorMessageText(), "Неверный текст сообщения об ошибке");
    }

    @Test
    public void autorizationWithUsernameInCorrect() {
        loginPage.open();
        loginPage.login("test@gmail.com", LoginPage.PASSWORD);
        assertTrue(loginPage.errorMessageIsVisible());
        assertEquals("Epic sadface: Username and password do not match any user in this service", loginPage.getErrorMessageText(), "Неверный текст сообщения об ошибке");
    }

    @Test
    public void autorizationWithPasswordIsIncorrect() {
        loginPage.open();
        loginPage.login(LoginPage.STANDARD_USER,"qwerty123");
        assertTrue(loginPage.errorMessageIsVisible());
        assertEquals("Epic sadface: Username and password do not match any user in this service", loginPage.getErrorMessageText(), "Неверный текст сообщения об ошибке");
    }
}