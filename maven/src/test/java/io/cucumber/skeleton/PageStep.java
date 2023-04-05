package io.cucumber.skeleton;

import org.openqa.selenium.By;

import io.cucumber.skeleton.libraries.TestContext;

public class PageStep {
    public TestContext testContext;
    public By searchBar = By.xpath("//input[@name='q']");
    public By firstDate = By.xpath("//text()[contains(., \"hacia el año\")]");  
    public By firstDateWebElemet = By.xpath("//text()[contains(., \"hacia el año\")]/..");
    public By acceptButton  = By.xpath("//button[@id='L2AGLb']");
    public PageStep(TestContext testContext){
        this.testContext = testContext;
    }

    public By  getLinkwith(String site){
        String link = "//a[contains(@href,'"+site+ "')]";
        return By.xpath(link);
    }

}
