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
	int i=0;        //��¼��ȡ����
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
       	     Element e1 = content.get(i+2);      /*��2 ����Ϊ΢����ʽ����*/
         	 Element e2 = time.get(i);
         	 Time[i]=e2.text();    //ʱ��Ͳ����й�񻯴���
         	 Time[i]=Time[i].replace("?","");
         	 Content[i]=format.format_Text(e1.text());
        }
        person=page.getDoc().title();
        Divide(Time,Content);
        return null;  
    }  
    
    public void Divide(String[] Time,String[]Content){
    	 for(int i=0;Time[i]!=null;i++){
    		 recode[i]=Content[i]+"|"+Time[i];    /*��ȥ�ı��е������ַ�*/
    	 }
    	 String name=format.format_Text(person);   /*��ȥ�����е��ض��ַ�*/
    	 String time1=null,time2=null;
		 if(Mainframe.analysetype==0){      //��������ֶ�ץȡ��״̬����ֱ��д�����ݿ⣬�����з���
		    	 putIn_Sql.PutIn_Sql(recode,2,name); 
		 }
		 else if(Mainframe.analysetype==1){
		    	 name=Timerclass.person+"��΢��";
		    	 time1=format.timeFormat(getData.getfirstdata(name));    //ץԭʼ�������time����
				 time2=format.timeFormat(Time[0]);             //ץ�±������time����
				 if((time1==null||!time1.equals(time2))&&time2!=null){      //�˴���ת���з���,ע��˴����ж�����*******
				    	putIn_Sql.PutIn_Sql(recode,2,name); 
				    	newMessage_analyse.analyse(name);
				 }
		 }
       //  file.Createfile(name,recode);           /*������д���ļ�������*/
    }
}
