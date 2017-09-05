package com.chedai.page;


import static org.testng.Assert.assertEquals;

import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.xinyue.IO.AutoUI;
import com.xinyue.IO.ConfigUtils;
import com.xinyue.IO.FileTxtUtil;
import com.xinyue.core.BasePage;
import com.xinyue.core.ChineseUtil;
import com.xinyue.core.IDCardUtil;
import com.xinyue.core.NumberUtil;
import com.xinyue.core.RandomStringUtils;



public class NewPackPage extends BasePage {
	private static String config = System.getProperty("user.dir") + "/resources/data/chedai.properties";
	private Properties locatorProp = ConfigUtils.getProperties(config);
	String path1=System.getProperty("user.dir")+"/resources/upload/xinshen.exe";
	String path2=System.getProperty("user.dir")+"/resources/upload/evaluation.exe";
	String outputfile=locatorProp.getProperty("输出文件的位置");
	// 构造函数
	public NewPackPage(WebDriver driver) throws Exception {
		super(driver);
	}

	/**
	 * 登陆操作
	 */
	public void loginpage(String usr,String pwd) throws Exception{
		this.sendKeys("username",usr );
		this.sendKeys("password", pwd);
		this.click("login");
		if (this.islogged()) {
			System.out.println("登陆成功");
			log.info("登陆成功");
			log.info("登陆成功后的地址: "+driver.getCurrentUrl());
		}
		else {
			System.out.println("登陆成功");
			log.error("登陆失败");
		}
	}

	/**
	 * 判断是否是已登陆状态
	 */
	public boolean islogged() throws Exception{
		try{
		 this.isElementPresent(3, "和信");
		 return true;
		}catch (Exception e) {
			 return false;
		}
		
	}
	
	/**
	 * 进入新增进件, 菜单操作
	 */
	public void intoNewPack() throws Exception{
		driver.navigate().refresh();
		this.sleep(1000);
		this.click("进件管理");
		this.click("新建进件");
	}

	/**
	 * 搜索功能, 相关操作, 通过客户姓名
	 */
	public void searchname(String custname ) throws Exception{
		this.sendKeys("筛选_客户姓名",custname);		
		this.click("筛选_查找按钮");
	}	
	
	/**
	 *  搜索功能, 相关操作 ; 通过进件编号
	 */
	public void searchId(String packID ) throws Exception{
		this.sendKeys("筛选_进件编号",packID);		
		this.click("筛选_查找按钮");
	}	

	/**
	 *  新增进件, 实例操作
	 */
	public void newPackPage() throws Exception{
		this.click("新增");
		// 以下是新增进件具体内容
		String custname =ChineseUtil.getRandomName();
		this.sendKeys("客户姓名",custname );
		this.sendKeys("身份证号",IDCardUtil.generate());
		this.clickKeyEentry("认证按钮");
		this.closeAlertAndGetItsText(true);
		this.click("销售^");
		this.click("一组团队");
		this.click("销售人员^");
		this.click("销售人员");
		this.click("销售经理^");
		this.click("销售经理");
		this.click("产品类型^");
		if (locatorProp.getProperty("产品类型").equals("押车")) {
			this.click("产品_押车");
		}
		else if (locatorProp.getProperty("产品类型").equals("押证")) {
			this.click("产品_押证");
		}
		else {
			System.err.println("chedai.properties配置文件设置有误");
		}
		if (locatorProp.getProperty("还款方式").equals("付息还本")) {	
			this.click("还款方式^");
			this.click("付息还本");
		}else if (locatorProp.getProperty("还款方式").equals("等额本息")) {
			this.click("还款方式^");
			this.click("等额本息");
		}
		else {
			System.err.println("chedai.properties配置文件设置有误");
		}
		this.sendKeys("还款周期","1");
		this.click("周期类型^");
		this.click("周期类型_月");
		this.sendKeys("还款期数", locatorProp.getProperty("还款期数"));
		this.sendKeys("借款额", locatorProp.getProperty("借款额"));
		this.sendKeys("产品利率",locatorProp.getProperty("产品利率"));
//		this.snapshot();
		this.click("提交按钮");
		this.click("添加成功");
		FileTxtUtil.outputFile(outputfile, custname);
		System.out.println("新增进件成功啦.HAHAHAH");
	}
	
	// 退出登陆
	public void logout() throws Exception{
		this.click("退出登陆1");
		this.click("退出登陆2");
	}
	

