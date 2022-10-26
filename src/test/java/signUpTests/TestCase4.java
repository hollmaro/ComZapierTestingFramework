package signUpTests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.SignUpPage;
import parentTest.ParentTest;
import userLibs.ConfigData;
import userLibs.ExcelDriver;

import java.io.IOException;
import java.util.Map;

/**
 * Created by roman on 2/18/16.
 */
public class TestCase4 extends ParentTest {



    SignUpPage signUpPage;


    @Test
    public void test4() throws IOException {
        Map map = ExcelDriver.getData(ConfigData.getCfgValue("TestDataSignUp"), "TestCase4");
        ExcelDriver excelDriver = new ExcelDriver(map);
        signUpPage = new SignUpPage(driver);

        signUpPage.openSignUpPage();
        Assert.assertTrue(signUpPage.typeFirstNameIntoInputFirstName(excelDriver.getValueByKey("firstName"))&&
                signUpPage.typeLastNameIntoInputLastName(excelDriver.getValueByKey("lastName"))&&
                signUpPage.typeRandomEmailIntoInputEmail()&&
                signUpPage.typePasswIntoInputPassword(excelDriver.getValueByKey("password"))&&
                signUpPage.clickSignUPButton(), "Check signUp steps: FAILED");
        Assert.assertTrue(signUpPage.isAppSelectionOnPage()&&
                signUpPage.clickContinueButton()&&
                signUpPage.clickNoThanksButton(), "Check is app selection on page: FAILED");
        Assert.assertTrue(signUpPage.isUserIconOnPage(), "Check is user loged in: FAILED");

    }

    @AfterClass
    public void tearDown(){
        signUpPage.closeSignUpPage();
    }



}
