package sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

public class hasTable {        /*判断要创建的表是否存在*/
	
	public static int hastable(Connection dbConn,String name) throws Exception{
		DatabaseMetaData meta = dbConn.getMetaData();
		ResultSet rsTables = meta.getTables(null , null, name, null);    /*name 为表名*/
		if(rsTables.next()){
		   return 1;           /*如果有，则返回1*/
		}else{
		   return 0;
		}
	}

}
