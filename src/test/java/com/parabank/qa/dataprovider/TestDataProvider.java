package com.parabank.qa.dataprovider;

import org.testng.annotations.DataProvider;

import com.parabank.qa.utils.ExcelUtil;

public class TestDataProvider {
	
	//1.Data Provider for Login Module
	@DataProvider(name = "loginTestData")
	public static Object[][] getLoginData(){
		return ExcelUtil.getTestDat("login");
	}
	
	
	//2. Data Provider for Register
	@DataProvider(name = "registerTestData")
	public static Object[][] getRegisterData(){
		return ExcelUtil.getTestDat("register");
	}
	
	//3. Data Provider for Bill Pay Module
	@DataProvider(name = "billPayTestData")
	public static Object[][] getBillPayData(){
		return ExcelUtil.getTestDat("billpay");
	}
	
	
	//4. Data Provider for Transfer Funds Module
	@DataProvider(name = "transferFundsTestData")
	public static Object[][] getTransferFundsData(){
		return ExcelUtil.getTestDat("transfer");
	}

}
