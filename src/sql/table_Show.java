package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class table_Show {
	public static DefaultTableModel Show(String name){

		try {
		    Connection conn=connect_sql.Connect();
	        DefaultTableModel myTable=new DefaultTableModel();
	     	Statement stmt;
	     	int number;
			stmt = conn.createStatement();	
			ResultSet rs=stmt.executeQuery("select * from "+name);	
			ResultSetMetaData metaData = rs.getMetaData();
			number=metaData.getColumnCount();      //表的总列数
		    Vector<Object> rows=new Vector<Object>();
 	     	Vector<Object> columnNames=new Vector<Object>();
 	     	
 	     	for(int num=0;num<number;num++){
			    columnNames.addElement(metaData.getColumnName(num+1));
		    }
 
    		while(rs.next()){
    			Vector<Object> newRow = new Vector<Object>();
    			for (int i = 1; i <= number; i++) {
    				newRow.addElement(rs.getObject(i));
    		    }
    			rows.addElement(newRow);
    		}
    		
 	     	myTable=new DefaultTableModel(rows,columnNames);
 	     	return myTable;
		    } catch (SQLException e) {
			    e.printStackTrace();
	    	}
		
		return null;
	}
}
