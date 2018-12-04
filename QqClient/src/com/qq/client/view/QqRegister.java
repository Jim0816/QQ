/*
 * @���ߣ������
 * @���ܣ�ע���û���Ϣ
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
	//�������
	private JLabel jl1,jl2;
	private JButton btn1;
	private JTextField uTxt;
	private JPasswordField pTxt;
	//�����л�ͼƬ��������
	String password;
	 MyJPanel mp;
	    int flag=0;
	    ImageIcon[] imgs = { new ImageIcon("image\\Register\\photo1.jpg"),
	            new ImageIcon("image\\Register\\photo2.jpg"),new ImageIcon("image\\Register\\photo3.jpg")};
	//main����
	public static void main(String[] args) {
		new QqRegister();
	}
	//���캯��
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
		//�����̶߳�MyJPanel�����ػ�
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
	//��ʼ������
	public void intiRegisterWindows() {
		this.setSize(1360,720);
		this.setLocation(0, 5);
		this.getContentPane().setBackground(Color.WHITE);
		this.setTitle("�û�ע��");
		this.setLayout(null);
		this.setResizable(false);	
	}
	//����������Ϸ����ֱ���
	public JLabel setUpFont(){
		jl2 = new JLabel(new ImageIcon("Image/Register/biaoti.jpg"));
		jl2.setBounds(690, 40, 265,122);
		return jl2;
	}
	//��������򱳾�
	public JLabel setTextBack(){
		jl1 = new JLabel(new ImageIcon("Image/Register/shurukuang.jpg"));
		jl1.setBounds(680, 180, 520,183);
		return jl1;
	}
	//����ע�ᰴť
	public JButton setBtn(){
		btn1 = new JButton(new ImageIcon("Image/Register/zhuceanniu.jpg"));
		btn1.setBounds(690, 580, 485, 70);
		btn1.setBorderPainted(false);
		btn1.addActionListener(this);
		return btn1;
	}
	//�����˺��ı���
	public JTextField setText(){
		uTxt = new JTextField("�ǳ�",50);
		Font font = new Font("����",Font.ITALIC,27);
		uTxt.setFont(font);
		uTxt.setForeground(Color.BLACK);
		uTxt.setBounds(705, 213, 470, 40);
		uTxt.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 1));
		uTxt.addMouseListener(this);
		return uTxt;
	}
	//���������ı���
	public JPasswordField setpTxt(){
		pTxt = new JPasswordField("����",50);
		pTxt.setEchoChar((char) 0);
		Font font = new Font("����",Font.ITALIC,27);
		pTxt.setFont(font);
		pTxt.setForeground(Color.BLACK);
		pTxt.setBounds(705, 300, 470, 40);
		pTxt.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 1));
		pTxt.addMouseListener(this);
		return pTxt;
	}
	//���ض�̬ͼƬ��JPanel
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
	//��ť�¼�����
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn1){
			try{
				ClientUser clientUser = new ClientUser();
				User user = new User();
				String id;
				//trim()ȥ���ַ����еĿո�
				user.setType("0");
				user.setName(uTxt.getText().trim());
				user.setPasswd(new String(pTxt.getPassword()));
				id = clientUser.CheckNewUser(user);
				if(id.equals("")){
					JOptionPane.showMessageDialog(this, "ע��ʧ�ܣ�");
				}else{
					new RegisterTip(id);
				}
			}catch(Exception arg){
				JOptionPane.showMessageDialog(this, "�������Ӵ����������磡");
			}	
		}
	}
	public void mouseClicked(MouseEvent e) {
		password = new String(pTxt.getPassword());
		if(e.getClickCount()==1){
			if(uTxt.getText().equals("�ǳ�")){
				uTxt.setText("");
			}else if(password.equals("����")){
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


