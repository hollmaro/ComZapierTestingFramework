
package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import pages.SignUpPage;


public class TC1 extends ParentTest{
	SignUpPage signUpPage;
	
	@Test
	public void test1(){
		signUpPage = new SignUpPage(driver);
		signUpPage.openSignUpPage();
		
		Assert.assertTrue("Check opening page SignUP: ", 
				signUpPage.typeFirstNameIntoInputFirstName("Roman")&&
				signUpPage.typeLastNameIntoInputLastName("Lekh")&&
				signUpPage.typeEmailIntoInputEmail("romanlekh@")&&
				signUpPage.typePasswIntoInputPassword("123")&&
				signUpPage.unCheckUpdatesCheckBox()&&
				signUpPage.clickSignUPButton());
		Assert.assertTrue("Check result ", signUpPage.isErrorMessages());
	}			
		
	@After
	public void tearDown(){
		signUpPage.closeSignUpPage();
	}
		
		
}

