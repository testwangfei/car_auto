
package com.xinyue.IO;  

import java.io.File;

// 调用本地计算机上传图片,通过一个AUTO的可执行程序
public class AutoUI
{
    public  void UploadImage(String  uploadprograme){
    	
        try{
            Runtime mt =Runtime.getRuntime();
             // 找到相对应的绝对路径。启动记事本文件
            //创建新的文件路径,启动浏览器
           File myfile = new File(uploadprograme);
            mt.exec(myfile.getAbsolutePath());   
          }
        catch(Exception e)
        {
          System.out.println(e);            
        }
    }
}


