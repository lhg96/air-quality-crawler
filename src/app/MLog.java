package app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MLog {
	/* 콘솔 출력용 로거. logback.xml의 root 설정값이 적용된다. */
	public static final Logger consoleLogger = LoggerFactory.getLogger(MLog.class);
	/* 파일 저장 및 콘솔 출력용 로거. logback.xml의 log.invalid 설정값이 적용된다. */
	public static final Logger invalidFileLogger = LoggerFactory.getLogger("log.invalid");
	
	public static void console(String message) {
		consoleLogger.info(message);
	}

	public static void write(String message) {
		invalidFileLogger.warn(message);
	}

	public static void write(String message, Exception e) {
		invalidFileLogger.warn(message,e);		
	}
}
