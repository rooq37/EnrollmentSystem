package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class BasePage {

    public static final String URL = "http://localhost:4200";

    private static final String PROPERTY_KEY = "webdriver.chrome.driver";
    private static final String PROPERTY_VALUE = "lib/chromedriver_win32/chromedriver88.exe";

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if(driver == null){
            System.setProperty(PROPERTY_KEY, PROPERTY_VALUE);
            driver = new ChromeDriver();
        }
        return driver;
    }

}
