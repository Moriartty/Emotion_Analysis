package filetest;

public class format {         /*格式化表名*/
	public static String format_Text(String str){
		String title=str;
		title=title.replace("!","");
		title=title.replace("！","");
		title=title.replace("-","");
		title=title.replace("=","");
		title=title.replace("+","");
		title=title.replace("？","");
		title=title.replace("?","");
		title=title.replace("。","");
		title=title.replace("°","");
		title=title.replace("'","");
		title=title.replace(" ","");
		title=title.replace("，","");
		title=title.replace("_","");
		title=title.replace("_","");
		title=title.replace("1","");
		title=title.replace("2","");
		title=title.replace("3","");
		title=title.replace("4","");
		title=title.replace("5","");
		title=title.replace("6","");
		title=title.replace("7","");
		title=title.replace("8","");
		title=title.replace("9","");
		title=title.replace("0","");
		title=title.replace("[","");
		title=title.replace("]","");
		return title;
	}
	
	public static String timeFormat(String temp){
		if(temp==null)
			return null;
		else{
			 String s2;
			 char c[]=temp.toCharArray();
			 char C[]=new char[12];
			 for(int i=0;i<12;i++)
				C[i]=c[i];
			 s2=new String(C);
			 return s2;
		}
	}
}
