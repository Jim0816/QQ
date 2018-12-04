package com.qq.server.view;
import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JTextField;
import com.qq.common.LoginRecord;
import com.qq.server.database.Dao;
import com.qq.server.database.DaoImplement;
public class UpdateRecord extends JFrame implements MouseListener,KeyListener{
	//定义组件
	private JLabel jl1,jl2;
	private JLabel jlb3,jlb4,jlb5,jlb6,jlb7;
	private JTextField text1,text2,text3,text4,text5;
	private JButton btn1,btn2;
	Dao dao = new DaoImplement();
	LoginRecord record;
	String id;
    //main函数
	public static void main(String []args){
		//new UpdateRecord();
	}
	//构造函数
	public UpdateRecord(LoginRecord record,String id){
		this.record = record;
		this.id =id;
		setLabel();
		setBtn();
		setTextName();
		setText();
		intiWindows();
	}
	//初始化窗体
	public void intiWindows() {
		this.setSize(500,600);
		this.setLayout(null);
		this.setResizable(false);
		this.setTitle("修改记录");
		this.getContentPane().setBackground(Color.WHITE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	//设置顶端label
	public void setLabel(){
		jl1 = new JLabel(new ImageIcon("Image1/UpdateRegister/670x50.jpg"));
		jl1.setBounds(0, 0, 670, 50);
		this.add(jl1);
		jl2 = new JLabel("登录记录");
		jl2.setFont(new Font("宋体",Font.BOLD, 25));
		jl2.setForeground(Color.PINK);
		jl2.setBounds(190, 60, 150,50 );
		this.add(jl2);
	}
	//设置输入框前的文字标签
	public void setTextName(){
		jlb3 = new JLabel("登录时间:");
		jlb3.setFont(new Font("宋体",Font.BOLD, 15));
		jlb3.setForeground(Color.BLACK);
		jlb3.setBounds(100, 120, 80,50 );
		this.add(jlb3);
		jlb4 = new JLabel("下线时间:");
		jlb4.setFont(new Font("宋体",Font.BOLD, 15));
		jlb4.setForeground(Color.BLACK);
		jlb4.setBounds(100, 180, 80,50 );
		this.add(jlb4);
		jlb5 = new JLabel("在线时长:");
		jlb5.setFont(new Font("宋体",Font.BOLD, 15));
		jlb5.setForeground(Color.BLACK);
		jlb5.setBounds(100, 240, 80,50 );
		this.add(jlb5);
		jlb6 = new JLabel("登录处IP:");
		jlb6.setFont(new Font("宋体",Font.BOLD, 15));
		jlb6.setForeground(Color.BLACK);
		jlb6.setBounds(95, 300, 80,50 );
		this.add(jlb6);
		jlb7 = new JLabel("状态:");
		jlb7.setFont(new Font("宋体",Font.BOLD, 15));
		jlb7.setForeground(Color.BLACK);
		jlb7.setBounds(130, 360, 60,50 );
		this.add(jlb7);
	}
	//设置输入框
	public void setText(){
		text1 = new JTextField(record.getTimeLogin(),25);
		text1.setBounds(180,130,230,30);
		this.add(text1);
		text2 = new JTextField(record.getTimeLeft(),25);
		text2.setBounds(180,190,200,30);
		this.add(text2);
		text3 = new JTextField(record.getTimeLength(),25);
		text3.setBounds(180,250,170,30);
		this.add(text3);
		text4 = new JTextField(record.getIp(),25);
		text4.setBounds(180,310,140,30);
		this.add(text4);
		text5 = new JTextField(record.getState(),25);
		text5.setBounds(180,370,110,30);
		this.add(text5);
	}
	//设置按钮
	public void setBtn(){
		btn1 = new JButton(new ImageIcon("Image1/UpdateRegister/queren.jpg"));
		btn1.setBounds(200, 450, 80, 30);
		btn1.addMouseListener(this);
		this.add(btn1);
		btn2 = new JButton(new ImageIcon("Image1/UpdateRegister/fanhui.jpg"));
		btn2.setBounds(200, 520, 80, 30);
		btn2.addMouseListener(this);
		this.add(btn2);
	}
	//事件处理
	public void mouseClicked(MouseEvent e) {
		//确认
		if(e.getClickCount()==1&&e.getSource()==btn1){
			if(text1.getText().equals("")&&text2.getText().equals("")&&text3.getText().equals("")&&text4.getText().equals("")&&text5.getText().equals("")){
				JOptionPane.showMessageDialog(this, "内容为空，无法修改！");
			}else{
				if(!text1.getText().equals("")){
					record.setTimeLogin(text1.getText());
				}
				if(!text2.getText().equals("")){
					record.setTimeLeft(text2.getText());
				}
				if(!text3.getText().equals("")){
					record.setTimeLength(text3.getText());
				}
				if(!text4.getText().equals("")){
					record.setIp(text4.getText());
				}
				if(!text5.getText().equals("")){
					record.setState(text5.getText());
				}
				dao.updateRecord(record);
				JOptionPane.showMessageDialog(this, "修改成功！");
				this.dispose();
				new ServerManager(dao.getAllRecord(),id);
			}
		}
		//返回
        if(e.getClickCount()==1&&e.getSource()==btn2){
			this.dispose();
		    new ServerManager(dao.getAllRecord(),id);
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
	public void keyPressed(KeyEvent e) {	
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
	}
}
