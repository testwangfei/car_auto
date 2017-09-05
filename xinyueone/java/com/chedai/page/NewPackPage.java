package com.chedai.page;


import static org.testng.Assert.assertEquals;

import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import com.core.IO.AutoUI;
import com.core.IO.ConfigUtils;
import com.core.IO.FileTxtUtil;
import com.core.utils.BasePage;
import com.core.utils.ChineseUtil;
import com.core.utils.IDCardUtil;
import com.core.utils.Locator;
import com.core.utils.NumberUtil;
import com.core.utils.RandomStringUtils;


public class NewPackPage extends BasePage {
	private static String config = System.getProperty("user.dir") + "/data/chedai.properties";
	private Properties locatorProp = ConfigUtils.getProperties(config);
	String path1=System.getProperty("user.dir")+"\\upload\\xinshen.exe";
	String path2=System.getProperty("user.dir")+"\\upload\\evaluation.exe";
	String outputfile=locatorProp.getProperty("输出文件的位置");
	// 构造函数
	public NewPackPage(WebDriver driver) throws Exception {
		super(driver);
	}

	/**
	 * 登陆操作
	 */
	public void loginpage(String usr,String pwd) throws Exception{
		this.sendkeys(new Locator("username"),usr );
		this.sendkeys(new Locator("password"), pwd);
		this.click(new Locator("login"));
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
		 this.getElement(new Locator("和信"));
		 return true;
		}catch (Exception e) {
			 return false;
		}
		
	}
	
	/**
	 * 进入新增进件, 菜单操作
	 */
	public void intoNewPack() throws Exception{
		this.sleep(500);
		driver.navigate().refresh();
		this.sleep(500);
		this.click(new Locator("进件管理"));
		this.click(new Locator("新建进件"));
	}

	/**
	 * 搜索功能, 相关操作, 通过客户姓名
	 */
	public void searchname(String custname ) throws Exception{
		this.getElement(new Locator("筛选_进件编号")).clear();
		this.sendkeys(new Locator("筛选_客户姓名"),custname);		
		this.click(new Locator("筛选_查找按钮"));
	}	
	
	/**
	 *  搜索功能, 相关操作 ; 通过进件编号
	 */
	public void searchId(String packID ) throws Exception{
		this.getElement(new Locator("筛选_进件编号")).clear();
		this.sendkeys(new Locator("筛选_进件编号"),packID);		
		this.click(new Locator("筛选_查找按钮"));
	}	

	/**
	 *  新增进件, 实例操作
	 */
	public void newPackPage() throws Exception{
		this.sleep(500);
		this.click(new Locator("新增"));
		// 以下是新增进件具体内容
		String custname =ChineseUtil.getRandomName();
		this.sendkeys(new Locator("客户姓名"),custname );
		this.sendkeys(new Locator("身份证号"),IDCardUtil.generate());
		this.clickKeyEentry(new Locator("认证按钮"));
		this.sleep(500); 
		this.alertConfirm();
		this.click(new Locator("销售^"));
		this.click(new Locator("一组团队"));
		this.click(new Locator("销售人员^"));
		this.click(new Locator("销售人员"));
		this.click(new Locator("销售经理^"));
		this.click(new Locator("销售经理"));
		this.click(new Locator("产品类型^"));
		if (locatorProp.getProperty("产品类型").equals("押车")) {
			this.click(new Locator("产品_押车"));
		}
		else if (locatorProp.getProperty("产品类型").equals("押证")) {
			this.click(new Locator("产品_押证"));
		}
		else {
			System.err.println("chedai.properties配置文件设置有误");
		}
		if (locatorProp.getProperty("还款方式").equals("付息还本")) {	
			this.click(new Locator("还款方式^"));
			this.click(new Locator("付息还本"));
		}else if (locatorProp.getProperty("还款方式").equals("等额本息")) {
			this.click(new Locator("还款方式^"));
			this.click(new Locator("等额本息"));
		}
		else {
			System.err.println("chedai.properties配置文件设置有误");
		}
		this.sendkeys(new Locator("还款周期"), "1");
		this.click(new Locator("周期类型^"));
		this.click(new Locator("周期类型_月"));
		this.sendkeys(new Locator("还款期数"), locatorProp.getProperty("还款期数"));
		this.sendkeys(new Locator("借款额"), locatorProp.getProperty("借款额"));
		this.sendkeys(new Locator("产品利率"),locatorProp.getProperty("产品利率"));
//		this.snapshot();
		this.click(new Locator("提交按钮"));
		this.click(new Locator("添加成功"));
		FileTxtUtil.outputFile(outputfile, custname);
		System.out.println("新增进件成功啦.HAHAHAH");
	}
	// 退出登陆
	public void logout() throws Exception{
		this.click(new Locator("退出登陆1"));
		this.click(new Locator("退出登陆2"));
	}
	

	/**
	 * 新增信审, 相关操作
	 */
	public void newCreditPage() throws Exception{
		this.sleep(500);
		AutoUI AU=new AutoUI();
		this.click(new Locator("新增信审"));
		this.switchToWindow("和信车贷系统");
		driver.switchTo().frame(this.findElement(driver, new Locator("frame1")));
		driver.switchTo().frame(this.findElement(driver, new Locator("frame2")));
		this.sleep(500);
		this.click(new Locator("批量选择文件"));
		this.sleep(500);
		AU.UploadImage(path1);
		this.sleep(3000);
		this.click(new Locator("开始上传"));
		driver.switchTo().parentFrame();
		driver.switchTo().parentFrame();
		this.clickKeyEentry(new Locator("主借人"));
		this.click(new Locator("主借人"));
		this.sendkeys(new Locator("身份证有效期"),"2020-12-25");
		this.entry();
		this.select(new Locator("户籍省"),"北京");
		this.select(new Locator("户籍市"), "北京市");
		this.select(new Locator("户籍县"), "东城区");
		this.sendkeys(new Locator("户籍街道"),ChineseUtil.getRandom(8));
		this.select(new Locator("本市省"),"北京");
		this.select(new Locator("本市市"), "北京市");
		this.select(new Locator("本市县"), "东城区");
		this.sendkeys(new Locator("本市街道"), ChineseUtil.getRandom(8));
		this.sendkeys(new Locator("本市居住电话"), NumberUtil.TelNumber());
		this.sendkeys(new Locator("本市邮政编码"), NumberUtil.postCode());
		this.sendkeys(new Locator("申请人手机号"), NumberUtil.phoneNumber());
		this.sendkeys(new Locator("申请人邮箱"), NumberUtil.email());
		this.sendkeys(new Locator("信用卡额度"), "500000");
		this.select(new Locator("客户来源"),"来访");
		this.select(new Locator("婚姻状况"), "已婚");
		this.sendkeys(new Locator("子女数量"), "3");
		this.sendkeys(new Locator("月收入"), "10000");
		this.select(new Locator("最高学历"), "本科");
		this.sendkeys(new Locator("工作单位全称"), ChineseUtil.getRandom(8));
		this.select(new Locator("工作省"),"北京");
		this.select(new Locator("工作市"),"北京市");
		this.select(new Locator("工作县"), "东城区");
		this.sendkeys(new Locator("工作详细地址"),ChineseUtil.getRandom(8));
		this.sendkeys(new Locator("单位电话"), NumberUtil.TelNumber());
		this.sendkeys(new Locator("部门"), "研发");
		this.sendkeys(new Locator("职务"),"设计");
		this.sendkeys(new Locator("单位邮编"), NumberUtil.postCode());
		this.sendkeys(new Locator("入职时间"), "2012-12-12");
		this.entry();
		this.sendkeys(new Locator("备注"), ChineseUtil.getRandom(20));
		this.sendkeys(new Locator("证明人姓名"),ChineseUtil.getRandomName());
		this.sendkeys(new Locator("证明人电话"), NumberUtil.phoneNumber());
		this.sendkeys(new Locator("证明人关系"), "同事");
		this.sendkeys(new Locator("家庭人姓名"), ChineseUtil.getRandomName());
		this.sendkeys(new Locator("家庭人电话"), NumberUtil.phoneNumber());
		this.sendkeys(new Locator("家庭人关系"), "哥哥");
		this.select(new Locator("家庭人省"), "北京");
		this.select(new Locator("家庭人市"), "北京市");
		this.select(new Locator("家庭人县"), "东城区");
		this.sendkeys(new Locator("家庭人详细"), ChineseUtil.getRandom(8));
		this.sendkeys(new Locator("家庭人邮编"), NumberUtil.postCode());
		this.sendkeys(new Locator("紧急人姓名"),ChineseUtil.getRandomName());
		this.sendkeys(new Locator("紧急人电话"), NumberUtil.phoneNumber());
		this.sendkeys(new Locator("紧急人关系"), "同学");
		this.select(new Locator("紧急人省"),"北京");
		this.select(new Locator("紧急人市"), "北京市");
		this.select(new Locator("紧急人县"),"东城区");
		this.sendkeys(new Locator("紧急人详细"), ChineseUtil.getRandom(8));
		this.sendkeys(new Locator("紧急人邮编"),NumberUtil.postCode());
		/**
			this.click(new Locator("信审保存草稿"));
			this.sleep(1000);
			this.click(new Locator("信审保存草稿确认"));
		**/	

		this.click(new Locator("信审提交审核"));
		this.sleep(500);
		assertEquals(this.isDisplayed(new Locator("信审提交确认")), true);
		this.click(new Locator("信审提交确认"));
		this.switchToWindow("和信车贷系统");
		System.out.println("新增信审成功啦, ~O(∩_∩O哈哈哈~");
	}

	/**
	 * 新增评估, 相关操作
	 */
	public void newEvaluationPage() throws Exception{
		this.sleep(1000);
		AutoUI AU=new AutoUI();
		this.click(new Locator("新增评估"));
		this.switchToWindow("和信车贷系统");
		driver.switchTo().frame(this.findElement(driver, new Locator("frame1")));
		driver.switchTo().frame(this.findElement(driver, new Locator("frame2")));
		this.click(new Locator("评估上传图片"));
		this.sleep(2000);
		AU.UploadImage(path2);
		this.sleep(3000);
		this.click(new Locator("评估开始上传"));
		driver.switchTo().parentFrame();
		driver.switchTo().parentFrame();
		this.click(new Locator("抵押信息"));
		this.sendkeys(new Locator("发动机号"), RandomStringUtils.randomAlphanumeric(6));
		this.sendkeys(new Locator("机动车登记证书"), RandomStringUtils.randomAlphanumeric(6));
		this.sendkeys(new Locator("车牌号码"), RandomStringUtils.randomNumeric(6));
		this.sendkeys(new Locator("出厂日期"),"2015-05-25");
		this.entry();
		this.sendkeys(new Locator("品牌"), ChineseUtil.getRandom(3));
		this.sendkeys(new Locator("型号"), RandomStringUtils.randomAlphanumeric(6));
		this.sendkeys(new Locator("登记日期"),"2016-03-02");
		this.entry();
		this.sendkeys(new Locator("车身颜色"),"红色");
		this.select(new Locator("使用性质"), "其他");
		this.sendkeys(new Locator("过户次数"),"1");
		this.select(new Locator("变速器"), "AT");
		this.sendkeys(new Locator("开票价"), "90000");
		this.sendkeys(new Locator("同类市场价格"), "80000");
		this.sendkeys(new Locator("车架号核实"), RandomStringUtils.randomAlphanumeric(6));
//		this.click(new Locator("行驶证"));
		this.sendkeys(new Locator("手续现状描述"), "完整齐全");
		this.sendkeys(new Locator("强制有效期"), "2022-12-12");
		this.sendkeys(new Locator("保险有效期"),"2025-10-15");
		this.sendkeys(new Locator("商业保险"), "2020-02-02");
		this.sendkeys(new Locator("年检有效期"), "2022-12-22");
		this.entry();
		this.sendkeys(new Locator("车辆评级"), "优");
		this.sendkeys(new Locator("累计扣分"), "0");
		this.sendkeys(new Locator("违章罚款金额"), "0");
		this.sendkeys(new Locator("车辆改装情况"),"未改装");
		this.sendkeys(new Locator("出险次数"), "0");
		this.sendkeys(new Locator("出险金额"), "0");
		this.sendkeys(new Locator("评估金额"), "90000");
		//后期放在配置文件中
		this.sendkeys(new Locator("建议借款额"),"20000" );
		this.sendkeys(new Locator("车况描述"), "新车还配备有真皮多功能方向盘、皮质座椅、全景天窗、ESP车身电子稳定系统、上坡辅助、陡坡缓降、胎压监测、自动驻车、倒车影像、无钥匙进入、一键式启动等。");
		this.click(new Locator("评估提交审核"));
		this.sleep(500);
		assertEquals(this.isDisplayed(new Locator("评估审核确认")), true);
		this.click(new Locator("评估审核确认"));
		this.switchToWindow("和信车贷系统");
		System.out.println("新增评估成功啦, ~O(∩_∩O哈哈哈~");
	}

	/**
	 * 进入新增进件，报签菜单操作
	 */
	public void intoSignContract() throws Exception{
		driver.navigate().refresh();
		this.sleep(500);
		this.click(new Locator("进件管理"));
		this.click(new Locator("报签"));
		System.out.println("报签菜单点击成功啦, ~O(∩_∩O哈哈哈~");
	}
	
	
	/**
	 * 发起复议, 相关操作
	 */
	public void ProposeDiscussPage() throws Exception{
		this.sleep(500);
		this.click(new Locator("发起复议"));
		System.out.println("发起复议成功啦, ~O(∩_∩O哈哈哈~");
	

	}
	
	/**
	 * 同意审核, 相关操作
	 */
	public void AgreeReviewPage() throws Exception{
		this.sleep(500);
		this.click(new Locator("同意审核2"));
		if (locatorProp.getProperty("还款方式").equals("等额本息")) {
			this.sendkeys(new Locator("确定月还款"), locatorProp.getProperty("月还款金额"));
		}	
		this.click(new Locator("确定审核"));
		this.click(new Locator("确定"));
		System.out.println("同意审核,成功啦, ~O(∩_∩O哈哈哈~");	
		}
	
	/**
	 * 报签合同
	 */
	public void SignContractPage() throws Exception{
		this.sleep(500);
		this.click(new Locator("报签合同"));
		this.sleep(500);
		this.click(new Locator("箭头"));
		this.click(new Locator("押证24期"));
		this.sleep(500);
		String jsString = "document.getElementById('signingDate').nextSibling.firstChild.removeAttribute('readonly')";
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(jsString);
		this.sendkeys(new Locator("签约日期"), "2017-08-21");
		this.entry();
		this.click(new Locator("是箭头"));
		this.click(new Locator("是"));
		this.sendkeys(new Locator("最终审批金额"),locatorProp.getProperty("最终审金额"));
		if (locatorProp.get("还款方式").equals("等额本息")) {
			this.sendkeys(new Locator("打款金额"), locatorProp.getProperty("打款金额"));
			this.sleep(500);
			this.click(new Locator("合同金额"));
		}
		if (locatorProp.get("还款方式").equals("付息还本")) {
			this.click(new Locator("计算"));
		}
		this.click(new Locator("开户银行箭头"));
		this.click(new Locator("开户银行"));
		this.click(new Locator("省箭头"));
		this.click(new Locator("开户行地址省"));
		this.click(new Locator("市箭头"));
		this.click(new Locator("市"));
		this.click(new Locator("区箭头"));
		this.click(new Locator("区"));
		this.sendkeys(new Locator("开户支行名称"), "测试运行支行");
		this.sendkeys(new Locator("银行账号"), "6226223116965663");
		this.click(new Locator("报签提交"));
		this.sleep(500);
		this.click(new Locator("报签成功"));
		System.out.println("报签合同,成功啦, ~O(∩_∩O哈哈哈~");	
	}
	

	
	
	
}
