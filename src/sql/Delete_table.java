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
			Has=hasTable.hastable(dbConn,name);    /*�ж�Ҫ�����ı��Ƿ����*/
			if(Has==1){
				table="DROP TABLE "+name;
				stmt.executeUpdate(table);
			}
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
	}

}
