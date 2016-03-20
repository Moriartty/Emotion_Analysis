package filetest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class file {
	public static void Createfile(String name,String[] recode){
		File f=new File("D:\\Project\\"+name+".txt");
        FileWriter fw;
        try {
        	   fw=new FileWriter(f,true);
        	   for(int i=0;recode[i]!=null;i++){
        		   fw.write(recode[i]+"\r\n\r\n");
        	   }
        	   fw.close(); 
        	  } catch (IOException e) {
        	   e.printStackTrace();
        	  }
	}

}
