package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    private static final By usernameInput = By.id("username");
    private static final By passwordInput = By.id("password");
    private static final By loginButton = By.xpath("//button[text()='Continue']");

    public void inputUsername(String username) {
        new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        getDriver().findElement(usernameInput).sendKeys(username);
    }

    public void inputPassword(String password) {
        new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        getDriver().findElement(passwordInput).sendKeys(password);
    }

    public void clickLoginButton() {
        new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        getDriver().findElement(loginButton).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
