package frameActionPerformed;

import javax.swing.JOptionPane;

import mainframe.Mainframe;

import sql.getData;

public class get_Action {
	public static void getAction(){
		
		String person=null;                                      /*存储地址*/
    	if(Mainframe.Gettype_flag==0)
    		person=Mainframe.name.getText();
    	else if(Mainframe.Gettype_flag==1){
    		if(Mainframe.nametemp!=null)
    			person=getData.GetPerson_info(Mainframe.nametemp,Mainframe.flag);    //此处需要添加判断flag>0的语句
    	}
    	if(!person.equals(Mainframe.primary)&&Mainframe.flag>0){                  /*get按钮的执行动作*/
    		if(!person.equals("null")){
    			frameActionPerformed.ok_Action.action(person,Mainframe.flag);  
        	    if(Mainframe.Gettype_flag==0)   //如果系统处于自动模式状态下就不用跳出提示框了
	                 JOptionPane.showMessageDialog(null,"抓取结束","提示窗口",JOptionPane.INFORMATION_MESSAGE);
    		}
        }
        else
    	    JOptionPane.showMessageDialog(null,"不能为空","提示",JOptionPane.ERROR_MESSAGE);//当没进行地址输入或没选择对象是，报错
        Mainframe_Renew.newshow();
        Mainframe.Gettype_flag=0;
	}

}
