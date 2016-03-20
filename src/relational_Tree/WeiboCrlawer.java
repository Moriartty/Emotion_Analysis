package relational_Tree;


import org.jsoup.select.Elements;

import cn.edu.hfut.dmic.webcollector.crawler.DeepCrawler;  
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequesterImpl;

import sql.putIn_Sql;
import weibo.WeiboCN;
import filetest.format;

public class WeiboCrlawer extends DeepCrawler{
	static String recode[]=new String[1000];
	String person;
    public WeiboCrlawer(String crawlPath) throws Exception {
        super(crawlPath);  
        String cookie=WeiboCN.getSinaCookie("2992821771@qq.com", "moriarty");
        HttpRequesterImpl myRequester=(HttpRequesterImpl) this.getHttpRequester();  
        myRequester.setCookie(cookie); 
        //Search.getSinaCookie("Mr埃文");
    }  
    public Links visitAndGetNextLinks(Page page) {  
        Elements test=page.getDoc().select("div");
        System.out.print(test);
        return null;  
    }  

  
    
    public void Divide(){
    	 String name=format.format_Text(person);   /*除去名字中的特定字符*/
    	 putIn_Sql.PutIn_Sql(recode,2,name);         /*将数据放入数据库*/
       //  file.Createfile(name,recode);           /*将数据写入文件做测试*/
    }
    
    public static void main(String[] args) throws Exception{  
        WeiboCrlawer crawler=new WeiboCrlawer("/download");  
        crawler.setThreads(2);  
        crawler.addSeed("http://weibo.cn/search/?pos=search");   
        crawler.start(1);  
    }
}
