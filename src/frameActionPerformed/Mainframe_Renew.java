package frameActionPerformed;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import filetest.deletefile;

import mainframe.Mainframe;
import mainframe.Showframe;

import sql.Delete_table;
import sql.connect_sql;
import sql.getData;
import sql.get_Tablenames;
import sql.hasTable;
import sql.table_Show;

public class Mainframe_Renew {
	
	 public static void delete(){
		 Delete_table.delete_table(Get_Listname.getListname());
       	 try { 
          	Connection dbConn=connect_sql.Connect();
  			int Has=hasTable.hastable(dbConn,"Person_State");
  			if(Has==1)
  				JOptionPane.showMessageDialog(null,"ɾ���ɹ�","��ʾ����",JOptionPane.INFORMATION_MESSAGE);
  		} catch (Exception e) {
  			e.printStackTrace();
  		}  
      	Mainframe_Renew.newshow();
	 }
	 
	 public static void newshow(){
	    	String Name[]=get_Tablenames.getTablename();
	    	String tieba_name[]=new String[512];
	    	String weibo_name[]=new String[512];
	    	String renren_name[]=new String[512];
	    	int a=0,b=0,c=0;
	    	for(int i=0;Name[i]!=null;i++){
	    		if(Name[i].contains("����"))
	    			tieba_name[a++]=Name[i];
	    		else if(Name[i].contains("΢��"))
	    			weibo_name[b++]=Name[i];
	    		else if(Name[i].contains("������"))
	    			renren_name[c++]=Name[i];
	    	}
	    	   Mainframe.listModel1 = new DefaultListModel<String>();  //����ʹ�� 
	           for(int i=0;tieba_name[i]!=null;i++)
	        	   Mainframe.listModel1.addElement(tieba_name[i]);
	           
	           Mainframe.list_tieba = new JList<String>(Mainframe.listModel1);
	           Mainframe.list_tieba.addListSelectionListener(new ListSelectionListener() {
	               public void valueChanged(ListSelectionEvent e) {
	            	   Mainframe.listflag=1;
	               }
	           });
	           MouseListener mouseListener = new MouseAdapter() {
	               public void mouseClicked(MouseEvent mouseEvent) {
	                 if (mouseEvent.getClickCount() == 2) {
	                	 new Showframe().setVisible(true);        //˫����ʾ
	                 }
	               }
	             };
	           Mainframe.list_tieba.addMouseListener(mouseListener);
	           
	           Mainframe.list_tieba.addKeyListener(new KeyAdapter(){
	                 public void keyPressed(KeyEvent event)
	                 {
	                	 if (event.isControlDown() && event.getKeyCode() == KeyEvent.VK_D){
	                    	delete();
	                      }
	                  }     
	             });
	        Mainframe.jScrollPane2.setViewportView(Mainframe.list_tieba);
	        
	        Mainframe.listModel2 = new DefaultListModel<String>();  //΢��ʹ��
	           for(int i=0;weibo_name[i]!=null;i++)
		            Mainframe.listModel2.addElement(weibo_name[i]);
	           Mainframe.list_weibo = new JList<String>(Mainframe.listModel2);
	           Mainframe.list_weibo.addListSelectionListener(new ListSelectionListener() {
	               public void valueChanged(ListSelectionEvent e) {
	            	   Mainframe.listflag=2;
	               }
	           });
	           Mainframe.list_weibo.addMouseListener(mouseListener);        //˫�����в鿴
	           Mainframe.list_weibo.addKeyListener(new KeyAdapter(){
	                 public void keyPressed(KeyEvent event)
	                 {
	                	 if (event.isControlDown() && event.getKeyCode() == KeyEvent.VK_D){
	                    	delete();
	                      }
	                  }     
	             });
	        Mainframe.jScrollPane3.setViewportView(Mainframe.list_weibo);
	        
	        Mainframe.listModel3 = new DefaultListModel<String>();  //����ʹ��
	           for(int i=0;renren_name[i]!=null;i++)
		            Mainframe.listModel3.addElement(renren_name[i]);
	           Mainframe.list_renren = new JList<String>(Mainframe.listModel3);
	           Mainframe.list_renren.addListSelectionListener(new ListSelectionListener() {
	               public void valueChanged(ListSelectionEvent e) {
	            	   Mainframe.listflag=3;
	               }
	           });
	           Mainframe.list_renren.addMouseListener(mouseListener);        //˫�����в鿴
	           Mainframe.list_renren.addKeyListener(new KeyAdapter(){
	                 public void keyPressed(KeyEvent event)
	                 {
	                	 if (event.isControlDown() && event.getKeyCode() == KeyEvent.VK_D){
	                    	delete();
	                      }
	                  }     
	             });
	        Mainframe.jScrollPane4.setViewportView(Mainframe.list_renren);
	        
	        if(Mainframe.hasData==1){
	        	String name="Person_State";          //��ȡlist�б�ָ������name
	        	DefaultTableModel myTable=table_Show.Show(name);  /*�������ݿ⣬���ƶ���Ϣ����table*/
			    Mainframe.list_collect=new JTable(myTable);
			    Mainframe.jScrollPane1.setViewportView(Mainframe.list_collect);
	        }
	        
	    }

}
