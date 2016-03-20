package sql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import mainframe.Loginframe;

public class connect_sql {                /*数据库连接部分*/
    public static String userName;
	public static String userPwd;
	public static Connection Connect(){
		String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=Test";
		Connection dbConn=null;
		try {
			Class.forName(driverName);
			dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return dbConn;
	}

}
