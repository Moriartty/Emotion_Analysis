package sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

public class hasTable {        /*�ж�Ҫ�����ı��Ƿ����*/
	
	public static int hastable(Connection dbConn,String name) throws Exception{
		DatabaseMetaData meta = dbConn.getMetaData();
		ResultSet rsTables = meta.getTables(null , null, name, null);    /*name Ϊ����*/
		if(rsTables.next()){
		   return 1;           /*����У��򷵻�1*/
		}else{
		   return 0;
		}
	}

}
