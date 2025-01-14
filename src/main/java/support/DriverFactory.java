package support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

public class DriverFactory {
    public static WebDriver driver;

    public DriverFactory() {
        PageFactory.initElements(driver, this);
    }

    public void openBrowser() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://www.webuyanycar.com/");
    }

    public void closeBrowser() {
        driver.quit();
    }
}
