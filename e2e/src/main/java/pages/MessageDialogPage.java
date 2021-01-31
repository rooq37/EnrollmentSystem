package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MessageDialogPage extends BasePage {

    private static final By successDialog = By.id("successDialog");
    private static final By errorDialog = By.id("errorDialog");
    private static final By okButton = By.id("okButton");

    public String getTextFromSuccessDialog() {
        new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.visibilityOfElementLocated(successDialog));
        return getDriver().findElement(successDialog).getText();
    }

    public String getTextFromErrorDialog() {
        new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.visibilityOfElementLocated(errorDialog));
        return getDriver().findElement(errorDialog).getText();
    }

    public void clickOkButton() {
        new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.visibilityOfElementLocated(okButton));
        getDriver().findElement(okButton).click();
        new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.invisibilityOfElementLocated(okButton));
    }

}
