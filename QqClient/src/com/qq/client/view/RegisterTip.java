/*
 * @����:�����
 * @���ܣ�ע��ɹ�����ʾ�����Ұ�ť��ת��¼����
 */
package com.qq.client.view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
public class RegisterTip extends JFrame implements ActionListener{
	//�������
	JButton btn1;
	JLabel jl1;
    String id;
  //main����
	public static void main(String[] args) {
		new RegisterTip("1");
	}
	public RegisterTip(String id){
		this.id = id;
		this.setSize(400,200);
		this.setIconImage(new ImageIcon("image\\Login\\tubiao.png").getImage());
		this.setTitle("ע����ʾ");
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);	
		jl1 = new JLabel("��ϲ������¼�ɹ��� �������˺�Ϊ��"+id);
		jl1.setBounds(40,20,350,50);
		btn1 = new JButton("ȥ��½");
		btn1.setBounds(140, 100, 80, 25);
		btn1.addActionListener(this);
		this.add(jl1);
		this.add(btn1);
		this.setVisible(true);
	}
	//��ť�¼�����
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn1){
			this.dispose();
			new QqClientLogin();
		}
	}
}

