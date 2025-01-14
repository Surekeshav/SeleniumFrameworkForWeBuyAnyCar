package org.car.StepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.car.pages.Homepage;
import support.Utils;
import java.io.IOException;
import java.util.List;

public class MyStepdefs {

    Homepage homepage = new Homepage();
    Utils utils = new Utils();
    List<String> vehicleNumberList;

    @Given("I am Homepage")
    public void iAmHomepage() {
        homepage.getHomePageUrl();
    }

    @When("I read the input file {string} and fetch vehicle registration number")
    public void iReadTheInputFileAndFetchVehicleRegistrationNumber(String filePath) throws IOException {
        vehicleNumberList = utils.readTextFromTextFile(filePath);
    }

    @Then("I search and assert the car details using {string} file")
    public void iSearchAndAssertTheCarDetailsUsingFile(String filePath) throws IOException {
        int randomnumber = new Utils().randomNumberGenerator(100000);
        homepage.doSearch(vehicleNumberList, randomnumber,filePath);
    }
}
