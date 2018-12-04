/*
 * @���ߣ������
 * @���ܣ��������
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
	//�������
	JTextArea jta1;
	JTextArea jta2;
	JButton btn1,btn2,btn3,btn4;
	JToolBar tb;
	//���崫�����
	String userId,friendId;
	//���캯��
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
	//��ʼ������
	public void intiChatWindows() {
		this.setSize(550,450);
		this.setIconImage(new ImageIcon("image\\Chat\\tubiao.png").getImage());
		this.setTitle(userId+" ���ں� "+friendId+" ����");
		this.getContentPane().setBackground(Color.WHITE);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	//����������ı���
	public JTextArea setText1(){
		jta1 = new JTextArea();
		jta1.setBounds(0, 0, 550, 270);
		return jta1;
		}
	//�����м�Ĺ�����
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
	//�����·����ı���
	public JTextArea setText2(){
		jta2 = new JTextArea();
		jta2.setBounds(0, 300, 550, 80);
		return jta2;
		}
	//�����Ͱ�ť
	public JButton setJButton(){
		btn4 = new JButton("�� ��");
		btn4.setBounds(230, 390, 80, 30);
		btn4.addActionListener(this);
		return btn4;
	}
	//�¼�����
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn4){
			//�������Ͷ�����Ϣ
			Message message = new Message();
			message.setType("4");
			message.setSender(userId);
			message.setGetter(friendId);
			message.setContent(jta2.getText());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
			String time=df.format(new Date());
			message.setSendTime(time);
			System.out.println("������棺"+message.getSender()+" �� "+message.getGetter()+" ˵ "+message.getContent());
			ClientMessage cm = new ClientMessage();
			cm.sendMessage(message);
			System.out.println("��������ѷ���");
		}
	}
}
