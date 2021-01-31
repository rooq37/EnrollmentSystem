package step_definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.GroupEnrollmentPage;

import static org.assertj.core.api.Assertions.assertThat;

public class GroupEnrollment {

    private final GroupEnrollmentPage groupEnrollmentPage;

    public GroupEnrollment() {
        this.groupEnrollmentPage = new GroupEnrollmentPage();
    }

    @Then("check if {string} label contains {string} on the Group Enrollment Page")
    public void checkIfLabelContainsOnTheGroupEnrollmentPage(String label, String expectedText) {
        switch (label) {
            case "Kierunek":
                assertThat(groupEnrollmentPage.getTextFromFieldOfStudyLabel()).contains(expectedText);
                break;
            case "Blok zapisów":
                assertThat(groupEnrollmentPage.getTextFromEnrollmentBlockLabel()).contains(expectedText);
                break;
        }
    }

    @When("select {string} {string} on the Group Enrollment Page")
    public void selectOnTheGroupEnrollmentPage(String select, String option) {
        switch (select) {
            case "Kursy":
                groupEnrollmentPage.selectCourse(option);
                break;
        }
    }

    @Then("check if {string} table contains {string} on the Group Enrollment Page")
    public void checkIfTableContainsOnTheGroupEnrollmentPage(String table, String expectedText) {
        switch (table) {
            case "Grupy":
                assertThat(groupEnrollmentPage.getGroupsTable()).contains(expectedText);
                break;
            case "Bieżące kursy":
                assertThat(groupEnrollmentPage.getCurrentCoursesTable()).contains(expectedText);
                break;
            case "Zaległe kursy":
                assertThat(groupEnrollmentPage.getOverdueCoursesTable()).contains(expectedText);
                break;
        }
    }

    @When("click button {string} in the row {string} in the {string} table on the Group Enrollment Page")
    public void clickButtonInTheRowInTheTableOnTheGroupEnrollmentPage(String button, String row, String table) {
        switch (table) {
            case "Grupy":
                groupEnrollmentPage.clickButtonInRowInGroupsTable(row, button);
                break;
            case "Bieżące kursy":
                groupEnrollmentPage.clickButtonInRowInCurrentCoursesTable(row, button);
                break;
            case "Zaległe kursy":
                groupEnrollmentPage.clickButtonInRowInOverdueCoursesTable(row, button);
                break;
        }
    }
}
