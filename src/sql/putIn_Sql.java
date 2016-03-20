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
				Has=hasTable.hastable(dbConn,name);    /*�ж�Ҫ�����ı��Ƿ����*/
				
				switch(flag){
				case 1:                   //����������
					if(Has==1){
						Delete_table.delete_table(name);
					}
					table="create table "+ name+" (id varchar(MAX),���ɳƺ�  varchar(MAX)," +
							"���ɵȼ�  varchar(MAX),content varchar(MAX),������  varchar(MAX),ʱ��  varchar(MAX))";
					stmt.executeUpdate(table);
					for(int i=0;str[i]!=null;i++){
						String arr[]=str[i].split("\\*");
						if(arr.length>=6){
							sql = "insert into " +name+" (id,���ɳƺ�,���ɵȼ�,content,������,ʱ��)" +
				    	     "values('"+arr[0]+"','"+arr[1]+"','"+arr[2]+"','"+arr[3]+"','"+arr[4]+"','"+arr[5]+"')";
					        stmt.executeUpdate(sql);
					    }
					}break;
					
				case 2:                  //΢��
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
  			        
				case 3:                 //���ɸ�����ҳ
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
  			        
				case 4:                 //����
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
  			        
				case 5:               //�ղ�����Ϣ
					if(Has==0){    //ע�⣺�������ǲ���Ҫɾ��PersonInfo�������ֻ��������������ݣ�
						table="create table "+ name+" (name varchar(MAX), tiebaID varchar(MAX), weibo varchar(MAX), renren varchar(MAX))";
						stmt.executeUpdate(table);
					}
					hasperson=hasPerson.HasPerson(dbConn,str[0]);
					String Str[]=new String[3];
					if(hasperson==1){       //���PersonInfo�����д��ˣ���ֻ���´�����Ϣ�����½��ղ���
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
				
			   case 6:               //�ղ���״̬
				   if(Has==0){      //ͬ��
					   table="create table "+ name+" (����  varchar(MAX),״ֵ̬1  float,״ֵ̬2  float,״ֵ̬3  float)";
					   stmt.executeUpdate(table);
				   }
				   sql = "insert into "+ name+" (����,״ֵ̬1,״ֵ̬2,״ֵ̬3)" +
	  			    	        "values('"+str[0]+"',0,0,0)";
	  			       stmt.executeUpdate(sql);
				   break;
			  }
				
			}
			catch(Exception e){
				e.printStackTrace();
				System.out.print("����ʧ��");
			}
	}

}
