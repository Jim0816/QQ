/*
 * @作者：李际明
 * @功能：聊天界面
 */
package com.qq.client.view;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

import com.qq.client.model.ClientMessage;
import com.qq.common.Message;
public class QqChat extends JFrame implements ActionListener{
	//定义组件
	JTextArea jta1;
	JTextArea jta2;
	JButton btn1,btn2,btn3,btn4;
	JToolBar tb;
	//定义传入参数
	String userId,friendId;
	//构造函数
	public QqChat(String userId,String friendId){
		this.userId = userId;
		this.friendId = friendId;
		setText1();
		setToolBar();
		setText2();
		setJButton();
		this.add(jta1);
		this.add(tb);
		this.add(jta2);
		this.add(btn4);
		intiChatWindows();
	}
	//初始化窗体
	public void intiChatWindows() {
		this.setSize(550,450);
		this.setIconImage(new ImageIcon("image\\Chat\\tubiao.png").getImage());
		this.setTitle(userId+" 正在和 "+friendId+" 聊天");
		this.getContentPane().setBackground(Color.WHITE);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	//处理上面的文本域
	public JTextArea setText1(){
		jta1 = new JTextArea();
		jta1.setBounds(0, 0, 550, 270);
		return jta1;
		}
	//处理中间的工具栏
	public JToolBar setToolBar(){
		btn1 = new JButton(new ImageIcon("image\\Chat\\biaoqing.jpg"));
		btn2 = new JButton(new ImageIcon("image\\Chat\\huatu.jpg"));
		btn3 = new JButton(new ImageIcon("image\\Chat\\liaotianjilu.jpg"));
		tb = new JToolBar();
		tb.setBounds(0, 270, 550, 30);
		tb.setLayout(new GridLayout(1,1,150,4));
		tb.add(btn1);
		tb.add(btn2);
		tb.add(btn3);
		return tb;	
	}
	//处理下方的文本域
	public JTextArea setText2(){
		jta2 = new JTextArea();
		jta2.setBounds(0, 300, 550, 80);
		return jta2;
		}
	//处理发送按钮
	public JButton setJButton(){
		btn4 = new JButton("发 送");
		btn4.setBounds(230, 390, 80, 30);
		btn4.addActionListener(this);
		return btn4;
	}
	//事件处理
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn4){
			//创建发送对象信息
			Message message = new Message();
			message.setType("4");
			message.setSender(userId);
			message.setGetter(friendId);
			message.setContent(jta2.getText());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String time=df.format(new Date());
			message.setSendTime(time);
			System.out.println("聊天界面："+message.getSender()+" 给 "+message.getGetter()+" 说 "+message.getContent());
			ClientMessage cm = new ClientMessage();
			cm.sendMessage(message);
			System.out.println("聊天界面已发送");
		}
	}
}
