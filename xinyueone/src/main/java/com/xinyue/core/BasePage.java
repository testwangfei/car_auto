package com.xinyue.core;

import static org.testng.Assert.fail;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.xinyue.IO.ConfigUtils;



public class BasePage {
	Properties properties;
	protected WebDriver driver;
	protected Log log = new Log(this.getClass());
	protected Properties locatorProp = null;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		String relativepath = "/resources/element/" + this.getClass().getSuperclass().getSimpleName()+ ".Properties";
		String path = System.getProperty("user.dir") + relativepath;
		try {
			locatorProp = ConfigUtils.getProperties(path);
		} catch (IOException e) {
			// 后期处理
		log.error("文件有误[ " + path+" ]");
		}
	}

	/**
	 * 获取webElement元素
	 */
	protected WebElement findElement(long timeOutInSeconds, String key) {
		WebElement element = null;

		if (locatorProp != null && locatorProp.containsKey(key)) {
			if (locatorProp.getProperty(key).contains("<")) {
				final String value = locatorProp.getProperty(key).split("<")[1];
				final String byType = locatorProp.getProperty(key).split("<")[0];
				element = (new WebDriverWait(driver, timeOutInSeconds)).until(new ExpectedCondition<WebElement>() {
					@Override
					public WebElement apply(WebDriver driver) {
						WebElement e = null;
						switch (byType.toLowerCase()) {
						case "id":
							e = driver.findElement(By.id(value));
							break;
						case "xpath":
							e = driver.findElement(By.xpath(value));
							break;
						case "name":
							e = driver.findElement(By.name(value));
							break;
						case "linktext":
							e = driver.findElement(By.linkText(value));
							break;
						case "tagname":
							e = driver.findElement(By.tagName(value));
							break;
						case "classname":
							e = driver.findElement(By.className(value));
							break;
						case "cssselector":
							e = driver.findElement(By.cssSelector(value));
							break;
						case "partiallinktext":
							e = driver.findElement(By.partialLinkText(value));
							break;
						default:
							log.error("不存在该定位方式:[ " + byType + " ]");
							e = null;
							break;
						}
						return e;
					}
				});
			} else {
				log.error("没有分割符< [ " + key + " ]");
			}

		} else {
			log.error("定位的索引值有误,不存在: [ " + key + " ]");
			// 提示properties为空
		}
		return element;
	}

	protected WebElement findElement(String key) {
		int timeout = 10;
		return findElement(timeout, key);
	}

	/**
	 * 用于检测非必须显示的元素是否存在, 主要用于错误性校验
	 */
	protected boolean isElementPresent(long timeOutInSeconds, String key) {
		boolean isPresent = false;
		try {
			findElement(timeOutInSeconds, key);
			isPresent = true;
		} catch (NoSuchElementException | TimeoutException e) {
			log.error("没有找到该元素"+key);
		}
		return isPresent;
	}

	/**
	 * 用于检测必需存在的元素是否存在, 主要用于正确性校验
	 */
	protected boolean isElementPresent(String key) {
		return isElementPresent(10, key);
	}

	/**
	 * 检测元素是否显示
	 */
	protected boolean isDisplayed(String key) {
		isElementPresent(key);
		return findElement(key).isDisplayed();
	}

	/**
	 * 检测元素是否可用
	 */
	protected boolean isEnabled(String key) {
		isElementPresent(key);
		return findElement(key).isEnabled();
	}

	/**
	 * 检测元素是否处于选中状态
	 */
	protected boolean isSelected(String key) {
		isElementPresent(key);
		return findElement(key).isSelected();
	}

	/**
	 * 判断是否存在警告弹窗
	 */
	protected boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	/**
	 * 点击弹窗确认,并获取其文本值
	 */
	protected String closeAlertAndGetItsText(boolean accept) {
		sleep(500);
		boolean acceptNextAlert = accept;
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
	}

	/**
	 * web元素输入框的清空操作
	 */
	protected void clear(String key) {
		isElementPresent(key);
		findElement(key).clear();
	}

	/**
	 * web元素的点击事件
	 */
	protected void click(String key) {
		isElementPresent(key);
		findElement(key).click();
	}

	/**
	 * 通过JS点击元素
	 */
	protected void clickByjs(String key) {
		WebElement e = findElement(key);
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", e);
	}
	
	/**
	 * 点击并并保持鼠标在该元素上
	 */
	protected void clickAndHold(String key) {
		WebElement e = findElement(key);
		Actions actions = new Actions(driver);
		actions.moveToElement(e).clickAndHold(e).perform();
	}

	/**
	 * 点击事件Keys.ENTER, 在具体元素上回车
	 */
	protected void clickKeyEentry(String key) {
		WebElement e = findElement(key);
		e.sendKeys(Keys.ENTER);
	}

	/**
	 * 取消全部选择
	 */
	protected void deselectAll(String key) {
		isElementPresent(key);
		Select select = new Select(findElement(key));
		select.deselectAll();
	}

	/**
	 * 通过元素属性value的值,取消选择
	 */
	protected void deselectByValue(String key, String value) {
		isElementPresent(key);
		Select select = new Select(findElement(key));
		select.deselectByValue(value);
	}

	/**
	 * 通过可见的文本信息,取消选择
	 */
	protected void deselectByVisibleText(String key, String text) {
		isElementPresent(key);
		Select select = new Select(findElement(key));
		select.deselectByVisibleText(text);
	}

	/**
	 * 通过可见的文本信息,取消选择
	 */
	protected void deselectByIndex(String key, int index) {
		isElementPresent(key);
		Select select = new Select(findElement(key));
		select.deselectByIndex(index);
	}

	/**
	 * 等待函数
	 */
	protected void sleep(int millis) {
		
			try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				log.error(e.toString());
			}
	}

	/**
	 * web元素的输入事件
	 */
	protected void sendKeys(String key, String keysToSend) {
		isElementPresent(key);
		findElement(key).clear();
		findElement(key).sendKeys(keysToSend);
	}
	
	/**
	 * 如果此当前元素是窗体或表单中的元素，则将其提交给远程服务器。
	 */
	protected void submit(String key) {
		isElementPresent(key);
		findElement(key).submit();
	}

	/**
	 * 通过JS给输入框传入值
	 */
	protected void sendkeysByjs(String key, String values) {
		isElementPresent(key);
		WebElement e = findElement(key);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value=\"" + values + "\"", e);
	}
	/**
	 * 通过可见的文本,进行web元素的选择操作
	 */
	protected void selectByVisibleText(String key, String text) {
		isElementPresent(key);
		Select select = new Select(findElement(key));
		select.selectByVisibleText(text);
	}

	/**
	 * 通过索引数值,进行web元素的选择操作
	 */
	protected void selectByIndex(String key, int index) {
		isElementPresent(key);
		Select select = new Select(findElement(key));
		select.selectByIndex(index);
	}

	/**
	 * 通过元素属性value的值,进行web元素的选择操作
	 */
	protected void selectByValue(String key, String value) {
		isElementPresent(key);
		Select select = new Select(findElement(key));
		select.selectByValue(value);
	}

	/**
	 * 给富文本框传入值
	 */
	protected void setRichTextBox(String key, String text) {
		WebElement e = findElement(key);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].innerHTML = \"" + text + "\"", e);
	}

	/**
	 * 切换父窗口
	 */
	protected void switchParentFrame(){
		 driver.switchTo().parentFrame();
		 sleep(500);
	}

	/**
	 * 切换浏览器窗口
	 */
	protected boolean switchToWindow(String windowTitle) {
		this.sleep(2000);
		boolean flag = false;
		try {
			String currentHandle = driver.getWindowHandle();
			Set<String> handles = driver.getWindowHandles();
			for (String s : handles) {
				if (s.equals(currentHandle)) {
					continue;
				} else {
					driver.switchTo().window(s);
					if (getTitle().contains(windowTitle)) {
						flag = true;
						 log.info("成功切换windows窗口到: " + windowTitle);
					}
				}
			}
		} catch (NoSuchWindowException e) {
			 log.info("没有找到窗口:" + windowTitle);
			flag = false;
		}
		return flag;
	}

	protected void switchFrame(String key){
		isElementPresent(key);
		driver.switchTo().frame(findElement(key));
		sleep(500);
	}
	
	
	/**
	 * 滚动页面,将原始显示到页面中心
	 */
	protected void scrollToElement(String key) {
		WebElement e = findElement(key);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", e);
	}

	/**
	 * 刷新浏览器
	 */
	protected void refresh(){
		driver.navigate().refresh();
		sleep(500);
	}
	
	/**
	 * 获取标题
	 */
	protected String getTitle() {
		return this.driver.getTitle();
	}
	
	/**
	 * 获取web元素的文本值
	 */
	protected String getText(String key) {
		isElementPresent(key);
		return findElement(key).getText();
	}

	/**
	 * 该方法将返回具有给定名称的属性的值（如果存在）
	 */
	protected String getAttribute(String key, String name) {
		isElementPresent(key);
		return findElement(key).getAttribute(name);
	}

	/**
	 * 获取富文本框的值
	 */
	protected String getRichTextBox(String key) {
		WebElement e = findElement(key);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (String) js.executeScript("var result=arguments[0].innerHTML;return result ", e);
	}

	/**
	 * 截图工具
	 */
	protected void getScreenshotAs() {
		String time = new SimpleDateFormat("yyyymmdd-hhmmss").format(new Date());
		String filename =File.separator + time + ".png";
		String currentPath = System.getProperty("user.dir")+ "/calendar/screenshot/" ;
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(currentPath + filename));
		} catch (IOException e) {
			 log.error("无法保存屏幕截图\n"+4);
		} finally {
			 log.info("屏幕截图完成");
		}
	}

	/**
	 * 待定
	 */
	protected void tearDown()  {
		StringBuffer verificationErrors = new StringBuffer();
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}




	
	
	/**
	 * 鼠标点击, 先移动到元素上,在执行点击操作
	 */
	protected void mouseClick(String key)  {
		WebElement e = findElement(key);
		Actions action = new Actions(driver);
		action.moveToElement(e);
		action.click();

	}

	/**
	 * 鼠标移到元素上悬停,通过JavaScript
	 */
	protected void mouseHoverByJavaScript(String key) {
		WebElement e = findElement(key);
		String mouseHoverjs = "var evObj = document.createEvent('MouseEvents');"
				+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
				+ "arguments[0].dispatchEvent(evObj);";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(mouseHoverjs, e);
	}

	/**
	 * 最大化windows窗口
	 */
	protected void maxwindows() {
		driver.manage().window().maximize();
	}

	/**
	 * 单选按钮/单选框相关操作
	 */
	protected void radioButtion(String key) {
		WebElement e = findElement(key);
		if (!e.isSelected()) {
			e.click();
		}
	}

	/**
	 * 回车事件,直接回车,
	 */
	protected void entry() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).perform();
	}

	/**
	 * 模拟浏览器后退
	 */
	protected void navigateBack(){
		driver.navigate().back();
	}
	
	/**
	 * 模拟浏览器前进
	 */
	protected void navigateForward(){
		driver.navigate().forward();
	}
	
	/**
	 * 在当前窗口加载新的网页
	 */
	protected void navagateTo(String url){
		driver.navigate().to(url);
	}
	


	/**
	 * 打开新的网页
	 */
	protected int open(String url) {
		if (url == null || url.equals("")) {
			log.error("invlid URL");
			return -1;
		}
		int responseStatus = 200;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			// 创建HTTP-GET.
			HttpGet httpget = new HttpGet(url);
			log.info("executing request " + httpget.getURI());
			// 执行get请求.
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				log.info("--------------------------------------");
				// 打印响应状态
				log.info(response.getStatusLine().toString());
				if (entity != null) {
					// 打印响应内容长度
					log.info("Response content length: " + entity.getContentLength());
					// 打印响应内容
					log.info("Response content: " + EntityUtils.toString(entity));
				}
				log.info("------------------------------------");
			} finally {
				response.close();
			}
		} catch (ParseException| IOException e) {
			log.error(e.toString());
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				log.error(e.toString());
			}
		}
		return responseStatus;
	}

}
