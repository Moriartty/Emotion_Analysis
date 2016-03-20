package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.apache.bcel.generic.RETURN;
import org.apache.commons.collections.map.StaticBucketMap;
import org.bouncycastle.jcajce.provider.asymmetric.RSA;

import mainframe.Mainframe;
import mainframe.Showframe;

import filetest.file;


public class getData {

	public static void Getsql_data(String name){       /*获取表名为name的表的数据信息*/
		Connection dbConn=connect_sql.Connect();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = dbConn.createStatement();
			//String sqlStr = "select reply from "+name+" where position='"+username+"'";
			String sqlStr=null;
		
			switch(Showframe.flag){
			case 1:sqlStr = "select top 1 content from "+name;break;
			case 2:sqlStr = "select top 3 content from "+name;break;
			case 3:sqlStr = "select top 5 content from "+name;break;
			case 4:sqlStr = "select content from "+name;break;
			}
			
			rs = stmt.executeQuery(sqlStr);
			String uid[]=new String[1000];     /*逐条记录需要分析的数据*/
			for(int i=0;rs.next();i++){
				uid[i]=rs.getString("content");
			}
			file.Createfile(name,uid);        /*将数据写入文件，用于分析*/
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public static String getfirstdata(String name){
		if(Mainframe.analysetype==1){
	    	Connection dbConn=connect_sql.Connect();
			try {
				Statement stmt =dbConn.createStatement();
				String sql=null;
			    int has=hasTable.hastable(dbConn,name);
			    if(has==1)
			    	return (getData.GetTime(name));	
			} catch (Exception e) {
				e.printStackTrace();
			}	
	    }
		return null;
	}
	
	public static String GetTime(String name){
		String time=null;
		Connection dbConn=connect_sql.Connect();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = dbConn.createStatement();
			String sqlStr=null;
			sqlStr = "select top 1 time from "+name;
			rs = stmt.executeQuery(sqlStr);
			String uid[]=new String[1000];     /*逐条记录需要分析的数据*/
			for(int i=0;rs.next();i++){
				uid[i]=rs.getString("time");
			}
			time=uid[0];
			return time;
			//file.Createfile(name,uid);        /*将数据写入文件，用于分析*/
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}
	
	public static String[] GetPerson_name(){
		Connection dbConn=connect_sql.Connect();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = dbConn.createStatement();
			String sqlStr = "select name from PersonInfo";
			rs = stmt.executeQuery(sqlStr);
			String uid[]=new String[1000];     /*逐条记录需要分析的数据*/
			for(int i=0;rs.next();i++){
				uid[i]=rs.getString("name");
			}
			return uid;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}
	
	public static String GetPerson_info(String name,int flag){
		Connection dbConn=connect_sql.Connect();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = dbConn.createStatement();
			String sqlStr = null;
			switch(flag){
			case 1:sqlStr = "select tiebaID from PersonInfo where name='"+name+"'";
			       break;
			       
			case 2:sqlStr = "select weibo from PersonInfo where name='"+name+"'";
		           break;
		           
			case 3:sqlStr = "select renren from PersonInfo where name='"+name+"'";
	               break;
			}
			rs = stmt.executeQuery(sqlStr);
			String uid=null;                     //记录需要分析的数据
			for(@SuppressWarnings("unused")
			int i=0;rs.next();i++){
				switch(flag){
				case 1:uid=rs.getString("tiebaID");;
				       break;
				       
				case 2:uid=rs.getString("weibo");;
			           break;
			           
				case 3:uid=rs.getString("renren");;
		               break;
				}
			}
			return uid;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
