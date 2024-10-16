package org.car.StepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import support.DriverFactory;
import support.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;

public class BaseClass {
    DriverFactory driverFactory=new DriverFactory();

 @Before
    public void setUp() throws IOException {
     driverFactory.openBrowser();
 }
 @After
    public void tearDown(){
     driverFactory.closeBrowser();
 }
}
