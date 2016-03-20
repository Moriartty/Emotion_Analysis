package filetest;

import java.io.File;

public class deletefile {
	public static void Delete(String name){
		 String s = "D:\\Project\\"+name+".txt";         //文件的路径
		 File file = new File(s);
		 if(file.exists())
		     file.delete();           //删除name文件
	}

}
