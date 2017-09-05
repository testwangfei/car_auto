package com.xinyue.IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import com.xinyue.core.Log;


/**
 * 这个类的方法用于获取配置信息的, config.properties
 */
public class ConfigUtils {

	private static Log log = new Log(ConfigUtils.class);
	static Properties properties = new Properties();
	
	private ConfigUtils(){
		
	}
	
	
	
	public static Properties getProperties(String config) throws IOException {
		Properties properties = new Properties();
		log.info("当前使用的配置文件: [ " + config+" ]");
		FileInputStream inStream = new FileInputStream(new File(config));
		InputStreamReader in = new InputStreamReader(inStream,("UTF-8"));	// 处理中文字符流
		try {
			properties.load(in);
		} catch (Exception e) {
			log.error("无法找到配置文件:  "+config);
			log.error(e.getMessage());
		}
		return properties;
	}
}
