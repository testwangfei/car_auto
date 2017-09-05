package com.chedai.test;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.chedai.business.NewPackBus;
import com.xinyue.IO.ConfigUtils;
import com.xinyue.IO.FileTxtUtil;
import com.xinyue.core.Log;
import com.xinyue.driver.BrowerDriver;


@Test(groups = "Init")
public  class InitTest{
	public static WebDriver driver;			//公共的WebDriver驱动,唯一的公共浏览器
	public static String new_user;			//新建进件的---用户名
	public static String admin_user;		//管理员的-----用户名
	public static String password;			//所有操作员共有的密码	
	public static String custname;	//每次新建进件等的动态的客户名称

	String config = System.getProperty("user.dir") + "/resources/data/chedai.properties";		//( 测试网址和操作员信息 )存放的文件路径
	Properties prop;								//存放Properties文件的对象
	String url;											// 测试网站的URL
	
	final Log log = new Log(this.getClass());
	BrowerDriver bd = new BrowerDriver();
	
	@BeforeSuite
	public void setUp(){		
		log.info("Start to run class " + this.getClass().getName()+ " on driverType");
			try {
				System.out.println(config);
				prop=ConfigUtils.getProperties(config);
			} catch (IOException e) {
				e.printStackTrace();
				log.error("获取配置文件失败"+config);
			}
			url = prop.getProperty("url");
			new_user=prop.getProperty("new_user");
			admin_user=prop.getProperty("admin_user");
			password=prop.getProperty("password");	
			driver =bd.startChrome(url);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	@BeforeClass
	public void AdminLoginTest() throws Exception {
		custname=FileTxtUtil.readline("/resources/data/chedainame.txt");		//每次新建进件等的动态的客户名称
		NewPackBus	NPB = new NewPackBus(driver);
		NPB.loginBus(admin_user, password);
		
	}
	
	
	@AfterClass
	public void logouttest() throws Exception{
		NewPackBus NPB =new NewPackBus(driver);
		NPB.logoutbus();
	}

	@AfterSuite
	public void tearDown() throws InterruptedException
	{
		if (driver != null)
		{
			driver.close();
			driver.quit();
		}
		log.info("关闭浏览器");
	}
}