	/**
	 * 新增信审, 相关操作
	 */
	public void newCreditPage() throws Exception{
		this.sleep(500);
		AutoUI AU=new AutoUI();
		this.click("新增信审");
		this.switchToWindow("和信车贷系统");
		this.switchFrame("frame1");
		this.switchFrame("frame2");

		this.click("批量选择文件");
		this.sleep(500);
		AU.UploadImage(path1);
		this.sleep(3000);
		this.click("开始上传");
		this.switchParentFrame();
		this.switchParentFrame();
		this.clickKeyEentry("主借人");
		this.click("主借人");
		this.sendKeys("身份证有效期","2020-12-25");
		this.entry();
		this.selectByVisibleText("户籍省","北京");
		this.selectByVisibleText("户籍市","北京市");
		this.selectByVisibleText("户籍县","东城区");
		this.sendKeys("户籍街道",ChineseUtil.getRandom(8));
		this.selectByVisibleText("本市省","北京");
		this.selectByVisibleText("本市市","北京市");
		this.selectByVisibleText("本市县","东城区");
		this.sendKeys("本市街道", ChineseUtil.getRandom(8));
		this.sendKeys("本市居住电话", NumberUtil.TelNumber());
		this.sendKeys("本市邮政编码", NumberUtil.postCode());
		this.sendKeys("申请人手机号", NumberUtil.phoneNumber());
		this.sendKeys("申请人邮箱", NumberUtil.email());
		this.sendKeys("信用卡额度","500000");
		this.selectByVisibleText("客户来源","来访");
		this.selectByVisibleText("婚姻状况","已婚");
		this.sendKeys("子女数量","3");
		this.sendKeys("月收入","10000");
		this.selectByVisibleText("最高学历","本科");
		this.sendKeys("工作单位全称", ChineseUtil.getRandom(8));
		this.selectByVisibleText("工作省","北京");
		this.selectByVisibleText("工作市","北京市");
		this.selectByVisibleText("工作县","东城区");
		this.sendKeys("工作详细地址",ChineseUtil.getRandom(8));
		this.sendKeys("单位电话", NumberUtil.TelNumber());
		this.sendKeys("部门","研发");
		this.sendKeys("职务","设计");
		this.sendKeys("单位邮编", NumberUtil.postCode());
		this.sendKeys("入职时间","2012-12-12");
		this.entry();
		this.sendKeys("备注", ChineseUtil.getRandom(20));
		this.sendKeys("证明人姓名",ChineseUtil.getRandomName());
		this.sendKeys("证明人电话", NumberUtil.phoneNumber());
		this.sendKeys("证明人关系","同事");
		this.sendKeys("家庭人姓名", ChineseUtil.getRandomName());
		this.sendKeys("家庭人电话", NumberUtil.phoneNumber());
		this.sendKeys("家庭人关系","哥哥");
		this.selectByVisibleText("家庭人省","北京");
		this.selectByVisibleText("家庭人市","北京市");
		this.selectByVisibleText("家庭人县","东城区");
		this.sendKeys("家庭人详细", ChineseUtil.getRandom(8));
		this.sendKeys("家庭人邮编", NumberUtil.postCode());
		this.sendKeys("紧急人姓名",ChineseUtil.getRandomName());
		this.sendKeys("紧急人电话", NumberUtil.phoneNumber());
		this.sendKeys("紧急人关系","同学");
		this.selectByVisibleText("紧急人省","北京");
		this.selectByVisibleText("紧急人市","北京市");
		this.selectByVisibleText("紧急人县","东城区");
		this.sendKeys("紧急人详细", ChineseUtil.getRandom(8));
		this.sendKeys("紧急人邮编",NumberUtil.postCode());
		/**
			this.click("信审保存草稿");
			this.sleep(1000);
			this.click("信审保存草稿确认");
		**/	

		this.click("信审提交审核");
		this.sleep(500);
		assertEquals(this.isDisplayed("信审提交确认"), true);
		this.click("信审提交确认");
		this.sleep(500);
		this.switchToWindow("和信车贷系统");
		System.out.println("新增信审成功啦, ~O(∩_∩O哈哈哈~");
	}

