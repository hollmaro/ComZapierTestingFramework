package tests.signUpTests;

import libs.ConfigData;
import libs.ExcelDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import pages.SignUpPage;
import tests.ParentTest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

/**
 * Created by roman on 2/18/16.
 */
public class TestCase4 extends ParentTest {

    public TestCase4(String browser) throws MalformedURLException {
        super(browser);
    }

    SignUpPage signUpPage;


    @Test
    public void test4() throws IOException {
        Map map = ExcelDriver.getData(ConfigData.getCfgValue("TestDataSignUp"), "TestCase4");
        ExcelDriver excelDriver = new ExcelDriver(map);
        signUpPage = new SignUpPage(driver);

        signUpPage.openSignUpPage();
        Assert.assertTrue("Check signUp steps: ",
                signUpPage.typeFirstNameIntoInputFirstName(excelDriver.getValueByKey("firstName"))&&
                signUpPage.typeLastNameIntoInputLastName(excelDriver.getValueByKey("lastName"))&&
                signUpPage.typeRandomEmailIntoInputEmail()&&
                signUpPage.typePasswIntoInputPassword(excelDriver.getValueByKey("password"))&&
                signUpPage.clickSignUPButton());
        Assert.assertTrue("Check is app selection on page!",
                signUpPage.isAppSelectionOnPage()&&
                signUpPage.clickContinueButton()&&
                signUpPage.clickNoThanksButton());
        Assert.assertTrue("Check is user loged in: ", signUpPage.isUserIconOnPage());

    }

    @After
    public void tearDown(){
        signUpPage.closeSignUpPage();
    }



}
