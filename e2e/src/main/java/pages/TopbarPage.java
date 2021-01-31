package pages;

import org.openqa.selenium.By;

public class TopbarPage extends BasePage {

    private static final By enrollmentsButton = By.xpath("//button/span[contains(.,'Zapisy')]");

    public void clickEnrollmentsButton() {
        getDriver().findElement(enrollmentsButton).click();
    }

}
