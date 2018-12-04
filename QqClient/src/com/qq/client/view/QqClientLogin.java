/*
 * @author:�����
 * @function:�û���¼����
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
	//�������
	private JLabel jl1;
	private JTextField uTxt;
	private JPasswordField pTxt;
	private JButton btn2,btn3,close_jb,min_jb;
	private JPanel jp1,jp2;
	JLayeredPane layeredPane;
	//�������ñ���
	int xOld,yOld;	
	//Image image;
	String password;
	//main����
	public static void main(String[] args) {
		new QqClientLogin();
	}
	//���췽��
	public QqClientLogin() {
		ImageIcon background = new ImageIcon("image\\Login\\denglubg.jpg");
		layeredPane = new JLayeredPane(); 
		intiLoginWindows();
		intiLabel();
		JFrameNoBorder(background);
		setInputGround();
		setJBtn2();
		setJBtn3();
		//����
		uTxt.addMouseListener(this);
		pTxt.addMouseListener(this);
		//��Ӹ�������ֲ����
		layeredPane.add(jl1,JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(jp1,JLayeredPane.MODAL_LAYER);
		layeredPane.add(btn3,JLayeredPane.MODAL_LAYER);
		layeredPane.add(btn2,JLayeredPane.MODAL_LAYER);
		layeredPane.add(close_jb,JLayeredPane.MODAL_LAYER);
		layeredPane.add(min_jb,JLayeredPane.MODAL_LAYER);
		this.setLayeredPane(layeredPane);
		this.setVisible(true);
	}
	//��ʼ������
	public void intiLoginWindows() {
		this.setSize(451,325);
		this.setIconImage(new ImageIcon("image\\Login\\tubiao.png").getImage());
		this.setTitle("�û���¼");
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);	
	}
	//���ñ���
	public JLabel intiLabel() {
		jl1=new JLabel(new ImageIcon("image\\Login\\denglubg.jpg"));
		jl1.setBounds(0, 0, 451, 297);
		return jl1;
	}
	//����߿򷽷�
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
		QqClientLogin.this.setLocation(xx, yy);//������ק�󣬴��ڵ�λ��
		    }
		   });
		   this.setUndecorated(true);
		 }
	//�����ı��������򹹳ɵ����jp1
	public JPanel setInputGround(){
		uTxt= new JTextField("�������ʺ�",20);
		uTxt.setForeground(Color.BLACK);
		uTxt.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 0));
		uTxt.setOpaque(false);
		pTxt= new JPasswordField("����������",20);
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
	//���õ�¼��ť
	public JButton setJBtn3(){
		btn3=new JButton(new ImageIcon("image\\Login\\dengluanniu.jpg"));
		btn3.setBorderPainted(false);
		btn3.setBounds(330, 168, 68, 39);
		btn3.addActionListener(this);
		return btn3;
	}
	//����ע�ᰴť
	public JButton setJBtn2() {
		btn2=new JButton(new ImageIcon("image\\Login\\zhuce.jpg"));
		btn2.setBounds(338, 258, 54, 28);
		btn2.setBorderPainted(false);
		btn2.addActionListener(this);
		return btn2;
	}
	//��ť�¼�����
	public void actionPerformed(ActionEvent e) {
		//ע��
		if(e.getSource()==btn2){
			this.dispose();
			new QqRegister();
		}
		//��¼
	    if(e.getSource()==btn3){
	    	try{
	    		ClientUser clientUser = new ClientUser();
				User user = new User();
				//trim()ȥ���ַ����еĿո�
				user.setUserId(uTxt.getText().trim());
				user.setPasswd(new String(pTxt.getPassword()));
				user.setState("true");
				user.setType("1");
				User u = clientUser.CheckUser(user);
				//��¼�ɹ�
				if(u.getState().equals("true")){
					this.dispose();
					QqFriendList qqList = new QqFriendList(u);
					//new Thread(qqList).start();
				}
				//��Ϣ�����µ�¼ʧ��
				else if(u.getState().equals("false")){
					this.dispose();
					new LoginErrorTip();
				}			
	    	}catch(Exception ep){
	    		JOptionPane.showMessageDialog(this, "�������Ӵ����������磡");
	    	}
		}
	  //������ư�ť
		if(e.getSource()==close_jb)
		{
			this.dispose();
		}
		if(e.getSource()==min_jb)
		{
			this.setExtendedState(JFrame.ICONIFIED);
		}
	}
	//����¼�����(������ʼ�������Զ���ʧ)
	public void mouseClicked(MouseEvent e) {
		password = new String(pTxt.getPassword());
		if(e.getClickCount()==1){
			if(uTxt.getText().equals("�������ʺ�")){
				uTxt.setText("");
			}else if(password.equals("����������")){
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
