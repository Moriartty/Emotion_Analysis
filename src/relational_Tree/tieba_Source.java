package relational_Tree;


import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import sql.putIn_Sql;

import cn.edu.hfut.dmic.webcollector.crawler.BreadthCrawler;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;
import filetest.file;

public class tieba_Source extends BreadthCrawler {
	    String recode[]=new String[100];
	    String person;
	    int option=1;
	    public tieba_Source(String crawlPath, boolean autoParse) {
		    super(crawlPath, autoParse);
    	}  
	
		public void visit(Page page, Links arg1) {
	        Elements concern_item=page.getDoc().select("div [class=concern_item]");
	        int size=concern_item.size();
	        int flag1,flag2;
	        int j=0;
	        for(int i=0;i<size;i++){
	        	Element e=concern_item.get(i);
	        	String str = e.toString();
	        	flag1=str.indexOf("un=");
	 	        flag2=str.indexOf("&amp");
	 	        String test=str.substring(flag1+3,flag2);
	 	        if(bianli(test,recode)==0)
			        recode[j++]=test;
	        }
	        String name=page.getDoc().title();
	        file.Createfile(name,recode);
	        putIn_Sql.PutIn_Sql(recode,6,name);
		}
		
		public int bianli(String str,String recode[]){
			int flag=0;
			for(int i=0;recode[i]!=null;i++){
				if(recode[i].contains(str)){
					flag=1;
					break;
				}	
	      	}
			return flag;
		}
		
		public static void main(String[] args) throws Exception {
			tieba_Source crawler=new tieba_Source("crawl",true);
	        crawler.setThreads(1);
	        crawler.addSeed("http://tieba.baidu.com/home/main?un=dd77zxc&ie=utf-8&fr=pb"); 
	        crawler.start(1);
	     }
}
