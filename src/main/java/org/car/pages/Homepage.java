package org.car.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.junit.Assert;
import support.Utils;
import java.io.*;
import java.time.Duration;
import java.util.List;

import static support.DriverFactory.driver;

public class Homepage {

    Utils utils = new Utils();
    int i;
    @FindBy(id = "onetrust-accept-btn-handler")
    private WebElement acceptCookiesButton;
    @FindBy(id = "vehicleReg")
    private WebElement vehicleRegTxtbox;
    @FindBy(id = "Mileage")
    private WebElement mileageTxtbox;
    @FindBy(id = "btn-go")
    private WebElement getMyValuationButton;
    @FindBy(css = "div[class^='position-relative overflow-hidden'] div[class='d-table'] div:nth-child(1) div:nth-child(2)")
    private WebElement carMakeText;
    @FindBy(css = "div[class^='position-relative overflow-hidden'] div[class='d-table'] div:nth-child(2) div:nth-child(2)")
    private WebElement carModelText;
    @FindBy(css = "div[class^='position-relative overflow-hidden'] div[class='d-table'] div:nth-child(3) div:nth-child(2)")
    private WebElement carYearText;
    @FindBy(id = "btn-back")
    private WebElement backButton;

    public Homepage() {
        PageFactory.initElements(driver, this);
    }

    public void getHomePageUrl() {
        String actual = driver.getCurrentUrl();
        Assert.assertEquals("https://www.webuyanycar.com/", actual);

    }

    public void doSearch(List<String> numberList, int miles,String outputFilepath) throws IOException {
        utils.waitForElementToBeClickable(acceptCookiesButton, 5);
        acceptCookiesButton.click();
        for (i = 0; i < numberList.size(); i++) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(90));
            utils.waitForElementToBeClickable(vehicleRegTxtbox, 120);
            vehicleRegTxtbox.sendKeys(numberList.get(i));
            utils.waitForElementToBeClickable(mileageTxtbox, 30);
            mileageTxtbox.sendKeys(String.valueOf(miles));
            getMyValuationButton.click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            List<WebElement> elements = driver.findElements(By.cssSelector(".text-focus.ng-star-inserted"));
            if (!elements.isEmpty()) {
                System.out.println("Testcase failed: Not valid vehicle Registration number" + numberList.get(i));
                driver.navigate().back();
                utils.waitForElementToBeClickable(vehicleRegTxtbox,60);
            } else
                assertTheVehicleDetails(outputFilepath, numberList);
        }
    }

    public void assertTheVehicleDetails(String filePath, List<String> numberList) throws IOException {
        utils.waitForElementToBeVisibleWithBoolean(carMakeText, 90);
        BufferedReader in = new BufferedReader(new FileReader(filePath));
        String line;
        String regText = numberList.get(i);
        String text = regText.replace(" ", "");
        while ((line = in.readLine()) != null) {
            String[] carDetails = line.split(",");
            if (line.startsWith(text)) {
                String make = carDetails[1];
                String model = carDetails[2];
                String year = carDetails[3];
                Assert.assertEquals(make, carMakeText.getText());
                Assert.assertEquals(model, carModelText.getText());
                Assert.assertEquals(year, carYearText.getText());

            }
        }
        in.close();
        utils.waitForElementToBeClickable(backButton,30);
        backButton.click();
        utils.waitForElementToBeVisible(vehicleRegTxtbox, 30);
        vehicleRegTxtbox.clear();
        mileageTxtbox.clear();
    }

}
