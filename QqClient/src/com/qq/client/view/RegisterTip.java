/*
 * @作者:李际明
 * @功能：注册成功的提示，并且按钮跳转登录界面
 */
package com.qq.client.view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
public class RegisterTip extends JFrame implements ActionListener{
	//定义组件
	JButton btn1;
	JLabel jl1;
    String id;
  //main函数
	public static void main(String[] args) {
		new RegisterTip("1");
	}
	public RegisterTip(String id){
		this.id = id;
		this.setSize(400,200);
		this.setIconImage(new ImageIcon("image\\Login\\tubiao.png").getImage());
		this.setTitle("注册提示");
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);	
		jl1 = new JLabel("恭喜您，登录成功！ 您的新账号为："+id);
		jl1.setBounds(40,20,350,50);
		btn1 = new JButton("去登陆");
		btn1.setBounds(140, 100, 80, 25);
		btn1.addActionListener(this);
		this.add(jl1);
		this.add(btn1);
		this.setVisible(true);
	}
	//按钮事件处理
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn1){
			this.dispose();
			new QqClientLogin();
		}
	}
}

