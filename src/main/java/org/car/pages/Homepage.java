package org.car.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.junit.Assert;

import support.DriverFactory;
import support.Utils;

import java.io.*;
import java.util.List;
import java.util.Scanner;

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
    @FindBy(xpath = "//div[@class='d-none d-sm-block mb-4 ng-tns-c2261452767-2 ng-star-inserted']//div[normalize-space()='Manufacturer:']/following-sibling::div")
    private WebElement carMakeText;
    @FindBy(xpath = "//div[@class='d-none d-sm-block mb-4 ng-tns-c2261452767-2 ng-star-inserted']//div[normalize-space()='Model:']/following-sibling::div")
    private WebElement carModelText;
    @FindBy(xpath = "//div[@class='d-none d-sm-block mb-4 ng-tns-c2261452767-2 ng-star-inserted']//div[normalize-space()='Year:']/following-sibling::div")
    private WebElement carYearText;;
    @FindBy(id = "btn-back")
    private WebElement backButton;
     @FindBy(css = ".text-focus.ng-star-inserted")
    private WebElement sorryMsg;

    public Homepage(){
        PageFactory.initElements(driver,this);
    }
    public void getHomePageUrl(){
        String actual= driver.getCurrentUrl();
        Assert.assertEquals("https://www.webuyanycar.com/",actual);

    }
    public void doSearch(List<String> numberList, int miles) throws IOException {
            utils.waitForElementToBeClickable(acceptCookiesButton, 5);
            acceptCookiesButton.click();
            for(i=0;i<numberList.size();i++){
            utils.waitForElementToBeClickable(vehicleRegTxtbox, 90);
            vehicleRegTxtbox.sendKeys(numberList.get(i));
            utils.waitForElementToBeClickable( mileageTxtbox, 30);
            mileageTxtbox.sendKeys(String.valueOf(miles));
//            utils.waitForStaleElementToBeClickable(getMyValuationButton,30);
            getMyValuationButton.click();
//            utils.javaScriptExecutorClick(getMyValuationButton);
//            if(utils.waitForElementToBeVisible(carMakeText,90))

//            if(!(sorryMsg.getText()).equalsIgnoreCase("Sorry, we couldn't find your car"))
                assertTheVehicleDetails("car_output.txt",numberList);
        }
    }
    public void assertTheVehicleDetails(String filePath, List<String> numberList) throws IOException {
        if (utils.waitForElementToBeVisibleWithBoolean(carMakeText, 90)) {
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
            backButton.click();
            utils.waitForElementToBeVisible(vehicleRegTxtbox, 30);
            vehicleRegTxtbox.clear();
            mileageTxtbox.clear();
        } else {
            Assert.fail();
            driver.navigate().back();
        }
    }

}
