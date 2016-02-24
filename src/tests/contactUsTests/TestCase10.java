package tests.contactUsTests;

import libs.ConfigData;
import libs.ExcelDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import pages.ContactUsPage;
import tests.ParentTest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

public class TestCase10 extends ParentTest{
    ContactUsPage contactUsPage;

    /**
     * CONSTRUCTOR for TestCase10
     * @param browser
     * @throws MalformedURLException
     */
    public TestCase10(String browser) throws MalformedURLException {
        super(browser);
    }

    @Test
    public void test10() throws IOException {
        contactUsPage = new ContactUsPage(driver);
        contactUsPage.openContactUsPageAndBrowser();
        Assert.assertTrue("Check step1: ",
                contactUsPage.selectNoForHavingAccountFromDD()&&
                contactUsPage.selectQuestionFromDDByQuestionNum(1)&&
                contactUsPage.clickSendEmailButton());
        Assert.assertTrue("Check result: ", contactUsPage.isPopUpErrorOnPage());
    }
    @After
    public void tearDown(){
        contactUsPage.closeContactUsPageAndBrowser();
    }
}
