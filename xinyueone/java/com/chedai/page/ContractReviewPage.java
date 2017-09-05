package com.chedai.page;

import org.openqa.selenium.WebDriver;
import com.core.utils.BasePage;
import com.core.utils.Locator;


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
			this.click(new Locator("合同管理"));
			this.sleep(1000);
			this.click(new Locator("复核"));
		}
		
		/**
		 * 搜索功能相关操作, 通过客户姓名
		 */
		public void searchname(String custname ) throws Exception{
			this.getElement(new Locator("筛选_进件编号")).clear();
			this.sendkeys(new Locator("筛选_客户姓名"),custname);		
			this.click(new Locator("筛选_查找按钮"));
			this.sleep(1000);
		}
		
		/**
		 * 合同复核
		 */
		public void skipContractReviewList() throws Exception{
			this.click(new Locator("领件复选框"));
			this.sleep(500);
			this.click(new Locator("领件按钮"));
			this.click(new Locator("是否领件"));
			this.click(new Locator("领件成功"));
			this.sleep(500);
			this.click(new Locator("GPS复核"));
			this.sleep(500);
			this.click(new Locator("GPS复核结果箭头"));
			this.sleep(500);
			this.click(new Locator("GPS复核通过"));
			this.sleep(500);
			this.click(new Locator("PGS复核通过确认"));
			this.click(new Locator("更新状态成功"));
			this.sleep(500);
			this.click(new Locator("手续复核"));
			this.sleep(2500);
			this.switchToWindow("和信车贷系统");
			this.click(new Locator("手续复核结果选项卡"));
			this.sleep(500);
			this.click(new Locator("复核结果箭头"));
			this.sleep(500);
			this.click(new Locator("复核结果通过"));
			this.sleep(1000);
			this.click(new Locator("手续复核结果提交"));
			this.sleep(500);
			this.click(new Locator("合同复核审核成功"));
			this.switchToWindow("和信车贷系统");
			this.sleep(500);
			System.out.println("合同复核成功啦");
		}

}
