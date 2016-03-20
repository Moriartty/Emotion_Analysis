package Tieba;

import cn.edu.hfut.dmic.webcollector.crawler.BreadthCrawler;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;
import sql.putIn_Sql;
import filetest.format;

public class TiebaCrawler extends BreadthCrawler {
	String[] recode=new String[100];
	String ti;
	public TiebaCrawler(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
	}

	public void visit(Page page, Links arg1) {
		System.out.println("正在抽取"+page.getUrl());
        String ContentAuthor=page.getDoc().select("div [class=p_author]").text();
        String Content=page.getDoc().select("cc").text();
        String ContentTime=page.getDoc().select("div [class=post-tail-wrap]").text();
        ti=page.getDoc().title();
        Divide(ContentAuthor,Content,ContentTime);
	}
	
	public void Divide(String ContentAuthor,String Content,String ContentTime){
		String temp=null;
		String contentAuthor[]=ContentAuthor.split(" ");
		String content[]=Content.split(" ");
		String contenttime_temp[]=ContentTime.split("楼");
		for(int i=0,j=0;i<content.length&&j<contentAuthor.length;i++,j+=3){
			String contenttime[]=contenttime_temp[i+1].split(" ");
			temp=contentAuthor[j]+"*"+contentAuthor[j+1]+"*"+contentAuthor[j+2]+"*"
		         +content[i]+"*"+contenttime[0]+"*"+contenttime[1];
			recode[i]=format.format_Text(temp);
		}
		String name=format.format_Text(ti);       /*除去表名中的特殊字符*/
		System.out.print(name);
		putIn_Sql.PutIn_Sql(recode,1,name);         /*将数据放入数据库*/
		//file.Put_in(recode,"tieba");           /*将数据写入文件做测试*/
	}
}
