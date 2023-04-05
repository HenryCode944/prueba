package io.cucumber.skeleton;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.skeleton.libraries.TestContext;

public class StepDefinitions {
    public WebDriver driver;
    TestContext testContext;
    public PageStep pageStep = new PageStep(testContext);
    public StepDefinitions(TestContext testContext){
        this.testContext = testContext;
        this.driver = testContext.getWebDriverManager();
    }

    @Given("^Search in google \"([^\"]*)\"$")
public void search_in_google(String word) {
  driver.get("https://www.google.com/");
  driver.findElement(pageStep.acceptButton).click();
  WebElement searchBar = driver.findElement(pageStep.searchBar);
  searchBar.sendKeys(word);
  driver.findElement(pageStep.searchBar).submit();
  testContext.data.put("word", word);


}
@When("^Click on \"([^\"]*)\" link$")
public void click_on_wikipedia_link(String site) {
   driver.findElement(pageStep.getLinkwith(site)).click();
}
@Then("^Check first automatization date$")
public void check_first_automatization_date() throws InterruptedException {
    String firstDate = driver.findElement(pageStep.firstDateWebElemet).getText();
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(pageStep.firstDateWebElemet));
    Thread.sleep(500); 
    assertTrue(firstDate.contains("270 a. C"));
}

   

   
}
