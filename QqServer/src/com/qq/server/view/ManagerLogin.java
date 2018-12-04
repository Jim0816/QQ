/*
 * @作者：李际明
 * @功能：管理员登录界面
 */
package com.qq.server.view;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.qq.server.database.Dao;
import com.qq.server.database.DaoImplement;
public class ManagerLogin extends JFrame implements MouseListener{
	//背景图层
	ImageIcon background;
	JPanel buttom;
	JLabel imgLabel;
	//定义组件
	private JButton btn1,btn2;
	private JTextField text1,text3;
	private JPasswordField text2;
	//定义数据
	int xOld,yOld;
	Dao dao = new DaoImplement();
	public static void main(String[] args) {
		ManagerLogin manager = new ManagerLogin();
	}
	//构造函数
	public ManagerLogin(){
		backGround();
		JFrameNoBorder();
		setBtn();
		setText();
		intiWindows();
	}
	//初始化窗体
	public void intiWindows() {
		this.setSize(943,449);
		this.setLayout(null);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.WHITE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	//处理背景方法
	public void backGround()
	{
		background = new ImageIcon("image1/ManagerLogin/bg.jpg");
		imgLabel = new JLabel(background);
		imgLabel.setBounds(0, 0,  background.getIconWidth(), background.getIconHeight());
		buttom=(JPanel)this.getContentPane();
		//将contentPane设置为透明的
		buttom.setOpaque(false);
		this.getLayeredPane().add(imgLabel , new Integer(Integer.MIN_VALUE));
	}
	//处理边框方法
	public void JFrameNoBorder() { 
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
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
		ManagerLogin.this.setLocation(xx, yy);//设置拖拽后，窗口的位置
		    }
		   });
		   this.setUndecorated(true);
		 }
	//处理关闭和登录按钮
	public void setBtn(){
		//登录
		btn1 = new JButton(new ImageIcon("image1/ManagerLogin/denglu.jpg"));
		btn1.setBounds(380, 280, 66, 34);
		btn1.addMouseListener(this);
		this.add(btn1);
		//关闭
		btn2 = new JButton(new ImageIcon("image1/ManagerLogin/close.jpg"));
		btn2.setBounds(913, 0, 27, 25);
		btn2.setBorderPainted(false);
		btn2.addMouseListener(this);
		this.add(btn2);
	}
	//设置中间文本框区域
	public void setText(){
		text1 = new JTextField("admin",25);
		text1.setBounds(380,151,210,22);
		text1.setForeground(Color.black);
		text1.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 0));
		text1.addMouseListener(this);
		this.add(text1);
		text2 = new JPasswordField("password",25);
		text2.setEchoChar((char) 0);
		text2.setBounds(380,187,210,22);
		text2.setForeground(Color.black);
		text2.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 0));
		text2.addMouseListener(this);
		this.add(text2);
		text3 = new JTextField("请输入验证码",20);
		text3.setBounds(380,223,117,24);
		text3.setForeground(Color.black);
		text3.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 0));
		text3.addMouseListener(this);
		this.add(text3);
	}
	//事件处理
	public void mouseClicked(MouseEvent e) {
		//登录
		if(e.getClickCount()==1&&e.getSource()==btn1){
			if(text3.getText().equals("")){
				text3.setBorder(javax.swing.BorderFactory.createLineBorder(Color.RED, 1));
			}else{
				if(text3.getText().equals("3frm")||text3.getText().equals("3FRM")){
					String id = text1.getText();
					String password = new String(text2.getPassword());
					String dataId = dao.getManager(id).getId();
					String dataPwd = dao.getManager(id).getPassword();
					if(id.equals(dataId)&&password.equals(dataPwd)){
						this.dispose();
						Object[][] tableData = dao.getAllRecord(); 
						new ServerManager(tableData,id);
					}else{
						JOptionPane.showMessageDialog(this, "登录信息有误，请重新输入！");
						}	
				}else{
					text3.setBorder(javax.swing.BorderFactory.createLineBorder(Color.RED, 1));
				}
			}
		}
		//关闭
        if(e.getClickCount()==1&&e.getSource()==btn2){
			this.dispose();
		}
        //清空点击文本框
        if(e.getClickCount()==1&&e.getSource()==text1){
        	if(text1.getText().equals("admin")){
        		text1.setText("");
        	}	
        }
        if(e.getClickCount()==1&&e.getSource()==text2){
        	String password = new String(text2.getPassword());
        	if(password.equals("password")){
        		text2.setText("");
				text2.setEchoChar('*');
        	}
        }
        if(e.getClickCount()==1&&e.getSource()==text3){
        	if(text3.getText().equals("请输入验证码")){
        		text3.setText("");
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
