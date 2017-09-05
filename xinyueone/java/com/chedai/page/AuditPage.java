package com.chedai.page;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.core.IO.ConfigUtils;
import com.core.utils.BasePage;
import com.core.utils.ChineseUtil;
import com.core.utils.Locator;

public class AuditPage extends BasePage {
	private static String config = System.getProperty("user.dir") + "/data/chedai.properties";
	private Properties locatorProp = ConfigUtils.getProperties(config);
	
	public AuditPage(WebDriver driver) throws Exception {
		super(driver);
	}
	
	/**
	 * 进入审核管理菜单
	 */
	public void AuditManagementPage() throws Exception{
		driver.navigate().refresh();
		this.click(new Locator("审核管理"));
	}
	
	/**
	 * 质检列表
	*/
	public void qualityAuditListPage() throws Exception{
		this.click(new Locator("质检列表"));		
	}
	
	/**
	 * 评估终审列表
	*/
	public void assessAuditListPage() throws Exception{
		this.click(new Locator("评估终审"));
	}
	
	/**
	 * 通过姓名搜索进件
	*/
	public void searchNamePage(String name) throws Exception{
		this.sendkeys(new Locator("搜索姓名"),name);
		this.click(new Locator("查找按钮"));
	}
	
	/**
	 * 领件
	*/
	public void lingjianPage() throws Exception{
		this.click(new Locator("单选框"));
		this.click(new Locator("领件"));
		this.sleep(500);
		this.click(new Locator("弹框确认"));
		this.sleep(1000);
		this.click(new Locator("确认成功"));
	}
	
	/**
	 * 质检管理审核
	 */
	public void qualityAuditPage() throws Exception{
		this.click(new Locator("质检审核"));
		this.sleep(500);
		this.switchToWindow("和信车贷系统");
		this.sleep(500);
		this.click(new Locator("审核选项"));
		this.sleep(500);
		this.click(new Locator("审核结果"));
		this.sleep(500);
		this.sendkeys(new Locator("返回门店描述"),ChineseUtil.getRandom(10));
		this.click(new Locator("提交"));
		this.sleep(1000);
		this.click(new Locator("提交确认"));
		this.switchToWindow("和信车贷系统");
		System.out.println("质检管理审核成功啦, ~O(∩_∩O哈哈哈~");
		
	}
	
	/**
	 * 评估终审审核
	 */
	public void assessAuditPage() throws Exception{
		this.click(new Locator("评估审核"));
		this.sleep(500);
		this.switchToWindow("和信车贷系统");
		this.click(new Locator("审批结果选项"));
		this.click(new Locator("审批结果"));
		this.sendkeys(new Locator("扣款金额"),"2000");
		this.sendkeys(new Locator("评估金额"), "30000");
		this.sendkeys(new Locator("内部决策描述1"), ChineseUtil.getRandom(20));
		this.sendkeys(new Locator("返回门店描述1"), ChineseUtil.getRandom(20));
		this.sleep(1000);
		this.click(new Locator("确认"));
		this.click(new Locator("点击确认按钮"));
		this.switchToWindow("和信车贷系统");
		System.out.println("评估终审审核成功啦, ~O(∩_∩O哈哈哈~");
	}
	/**
	 * 初审审核列表
	*/	
	public void PreliminaryAuditListPage() throws Exception{
		this.click(new Locator("初审列表"));	
	}
	
	/**
	 * 初审审核
	*/
	public void PreliminaryAuditPage() throws Exception{
		this.click(new Locator("初审审核"));
		this.switchToWindow("和信车贷系统");
		this.click(new Locator("初审决策信息"));
		this.sleep(500);
		this.select(new Locator("初审结果"), "通过");
		this.sleep(500);
		this.sendkeys(new Locator("初审信用金额"), "60000");
		this.sendkeys(new Locator("内部决策描述2"), ChineseUtil.getRandom(20));
		this.sendkeys(new Locator("返回门店描述2"), ChineseUtil.getRandom(20));
		this.sleep(500);
		this.click(new Locator("初审保存"));
		this.sleep(500);
		this.click(new Locator("初审审核通过确认"));
		this.switchToWindow("和信车贷系统");
		System.out.println("初审审核 成功啦, ~O(∩_∩O哈哈哈~");
	}
	
	/**
	 * 终审审核列表
	*/
	
	public void FinalAuditListPage() throws Exception{
		this.click(new Locator("终审列表"));
	}
	
	/**
	 * 终审审核
	*/
	public void FinalAuditPage() throws Exception{
		this.click(new Locator("终审审核"));
		this.switchToWindow("和信车贷系统");
		this.click(new Locator("终审决策信息"));
		this.sleep(500);
		this.sendkeys(new Locator("终审信用额度"), "70000");
		if (locatorProp.getProperty("还款方式").equals("付息还本")) {
			this.sendkeys(new Locator("固定费率"), locatorProp.getProperty("终审固定费率"));
		}
		else if (locatorProp.getProperty("还款方式").equals("等额本息")) {
			this.sendkeys(new Locator("产品利率"), locatorProp.getProperty("终审利率"));
		}		
		this.sendkeys(new Locator("审批金额"), locatorProp.getProperty("审批金额"));
		this.select(new Locator("是否抵押"), "是");
		this.select(new Locator("是否实地"), "是");
		this.select(new Locator("是否补充材料"), "否");
		this.select(new Locator("有线GPS安装数量"), "2");
		this.select(new Locator("无线GPS安装数量"), "0");
		this.select(new Locator("终审审批结果"), "通过");
		this.sendkeys(new Locator("内部决策描述3"),  ChineseUtil.getRandom(20));
		this.sendkeys(new Locator("返回门店描述3"), ChineseUtil.getRandom(20));
		this.sleep(500);
		this.click(new Locator("终审保存"));	
		this.sleep(500);
		this.click(new Locator("终审审核通过确认"));
		this.switchToWindow("和信车贷系统");
		System.out.println("终审审核 成功啦, ~O(∩_∩O哈哈哈~");
	}
}
