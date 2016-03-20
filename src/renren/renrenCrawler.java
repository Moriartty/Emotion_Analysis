package renren;



import java.sql.Connection;
import java.sql.Statement;

import mainframe.Mainframe;

import org.jsoup.select.Elements;

import Auto_analyse.Timerclass;
import Auto_analyse.newMessage_analyse;

import sql.connect_sql;
import sql.getData;
import sql.hasTable;
import sql.putIn_Sql;

import cn.edu.hfut.dmic.webcollector.crawler.BreadthCrawler;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;
import filetest.format;


public class renrenCrawler extends BreadthCrawler {
	String recode[]=new String[1000];

	public renrenCrawler(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
		 @SuppressWarnings("unused")
		RenRenNotify notify = new RenRenNotify("13889446741","313569773b"); 
	}
	
	public void visit(Page page, Links arg1) {
		//System.out.println(page.getUrl());
		Elements content=page.getDoc().select("div[class=list]");
		Elements time=page.getDoc().select("p[class=gs]");
		int size=time.size();
		String text=content.text();
		String Time[]=new String[100];
		for(int i=0;i<size;i++){
			text=text.replaceAll(time.get(i).text(),"*");
		}
	    String temp[]=text.split("\\*");
	    for(int i=0;i<temp.length-1;i++){
	    	Time[i]=time.get(i).text();
	    	recode[i]=format.format_Text(temp[i])+"+"+Time[i];
	    }
    //    String person=page.getDoc().title();
     //   String name=format.format_Text(person);
	    String name=Timerclass.person+"的人人网";
        String time1=null,time2=null;
	    if(Mainframe.analysetype==0){      //如果处于手动抓取的状态，则直接写入数据库，不进行分析
	    	putIn_Sql.PutIn_Sql(recode,4,name); 
	    }
	    else if(Mainframe.analysetype==1){
	    	 time1=getData.getfirstdata(name);    //抓原始表的最新time数据
			 time2=Time[0];             //抓新表的最新time数据
			 if((time1==null||!time1.equals(time2))&&time2!=null){      //此处跳转进行分析
			    	putIn_Sql.PutIn_Sql(recode,4,name); 
			    	newMessage_analyse.analyse(name);
			 }
	    }
       // file.Createfile("renren",recode);
	}

}
