package com.chedai.page;


import org.openqa.selenium.WebDriver;

import com.xinyue.IO.AutoUI;
import com.xinyue.core.BasePage;


public class contractSignPage extends BasePage {
	String path=System.getProperty("user.dir")+"/resources/upload/Contract.exe";
	
	//构造函数
	public contractSignPage(WebDriver driver) throws Exception {
		super(driver);
	}
	
	/**
	 * 进入合同管理，合同签约操作
	 */
	public void intoContractSign() throws Exception{
		driver.navigate().refresh();
		this.click("合同管理");
		this.sleep(1000);
		this.click("合同签约");
	}
	
	
	/**
	 * 搜索功能相关操作, 通过客户姓名
	 */
	public void searchname(String custname ) throws Exception{
		this.sendKeys("筛选_客户姓名",custname);		
		this.click("筛选_查找按钮");
		this.sleep(1000);
	}	
	
	
	/**
	 * 合同签约
	 */
	public void signContractListPage() throws Exception{
		this.click("上传图片");
		this.sleep(500);
		AutoUI AU=new AutoUI();
		this.switchFrame("frame");
		this.click("合同批量选择文件");
		this.sleep(2000);
		AU.UploadImage(path);
		this.sleep(4000);
		this.click("合同开始上传");
		driver.switchTo().parentFrame();
		this.sleep(1000);
		this.click("合同上传图片提交");
		this.sleep(1000);
		this.click("上传成功");
		this.sleep(1000);
		this.click("GPS确认");
		this.sleep(1000);
		this.click("GPS确认2");
		this.sleep(500);
		this.click("新增GPS确定");
		this.sleep(1000);
		System.out.println("合同签约成功啦");
		
	}

}
