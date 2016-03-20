package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class get_Tablenames {
	static String name[]=new String[512];
	
	public static String[] getTablename(){
		Connection dbConn=connect_sql.Connect();
	    try {
		    ResultSet rs =  dbConn.getMetaData().getTables(null,null,null,new String[] { "TABLE" });
		    for(int i=0;rs.next();i++){
				name[i]=rs.getString("TABLE_NAME");
	        }
      	} catch (SQLException e) {
		e.printStackTrace();
	    }
	    return name;
    
	}
}
