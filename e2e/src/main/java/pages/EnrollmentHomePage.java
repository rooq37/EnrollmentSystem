package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EnrollmentHomePage extends BasePage {

    private static final String XPATH_SELECT_OPTION = "//span[@class='mat-option-text' and contains(.,'${text}')]";

    private static final By fieldOfStudySelect = By.id("fieldOfStudySelect");
    private static final By enrollmentBlockSelect = By.id("enrollmentBlockSelect");
    private static final By informationSection = By.id("informationSection");
    private static final By currentCoursesTable = By.id("currentCourses");
    private static final By overdueCoursesTable = By.id("overdueCourses");
    private static final By goToEnrollmentsButton = By.id("goToEnrollmentsButton");

    public void openHomePage() {
        getDriver().get(URL);
    }

    public void selectFieldOfStudy(String fieldOfStudy) {
        new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.visibilityOfElementLocated(fieldOfStudySelect));
        getDriver().findElement(fieldOfStudySelect).click();
        String optionXpath = XPATH_SELECT_OPTION.replace("${text}", fieldOfStudy);
        getDriver().findElement(By.xpath(optionXpath)).click();
    }

    public void selectEnrollmentBlock(String enrollmentBlock) {
        new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.visibilityOfElementLocated(enrollmentBlockSelect));
        getDriver().findElement(enrollmentBlockSelect).click();
        String optionXpath = XPATH_SELECT_OPTION.replace("${text}", enrollmentBlock);
        getDriver().findElement(By.xpath(optionXpath)).click();
    }

    public String getInformationSection() {
        new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.visibilityOfElementLocated(informationSection));
        return getDriver().findElement(informationSection).getText();
    }

    public String getCurrentCourses() {
        new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.visibilityOfElementLocated(currentCoursesTable));
        return getDriver().findElement(currentCoursesTable).getText();
    }

    public String getOverdueCourses() {
        new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.visibilityOfElementLocated(overdueCoursesTable));
        return getDriver().findElement(overdueCoursesTable).getText();
    }

    public void clickGoToEnrollmentsButton() {
        new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.visibilityOfElementLocated(goToEnrollmentsButton));
        getDriver().findElement(goToEnrollmentsButton).click();
    }

}
