package step_definitions;

import io.cucumber.java.en.And;
import pages.LoginPage;

public class Login {

    private final LoginPage loginPage;

    public Login() {
        this.loginPage = new LoginPage();
    }

    @And("enter {string} {string} on the Login Page")
    public void enterOnTheLoginPage(String field, String value) {
        switch (field) {
            case "Username":
                loginPage.inputUsername(value);
                break;
            case "Password":
                loginPage.inputPassword(value);
                break;
        }
    }

    @And("click button {string} on the Login Page")
    public void clickButtonOnTheLoginPage(String button) {
        switch (button) {
            case "Login":
                loginPage.clickLoginButton();
                break;
        }
    }
}
