package frameActionPerformed;

import renren.renrenCrawler;
import weibo.WeiboCrlawer;
import Tieba.TiebaCrawler;
import Tieba.TiebaProfileCrawl;

public class ok_Action {
	 public static int analyse(String str){      /*判断是抓个人还是抓全体*/
	    	if(str.contains("http"))
	    		return 0;
	    	else return 1;
	    }
	 
	 public static void action(String person ,int flag){
	 	 try{
	          if(flag==1){           /*贴吧*/
	        	  int mark=analyse(person);             
	        	  if(mark==1){       /*个人贴吧主页*/
	        		  String URL="http://tieba.baidu.com/home/main?un="+person+"&ie=utf-8&fr=pb";
	        		  TiebaProfileCrawl crawler=new TiebaProfileCrawl("D:\\Project\\crawl",true);
	      	          crawler.setThreads(1);
	      	          crawler.addSeed(URL);
	      	          crawler.start(1);
	        	  }
	        	  else{            /*一个帖子的内容*/
	        		   TiebaCrawler crawler=new TiebaCrawler("D:\\Project\\crawl",true);
                       crawler.setThreads(1);
                       crawler.addSeed(person);
                   //  crawler.addSeed("http://tieba.baidu.com/p/4057575757");
                       crawler.start(1);
	        	  }
	          }
	          
	          else if(flag==2){           /*微博*/
	        	  WeiboCrlawer crawler=new WeiboCrlawer("D:\\Project\\crawl");  
	              crawler.setThreads(1);      /*微博还是需要输入地址*/
	             // String URL="http://weibo.cn/mayun?c=spr_qdhz_bd_baidusmt_weibo_s&nick="+person+"&page=";
	              crawler.addSeed(person);
	             // crawler.addSeed("http://weibo.cn/mayun?c=spr_qdhz_bd_baidusmt_weibo_s&nick=%E9%9D%92%E6%98%A5%E5%A6%82%E6%AD%8C-%E4%B9%A1%E6%9D%91%E6%95%99%E5%B8%88%E4%BB%A3%E8%A8%80%E4%BA%BA-%E9%A9%AC%E4%BA%91&page=");
	              crawler.start(1);  
	          }
	          
	          else if(flag==3){           /*人人*/
	        	 renrenCrawler crawler=new renrenCrawler("D:\\Project\\crawl",true);
	     	     crawler.setThreads(1);
	     	     crawler.addSeed(person);
	     	   //  crawler.addSeed("http://3g.renren.com/profile.do?id=312917188&htf=740&sid=Xov_pR4BvJbGX6Le4pb6r2&ndxqj7"); 
	     	     crawler.start(1);
	          }
	          
          }catch(Exception e1){
                e1.printStackTrace();
         }
		
	}
}
