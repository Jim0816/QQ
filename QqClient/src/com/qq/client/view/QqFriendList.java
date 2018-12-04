package com.qq.client.view;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import com.qq.client.model.ClientUser;
import com.qq.client.view.QqChat;
import com.qq.common.User;
public class QqFriendList extends JFrame implements ActionListener,MouseListener,Runnable{
	//����ͼ��
	ImageIcon background;
	JPanel buttom;
	JLabel imgLabel;
	JButton jb;
	//�ϲ㱱��
	JLabel head,name,sign;
	JButton close_jb,min_jb;
	//�ϲ��ϱ�
	JPanel jp;//��Ƭ����
	CardLayout cl;
	//��һ�ſ�Ƭ
	JPanel jp1;
	JButton jp1_jb1,jp1_jb2,jp1_jb3;
	//�ڶ��ſ�Ƭ
	JPanel jp2;
	JScrollPane jsp;
	JPanel jp_jsp;
	JButton jp2_jb1,jp2_jb2,jp2_jb3;
	//�����ſ�Ƭ
	JPanel jp3;
	JScrollPane jsp2;
	JPanel jp_jsp2;//������jsp2
	JButton jp3_jb1,jp3_jb2,jp3_jb3;
	//�����ſ�Ƭ
	JPanel jp4;
	JScrollPane jsp3;
	JPanel jp_jsp3;
	JButton jp4_jb1,jp4_jb2,jp4_jb3;
    //������������
	int xOld,yOld;
	User user;
	String userId,userName;
	//���캯��
	public QqFriendList( User user)
	{
		this.user = user;
		this.userId = user.getUserId();
		this.userName = user.getName();
		//������
		backGround();
		//����߿�
		JFrameNoBorder(background);
        //������
		setNorth(userId);
		//���ú����б�Ϊ��Ƭ����
		cl = new CardLayout();
		jp = new JPanel();
		jp.setOpaque(false);
		jp.setBounds(0,	187, background.getIconWidth(),360);
		//�����һ�ſ�Ƭ
		firstCard();	
		//����ڶ��ſ�Ƭ
		secondCard();	
		//��������ſ�Ƭ
		thirdCard();
		//��������ſ�Ƭ
		fourthCard();
		this.add(close_jb);
		this.add(min_jb);
		this.add(head);
		this.add(name);
		this.add(sign);
		jp.setLayout(cl);
		jp.add(jp1,"1");
		jp.add(jp2,"2");
		jp.add(jp3,"3");
		jp.add(jp4,"4");
		this.add(jp);
		this.setLayout(null);
		this.setSize(247, 605);
		this.setLocation(5, 5);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	//����������
	public ImageIcon backGround()
	{
		background = new ImageIcon("Image/QqFriendList/bg.jpg");
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
		QqFriendList.this.setLocation(xx, yy);//������ק�󣬴��ڵ�λ��
		    }
		   });
		   this.setUndecorated(true);
		 }
	//������
	public void setNorth(String userId){
		//�����ߵĶ�����ͷ���ǳƣ�ǩ����
		close_jb = new JButton(new ImageIcon("Image/QqFriendList/guanbi.jpg"));
		close_jb.setBounds(226, 2, 19, 14);
		close_jb.setFocusPainted(false);
		close_jb.setBorderPainted(false);
		close_jb.addActionListener(this);
		min_jb = new JButton(new ImageIcon("Image/QqFriendList/zuixiaohua.jpg"));
		min_jb.setBounds(207, 1, 22, 14);
		min_jb.setFocusPainted(false);
		min_jb.setBorderPainted(false);
		min_jb.addActionListener(this);
		head = new JLabel(new ImageIcon("Image/QqFriendList/qqhead.jpg"));
		head.setBounds(10, 40, 50, 50);
		name = new JLabel(""+userName+"("+userId+")");
		name.setBounds(70, 42, 160, 20);
		name.setFont(new Font("����",Font.BOLD, 12));
		name.setForeground(Color.white);
		sign = new JLabel("����ǩ��");
		sign.setBounds(70, 70, 80, 20);
		sign.setForeground(Color.white);
	}
	//�����һ�ſ�Ƭ����
	public void firstCard()
	{
		jp1 = new JPanel();
		jp1_jb1 = new JButton(new ImageIcon("Image/QqFriendList/wodehaoyou.jpg"));
		jp1_jb1.setBorderPainted(false);
		jp1_jb1.addActionListener(this);
		jp1_jb1.setLayout(null);
		jp1_jb1.setBounds(-20, 0, 263, 30);
		jp1_jb1.setHorizontalAlignment(SwingConstants.LEFT );
		jp1_jb2 = new JButton(new ImageIcon("Image/QqFriendList/moshengren.jpg"));
		jp1_jb2.setBorderPainted(false);
		jp1_jb2.addActionListener(this);
		jp1_jb2.setLayout(null);
		jp1_jb2.setBounds(-20, 40, 263, 30);
		jp1_jb2.setHorizontalAlignment(SwingConstants.LEFT );
		jp1_jb3 = new JButton(new ImageIcon("Image/QqFriendList/heimingdan.jpg"));
		jp1_jb3.setBorderPainted(false);
		jp1_jb3.addActionListener(this);
		jp1_jb3.setLayout(null);
		jp1_jb3.setBounds(-20, 80, 263, 30);
		jp1_jb3.setHorizontalAlignment(SwingConstants.LEFT );
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);
		jp1.setLayout(null);
		jp1.setOpaque(false);
	}
	//����ڶ��ſ�Ƭ����
	public void secondCard()
	{
		jp2 = new JPanel();
		jp2_jb1 = new JButton(new ImageIcon("Image/QqFriendList/wodehaoyou2.jpg"));
		jp2_jb1.setBorderPainted(false);
		jp2_jb1.setBounds(-20, 0, 263, 30);
		jp2_jb1.addActionListener(this);
		jp2_jb1.setLayout(null);
		jp2_jb1.setSize(277, 35);
		jp2_jb1.setHorizontalAlignment(SwingConstants.LEFT );
		jp2_jb2 = new JButton(new ImageIcon("Image/QqFriendList/moshengren.jpg"));
		jp2_jb2.setBorderPainted(false);
		jp2_jb2.addActionListener(this);
		jp2_jb2.setLayout(null);
		jp2_jb2.setBounds(-20, 354, 263, 35);
		jp2_jb2.setHorizontalAlignment(SwingConstants.LEFT );
		jp2_jb3 = new JButton(new ImageIcon("Image/QqFriendList/heimingdan.jpg"));
		jp2_jb3.setBorderPainted(false);
		jp2_jb3.addActionListener(this);
		jp2_jb3.setLayout(null);
		jp2_jb3.setBounds(-20, 389, 263, 35);
		jp2_jb3.setHorizontalAlignment(SwingConstants.LEFT );
		//�ٶ�30������
		jp_jsp = new JPanel(new GridLayout(30,1));
		jp_jsp.setBackground(Color.WHITE);
		jsp = new JScrollPane(jp_jsp);
		//��ʼ��30������
		JLabel[] jbls = new JLabel[30];
		for(int i=0; i<jbls.length; i++)
		{
			jbls[i] = new JLabel(i+1+"�Ż�����", new ImageIcon("Image/QqFriendList/qqhead.jpg"), JLabel.LEFT);
			jbls[i].addMouseListener(this);
			jp_jsp.add(jbls[i]);
		}
		jsp.setBounds(1, 35, 242, 360);
		jp2.add(jsp);
		jp2.add(jp2_jb1);
		jp2.add(jp2_jb2);
		jp2.add(jp2_jb3);
		jp2.setLayout(null);
		jp2.setOpaque(false);
	}
	//��������ſ�Ƭ����
	public void thirdCard()
	{
		jp3 = new JPanel();
		jp3_jb1 = new JButton(new ImageIcon("Image/QqFriendList/wodehaoyou.jpg"));
		jp3_jb1.setBorderPainted(false);
		jp3_jb1.addActionListener(this);
		jp3_jb1.setLayout(null);
		jp3_jb1.setBounds(-20, 0, 263, 30);
		jp3_jb1.setHorizontalAlignment(SwingConstants.LEFT );
		jp3_jb2 = new JButton(new ImageIcon("Image/QqFriendList/moshengren2.jpg"));
		jp3_jb2.setBorderPainted(false);
		jp3_jb2.setBounds(-20, 40, 263, 30);
		jp3_jb2.addActionListener(this);
		jp3_jb2.setLayout(null);
		jp3_jb2.setBounds(-20, 35, 263, 30);
		jp3_jb2.setHorizontalAlignment(SwingConstants.LEFT );
		jp3_jb3 = new JButton("> ������");
		jp3_jb3.addActionListener(this);
		jp3_jb3.setLayout(null);
		jp3_jb3.setBounds(0, 389, 277, 35);
		jp3_jb3.setHorizontalAlignment(SwingConstants.LEFT );
		//�ٶ�30������
		jp_jsp2 = new JPanel(new GridLayout(10,1));
		jp_jsp2.setBackground(Color.WHITE);
		jsp2 = new JScrollPane(jp_jsp2);
		//��ʼ��30������
		JLabel[] jbls = new JLabel[10];
		for(int i=0; i<jbls.length; i++)
		{
			jbls[i] = new JLabel(i+1+"��İ����", new ImageIcon("Image/QqFriendList/qqhead.jpg"), JLabel.LEFT);
			jbls[i].addMouseListener(this);
			jp_jsp2.add(jbls[i]);
		}
		jsp2.setBounds(1, 70, 242, 360);
		jp3.add(jsp2);
		jp3.add(jp3_jb1);
		jp3.add(jp3_jb2);
		jp3.add(jp3_jb3);
		jp3.setLayout(null);
		jp3.setOpaque(false);
	}
	//��������ſ�Ƭ����
	public void fourthCard()
	{
		jp4 = new JPanel();
		jp4_jb1 = new JButton(new ImageIcon("Image/QqFriendList/wodehaoyou.jpg"));
		jp4_jb1.setBorderPainted(false);
		jp4_jb1.addActionListener(this);
		jp4_jb1.setLayout(null);
		jp4_jb1.setBounds(-20, 0, 263, 30);
		jp4_jb1.setHorizontalAlignment(SwingConstants.LEFT );
		jp4_jb2 = new JButton(new ImageIcon("Image/QqFriendList/moshengren.jpg"));
		jp4_jb2.setBorderPainted(false);
		jp4_jb2.addActionListener(this);
		jp4_jb2.setLayout(null);
		jp4_jb2.setBounds(-20, 35, 263, 30);
		jp4_jb2.setHorizontalAlignment(SwingConstants.LEFT );
		jp4_jb3 = new JButton(new ImageIcon("Image/QqFriendList/heimingdan2.jpg"));
		jp4_jb3.setBorderPainted(false);
		jp4_jb3.setBounds(-20, 80, 263, 30);
		jp4_jb3.addActionListener(this);
		jp4_jb3.setLayout(null);
		jp4_jb3.setBounds(-20, 70, 263, 30);
		jp4_jb3.setHorizontalAlignment(SwingConstants.LEFT );
		//�ٶ�30������
		jp_jsp3 = new JPanel(new GridLayout(10,1));
		jp_jsp3.setBackground(Color.WHITE);
		jsp3 = new JScrollPane(jp_jsp3);
		//��ʼ��30������
		JLabel[] jbls = new JLabel[5];
		for(int i=0; i<jbls.length; i++)
		{
			jbls[i] = new JLabel(i+1+"�ź�����", new ImageIcon("Image/QqFriendList/qqhead.jpg"), JLabel.LEFT);
			jbls[i].addMouseListener(this);
			jp_jsp3.add(jbls[i]);
		}
		jsp3.setBounds(1, 105, 242, 362);
		jp4.add(jsp3);
		jp4.add(jp4_jb1);
		jp4.add(jp4_jb2);
		jp4.add(jp4_jb3);
		jp4.setLayout(null);
		jp4.setOpaque(false);
	}
	//��ť�¼�����
	public void actionPerformed(ActionEvent e) {
		//���߰�ť
		if(e.getSource()==close_jb)
		{
			//���߷���֪ͨ��������
			this.dispose();
			ClientUser clientUser = new ClientUser();
			user.setType("3");
			user.setState("false");
			User u = clientUser.CheckUser(user);
		}
		if(e.getSource()==min_jb)
		{
			this.setExtendedState(JFrame.ICONIFIED);
		}
		//��һ�ſ�Ƭ�İ�ť
		if(e.getSource()==jp1_jb1)
		{
			cl.show(jp, "2");
		}
		if(e.getSource()==jp1_jb2)
		{
			cl.show(jp, "3");
		}
		if(e.getSource()==jp1_jb3)
		{
			cl.show(jp, "4");
		}
		//�ڶ��ſ�Ƭ�İ�ť
		if(e.getSource()==jp2_jb1)
		{
			cl.show(jp, "1");
		}
		if(e.getSource()==jp2_jb2)
		{
			cl.show(jp, "3");
		}
		if(e.getSource()==jp2_jb3)
		{
			cl.show(jp, "4");
		}
		//�����ſ�Ƭ�İ�ť
		if(e.getSource()==jp3_jb1)
		{
			cl.show(jp, "2");
		}
		if(e.getSource()==jp3_jb2)
		{
			cl.show(jp, "1");
		}
		if(e.getSource()==jp3_jb3)
		{
			cl.show(jp, "4");
		}
		//�����ſ�Ƭ�İ�ť
		if(e.getSource()==jp4_jb1)
		{
			cl.show(jp, "2");
		}
		if(e.getSource()==jp4_jb2)
		{
			cl.show(jp, "3");
		}
		if(e.getSource()==jp4_jb3)
		{
			cl.show(jp, "1");
		}
	}
	//����¼�����
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==2){
			String friendNo = ((JLabel)e.getSource()).getText();
			new QqChat(user.getUserId(),friendNo);
		}
	}
	public void mouseEntered(MouseEvent e) {
		JLabel j1 = (JLabel)e.getSource();
		j1.setForeground(Color.GREEN);
	}
	public void mouseExited(MouseEvent e) {
		JLabel j1 = (JLabel)e.getSource();
		j1.setForeground(Color.black);
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	//�����Ƿ��ظ���¼
	public void run() {
		while(true){
			ClientUser clientUser = new ClientUser();
			user.setType("2");
			User u = clientUser.CheckUser(user);
			if(u.getState().equals("false")){
				this.dispose();
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}	
}