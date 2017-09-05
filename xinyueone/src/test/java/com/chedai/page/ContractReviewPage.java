package com.chedai.page;

import org.openqa.selenium.WebDriver;

import com.xinyue.core.BasePage;



public class ContractReviewPage extends BasePage{
	//构造函数
		public ContractReviewPage(WebDriver driver) throws Exception {
			super(driver);
		}
		
		/**
		 * 进入合同管理，合同复核操作
		 */
		public void intoContractReview() throws Exception{
			driver.navigate().refresh();
			this.click("合同管理");
			this.sleep(1000);
			this.click("复核");
		}
		
		/**
		 * 搜索功能相关操作, 通过客户姓名
		 */
		public void searchname(String custname ) throws Exception{
			this.sendKeys("筛选_客户姓名",custname);	
			this.sleep(500);
			this.click("筛选_查找按钮");
		}
		
		/**
		 * 合同复核
		 */
		public void skipContractReviewList() throws Exception{
			this.sleep(1000);
			this.click("领件复选框");		
			this.click("领件按钮");
			this.click("是否领件");
			this.click("领件成功");		
			this.click("GPS复核");		
			this.click("GPS复核结果箭头");	
			this.click("GPS复核通过");		
			this.click("PGS复核通过确认");
			this.click("更新状态成功");	
			this.click("手续复核");
			this.switchToWindow("和信车贷系统");
			this.click("手续复核结果选项卡");		
			this.click("复核结果箭头");		
			this.click("复核结果通过");
			this.click("手续复核结果提交");	
			this.click("合同复核审核成功");
			this.switchToWindow("和信车贷系统");		
			System.out.println("合同复核成功啦");
		}

}
