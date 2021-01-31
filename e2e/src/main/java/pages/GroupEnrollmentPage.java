package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GroupEnrollmentPage extends BasePage {

    private static final String XPATH_SELECT_OPTION = "//span[@class='mat-option-text' and contains(.,'${text}')]";
    private static final String XPATH_BUTTON_IN_TABLE_ROW = "//div[@id='${tableId}']//tr[contains(.,'${textInRow}')]//span[contains(.,'${buttonName}')]";

    private static final By fieldOfStudyLabel = By.id("fieldOfStudyLabel");
    private static final By enrollmentBlockLabel = By.id("enrollmentBlockLabel");
    private static final By courseSelect = By.id("courseSelect");
    private static final By groupsTable = By.id("groupsTable");
    private static final By currentCoursesTable = By.id("currentCoursesTable");
    private static final By overdueCoursesTable = By.id("overdueCoursesTable");

    public String getTextFromFieldOfStudyLabel() {
        new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.visibilityOfElementLocated(fieldOfStudyLabel));
        return getDriver().findElement(fieldOfStudyLabel).getText();
    }

    public String getTextFromEnrollmentBlockLabel() {
        new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.visibilityOfElementLocated(enrollmentBlockLabel));
        return getDriver().findElement(enrollmentBlockLabel).getText();
    }

    public void selectCourse(String course) {
        new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.visibilityOfElementLocated(courseSelect));
        getDriver().findElement(courseSelect).click();
        String optionXpath = XPATH_SELECT_OPTION.replace("${text}", course);
        getDriver().findElement(By.xpath(optionXpath)).click();
    }

    public String getGroupsTable() {
        new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.visibilityOfElementLocated(groupsTable));
        return getDriver().findElement(groupsTable).getText();
    }

    public String getCurrentCoursesTable() {
        new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.visibilityOfElementLocated(currentCoursesTable));
        return getDriver().findElement(currentCoursesTable).getText();
    }

    public String getOverdueCoursesTable() {
        new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.visibilityOfElementLocated(overdueCoursesTable));
        return getDriver().findElement(overdueCoursesTable).getText();
    }

    public void clickButtonInRowInGroupsTable(String row, String button) {
        String xpath = XPATH_BUTTON_IN_TABLE_ROW
                .replace("${tableId}", "groupsTable")
                .replace("${textInRow}", row)
                .replace("${buttonName}", button);
        new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        getDriver().findElement(By.xpath(xpath)).click();
    }

    public void clickButtonInRowInCurrentCoursesTable(String row, String button) {
        String xpath = XPATH_BUTTON_IN_TABLE_ROW
                .replace("${tableId}", "currentCoursesTable")
                .replace("${textInRow}", row)
                .replace("${buttonName}", button);
        new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        getDriver().findElement(By.xpath(xpath)).click();
    }

    public void clickButtonInRowInOverdueCoursesTable(String row, String button) {
        String xpath = XPATH_BUTTON_IN_TABLE_ROW
                .replace("${tableId}", "overdueCoursesTable")
                .replace("${textInRow}", row)
                .replace("${buttonName}", button);
        new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        getDriver().findElement(By.xpath(xpath)).click();
    }

}
