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
	//背景图层
	ImageIcon background;
	JPanel buttom;
	JLabel imgLabel;
	JButton jb;
	//上层北边
	JLabel head,name,sign;
	JButton close_jb,min_jb;
	//上层南边
	JPanel jp;//卡片布局
	CardLayout cl;
	//第一张卡片
	JPanel jp1;
	JButton jp1_jb1,jp1_jb2,jp1_jb3;
	//第二张卡片
	JPanel jp2;
	JScrollPane jsp;
	JPanel jp_jsp;
	JButton jp2_jb1,jp2_jb2,jp2_jb3;
	//第三张卡片
	JPanel jp3;
	JScrollPane jsp2;
	JPanel jp_jsp2;//用来放jsp2
	JButton jp3_jb1,jp3_jb2,jp3_jb3;
	//第四张卡片
	JPanel jp4;
	JScrollPane jsp3;
	JPanel jp_jsp3;
	JButton jp4_jb1,jp4_jb2,jp4_jb3;
    //定义所用数据
	int xOld,yOld;
	User user;
	String userId,userName;
	//构造函数
	public QqFriendList( User user)
	{
		this.user = user;
		this.userId = user.getUserId();
		this.userName = user.getName();
		//处理背景
		backGround();
		//处理边框
		JFrameNoBorder(background);
        //处理北边
		setNorth(userId);
		//设置好友列表为卡片布局
		cl = new CardLayout();
		jp = new JPanel();
		jp.setOpaque(false);
		jp.setBounds(0,	187, background.getIconWidth(),360);
		//处理第一张卡片
		firstCard();	
		//处理第二张卡片
		secondCard();	
		//处理第三张卡片
		thirdCard();
		//处理第四张卡片
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
	//处理背景方法
	public ImageIcon backGround()
	{
		background = new ImageIcon("Image/QqFriendList/bg.jpg");
		imgLabel = new JLabel(background);
		imgLabel.setBounds(0, 0,  background.getIconWidth(), background.getIconHeight());
		buttom=(JPanel)this.getContentPane();
		//将contentPane设置为透明的
		buttom.setOpaque(false);
		this.getLayeredPane().add(imgLabel , new Integer(Integer.MIN_VALUE));
		return background;
	}
	//处理边框方法
	public void JFrameNoBorder(ImageIcon img) { 
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		img.setImageObserver(null);
		this.setSize(( img).getIconWidth(),img.getIconHeight()); 
		//处理拖动事件---去掉默认边框后，不能拖动了，具体实现如下
		this.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
		    xOld = e.getX();//记录鼠标按下时的坐标
		    yOld = e.getY();
		   }
		  });
		this.addMouseMotionListener(new MouseMotionAdapter() {
		public void mouseDragged(MouseEvent e) {
		int xOnScreen = e.getXOnScreen();
		int yOnScreen = e.getYOnScreen();
		int xx = xOnScreen - xOld;
		int yy = yOnScreen - yOld;
		QqFriendList.this.setLocation(xx, yy);//设置拖拽后，窗口的位置
		    }
		   });
		   this.setUndecorated(true);
		 }
	//处理北边
	public void setNorth(String userId){
		//处理北边的东西（头像，昵称，签名）
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
		name.setFont(new Font("宋体",Font.BOLD, 12));
		name.setForeground(Color.white);
		sign = new JLabel("个性签名");
		sign.setBounds(70, 70, 80, 20);
		sign.setForeground(Color.white);
	}
	//处理第一张卡片方法
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
	//处理第二张卡片方法
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
		//假定30个好友
		jp_jsp = new JPanel(new GridLayout(30,1));
		jp_jsp.setBackground(Color.WHITE);
		jsp = new JScrollPane(jp_jsp);
		//初始化30个好友
		JLabel[] jbls = new JLabel[30];
		for(int i=0; i<jbls.length; i++)
		{
			jbls[i] = new JLabel(i+1+"号机器人", new ImageIcon("Image/QqFriendList/qqhead.jpg"), JLabel.LEFT);
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
	//处理第三张卡片方法
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
		jp3_jb3 = new JButton("> 黑名单");
		jp3_jb3.addActionListener(this);
		jp3_jb3.setLayout(null);
		jp3_jb3.setBounds(0, 389, 277, 35);
		jp3_jb3.setHorizontalAlignment(SwingConstants.LEFT );
		//假定30个好友
		jp_jsp2 = new JPanel(new GridLayout(10,1));
		jp_jsp2.setBackground(Color.WHITE);
		jsp2 = new JScrollPane(jp_jsp2);
		//初始化30个好友
		JLabel[] jbls = new JLabel[10];
		for(int i=0; i<jbls.length; i++)
		{
			jbls[i] = new JLabel(i+1+"号陌生人", new ImageIcon("Image/QqFriendList/qqhead.jpg"), JLabel.LEFT);
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
	//处理第四张卡片方法
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
		//假定30个好友
		jp_jsp3 = new JPanel(new GridLayout(10,1));
		jp_jsp3.setBackground(Color.WHITE);
		jsp3 = new JScrollPane(jp_jsp3);
		//初始化30个好友
		JLabel[] jbls = new JLabel[5];
		for(int i=0; i<jbls.length; i++)
		{
			jbls[i] = new JLabel(i+1+"号黑名单", new ImageIcon("Image/QqFriendList/qqhead.jpg"), JLabel.LEFT);
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
	//按钮事件处理
	public void actionPerformed(ActionEvent e) {
		//北边按钮
		if(e.getSource()==close_jb)
		{
			//下线发送通知给服务器
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
		//第一张卡片的按钮
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
		//第二张卡片的按钮
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
		//第三张卡片的按钮
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
		//第四张卡片的按钮
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
	//鼠标事件处理
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
	//检验是否重复登录
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