import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", glue={"org.car.StepDefs"},
        plugin = {"pretty","html:target/cucumber","json:target/cucumber.json"},
        tags="@Regression")
public class RunnerTest {
}
