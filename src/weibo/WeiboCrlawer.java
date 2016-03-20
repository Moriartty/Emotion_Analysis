package weibo;


import java.sql.Connection;
import java.sql.Statement;

import javax.swing.text.AbstractDocument.Content;

import mainframe.Mainframe;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Auto_analyse.Timerclass;
import Auto_analyse.newMessage_analyse;

import cn.edu.hfut.dmic.webcollector.crawler.DeepCrawler;  
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequesterImpl;

import sql.connect_sql;
import sql.getData;
import sql.hasTable;
import sql.putIn_Sql;
import filetest.file;
import filetest.format;

public class WeiboCrlawer extends DeepCrawler{
	static String recode[]=new String[1000];
	String person;
	int i=0;        //记录爬取次数
    public WeiboCrlawer(String crawlPath) throws Exception {
    	super(crawlPath); 
    	if(i==0){
    	      String cookie=WeiboCN.getSinaCookie("2992821771@qq.com", "moriarty");
    	      HttpRequesterImpl myRequester=(HttpRequesterImpl) this.getHttpRequester();  
    	      myRequester.setCookie(cookie);  
    	}
    }  

    public Links visitAndGetNextLinks(Page page) {  
    	i++;
        Elements content=page.getDoc().select("span[class=ctt]");
        Elements time=page.getDoc().select("span[class=ct]");
        int size2=time.size();
        String Time[]=new String[50];
        String Content[]=new String[50];
        for(int i=0;i<size2;i++){
       	     Element e1 = content.get(i+2);      /*加2 是因为微博格式问题*/
         	 Element e2 = time.get(i);
         	 Time[i]=e2.text();    //时间就不进行规格化处理
         	 Time[i]=Time[i].replace("?","");
         	 Content[i]=format.format_Text(e1.text());
        }
        person=page.getDoc().title();
        Divide(Time,Content);
        return null;  
    }  
    
    public void Divide(String[] Time,String[]Content){
    	 for(int i=0;Time[i]!=null;i++){
    		 recode[i]=Content[i]+"|"+Time[i];    /*除去文本中的敏感字符*/
    	 }
    	 String name=format.format_Text(person);   /*除去名字中的特定字符*/
    	 String time1=null,time2=null;
		 if(Mainframe.analysetype==0){      //如果处于手动抓取的状态，则直接写入数据库，不进行分析
		    	 putIn_Sql.PutIn_Sql(recode,2,name); 
		 }
		 else if(Mainframe.analysetype==1){
		    	 name=Timerclass.person+"的微博";
		    	 time1=format.timeFormat(getData.getfirstdata(name));    //抓原始表的最新time数据
				 time2=format.timeFormat(Time[0]);             //抓新表的最新time数据
				 if((time1==null||!time1.equals(time2))&&time2!=null){      //此处跳转进行分析,注意此处的判断条件*******
				    	putIn_Sql.PutIn_Sql(recode,2,name); 
				    	newMessage_analyse.analyse(name);
				 }
		 }
       //  file.Createfile(name,recode);           /*将数据写入文件做测试*/
    }
}
