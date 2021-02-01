package step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.MessageDialogPage;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageDialog {

    private final MessageDialogPage messageDialogPage;

    public MessageDialog() {
        this.messageDialogPage = new MessageDialogPage();
    }

    @Then("check if {string} dialog contains {string} on the Message Dialog")
    public void checkIfDialogContainsOnTheMessageDialog(String dialog, String expectedText) {
        switch (dialog) {
            case "Sukces":
                assertThat(messageDialogPage.getTextFromSuccessDialog()).contains(expectedText);
                break;
            case "Błąd":
                assertThat(messageDialogPage.getTextFromErrorDialog()).contains(expectedText);
                break;
        }
    }

    @And("click button {string} on the Message Dialog")
    public void clickButtonOnTheMessageDialog(String button) {
        switch (button) {
            case "Ok":
                messageDialogPage.clickOkButton();
                break;
        }
    }
}
