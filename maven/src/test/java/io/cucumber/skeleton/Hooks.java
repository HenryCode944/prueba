package io.cucumber.skeleton;

import java.lang.reflect.Field;
import java.util.List;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.core.backend.TestCaseState;
import io.cucumber.core.gherkin.Pickle;
import io.cucumber.core.gherkin.Step;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.event.TestCase;
import io.cucumber.skeleton.libraries.TestContext;

public class Hooks {
    public TestContext testContext;
    public PageManager pageManger;
    public Scenario scenario;

    public Hooks(TestContext testContext) {
        this.testContext = testContext;
        this.pageManager = new PageManager(testContext);
    }

    public PageManager pageManager;

    @Before
    public void before(Scenario scenario) {

        testContext.scenario = scenario;
        this.scenario = scenario;
    }

    @After
    public void after()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) testContext.getWebDriverManager())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Error"); // stick it in the report

        } else {
            Field delegate = scenario.getClass().getDeclaredField("delegate");
            delegate.setAccessible(true);
            TestCaseState state = (TestCaseState) delegate.get(scenario);
            Field testCaseF = state.getClass().getDeclaredField("testCase");
            testCaseF.setAccessible(true);
            TestCase testCase = (TestCase) testCaseF.get(state);
            Field pickle = testCase.getClass().getDeclaredField("pickle");
            pickle.setAccessible(true);
            Pickle pickleState = (Pickle) pickle.get(testCase);
            List<Step> steps = pickleState.getSteps();

            final byte[] screenshot2 = ((TakesScreenshot) testContext.getWebDriverManager())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot2, "image/png", steps.get(steps.size()-1).getText()); // stick it in the report
        }
        testContext.getWebDriverManager().close();

    }
}