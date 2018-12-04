/*
 * @作者：李际明
 * @功能：注册用户信息
 */
package com.qq.client.view;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.qq.client.model.ClientUser;
import com.qq.common.User;
public class QqRegister extends JFrame implements ActionListener,MouseListener{
	//定义组件
	private JLabel jl1,jl2;
	private JButton btn1;
	private JTextField uTxt;
	private JPasswordField pTxt;
	//定义切换图片所用数据
	String password;
	 MyJPanel mp;
	    int flag=0;
	    ImageIcon[] imgs = { new ImageIcon("image\\Register\\photo1.jpg"),
	            new ImageIcon("image\\Register\\photo2.jpg"),new ImageIcon("image\\Register\\photo3.jpg")};
	//main函数
	public static void main(String[] args) {
		new QqRegister();
	}
	//构造函数
	public QqRegister(){
		mp = new MyJPanel(flag);
		mp.setBounds(0, 20, 475, 602);
		mp.setOpaque(false);
		setUpFont();
		setTextBack();
		setBtn();
		setText();
		setpTxt();
		this.add(mp);
		this.add(jl1);
		this.add(jl2);
		this.add(btn1);
		this.add(uTxt);
		this.add(pTxt);
		intiRegisterWindows();
		//启动线程对MyJPanel进行重绘
	    new Thread(new Runnable() {
	          public void run() {
	              while(true){
	            	  mp.flag = mp.flag+1; 
	            	  if(mp.flag>=4){
	                	  mp.flag = 1;
	                  }
	                  mp.repaint();
	                  try {
	                      Thread.sleep(2000);
	                  } catch (InterruptedException e) {
	                      e.printStackTrace();
	                  }
	              }  
	          }
	      }).start();
	    this.setVisible(true);
	}	
	//初始化窗体
	public void intiRegisterWindows() {
		this.setSize(1360,720);
		this.setLocation(0, 5);
		this.getContentPane().setBackground(Color.WHITE);
		this.setTitle("用户注册");
		this.setLayout(null);
		this.setResizable(false);	
	}
	//设置输入框上方文字标题
	public JLabel setUpFont(){
		jl2 = new JLabel(new ImageIcon("Image/Register/biaoti.jpg"));
		jl2.setBounds(690, 40, 265,122);
		return jl2;
	}
	//设置输入框背景
	public JLabel setTextBack(){
		jl1 = new JLabel(new ImageIcon("Image/Register/shurukuang.jpg"));
		jl1.setBounds(680, 180, 520,183);
		return jl1;
	}
	//设置注册按钮
	public JButton setBtn(){
		btn1 = new JButton(new ImageIcon("Image/Register/zhuceanniu.jpg"));
		btn1.setBounds(690, 580, 485, 70);
		btn1.setBorderPainted(false);
		btn1.addActionListener(this);
		return btn1;
	}
	//设置账号文本框
	public JTextField setText(){
		uTxt = new JTextField("昵称",50);
		Font font = new Font("宋体",Font.ITALIC,27);
		uTxt.setFont(font);
		uTxt.setForeground(Color.BLACK);
		uTxt.setBounds(705, 213, 470, 40);
		uTxt.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 1));
		uTxt.addMouseListener(this);
		return uTxt;
	}
	//设置密码文本框
	public JPasswordField setpTxt(){
		pTxt = new JPasswordField("密码",50);
		pTxt.setEchoChar((char) 0);
		Font font = new Font("宋体",Font.ITALIC,27);
		pTxt.setFont(font);
		pTxt.setForeground(Color.BLACK);
		pTxt.setBounds(705, 300, 470, 40);
		pTxt.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 1));
		pTxt.addMouseListener(this);
		return pTxt;
	}
	//加载动态图片的JPanel
	class MyJPanel extends JPanel{
	    int flag;
	    public MyJPanel(int flag) {
	        this.flag = flag;
	    }
	    public void paint(Graphics g) {
	        super.paint(g);
	        if(flag == 1){
	            g.drawImage(imgs[0].getImage(), 0, 40,this);
	        }
	        if(flag == 2){
	            g.drawImage(imgs[1].getImage(), 0, 40,this);
	        }
	        if(flag == 3){
	        	g.drawImage(imgs[2].getImage(), 0, 40,this);
	        }
	    }
	}
	//按钮事件处理
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn1){
			try{
				ClientUser clientUser = new ClientUser();
				User user = new User();
				String id;
				//trim()去除字符串中的空格
				user.setType("0");
				user.setName(uTxt.getText().trim());
				user.setPasswd(new String(pTxt.getPassword()));
				id = clientUser.CheckNewUser(user);
				if(id.equals("")){
					JOptionPane.showMessageDialog(this, "注册失败！");
				}else{
					new RegisterTip(id);
				}
			}catch(Exception arg){
				JOptionPane.showMessageDialog(this, "网络连接错误，请检查网络！");
			}	
		}
	}
	public void mouseClicked(MouseEvent e) {
		password = new String(pTxt.getPassword());
		if(e.getClickCount()==1){
			if(uTxt.getText().equals("昵称")){
				uTxt.setText("");
			}else if(password.equals("密码")){
				pTxt.setText("");
				pTxt.setEchoChar('*');
			}
		}
	}
	public void mouseEntered(MouseEvent e) {	
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}	
}


