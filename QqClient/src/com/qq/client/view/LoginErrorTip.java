/*
 * @���ߣ������
 * @���ܣ���¼��Ϣ������ʾ�������һ����밴ť
 */
package com.qq.client.view;
import java.awt.Color;
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
import javax.swing.JPanel;
public class LoginErrorTip extends JFrame implements ActionListener,MouseListener{
	//�������
	private JLabel jl1,imgLabel;
	private JButton btn1,btn2,btn3;
	private ImageIcon background;
	private JPanel buttom;
	private int xOld,yOld;
	public static void main(String[] args){
		new LoginErrorTip();
	}
	//���캯��
	public LoginErrorTip(){
		backGround();
		JFrameNoBorder(background);     //����߿�
		setJBtn();
		setFindCode();
		setJframeBtn();
		intiLoginWindows();
	}
	//��ʼ������
	public void intiLoginWindows() {
		this.setSize(429,328);
		this.setIconImage(new ImageIcon("image\\Login\\tubiao.png").getImage());
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);	
		this.setVisible(true);
	}
	//����������
	public ImageIcon backGround()
	{
		background = new ImageIcon("image\\LoginError\\bg.jpg");
		imgLabel = new JLabel(background);
		imgLabel.setBounds(0, 0,  background.getIconWidth(), background.getIconHeight());
		buttom=(JPanel)this.getContentPane();
		//��contentPane����Ϊ͸����
		buttom.setOpaque(false);
		this.getLayeredPane().add(imgLabel , new Integer(Integer.MIN_VALUE));
		return background;
	}
	//����߿򷽷�
	public void JFrameNoBorder(ImageIcon img) { 
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		img.setImageObserver(null);
		this.setSize(( img).getIconWidth(),img.getIconHeight()); 
		//�����϶��¼�---ȥ��Ĭ�ϱ߿�󣬲����϶��ˣ�����ʵ������
		this.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
		    xOld = e.getX();//��¼��갴��ʱ������
		    yOld = e.getY();
		   }
		  });
		this.addMouseMotionListener(new MouseMotionAdapter() {
		public void mouseDragged(MouseEvent e) {
		int xOnScreen = e.getXOnScreen();
		int yOnScreen = e.getYOnScreen();
		int xx = xOnScreen - xOld;
		int yy = yOnScreen - yOld;
		LoginErrorTip.this.setLocation(xx, yy);//������ק�󣬴��ڵ�λ��
		    }
		   });
		   this.setUndecorated(true);
		 }
	//���ý�����Ʊ�ǩ��ť
	public void setJframeBtn(){
		btn2 = new JButton(new ImageIcon("image\\LoginError\\zxh.jpg"));
		btn2.setBounds(375, 2, 22, 24);
		btn2.setBorderPainted(false);
		btn2.addActionListener(this);
		this.add(btn2);
		btn3 = new JButton(new ImageIcon("image\\LoginError\\gb.jpg"));
		btn3.setBounds(401, 0, 24, 30);
		btn3.setBorderPainted(false);
		btn3.addActionListener(this);
		this.add(btn3);
	}
	//����ȷ����ť
	public void setJBtn(){
		btn1=new JButton(new ImageIcon("image\\LoginError\\queding.jpg"));
		btn1.setBounds(335, 300, 83, 25);
		btn1.addActionListener(this);
		this.add(btn1);
	}
	//�����һ�����ı�ǩ��ť
	public void setFindCode(){
		jl1 = new JLabel("�һ�����");
		jl1.setBounds(325, 195, 60, 20);
		jl1.addMouseListener(this);
		this.add(jl1);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn1){
			this.dispose();
			new QqClientLogin();
		}
		if(e.getSource()==btn2){
			this.setExtendedState(JFrame.ICONIFIED);
		}
		if(e.getSource()==btn3){
			this.dispose();
			new QqClientLogin();
		}
	}
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==1){
			//�����޸�����ҳ��
		}
	}
	public void mouseEntered(MouseEvent e) {
		JLabel j1 = (JLabel)e.getSource();
		j1.setForeground(Color.BLUE);
	}
	public void mouseExited(MouseEvent e) {
		JLabel j1 = (JLabel)e.getSource();
		j1.setForeground(Color.black);
	}
	public void mousePressed(MouseEvent e) {
		
	}
	public void mouseReleased(MouseEvent e) {
		
	}
}
