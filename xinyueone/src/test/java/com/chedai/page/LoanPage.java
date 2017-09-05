package com.chedai.page;

import org.openqa.selenium.WebDriver;

import com.xinyue.core.BasePage;
import com.xinyue.core.ChineseUtil;



public class LoanPage extends BasePage{
	
	public LoanPage(WebDriver driver) throws Exception{
		super(driver);
	}
	
	/**
	 * 进入放款管理菜单
	 */
	public void loanManagePage() throws Exception{
		this.sleep(1000);
		this.click("放款管理");
	}
	
	/**
	 * 放款申请
	*/
	public void LoanManageListPage() throws Exception{
		this.click("放款申请");		
	}
	
	/**
	 * 通过姓名搜索进件
	*/
	public void searchNamePage(String name) throws Exception{
		this.sendKeys("搜索姓名",name);
		this.click("查找按钮");
		this.sleep(1000);
	}
	
	/**
	 * 申请发标
	*/
	public void sendApplicationPage() throws Exception{
		this.click("申请发标");
		
		this.switchToWindow("和信车贷系统");
		this.sleep(1000);
		this.click("发标申请");
		
		this.sendKeys("工作年限", "5");
		this.click("单位性质1");
		this.click("单位性质选项");
		this.click("职位级别1");
		this.click("职位级别选项");
		this.sendKeys("借款用途","创业+资金周转" );
		
		this.click("提交");
		;
		this.click("提交确认");
		this.switchToWindow("和信车贷系统");
		System.out.println("发标成功啦");
	}
	

	/**
	 * 进入发标管理菜单
	*/	
	public void sendTenderAuditManagementPage() throws Exception{
//		driver.navigate().refresh();
		this.click("发标管理");
	}
	/**
	 * 发标审核列表
	*/	
	public void sendTenderAuditLPage() throws Exception{
//		driver.navigate().refresh();
		
		this.click("发标审核");
	}
	
	/**
	 * 发标审核
	*/	
	public void SendTenderAuditPage()throws Exception{
		
		this.click("审核");
		
		this.switchToWindow("和信车贷系统");
		this.click("审核结果按钮");
		this.click("审核结果选项");
		this.sendKeys("备注", ChineseUtil.getRandom(20));
		this.click("提交审核");
		this.sleep(500);
		this.click("发标审核确认");
		this.switchToWindow("和信车贷系统");
		System.out.println("发标审核完成啦");
		
	}
}
