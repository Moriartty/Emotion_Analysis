package frameActionPerformed;

import mainframe.Mainframe;
import mainframe.Warning_frame;

public class Get_Listname {
	public static String getListname(){
   	 int get=Mainframe.listflag;
     	 String name=null;
     	 Object valueList[]=null;
     	 if(get==1)
     		  valueList=Mainframe.list_tieba.getSelectedValues();
     	 else if(get==2)
     		  valueList=Mainframe.list_weibo.getSelectedValues();
     	 else if(get==3)
     		  valueList=Mainframe.list_renren.getSelectedValues();
     	 else if(get==4)
   		      valueList=Warning_frame.warningList.getSelectedValues();
     	 for(int i=0;i<valueList.length;i++){
     	     name=(String)valueList[i];
     	 }    
     	 return name;
     }
	

}
