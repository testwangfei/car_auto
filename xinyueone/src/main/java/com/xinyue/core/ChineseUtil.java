package com.xinyue.core;


import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;

import com.xinyue.IO.FileTxtUtil;


public class ChineseUtil {
	 /**
     * 获取指定长度随机简体中文
     */
    public static String getRandom(int len){
    	
        String ret= "";
          for(int i=0;i<len;i++){
              String str = null;
              int hightPos;	//定义高位
              int lowPos; // 定义高低位
              Random random = new Random();
              hightPos = (176 + Math.abs(random.nextInt(39))); //获取高位值
              lowPos = (161 + Math.abs(random.nextInt(93))); //获取低位值
              byte[] b = new byte[2];
              b[0] = (new Integer(hightPos).byteValue());
              b[1] = (new Integer(lowPos).byteValue());
              try
              {
                  str = new String(b, "GBK"); //转成中文
              }
              catch (UnsupportedEncodingException ex)
              {
                  ex.printStackTrace();
              }
               ret=ret+str;
               try {
				ret= new String( ret.getBytes(),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
          }
      return ret;
    }
    
    public static String getRandomName() {
    	String path="/src/main/java/resource/data/random_firstname.txt";   	
    	List<String> aList=	FileTxtUtil.readFile(path);

		Random aa= new Random();	
		String name = aList.get(aa.nextInt(1057))+ChineseUtil.getRandom(1);
		return name;
    }
    public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(ChineseUtil.getRandomName());
		}
	}
}