	/**
	 * 新增评估, 相关操作
	 */
	public void newEvaluationPage() throws Exception{
		this.sleep(1000);
		AutoUI AU=new AutoUI();
		this.click("新增评估");
		this.switchToWindow("和信车贷系统");
		this.switchFrame("frame1");
		this.switchFrame("frame2");
		this.click("评估上传图片");
		this.sleep(1000);
		AU.UploadImage(path2);
		this.sleep(4000);
		this.click("评估开始上传");
		this.switchParentFrame();
		this.switchParentFrame();
		this.click("抵押信息");
		this.sendKeys("发动机号", RandomStringUtils.randomAlphanumeric(6));
		this.sendKeys("机动车登记证书", RandomStringUtils.randomAlphanumeric(6));
		this.sendKeys("车牌号码", RandomStringUtils.randomNumeric(6));
		this.sendKeys("出厂日期","2015-05-25");
		this.entry();
		this.sendKeys("品牌", ChineseUtil.getRandom(3));
		this.sendKeys("型号", RandomStringUtils.randomAlphanumeric(6));
		this.sendKeys("登记日期","2016-03-02");
		this.entry();
		this.sendKeys("车身颜色","红色");
		this.selectByVisibleText("使用性质","其他");
		this.sendKeys("过户次数","1");
		this.selectByVisibleText("变速器","AT");
		this.sendKeys("开票价","90000");
		this.sendKeys("同类市场价格","80000");
		this.sendKeys("车架号核实", RandomStringUtils.randomAlphanumeric(6));
//		this.click("行驶证");
		this.sendKeys("手续现状描述","完整齐全");
		this.sendKeys("强制有效期","2022-12-12");
		this.sendKeys("保险有效期","2025-10-15");
		this.sendKeys("商业保险","2020-02-02");
		this.sendKeys("年检有效期","2022-12-22");
		this.entry();
		this.sendKeys("车辆评级","优");
		this.sendKeys("累计扣分","0");
		this.sendKeys("违章罚款金额","0");
		this.sendKeys("车辆改装情况","未改装");
		this.sendKeys("出险次数","0");
		this.sendKeys("出险金额","0");
		this.sendKeys("评估金额","90000");
		//后期放在配置文件中
		this.sendKeys("建议借款额","20000" );
		this.sendKeys("车况描述","新车还配备有真皮多功能方向盘、皮质座椅、全景天窗、ESP车身电子稳定系统、上坡辅助、陡坡缓降、胎压监测、自动驻车、倒车影像、无钥匙进入、一键式启动等。");
		this.click("评估提交审核");
		this.sleep(500);
		assertEquals(this.isDisplayed("评估审核确认"), true);
		this.click("评估审核确认");
		this.switchToWindow("和信车贷系统");
		System.out.println("新增评估成功啦, ~O(∩_∩O哈哈哈~");
	}

	/**
	 * 进入新增进件，报签菜单操作
	 */
	public void intoSignContract() throws Exception{
		driver.navigate().refresh();
		this.sleep(500);
		this.click("进件管理");
		this.click("报签");
		System.out.println("报签菜单点击成功啦, ~O(∩_∩O哈哈哈~");
	}
	
	
	/**
	 * 发起复议, 相关操作
	 */
	public void ProposeDiscussPage() throws Exception{
		this.sleep(500);
		this.click("发起复议");
		System.out.println("发起复议成功啦, ~O(∩_∩O哈哈哈~");
	

	}
	
	/**
	 * 同意审核, 相关操作
	 */
	public void AgreeReviewPage() throws Exception{
		this.sleep(500);
		this.click("同意审核2");
		if (locatorProp.getProperty("还款方式").equals("等额本息")) {
			this.sendKeys("确定月还款", locatorProp.getProperty("月还款金额"));
		}	
		this.click("确定审核");
		this.click("确定");
		System.out.println("同意审核,成功啦, ~O(∩_∩O哈哈哈~");	
		}
	
	/**
	 * 报签合同
	 */
	public void SignContractPage() throws Exception{
		this.sleep(500);
		this.click("报签合同");
		this.sleep(500);
		this.click("箭头");
		this.click("押证24期");
		this.sleep(500);
		String jsString = "document.getElementById('signingDate').nextSibling.firstChild.removeAttribute('readonly')";
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(jsString);
		this.sendKeys("签约日期","2017-08-21");
		this.entry();
		this.click("是箭头");
		this.click("是");
		this.sendKeys("最终审批金额",locatorProp.getProperty("最终审金额"));
		if (locatorProp.get("还款方式").equals("等额本息")) {
			this.sendKeys("打款金额", locatorProp.getProperty("打款金额"));
			this.click("合同金额");
		}
		if (locatorProp.get("还款方式").equals("付息还本")) {
			this.click("计算");
		}
		this.click("开户银行箭头");
		this.click("开户银行");
		this.click("省箭头");
		this.click("开户行地址省");
		this.click("市箭头");
		this.click("市");
		this.click("区箭头");
		this.click("区");
		this.sendKeys("开户支行名称","测试运行支行");
		this.sendKeys("银行账号","6226223116965663");
		this.click("报签提交");
		this.click("报签成功");
		System.out.println("报签合同,成功啦, ~O(∩_∩O哈哈哈~");	
	}
	

	
	
	
}
