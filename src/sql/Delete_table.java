package sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete_table {
	static int Has;
	static String table;
	public static void delete_table(String name){
		Connection dbConn=connect_sql.Connect();
		Statement stmt;
		try {
			stmt = dbConn.createStatement();
			String sql=null;
			Has=hasTable.hastable(dbConn,name);    /*判断要创建的表是否存在*/
			if(Has==1){
				table="DROP TABLE "+name;
				stmt.executeUpdate(table);
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}

}
