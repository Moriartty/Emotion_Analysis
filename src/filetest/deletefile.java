package filetest;

import java.io.File;

public class deletefile {
	public static void Delete(String name){
		 String s = "D:\\Project\\"+name+".txt";         //�ļ���·��
		 File file = new File(s);
		 if(file.exists())
		     file.delete();           //ɾ��name�ļ�
	}

}
