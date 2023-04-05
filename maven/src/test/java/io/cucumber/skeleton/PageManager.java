package io.cucumber.skeleton;

import io.cucumber.skeleton.libraries.TestContext;

public class PageManager {
    public TestContext testContext;
    PageManager(TestContext testContext)
    {
        this.testContext = testContext;
    }

    PageStep getPageStep(){
        return new PageStep(testContext);
    }
    
}
