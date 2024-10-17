package support;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils extends DriverFactory {

    List<String> regNumberList;

    public void waitForElementToBeVisible(WebElement element, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeVisibleWithBoolean(WebElement element, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeClickable(WebElement element, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public List<String> readTextFromTextFile(String filePath) throws IOException {
        regNumberList = new ArrayList<>();
        String textInfile = new String(Files.readAllBytes(Paths.get(filePath)));
        String regex = "\\b[A-Z]{2}\\d{2}\\s?[A-Z]{3}\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(textInfile);
        while (matcher.find()) {
            regNumberList.add(matcher.group());
        }
        System.out.println(regNumberList);
        return regNumberList;
    }

    public int randomNumberGenerator(int size) {
        Random random = new Random();
        return random.nextInt(size - 1);
    }

    public void javaScriptExecutorClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void waitForStaleElementToBeClickable(WebElement element, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

}
