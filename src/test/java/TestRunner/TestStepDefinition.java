package TestRunner;


import cucumber.api.junit.Cucumber;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources/Feature",
        glue = {"MockTestStepDefination"}
)
public class TestStepDefinition {
}
