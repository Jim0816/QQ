/*
 * @���ߣ������
 * @���ܣ�����Ա��¼����
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
	//����ͼ��
	ImageIcon background;
	JPanel buttom;
	JLabel imgLabel;
	//�������
	private JButton btn1,btn2;
	private JTextField text1,text3;
	private JPasswordField text2;
	//��������
	int xOld,yOld;
	Dao dao = new DaoImplement();
	public static void main(String[] args) {
		ManagerLogin manager = new ManagerLogin();
	}
	//���캯��
	public ManagerLogin(){
		backGround();
		JFrameNoBorder();
		setBtn();
		setText();
		intiWindows();
	}
	//��ʼ������
	public void intiWindows() {
		this.setSize(943,449);
		this.setLayout(null);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.WHITE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	//����������
	public void backGround()
	{
		background = new ImageIcon("image1/ManagerLogin/bg.jpg");
		imgLabel = new JLabel(background);
		imgLabel.setBounds(0, 0,  background.getIconWidth(), background.getIconHeight());
		buttom=(JPanel)this.getContentPane();
		//��contentPane����Ϊ͸����
		buttom.setOpaque(false);
		this.getLayeredPane().add(imgLabel , new Integer(Integer.MIN_VALUE));
	}
	//����߿򷽷�
	public void JFrameNoBorder() { 
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
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
		ManagerLogin.this.setLocation(xx, yy);//������ק�󣬴��ڵ�λ��
		    }
		   });
		   this.setUndecorated(true);
		 }
	//����رպ͵�¼��ť
	public void setBtn(){
		//��¼
		btn1 = new JButton(new ImageIcon("image1/ManagerLogin/denglu.jpg"));
		btn1.setBounds(380, 280, 66, 34);
		btn1.addMouseListener(this);
		this.add(btn1);
		//�ر�
		btn2 = new JButton(new ImageIcon("image1/ManagerLogin/close.jpg"));
		btn2.setBounds(913, 0, 27, 25);
		btn2.setBorderPainted(false);
		btn2.addMouseListener(this);
		this.add(btn2);
	}
	//�����м��ı�������
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
		text3 = new JTextField("��������֤��",20);
		text3.setBounds(380,223,117,24);
		text3.setForeground(Color.black);
		text3.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 0));
		text3.addMouseListener(this);
		this.add(text3);
	}
	//�¼�����
	public void mouseClicked(MouseEvent e) {
		//��¼
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
						JOptionPane.showMessageDialog(this, "��¼��Ϣ�������������룡");
						}	
				}else{
					text3.setBorder(javax.swing.BorderFactory.createLineBorder(Color.RED, 1));
				}
			}
		}
		//�ر�
        if(e.getClickCount()==1&&e.getSource()==btn2){
			this.dispose();
		}
        //��յ���ı���
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
        	if(text3.getText().equals("��������֤��")){
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
