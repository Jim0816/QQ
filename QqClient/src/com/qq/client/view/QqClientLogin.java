/*
 * @author:李际明
 * @function:用户登录界面
 */
package com.qq.client.view;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.qq.client.model.ClientUser;
import com.qq.common.User;
public class QqClientLogin extends JFrame implements ActionListener,MouseListener{
	//定义组件
	private JLabel jl1;
	private JTextField uTxt;
	private JPasswordField pTxt;
	private JButton btn2,btn3,close_jb,min_jb;
	private JPanel jp1,jp2;
	JLayeredPane layeredPane;
	//定义所用变量
	int xOld,yOld;	
	//Image image;
	String password;
	//main函数
	public static void main(String[] args) {
		new QqClientLogin();
	}
	//构造方法
	public QqClientLogin() {
		ImageIcon background = new ImageIcon("image\\Login\\denglubg.jpg");
		layeredPane = new JLayeredPane(); 
		intiLoginWindows();
		intiLabel();
		JFrameNoBorder(background);
		setInputGround();
		setJBtn2();
		setJBtn3();
		//监听
		uTxt.addMouseListener(this);
		pTxt.addMouseListener(this);
		//添加各组件到分层面板
		layeredPane.add(jl1,JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(jp1,JLayeredPane.MODAL_LAYER);
		layeredPane.add(btn3,JLayeredPane.MODAL_LAYER);
		layeredPane.add(btn2,JLayeredPane.MODAL_LAYER);
		layeredPane.add(close_jb,JLayeredPane.MODAL_LAYER);
		layeredPane.add(min_jb,JLayeredPane.MODAL_LAYER);
		this.setLayeredPane(layeredPane);
		this.setVisible(true);
	}
	//初始化窗体
	public void intiLoginWindows() {
		this.setSize(451,325);
		this.setIconImage(new ImageIcon("image\\Login\\tubiao.png").getImage());
		this.setTitle("用户登录");
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);	
	}
	//设置背景
	public JLabel intiLabel() {
		jl1=new JLabel(new ImageIcon("image\\Login\\denglubg.jpg"));
		jl1.setBounds(0, 0, 451, 297);
		return jl1;
	}
	//处理边框方法
	public void JFrameNoBorder(ImageIcon img) { 
		close_jb = new JButton(new ImageIcon("image\\Login\\close.jpg"));
		close_jb.setBounds(403, 60, 20, 20);
		close_jb.setFocusPainted(false);
		close_jb.setBorderPainted(false);
		close_jb.addActionListener(this);
		min_jb = new JButton(new ImageIcon("image\\Login\\min.jpg"));
		min_jb.setBounds(381, 61, 20, 20);
		min_jb.setFocusPainted(false);
		min_jb.setBorderPainted(false);
		min_jb.addActionListener(this);
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		img.setImageObserver(null);
		this.setSize(( img).getIconWidth(),img.getIconHeight()); 
		//处理拖动事件---去掉默认边框后，不能拖动了，具体实现如下
		this.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
		    xOld = e.getX();//记录鼠标按下时的坐标
		    yOld = e.getY();
		   }
		  });
		this.addMouseMotionListener(new MouseMotionAdapter() {
		public void mouseDragged(MouseEvent e) {
		int xOnScreen = e.getXOnScreen();
		int yOnScreen = e.getYOnScreen();
		int xx = xOnScreen - xOld;
		int yy = yOnScreen - yOld;
		QqClientLogin.this.setLocation(xx, yy);//设置拖拽后，窗口的位置
		    }
		   });
		   this.setUndecorated(true);
		 }
	//设置文本框和密码框构成的面板jp1
	public JPanel setInputGround(){
		uTxt= new JTextField("请输入帐号",20);
		uTxt.setForeground(Color.BLACK);
		uTxt.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 0));
		uTxt.setOpaque(false);
		pTxt= new JPasswordField("请输入密码",20);
		pTxt.setForeground(Color.BLACK);
		pTxt.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 0));
		pTxt.setEchoChar((char) 0);
		pTxt.setOpaque(false);
		jp1 = new JPanel(new GridLayout(2,1,10,19));     
		jp1.setBounds(143, 135, 175, 68);
		jp1.setOpaque(false);
		jp1.add(uTxt);
		jp1.add(pTxt);
		return jp1;
	}
	//设置登录按钮
	public JButton setJBtn3(){
		btn3=new JButton(new ImageIcon("image\\Login\\dengluanniu.jpg"));
		btn3.setBorderPainted(false);
		btn3.setBounds(330, 168, 68, 39);
		btn3.addActionListener(this);
		return btn3;
	}
	//设置注册按钮
	public JButton setJBtn2() {
		btn2=new JButton(new ImageIcon("image\\Login\\zhuce.jpg"));
		btn2.setBounds(338, 258, 54, 28);
		btn2.setBorderPainted(false);
		btn2.addActionListener(this);
		return btn2;
	}
	//按钮事件处理
	public void actionPerformed(ActionEvent e) {
		//注册
		if(e.getSource()==btn2){
			this.dispose();
			new QqRegister();
		}
		//登录
	    if(e.getSource()==btn3){
	    	try{
	    		ClientUser clientUser = new ClientUser();
				User user = new User();
				//trim()去除字符串中的空格
				user.setUserId(uTxt.getText().trim());
				user.setPasswd(new String(pTxt.getPassword()));
				user.setState("true");
				user.setType("1");
				User u = clientUser.CheckUser(user);
				//登录成功
				if(u.getState().equals("true")){
					this.dispose();
					QqFriendList qqList = new QqFriendList(u);
					//new Thread(qqList).start();
				}
				//信息错误导致登录失败
				else if(u.getState().equals("false")){
					this.dispose();
					new LoginErrorTip();
				}			
	    	}catch(Exception ep){
	    		JOptionPane.showMessageDialog(this, "网络连接错误，请检查网络！");
	    	}
		}
	  //界面控制按钮
		if(e.getSource()==close_jb)
		{
			this.dispose();
		}
		if(e.getSource()==min_jb)
		{
			this.setExtendedState(JFrame.ICONIFIED);
		}
	}
	//鼠标事件处理(输入框初始化字体自动消失)
	public void mouseClicked(MouseEvent e) {
		password = new String(pTxt.getPassword());
		if(e.getClickCount()==1){
			if(uTxt.getText().equals("请输入帐号")){
				uTxt.setText("");
			}else if(password.equals("请输入密码")){
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
