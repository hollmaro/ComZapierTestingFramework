package tests.signUpTests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import libs.ConfigData;
import libs.ExcelDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;


import pages.SignUpPage;
import tests.ParentTest;

public class TestCase2 extends ParentTest {
	SignUpPage signUpPage;
	ExcelDriver excelDriver;
	/**
	 * CONSTRUCTOR for TestCase2
	 * @param browser
	 * @throws MalformedURLException
	 */
	public TestCase2(String browser)throws MalformedURLException{
		super(browser);
	}
	
	@Test
	public void test2() throws IOException {
		Map map = ExcelDriver.getData(ConfigData.getCfgValue("TestDataSignUp"), "TestCase2" );
		excelDriver = new ExcelDriver(map);
		signUpPage = new SignUpPage(driver);
		signUpPage.openSignUpPage();
				
		Assert.assertTrue("Check steps 1 ", 
				signUpPage.typeLastNameIntoInputLastName(excelDriver.getValueByKey("lastName"))&&
				signUpPage.pressTabOnPage(5)&&
				signUpPage.pressEnterOnPage(1));		
		Assert.assertTrue("Check result! ", signUpPage.isErrorMessages());
	}
	
	@After
	public void tearDown(){
		signUpPage.closeSignUpPage();
	}

}
