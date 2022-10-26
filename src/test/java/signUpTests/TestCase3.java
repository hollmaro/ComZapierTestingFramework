package signUpTests;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.SignUpPage;
import parentTest.ParentTest;

public class TestCase3 extends ParentTest{

    SignUpPage signUpPage;

    @Test
    public void test3(){
        signUpPage = new SignUpPage(driver);
        signUpPage.openSignUpPage();

        Assert.assertTrue(signUpPage.clickSignUPButton(), "Check of steps failed");
        Assert.assertTrue(signUpPage.isErrorMessages(), "Check result failed");

    }

    @AfterClass
    public void tearDown(){
        signUpPage.closeSignUpPage();
    }
}
