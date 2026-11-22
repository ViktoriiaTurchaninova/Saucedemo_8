import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected String url = "https://www.saucedemo.com";
    protected By usernameLocator = By.id("user-name");
    protected By passwordLocator = By.id("password");
    protected By loginButtonLocator = By.id("login-button");
    protected By errorMessageLocator = By.cssSelector("[data-test=error]");
    protected By titleLocator = By.className("title");

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

        driver = new ChromeDriver(options);
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

