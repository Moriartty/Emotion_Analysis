package frameActionPerformed;

import javax.swing.JOptionPane;

import mainframe.Mainframe;

import sql.getData;

public class get_Action {
	public static void getAction(){
		
		String person=null;                                      /*�洢��ַ*/
    	if(Mainframe.Gettype_flag==0)
    		person=Mainframe.name.getText();
    	else if(Mainframe.Gettype_flag==1){
    		if(Mainframe.nametemp!=null)
    			person=getData.GetPerson_info(Mainframe.nametemp,Mainframe.flag);    //�˴���Ҫ����ж�flag>0�����
    	}
    	if(!person.equals(Mainframe.primary)&&Mainframe.flag>0){                  /*get��ť��ִ�ж���*/
    		if(!person.equals("null")){
    			frameActionPerformed.ok_Action.action(person,Mainframe.flag);  
        	    if(Mainframe.Gettype_flag==0)   //���ϵͳ�����Զ�ģʽ״̬�¾Ͳ���������ʾ����
	                 JOptionPane.showMessageDialog(null,"ץȡ����","��ʾ����",JOptionPane.INFORMATION_MESSAGE);
    		}
        }
        else
    	    JOptionPane.showMessageDialog(null,"����Ϊ��","��ʾ",JOptionPane.ERROR_MESSAGE);//��û���е�ַ�����ûѡ������ǣ�����
        Mainframe_Renew.newshow();
        Mainframe.Gettype_flag=0;
	}

}
