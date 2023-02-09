package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = "stepDefs",
        plugin = {"json:target/cucumber-reports/Cucumber.json"},
        tags = "@Runme"
)

public class TestRunner {

}
