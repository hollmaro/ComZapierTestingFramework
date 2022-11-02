package contactUsTests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import parentTest.ParentTest;

public class TestCase11 extends ParentTest{
    ContactUsPage contactUsPage;

    @Test
    public void test11() {
        contactUsPage = new ContactUsPage(driver);
        contactUsPage.openContactUsPageAndBrowser();
        Assert.assertTrue(contactUsPage.selectYesForHavingAccountFromDD(), "No such options in DD");
        Assert.assertTrue(contactUsPage.isInputEmailOnPage(), "Input email is not on page");
    }

    @AfterClass
    public void tearDown(){
        contactUsPage.closeContactUsPageAndBrowser();
    }

}
