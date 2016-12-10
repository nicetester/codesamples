package cc.simpletest.qa.model;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utils {
	
	 /** 
     * 读取修改时间的方法
     */  
    public static String getModifiedTime(File f){  
//        File f = new File("C:\\test.txt");              
        Calendar cal = Calendar.getInstance();  
        long time = f.lastModified();  
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");         
        cal.setTimeInMillis(time);  
        String formatedTimeString=formatter.format(cal.getTime());
        System.out.println("修改时间[2] " + formatedTimeString);     
        //输出：修改时间[2]    2009-08-17 10:32:38  
        return formatedTimeString;
    }

}
