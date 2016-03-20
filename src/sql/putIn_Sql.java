package sql;

import java.sql.Connection;

import java.sql.Statement;

import Auto_analyse.newMessage_analyse;

import com.google.common.base.CaseFormat;

import mainframe.Addinfo_frame;

import filetest.format;

public class putIn_Sql {  
	public static int hasperson;

	public static void PutIn_Sql(String str[],int flag,String name){
			String table;
			int Has;
			try{
				Connection dbConn=connect_sql.Connect();
				Statement stmt =dbConn.createStatement();
				String sql=null;
				Has=hasTable.hastable(dbConn,name);    /*判断要创建的表是否存在*/
				
				switch(flag){
				case 1:                   //整个主题帖
					if(Has==1){
						Delete_table.delete_table(name);
					}
					table="create table "+ name+" (id varchar(MAX),贴吧称号  varchar(MAX)," +
							"贴吧等级  varchar(MAX),content varchar(MAX),年月日  varchar(MAX),时间  varchar(MAX))";
					stmt.executeUpdate(table);
					for(int i=0;str[i]!=null;i++){
						String arr[]=str[i].split("\\*");
						if(arr.length>=6){
							sql = "insert into " +name+" (id,贴吧称号,贴吧等级,content,年月日,时间)" +
				    	     "values('"+arr[0]+"','"+arr[1]+"','"+arr[2]+"','"+arr[3]+"','"+arr[4]+"','"+arr[5]+"')";
					        stmt.executeUpdate(sql);
					    }
					}break;
					
				case 2:                  //微博
					if(Has==1){
						Delete_table.delete_table(name);
					}
					table="create table " +name+" (content varchar(MAX),time varchar(MAX))";
					stmt.executeUpdate(table);
					for(int i=0;str[i]!=null;i++){
  					    String arr[]=str[i].split("\\|");
  					    sql = "insert into "+ name+" (content,time)" +
  	  			    	     "values('"+arr[0]+"','"+arr[1]+"')";
  	  				        stmt.executeUpdate(sql);
  			        }break;
  			        
				case 3:                 //贴吧个人主页
					if(Has==1){
						Delete_table.delete_table(name);
					}
					table="create table "+ name+" (time varchar(MAX), titletxt varchar(MAX)," +
							" content varchar(MAX), position varchar(Max))";
					stmt.executeUpdate(table);
					for(int i=0;str[i]!=null;i++){
  					    String arr[]=str[i].split("\\*");
  					    sql = "insert into "+ name+" (time,titletxt,content,position)" +
  	  			    	     "values('"+arr[0]+"','"+arr[1]+"','"+arr[2]+"','"+arr[3]+"')";
  	  				        stmt.executeUpdate(sql);
  			        }break;
  			        
				case 4:                 //人人
					if(Has==1){
						Delete_table.delete_table(name);
					}
					table="create table "+ name+" (content varchar(MAX), time varchar(MAX))";
					stmt.executeUpdate(table);
					for(int i=0;str[i]!=null;i++){
  					    String arr[]=str[i].split("\\+");
  					    sql = "insert into "+ name+" (content,time)" +
  	  			    	     "values('"+arr[0]+"','"+arr[1]+"')";
  	  				        stmt.executeUpdate(sql);
  			        }break;
  			        
				case 5:               //收藏人信息
					if(Has==0){    //注意：这里我们不需要删除PersonInfo表，这个表只能用来添加新数据！
						table="create table "+ name+" (name varchar(MAX), tiebaID varchar(MAX), weibo varchar(MAX), renren varchar(MAX))";
						stmt.executeUpdate(table);
					}
					hasperson=hasPerson.HasPerson(dbConn,str[0]);
					String Str[]=new String[3];
					if(hasperson==1){       //如果PersonInfo表中有此人，则只更新此人信息，不新建收藏人
						for(int i=1;i<4;i++){
							switch(i){
							case 1:if(Addinfo_frame.updataflag[i]==1){
								      sql="update PersonInfo set tiebaID='"+str[1]+"' where name='"+str[0]+"'";
								      stmt.executeUpdate(sql);
							       }break;
							case 2:if(Addinfo_frame.updataflag[i]==1){
								      sql="update PersonInfo set weibo='"+str[2]+"' where name='"+str[0]+"'";
								      stmt.executeUpdate(sql);
						           }break;
							case 3:if(Addinfo_frame.updataflag[i]==1){
								      sql="update PersonInfo set renren='"+str[3]+"' where name='"+str[0]+"'";
							          stmt.executeUpdate(sql);
					               }break;
							}
						}
					}
					else{
						sql = "insert into "+ name+" (name,tiebaID,weibo,renren)" +
 	  			    	     "values('"+str[0]+"','"+str[1]+"','"+str[2]+"','"+str[3]+"')";
						stmt.executeUpdate(sql);
					}
					break;
				
			   case 6:               //收藏人状态
				   if(Has==0){      //同上
					   table="create table "+ name+" (姓名  varchar(MAX),状态值1  float,状态值2  float,状态值3  float)";
					   stmt.executeUpdate(table);
				   }
				   sql = "insert into "+ name+" (姓名,状态值1,状态值2,状态值3)" +
	  			    	        "values('"+str[0]+"',0,0,0)";
	  			       stmt.executeUpdate(sql);
				   break;
			  }
				
			}
			catch(Exception e){
				e.printStackTrace();
				System.out.print("连接失败");
			}
	}

}
