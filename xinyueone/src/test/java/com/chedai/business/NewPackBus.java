package com.chedai.business;


import org.openqa.selenium.WebDriver;
import com.chedai.page.NewPackPage;


public class NewPackBus extends NewPackPage {

	public NewPackBus(WebDriver driver) throws Exception {
		super(driver);
	}

	// 登陆
	public void loginBus(String usr,String pwd) throws Exception{
		this.loginpage(usr, pwd);
	}
	
	// 新增进件
	public void newPackBus() throws Exception{
		this.intoNewPack();
		this.newPackPage();
	}
	
	// 新增信审
	public void newCreditBus(String custname) throws Exception{
		this.intoNewPack();
		this.searchname(custname);
		this.newCreditPage();
	}
	// 新增评估
	public void newEvaluationBus(String custname) throws Exception{
		this.intoNewPack();
		this.searchname(custname);
		this.newEvaluationPage();
	}
	
	//同意审核
	public void AgreeReviewBus(String custname) throws Exception{
		this.intoNewPack();
		this.searchname(custname);
		this.AgreeReviewPage();
	}
	
	//报签合同
	public void SignContractBus(String custname) throws Exception{
		this.intoSignContract();
		this.searchname(custname);
		this.SignContractPage();
	}
	
	//退出登陆
	public void logoutbus() throws Exception{
		this.logout();
	}
	
	
}

