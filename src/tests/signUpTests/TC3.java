package tests.signUpTests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import pages.SignUpPage;
import tests.ParentTest;
import java.net.MalformedURLException;

public class TC3 extends ParentTest{

    SignUpPage signUpPage;

    /**
     * CONSTRUCTOR for TC3
     * @param browser
     * @throws MalformedURLException
     */
    public TC3(String browser)throws MalformedURLException{
        super(browser);
    }
    @Test
    public void test3(){
        signUpPage = new SignUpPage(driver);
        signUpPage.openSignUpPage();

        Assert.assertTrue("Check steps!",
                signUpPage.clickSignUPButton());
        Assert.assertTrue("Check result!",
                signUpPage.isErrorMessages());

    }

    @After
    public void tearDown(){
        signUpPage.closeSignUpPage();
    }
}
