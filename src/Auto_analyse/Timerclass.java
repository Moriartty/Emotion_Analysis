package Auto_analyse;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Timer;

import org.junit.runner.manipulation.Sortable;

import sql.connect_sql;
import sql.getData;
import sql.hasTable;

import emotion_analysis.SentimentApiExample;
import frameActionPerformed.Mainframe_Renew;
import frameActionPerformed.get_Action;

import mainframe.Mainframe;
import mainframe.Warning_frame;

public class Timerclass {
	public static String person=null;
	static String tablename="Person_state";
	static String table;
	public static String[] warning_name=new String[100];
	static int num=0;        //情感有问题人数
	static int number=0;     //每个人一个周期有新状态的位置数
	static float sum=0;        //总情感值
	public static class MyTask extends java.util.TimerTask{
        @Override
        public void run() {
            String[] Nametemp=getData.GetPerson_name();
            num=0;
            for(int i=0;Nametemp[i]!=null;i++){
            	Mainframe.nametemp=Nametemp[i];
            	person=Nametemp[i];
            	number=0;
            	sum=0;
            	for(Mainframe.flag=1;Mainframe.flag<4;Mainframe.flag++){
            		Mainframe.analysetype=1;
                    Mainframe.Gettype_flag=1;
                	get_Action.getAction();
                	System.out.println("________\n");
                }
            	float avg_score;
            	if(number==0)
            		avg_score=0;
            	else 
            		avg_score=sum/number;         //计算情感值均值,注意！除数不能为零！！！
            	 Connection dbConn=connect_sql.Connect();
    			 try {
    				 Statement stmt =dbConn.createStatement();
    				 String sql=null;
    				 int Has=hasTable.hastable(dbConn,tablename);    /*判断要创建的表是否存在*/
    				 if(Has==0){     //如果不存在此表，则创建
    					   table="create table "+ tablename+" (姓名  varchar(MAX),状态值1  float,状态值2  float,状态值3  float)";
    					   stmt.executeUpdate(table);
    					   sql = "insert into "+ tablename+"  (姓名,状态值1,状态值2,状态值3)" +
    	  			    	        "values('"+Timerclass.person+"','"+avg_score+"',0,0)";  //暂时将分析出来的值放在第一个状态值里
    	  			       stmt.executeUpdate(sql);
    				  }
    				 else{
    					 float Score[];
    					 Score=data_Sort.sort(avg_score,person,dbConn); 
    					 sql="update person_state set 状态值1='"+Score[0]+"',状态值2='"+Score[1]+"',状态值3='"+Score[2]+"' where 姓名='"+person+"'";
    					 stmt.executeUpdate(sql);
    					 Mainframe_Renew.newshow();
    				 }
    			 } catch (Exception e) {
    				e.printStackTrace();
    			 }
            	if(avg_score<-0.4){      //此处暂定
					 warning_name[num]=person;
					 num++;
				}
            }
            Mainframe.analysetype=0;
            Mainframe.Gettype_flag=0;
            if(num>0){     //如果情感有问题的人数大于0，则跳出警告窗口
            	 java.awt.EventQueue.invokeLater(new Runnable() {
                     public void run() {
                         new Warning_frame().setVisible(true);      
                     }
                 });
            }
        }
	}

}
