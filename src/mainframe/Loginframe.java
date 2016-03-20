package mainframe;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import sql.connect_sql;

public class Loginframe {
	 private javax.swing.JTextField text1;
	 private  JPasswordField text2;
	 JFrame jFrame;
	 
	 public Loginframe() {
        initComponents();
     }
	 private void initComponents() {
		    jFrame = new JFrame("登陆界面");
		    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		    jFrame.setBounds(((int)dimension.getWidth() - 200) / 2, ((int)dimension.getHeight() - 300) / 2, 400, 250);
		    jFrame.setResizable(false);
		    jFrame.setLayout(null);
		    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    
		    JLabel jlpic = new JLabel(); 
		    ImageIcon icon = new ImageIcon("1.jpg");  
		    icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth(),  
		            icon.getIconHeight(), Image.SCALE_DEFAULT));
		    jlpic.setBounds(0, 0, 400, 250);  
		    jlpic.setHorizontalAlignment(0);  
		    jlpic.setIcon(icon); 
		    
		    
		    JLabel label = new JLabel("大连理工大学舆情分析系统");
		    label.setFont(new Font("宋体",Font.BOLD, 20));
		    label.setBounds(75,5,270,40);
		    jFrame.add(label);

		    JLabel label1 = new JLabel("登录名");
		    label1.setBounds(75, 65, 100, 30);
		    jFrame.add(label1);

		    JLabel label2 = new JLabel("密码");
		    label2.setBounds(80, 115, 100, 30);
		    jFrame.add(label2);

		    text1 = new JTextField();
		    text1.setBounds(120, 70, 170, 20);
		    jFrame.add(text1);

		    text2 = new JPasswordField();
		    text2.setBounds(120, 120, 170, 20);
		    jFrame.add(text2);

		    JButton button = new JButton("登      陆");
		    button.setBounds(140, 170, 120, 30);
		   
		    text1.addKeyListener(new KeyAdapter(){
		           public void keyPressed(KeyEvent event)
		           {
		        	   KeyPressed(event);
		           } 
		     });
		    text2.addKeyListener(new KeyAdapter(){
		          public void keyPressed(KeyEvent event)
		          {
		        	   KeyPressed(event);
		          }  
		    });
		    jFrame.addKeyListener(new KeyAdapter(){
		          public void keyPressed(KeyEvent event)
		          {
		        	   KeyPressed(event);
		          }  
		    });
		    
		    button.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	connect_sql.userName=text1.getText();
		    	connect_sql.userPwd=text2.getText();
		    	Connection dbConn=connect_sql.Connect();
		    	if(dbConn!=null) {
		    		 Login();  
		    		 jFrame.dispose();
		    	}
		    	else {
		            JOptionPane.showMessageDialog(null, "错误", "提示", JOptionPane.ERROR_MESSAGE);
		            text1.setText("");
		            text2.setText("");
		        }
		        }
		   });
		   jFrame.add(button);
		   jFrame.add(jlpic);
		   jFrame.setVisible(true);
		   jFrame.setLocationRelativeTo(null);  
	}
	
	public static void Login(){
		 try {
	            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	                if ("Nimbus".equals(info.getName())) {
	                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                    break;
	                }
	            }
	        } catch (ClassNotFoundException ex) {
	            java.util.logging.Logger.getLogger(Mainframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (InstantiationException ex) {
	            java.util.logging.Logger.getLogger(Mainframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (IllegalAccessException ex) {
	            java.util.logging.Logger.getLogger(Mainframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	            java.util.logging.Logger.getLogger(Mainframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        }

	        /* Create and display the form */
	        java.awt.EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                new Mainframe().setVisible(true);
	            }
	        });
	  }
	
	  private void KeyPressed(java.awt.event.KeyEvent event){
    	if (event.getKeyCode() == KeyEvent.VK_ENTER) {  
          	 connect_sql.userName=text1.getText();
             connect_sql.userPwd=text2.getText();  
             Connection dbConn=connect_sql.Connect();
             if(dbConn!=null) {
              	  Login();
              	  jFrame.dispose();
             }
             else {
           	  JOptionPane.showMessageDialog(null, "登录名或密码错误，请重试！", "提示", JOptionPane.ERROR_MESSAGE);
                  text1.setText("");
                  text2.setText("");
             }
        }
      }
	
 public static void main(String[] args) {
    new Loginframe();
 }
} 
