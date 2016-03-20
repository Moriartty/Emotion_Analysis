package Auto_analyse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.json.JSONException;

import com.mashape.unirest.http.exceptions.UnirestException;

import emotion_analysis.SentimentApiExample;
import filetest.deletefile;
import mainframe.Mainframe;
import mainframe.Showframe;
import sql.connect_sql;
import sql.getData;
import sql.hasTable;

public class newMessage_analyse {
	public static void analyse(String name){
		Showframe.flag=1;         //ֻ�������µ�һ��״̬
		getData.Getsql_data(name); 
		SentimentApiExample sentimentAnalyse=new SentimentApiExample();    /*������������*/
		float score=0;
       	try {
			try {
				Timerclass.number++;
				score = SentimentApiExample.analyse(name);
				System.out.print(score);
				Timerclass.sum=Timerclass.sum+score;
				deletefile.Delete(name);     //ɾ���ļ�
			} catch (JSONException e1){
				e1.printStackTrace();
			} catch (UnirestException e1) {
				e1.printStackTrace();
			}
   		 } catch (IOException e) {
   			e.printStackTrace();
   		 }
		
	}

}
