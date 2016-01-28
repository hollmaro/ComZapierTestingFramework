package tests.contactUsTests;

import libs.ConfigData;
import libs.ExcelDriver;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import pages.ContactUsPage;
import tests.ParentTest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

/**
 * Created by roman on 1/24/16.
 */
public class TC10 extends ParentTest{
    ContactUsPage contactUsPage;
    ExcelDriver excelDriver;

    /**
     * CONSTRUCTOR for TC10
     * @param browser
     * @throws MalformedURLException
     */
    public TC10(String browser) throws MalformedURLException {
        super(browser);
    }

    @Test
    public void test10() throws IOException {
        Map map = ExcelDriver.getData(ConfigData.getCfgValue("TestDataContactUs"), "TC10");
        excelDriver = new ExcelDriver(map);
        contactUsPage = new ContactUsPage(driver);
        contactUsPage.openContactUsPageAndBrowser();


        Assert.assertTrue("Check step1: ",
                contactUsPage.selectQuestionFromDDByText());
        Assert.assertTrue("Check step2: ", contactUsPage.typeQuestionText(excelDriver.getValueByKey("questionText")));
        Assert.assertTrue("Check result: ", contactUsPage.isButtonNextIsClickableOnContactUsPage());
    }
    @After
    public void tearDown(){
        contactUsPage.closeContactUsPageAndBrowser();
    }
}
