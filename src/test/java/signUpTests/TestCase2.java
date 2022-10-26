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

public class TestCase2 extends ParentTest {
    SignUpPage signUpPage;
    ExcelDriver excelDriver;

    @Test
    public void test2() throws IOException {
        Map map = ExcelDriver.getData(ConfigData.getCfgValue("TestDataSignUp"), "TestCase2");
        excelDriver = new ExcelDriver(map);
        signUpPage = new SignUpPage(driver);
        signUpPage.openSignUpPage();

        Assert.assertTrue(
                signUpPage.typeLastNameIntoInputLastName(excelDriver.getValueByKey("lastName")) &&
                        signUpPage.pressTabOnPage(5) &&
                        signUpPage.pressEnterOnPage(1), "Check of step 1 is failed");
        Assert.assertTrue(signUpPage.isErrorMessages(), "Error is not shown");
    }

    @AfterClass
    public void tearDown() {
        signUpPage.closeSignUpPage();
    }

}
