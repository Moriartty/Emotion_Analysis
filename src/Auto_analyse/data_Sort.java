package Auto_analyse;

import java.rmi.server.UID;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.xml.transform.Templates;

import sql.connect_sql;
import sql.hasTable;

public class data_Sort {
	public static float[] sort(float score,String person,Connection dbConn){
		float Score[]=new float[3]; 
		float temp[]=new float[3];
		 try {
			 Statement stmt =dbConn.createStatement();
			 ResultSet rs = null;
			 String sqlStr=null;
			 sqlStr = "select ״ֵ̬1,״ֵ̬2,״ֵ̬3 from Person_state where ����='"+person+"'";
			 rs = stmt.executeQuery(sqlStr);
			 String uid[]=new String[3];                     //��¼��Ҫ����������
			 for(int i=0;rs.next();i++){
					uid[0]=rs.getString("״ֵ̬1");
					uid[1]=rs.getString("״ֵ̬2");
				    uid[2]=rs.getString("״ֵ̬3");
			 }
			 for(int i=0;i<3;i++)
			     temp[i]=Float.parseFloat(uid[i]);
		 } catch (Exception e) {
			e.printStackTrace();
		 }
		 Score[0]=score;
		 Score[1]=temp[0];
		 Score[2]=temp[1];
		 return Score;
	}
}
