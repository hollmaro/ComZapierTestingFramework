package contactUsTests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import parentTest.ParentTest;
import userLibs.ConfigData;
import userLibs.ExcelDriver;

import java.io.IOException;
import java.util.Map;

public class TestCase10 extends ParentTest {
    ContactUsPage contactUsPage;

    @Test
    public void test10() throws IOException {
        contactUsPage = new ContactUsPage(driver);
        Map map = ExcelDriver.getData(ConfigData.getCfgValue("TestDataSignUp"), "TestCase2");
        ExcelDriver excelDriver = new ExcelDriver(map);

        contactUsPage.openContactUsPageAndBrowser();
        Assert.assertTrue(
                contactUsPage.selectNoForHavingAccountFromDD()&&
                        contactUsPage.selectQuestionFromDDByQuestionNum(1)&&
                        contactUsPage.clickSendEmailButton(),
                "Check of step1 is failed");
        Assert.assertTrue(contactUsPage.isPopUpErrorOnPage(), "Error is not shown");
    }

    @AfterClass
    public void tearDown(){
        contactUsPage.closeContactUsPageAndBrowser();
    }
}
