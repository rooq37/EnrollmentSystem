package step_definitions;

import io.cucumber.java.en.When;
import pages.TopbarPage;

public class Topbar {

    private final TopbarPage topbarPage;

    public Topbar() {
        this.topbarPage = new TopbarPage();
    }

    @When("click button {string} on the Topbar")
    public void clickButtonOnTheTopbar(String button) {
        switch (button) {
            case "Zapisy":
                topbarPage.clickEnrollmentsButton();
                break;
        }
    }
}
