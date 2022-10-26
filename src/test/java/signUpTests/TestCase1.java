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

public class TestCase1 extends ParentTest {

	public TestCase1() {
		LOG.info("Constructor sighupTests.TestCase1");
	}

	SignUpPage signUpPage;

	@Test
	public void test1() throws IOException{
		Map mapDataTC = ExcelDriver.getData(ConfigData.getCfgValue("TestDataSignUp"), "TestCase1");
		ExcelDriver excelDriver = new ExcelDriver(mapDataTC);
		signUpPage = new SignUpPage(driver);
		signUpPage.openSignUpPage();

		Assert.assertTrue(
				signUpPage.typeFirstNameIntoInputFirstName(excelDriver.getValueByKey("firstName"))&&
						signUpPage.typeLastNameIntoInputLastName(excelDriver.getValueByKey("lastName"))&&
						signUpPage.typeEmailIntoInputEmail(excelDriver.getValueByKey("email"))&&
						signUpPage.typePasswIntoInputPassword(excelDriver.getValueByKey("password"))&&
						signUpPage.unCheckUpdatesCheckBox()&&
						signUpPage.clickSignUPButton(), "Check of opening page SignUP is failed");
		///*Need to implement soft assert*/ //TODO
		Assert.assertTrue(signUpPage.isErrorMessages(), "Check result");
	}

	@AfterClass
	public void tearDown(){
		signUpPage.closeSignUpPage();
	}


}

