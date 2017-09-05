package com.xinyue.core;

/**
 * 常见数字类型生成工具类
 */
public class NumberUtil {

		/**
		 * @return 随机的邮编
		 */
		public static String postCode(){
			StringBuffer firstcode = new StringBuffer();
			StringBuffer othercode = new StringBuffer();	
			
			firstcode.append(RandomUtils.nextInt(1, 10));
			
			for(int i = 1; i < 6; i++)
			{
				othercode.append(RandomUtils.nextInt(0, 10));
			}	
			return firstcode.toString()+othercode.toString();
		}
		/** 
	     * 返回手机号码 
	     */ 
		public static int getNum(int start,int end) {  
	        return (int)(Math.random()*(end-start+1)+start);  
	    }  
	
	    private static String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");  
	    public static String phoneNumber() {  
	        int index=getNum(0,telFirst.length-1);  
	        String first=telFirst[index];  
	        String second=String.valueOf(getNum(1,888)+10000).substring(1);  
	        String thrid=String.valueOf(getNum(1,9100)+10000).substring(1);  
	        return first+second+thrid;  
	    }
		
		/**
		 *  座机电话
		 */
		public static String TelNumber(){
				StringBuffer Tel = new StringBuffer();
				
				for(int i = 0; i < 8; i++)
				{
					Tel.append(RandomUtils.nextInt(2, 9));
				}
				
				return Tel.toString();
		}
		/**
		 * qq邮箱
		 */
		public static String email(){
			StringBuffer Tel = new StringBuffer();
			
			for(int i = 0; i < 8; i++)
			{
				Tel.append(RandomUtils.nextInt(1, 10));
			}
			
			return Tel.toString()+"@qq.com";
		}
		
		
		
		
}
