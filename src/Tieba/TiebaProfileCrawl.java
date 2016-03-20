package Tieba;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.bcel.generic.RETURN;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Auto_analyse.Timerclass;
import Auto_analyse.newMessage_analyse;

import mainframe.Mainframe;
import sql.connect_sql;
import sql.getData;
import sql.hasTable;
import sql.putIn_Sql;

import cn.edu.hfut.dmic.webcollector.crawler.BreadthCrawler;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;
import filetest.format;

public class TiebaProfileCrawl extends BreadthCrawler {
	    String recode[]=new String[100];
	    String person;
	    int option=1;
	    public TiebaProfileCrawl(String crawlPath, boolean autoParse) {
		    super(crawlPath, autoParse);
    	}  
	
		public void visit(Page page, Links arg1) {
			 Elements time=page.getDoc().select("div [class=n_post_time]");
		     Elements reply_content=page.getDoc().select("div [class=thread_name]");
		     Elements titletxt=page.getDoc().select("div [class=titletxt]");
		     Elements position=page.getDoc().select("div [class=n_name]");
			 int size=titletxt.size();
			 String Time[]=new String[50];
			 String Reply_content[]=new String[50];
			 String Titletxt[]=new String[50];
			 String Position[]=new String[50];
			 for(int i=0;i<size;i++){
				 Time[i]=time.get(i).text();
				 Reply_content[i]=reply_content.get(i).text();
				 Titletxt[i]=titletxt.get(i).text();
				 Position[i]=position.get(i).text();
			 }
	        person=page.getDoc().title();
	        Divide(Time,Reply_content,Titletxt,Position); 
		}
		
		public void Divide(String[] Time,String[] Reply_content,String[] Titletxt,String[] Position){
			for(int i=0;Time[i]!=null;i++){
				recode[i]=Time[i]+"*"+Titletxt[i]+"*"+Reply_content[i]+"*"+Position[i];
			}
		    String time1=null,time2=null;
		    String name=format.format_Text(person);
		    if(Mainframe.analysetype==0){      //如果处于手动抓取的状态，则直接写入数据库，不进行分析
		    	putIn_Sql.PutIn_Sql(recode,3,name); 
		    }
		    else if(Mainframe.analysetype==1){
		    	 name=Timerclass.person+"的贴吧";
		    	 time1=getData.getfirstdata(name);    //抓原始表的最新time数据
				 time2=Time[0];             //抓新表的最新time数据
				 if((time1==null||!time1.equals(time2))&&time2!=null){      //此处跳转进行分析
				    	putIn_Sql.PutIn_Sql(recode,3,name); 
				    	newMessage_analyse.analyse(name);
				 }
		    }
		   // file.Put_in(recode,"tieba");
		}
		
}
