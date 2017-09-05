package com.chedai.page;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.xinyue.IO.ConfigUtils;
import com.xinyue.core.BasePage;
import com.xinyue.core.ChineseUtil;


public class AuditPage extends BasePage {
	private static String config = System.getProperty("user.dir") + "/resources/data/chedai.properties";
	private Properties locatorProp = ConfigUtils.getProperties(config);
	
	public AuditPage(WebDriver driver) throws Exception {
		super(driver);
	}
	
	/**
	 * 进入审核管理菜单
	 */
	public void AuditManagementPage() throws Exception{
		driver.navigate().refresh();
		this.click("审核管理");
	}
	
	/**
	 * 质检列表
	*/
	public void qualityAuditListPage() throws Exception{
		this.click("质检列表");		
	}
	
	/**
	 * 评估终审列表
	*/
	public void assessAuditListPage() throws Exception{
		this.click("评估终审");
	}
	
	/**
	 * 通过姓名搜索进件
	*/
	public void searchNamePage(String name) throws Exception{
		this.sendKeys("搜索姓名",name);
		this.click("查找按钮");
	}
	
	/**
	 * 领件
	*/
	public void lingjianPage() throws Exception{
		this.click("单选框");
		this.click("领件");
		this.sleep(500);
		this.click("弹框确认");
		this.sleep(1000);
		this.click("确认成功");
	}
	
	/**
	 * 质检管理审核
	 */
	public void qualityAuditPage() throws Exception{
		this.click("质检审核");
		this.sleep(500);
		this.switchToWindow("和信车贷系统");
		this.sleep(500);
		this.click("审核选项");
		this.sleep(500);
		this.click("审核结果");
		this.sleep(500);
		this.sendKeys("返回门店描述",ChineseUtil.getRandom(10));
		this.click("提交");
		this.sleep(1000);
		this.click("提交确认");
		this.switchToWindow("和信车贷系统");
		System.out.println("质检管理审核成功啦, ~O(∩_∩O哈哈哈~");
		
	}
	
	/**
	 * 评估终审审核
	 */
	public void assessAuditPage() throws Exception{
		this.click("评估审核");
		this.sleep(500);
		this.switchToWindow("和信车贷系统");
		this.click("审批结果选项");
		this.click("审批结果");
		this.sendKeys("扣款金额","2000");
		this.sendKeys("评估金额", "30000");
		this.sendKeys("内部决策描述1", ChineseUtil.getRandom(20));
		this.sendKeys("返回门店描述1", ChineseUtil.getRandom(20));
		this.sleep(1000);
		this.click("确认");
		this.click("点击确认按钮");
		this.switchToWindow("和信车贷系统");
		System.out.println("评估终审审核成功啦, ~O(∩_∩O哈哈哈~");
	}
	/**
	 * 初审审核列表
	*/	
	public void PreliminaryAuditListPage() throws Exception{
		this.click("初审列表");	
	}
	
	/**
	 * 初审审核
	*/
	public void PreliminaryAuditPage() throws Exception{
		this.click("初审审核");
		this.switchToWindow("和信车贷系统");
		this.click("初审决策信息");
		this.sleep(500);
		this.selectByVisibleText("初审结果", "通过");
		this.sleep(500);
		this.sendKeys("初审信用金额", "60000");
		this.sendKeys("内部决策描述2", ChineseUtil.getRandom(20));
		this.sendKeys("返回门店描述2", ChineseUtil.getRandom(20));
		this.sleep(500);
		this.click("初审保存");
		this.sleep(500);
		this.click("初审审核通过确认");
		this.switchToWindow("和信车贷系统");
		System.out.println("初审审核 成功啦, ~O(∩_∩O哈哈哈~");
	}
	
	/**
	 * 终审审核列表
	*/
	
	public void FinalAuditListPage() throws Exception{
		this.click("终审列表");
	}
	
	/**
	 * 终审审核
	*/
	public void FinalAuditPage() throws Exception{
		this.click("终审审核");
		this.switchToWindow("和信车贷系统");
		this.click("终审决策信息");
		this.sleep(500);
		this.sendKeys("终审信用额度", "70000");
		if (locatorProp.getProperty("还款方式").equals("付息还本")) {
			this.sendKeys("固定费率", locatorProp.getProperty("终审固定费率"));
		}
		else if (locatorProp.getProperty("还款方式").equals("等额本息")) {
			this.sendKeys("产品利率", locatorProp.getProperty("终审利率"));
		}		
		this.sendKeys("审批金额", locatorProp.getProperty("审批金额"));
		this.selectByVisibleText("是否抵押", "是");
		this.selectByVisibleText("是否实地", "是");
		this.selectByVisibleText("是否补充材料", "否");
		this.selectByVisibleText("有线GPS安装数量", "2");
		this.selectByVisibleText("无线GPS安装数量", "0");
		this.selectByVisibleText("终审审批结果", "通过");
		this.sendKeys("内部决策描述3",  ChineseUtil.getRandom(20));
		this.sendKeys("返回门店描述3", ChineseUtil.getRandom(20));
		this.sleep(500);
		this.click("终审保存");	
		this.sleep(500);
		this.click("终审审核通过确认");
		this.switchToWindow("和信车贷系统");
		System.out.println("终审审核 成功啦, ~O(∩_∩O哈哈哈~");
	}
}
