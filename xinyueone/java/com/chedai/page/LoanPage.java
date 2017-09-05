package com.chedai.page;

import org.openqa.selenium.WebDriver;

import com.core.utils.BasePage;
import com.core.utils.ChineseUtil;
import com.core.utils.Locator;

public class LoanPage extends BasePage{
	
	public LoanPage(WebDriver driver) throws Exception{
		super(driver);
	}
	
	/**
	 * 进入放款管理菜单
	 */
	public void loanManagePage() throws Exception{
		this.sleep(1000);
		this.click(new Locator("放款管理"));
	}
	
	/**
	 * 放款申请
	*/
	public void LoanManageListPage() throws Exception{
		this.click(new Locator("放款申请"));		
	}
	
	/**
	 * 通过姓名搜索进件
	*/
	public void searchNamePage(String name) throws Exception{
		this.sendkeys(new Locator("搜索姓名"),name);
		this.click(new Locator("查找按钮"));
		this.sleep(1000);
	}
	
	/**
	 * 申请发标
	*/
	public void sendApplicationPage() throws Exception{
		this.click(new Locator("申请发标"));
		this.sleep(500);
		this.switchToWindow("和信车贷系统");
		this.sleep(1000);
		this.click(new Locator("发标申请"));
		this.sleep(500);
		this.sendkeys(new Locator("工作年限"), "5");
		this.click(new Locator("单位性质1"));
		this.click(new Locator("单位性质选项"));
		this.click(new Locator("职位级别1"));
		this.click(new Locator("职位级别选项"));
		this.sendkeys(new Locator("借款用途"),"创业+资金周转" );
		this.sleep(500);
		this.click(new Locator("提交"));
		this.sleep(500);;
		this.click(new Locator("提交确认"));
		this.switchToWindow("和信车贷系统");
		System.out.println("发标成功啦");
	}
	

	/**
	 * 进入发标管理菜单
	*/	
	public void sendTenderAuditManagementPage() throws Exception{
//		driver.navigate().refresh();
		this.click(new Locator("发标管理"));
	}
	/**
	 * 发标审核列表
	*/	
	public void sendTenderAuditLPage() throws Exception{
//		driver.navigate().refresh();
		this.sleep(500);
		this.click(new Locator("发标审核"));
	}
	
	/**
	 * 发标审核
	*/	
	public void SendTenderAuditPage()throws Exception{
		this.sleep(500);
		this.click(new Locator("审核"));
		this.sleep(500);
		this.switchToWindow("和信车贷系统");
		this.sleep(1000);
		this.click(new Locator("审核结果按钮"));
		this.sleep(500);
		this.click(new Locator("审核结果选项"));
		this.sleep(500);
		this.sendkeys(new Locator("备注"), ChineseUtil.getRandom(20));
		this.click(new Locator("提交审核"));
		this.sleep(500);
		this.click(new Locator("发标审核确认"));
		this.switchToWindow("和信车贷系统");
		System.out.println("发标审核完成啦");
		
	}
}
