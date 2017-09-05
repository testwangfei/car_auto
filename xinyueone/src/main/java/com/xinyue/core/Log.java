package com.xinyue.core;


// 自定义日志模块
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Log {
	
	private final Class<?>clazz;
	private Logger logger;
	
	public Log(Class<?>clazz){
		this.clazz=clazz;
		this.logger = LogManager.getLogger(this.clazz);
	}
	
	// 提示信息日志
	public void info(String message){
		logger.info("提示信息: "+message);
	}
	// 调试信息日志
	public void debug(String message) {
		logger.debug("调试信息: " + message);
	}
	// 错误信息日志
	public void error(String message) {
		logger.error("错误信息: "+ message);
	}
	// 追踪信息日志
	public void trace(String message) {
		logger.trace("追踪信息: " + message);
	}
	// 警告信息日志
	public void warn(String message) {
		logger.warn("警告信息: " + message);
	}
	// 致命信息日志
	public void fatal(String message) {
		logger.fatal("致命信息: " + message);
	}

}
