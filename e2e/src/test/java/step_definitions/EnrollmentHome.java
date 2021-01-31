package step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.EnrollmentHomePage;

import static org.assertj.core.api.Assertions.assertThat;

public class EnrollmentHome {

    private final EnrollmentHomePage enrollmentHomePage;

    public EnrollmentHome() {
        this.enrollmentHomePage = new EnrollmentHomePage();
    }

    @Given("open Home Page")
    public void openHomePage() {
        enrollmentHomePage.openHomePage();
    }

    @When("select {string} {string} on the Enrollment Home Page")
    public void selectOnTheEnrollmentHomePage(String select, String option) {
        switch (select) {
            case "Kierunek":
                enrollmentHomePage.selectFieldOfStudy(option);
                break;
            case "Blok zapisów":
                enrollmentHomePage.selectEnrollmentBlock(option);
                break;

        }
    }

    @Then("check if {string} section contains {string} on the Enrollment Home Page")
    public void checkIfSectionContainsOnTheEnrollmentHomePage(String section, String expectedText) {
        switch (section) {
            case "Informacje":
                assertThat(enrollmentHomePage.getInformationSection()).contains(expectedText);
                break;
        }
    }

    @And("check if {string} table contains {string} on the Enrollment Home Page")
    public void checkIfTableContainsOnTheEnrollmentHomePage(String table, String expectedText) {
        switch (table) {
            case "Bieżące kursy":
                assertThat(enrollmentHomePage.getCurrentCourses()).contains(expectedText);
                break;
            case "Zaległe kursy":
                assertThat(enrollmentHomePage.getOverdueCourses()).contains(expectedText);
                break;
        }
    }

    @When("click button {string} on the Enrollment Home Page")
    public void clickButtonOnTheEnrollmentHomePage(String buttonName) {
        switch (buttonName) {
            case "Przejdź do zapisów":
                enrollmentHomePage.clickGoToEnrollmentsButton();
                break;
        }
    }
}
