package tests.contactUsTests;

import libs.ExcelDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import pages.ContactUsPage;
import tests.ParentTest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

/**
 * Created by roman on 2/8/16.
 */
public class TestCase11 extends ParentTest{
    ContactUsPage contactUsPage;
    ExcelDriver excelDriver;
    Map map;

    public TestCase11(String browser) throws MalformedURLException {
        super(browser);
    }
    @Test
    public void test11() throws IOException {
        contactUsPage = new ContactUsPage(driver);
        contactUsPage.openContactUsPageAndBrowser();
        Assert.assertTrue("Checks steps: ",
                contactUsPage.selectYesForHavingAccountFromDD());
        Assert.assertTrue("Checks expected result: ",
                contactUsPage.isInputEmailOnPage());
    }

    @After
    public void tearDown(){
        contactUsPage.closeContactUsPageAndBrowser();
    }

}
