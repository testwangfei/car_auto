package com.xinyue.IO;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.testng.Assert;

import jxl.*;

/**
 * Excel放在Data文件夹下</p>
 * Excel命名方式：测试类名.xls</p>
 * Excel的sheet命名方式：测试方法名</p>
 * Excel第一行为Map键值</p>
 * 代码参考郑鸿志的Blog
 * {@link www.zhenghongzhi.cn/post/42.html}
 * @ClassName: ExcelDataProvider
 * @Description: TODO(读取Excel数据)
 */
public class ExcelDataProvider implements Iterator<Object[]> 
{

    private Workbook book         = null;
    private Sheet    sheet        = null;
    private int      rowNum       = 0;
    private int      currentRowNo = 0;
    private int      columnNum    = 0;
    private String[] columnnName;

    public ExcelDataProvider(String filepath, String sheetname) 
    {
        try 
        {

            int dotNum = filepath.indexOf(".");

            if (dotNum > 0)
            {
            	filepath = filepath.substring(filepath.lastIndexOf(".") + 1,
            			filepath.length());
            }

            String path = "data/" + filepath + ".xls";
            InputStream inputStream = new FileInputStream(path);

            book = Workbook.getWorkbook(inputStream);
            sheet = book.getSheet(sheetname);
            //sheet = book.getSheet(0);
            rowNum = sheet.getRows();
            Cell[] cell = sheet.getRow(0);
            columnNum = cell.length;
            columnnName = new String[cell.length];
            
            
            for (int i = 0; i < cell.length; i++) {
                columnnName[i] = cell[i].getContents().toString();
            }
            this.currentRowNo++;

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            Assert.fail("unable to read Excel data");
        }
    }

    public boolean hasNext() {
    	
        if (this.rowNum == 0 || this.currentRowNo >= this.rowNum){
        	
            try
            {
                book.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return false;
        } 
        else
        {try {
            // sheet下一行内容为空判定结束
            if ((sheet.getRow(currentRowNo))[0].getContents().equals("")){
            	return false;
            }    
            return true;      
		} catch (Exception e) {
			e.printStackTrace();
		}}
        return true; 
    }

    @SuppressWarnings("unused")
	public Object[] next() 
    {
    	
        Cell[] c = sheet.getRow(this.currentRowNo);
        DateCell ce;
        Map<String, String> data = new HashMap<String, String>();
        // List<String> list = new ArrayList<String>();

        for (int i = 0; i < this.columnNum; i++)
        {
            String temp = "";
            Date tmp=null;

            try 
            {
            	//日期类型
                if(c[i].getType()==CellType.DATE){
                	DateCell dateci=(DateCell)c[i];
                	tmp=dateci.getDate();
                	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    temp = sdf.format(tmp);
                	
                	
                }else{
                	temp = c[i].getContents();
                	
                }
                data.put(this.columnnName[i], temp);
            } 
            catch (ArrayIndexOutOfBoundsException ex)
            {
                temp = "";
            } 

            
            
        }
        Object object[] = new Object[1];
        object[0] = data;
        this.currentRowNo++;
        return object;
    }

    public void remove() 
    {
        throw new UnsupportedOperationException("remove unsupported.");
    }



}
