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
	static int num=0;        //�������������
	static int number=0;     //ÿ����һ����������״̬��λ����
	static float sum=0;        //�����ֵ
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
            		avg_score=sum/number;         //�������ֵ��ֵ,ע�⣡��������Ϊ�㣡����
            	 Connection dbConn=connect_sql.Connect();
    			 try {
    				 Statement stmt =dbConn.createStatement();
    				 String sql=null;
    				 int Has=hasTable.hastable(dbConn,tablename);    /*�ж�Ҫ�����ı��Ƿ����*/
    				 if(Has==0){     //��������ڴ˱��򴴽�
    					   table="create table "+ tablename+" (����  varchar(MAX),״ֵ̬1  float,״ֵ̬2  float,״ֵ̬3  float)";
    					   stmt.executeUpdate(table);
    					   sql = "insert into "+ tablename+"  (����,״ֵ̬1,״ֵ̬2,״ֵ̬3)" +
    	  			    	        "values('"+Timerclass.person+"','"+avg_score+"',0,0)";  //��ʱ������������ֵ���ڵ�һ��״ֵ̬��
    	  			       stmt.executeUpdate(sql);
    				  }
    				 else{
    					 float Score[];
    					 Score=data_Sort.sort(avg_score,person,dbConn); 
    					 sql="update person_state set ״ֵ̬1='"+Score[0]+"',״ֵ̬2='"+Score[1]+"',״ֵ̬3='"+Score[2]+"' where ����='"+person+"'";
    					 stmt.executeUpdate(sql);
    					 Mainframe_Renew.newshow();
    				 }
    			 } catch (Exception e) {
    				e.printStackTrace();
    			 }
            	if(avg_score<-0.4){      //�˴��ݶ�
					 warning_name[num]=person;
					 num++;
				}
            }
            Mainframe.analysetype=0;
            Mainframe.Gettype_flag=0;
            if(num>0){     //���������������������0�����������洰��
            	 java.awt.EventQueue.invokeLater(new Runnable() {
                     public void run() {
                         new Warning_frame().setVisible(true);      
                     }
                 });
            }
        }
	}

}
