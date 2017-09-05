package com.chedai.business;


import org.openqa.selenium.WebDriver;

import com.chedai.page.LoanPage;

public class LoanBus extends LoanPage{
	
	public LoanBus(WebDriver driver) throws Exception {
		super(driver);
	}
	
	/**
	 *放款申请
	 */
	public void sendApplicationBus(String name) throws Exception{
		this.loanManagePage();
		this.LoanManageListPage();
		this.searchNamePage(name);
		this.sleep(1000);
		this.sendApplicationPage();
	}
	
	/**
	 *发标审核
	 */
	public void SendTenderAuditBus(String name) throws Exception{
		this.sendTenderAuditManagementPage();
		this.sleep(500);
		this.sendTenderAuditLPage();
		this.searchNamePage(name);
		this.sleep(500);
		this.SendTenderAuditPage();
	}

}
