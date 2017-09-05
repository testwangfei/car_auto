package com.xinyue.IO;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class FileTxtUtil {
	private static String currentDir = System.getProperty("user.dir");
	
	/**
	 * 读取文件到list数组中
	 */
	public static List<String> readFile(String relativePath){
		String path=currentDir+relativePath;
		File file = new File(path);	
		List<String> list = new ArrayList<String>();
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			InputStreamReader io = new InputStreamReader(fileInputStream);
			BufferedReader reader = new BufferedReader(io);
			String terminalInfo = null;
			while((terminalInfo=reader.readLine())!=null){
				String terList = terminalInfo;
				list.add(terList);
			}
			reader.close();
			io.close();
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
		return list;
		
	}
	
	/**
	 * 读取单行字符串
	 */
	public static String  readline(String relativepath){
		String path = currentDir+relativepath;
		File file = new File(path);
		String str = new String ();
			FileInputStream fileInputStream;
			try {
				fileInputStream = new FileInputStream(file);
				InputStreamReader io = new InputStreamReader(fileInputStream);
				@SuppressWarnings("resource")
				BufferedReader reader = new BufferedReader(io);
				str =reader.readLine();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.err.println("没有找到文件: O(∩_∩)O哈哈~[ "+path+" ]");
			} catch (IOException e) {
				e.printStackTrace();
			}
		return str;
		
	}
	/**
	 * 将内容输出到文件中
	 */
	public static void outputFile(String relativeOutputPath, String txtfie) throws FileNotFoundException,IOException{
		String path=currentDir+relativeOutputPath;
		FileOutputStream fs = new FileOutputStream(new File(path));
		PrintStream p = new PrintStream(fs);
		p.println(txtfie);
		p.close();
	}

}