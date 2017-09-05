package com.chedai.business;

import org.openqa.selenium.WebDriver;

import com.chedai.page.AuditPage;

public class AuditBus extends AuditPage {
	
	
	public AuditBus(WebDriver driver) throws Exception {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/**
	 *质检领件,审核
	 */
	public void qualityAuditBus(String name) throws Exception{
		this.AuditManagementPage();
		this.qualityAuditListPage();
		this.searchNamePage(name);
		this.sleep(2000);
		this.lingjianPage();
		this.sleep(500);
		this.qualityAuditPage();
	}
	
	/**
	 * 车辆评估审核
	 */
	public void assessAuditBus(String name) throws Exception{
		this.AuditManagementPage();
		this.sleep(1000);
		this.assessAuditListPage();
		this.searchNamePage(name);
		this.sleep(1000);
		this.lingjianPage();
		this.sleep(500);
		this.assessAuditPage();
	}
	
	/**
	 * 初审审核
	 */
	public void PreliminaryAuditBus(String name) throws Exception{
		this.AuditManagementPage();
		this.sleep(1000);
		this.PreliminaryAuditListPage();
		this.searchNamePage(name);
		this.sleep(1000);
		this.lingjianPage();
		this.sleep(500);
		this.PreliminaryAuditPage();
	}

	/**
	 * 终审审核
	 */
   public void FinalAuditBus(String name) throws Exception{
	   this.AuditManagementPage();
	   this.sleep(1000);
	   this.FinalAuditListPage();
	   this.searchNamePage(name);
	   this.sleep(1000);
	   this.lingjianPage();
	   this.sleep(500);
	   this.FinalAuditPage();
   }
}
