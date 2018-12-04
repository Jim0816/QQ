/*
 * @作者：李际明
 * @功能：服务器后台
 */
package com.qq.server.view;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.qq.common.LoginRecord;
import com.qq.common.Manager;
import com.qq.server.database.*;
import com.qq.server.model.QqServer;
public class ServerManager extends JFrame implements ActionListener,MouseListener{
	//定义组件
    private CardLayout cl;
    private JPanel jp;
	//第一张卡片
	private JPanel jp1;
	private JPanel jp1_jp1,jp1_jp2,jp1_jp3,jp1_jp4;
	private JLabel jp1_jl1,jp1_jp1_jl1,jp1_jp1_jl2,jp1_jp1_jl3;
	private JLabel jp1_jp2_jl1,jp1_jp2_jl2,jp1_jp3_jl1,jp1_jp3_jl2;
	private JTextField uTxt1;
	private JButton jp1_jb2_btn1,jp1_jb2_btn2,jp1_jb2_btn3,jp1_jb2_btn4,jp1_jp3_btn1;
	private JTable table;
	public JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	//第二张卡片
	private CardLayout cl1;
	private JPanel jp2;
	private JPanel jp2_1,jp2_2,jp2_3;
	private JLabel jp2_1_jl1,jp2_1_jl2,jp2_1_jl3;
	private JPanel jp2_1_jp1,jp2_1_jp2;            //jp2_1上两块面板
	private JLabel jp2_1_jp1_jl1,jp2_1_jp1_jl2,jp2_1_jp2_jl1;
	private JButton jp2_1_jp1_btn1;
	private JPanel jp2_3_jp1,jp2_3_jp2,jp2_3_jp3;        //jp2_3上三张卡片
	private JButton jp2_3_jp2_open,jp2_3_jp2_close;
	//private JButton jp2_1_btn1,jp2_1_btn2,jp2_1_btn3;    //jp2_3上的三个按钮
	private JLabel jp2_2_jl1,jp2_2_jl2,jp2_2_jl3,jp2_2_jl4,jp2_2_jl5,jp2_2_jl6;
	//第三张卡片
	private CardLayout cl2;
	private JPanel jp3;
	private JPanel jp3_1,jp3_2,jp3_3;
	private JLabel jp3_1_jl1,jp3_1_jl2,jp3_1_jl3;
	private JPanel jp3_1_jp1,jp3_1_jp2;            //jp3_1上两块面板
	private JLabel jp3_1_jp1_jl1,jp3_1_jp1_jl2,jp3_1_jp2_jl1;
	private JButton jp3_1_jp1_btn1;
	private JPanel jp3_3_jp1,jp3_3_jp2,jp3_3_jp3;        //jp3_3上三张卡片
	//private JButton jp3_1_btn1,jp3_1_btn2,jp3_1_btn3;    //jp3_3上的三个按钮
	private JLabel jp3_2_jl1,jp3_2_jl2,jp3_2_jl3,jp3_2_jl4,jp3_2_jl5,jp3_2_jl6;
	//定义所用变量
	int xOld,yOld;	
	Dao dao = new DaoImplement();
	Object[][] data; 
	String id;
	public static void main(String[] args){
		Dao dao = new DaoImplement();
		new ServerManager(dao.getAllRecord(),"1");
	}
	//构造函数
	public ServerManager(Object[][] data,String id){
		this.data = data;
		this.id = id;
		cl = new CardLayout();
		jp = new JPanel();
		jp.setBounds(0, 0, 1200, 700);
		firstCard();
		SecondCard();
		ThirdCard();
		JFrameNoBorder();
		jp.setLayout(cl);
		jp.add(jp1,"1");
		jp.add(jp2,"2");
		jp.add(jp3,"3");
		this.add(jp);
		intiControlWindows();
	}
	//初始化窗体
	public void intiControlWindows() {
		this.setSize(1200,700);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	//处理边框方法
	public void JFrameNoBorder() { 
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		//img.setImageObserver(null);
		//this.setSize(( img).getIconWidth(),img.getIconHeight()); 
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
		ServerManager.this.setLocation(xx, yy);//设置拖拽后，窗口的位置
		    }
		   });
		   this.setUndecorated(true);
		 }
	//处理第一张卡片（基本功能）
	public void firstCard(){
		jp1 = new JPanel();
		jp1.setLayout(null);
		jp1.setBounds(0, 0, 1200, 700);
		jp1.setBackground(Color.WHITE);
		//处理表格
		//定义所需数据
		String[] columnNames = {"账号","网名","登录时间","下线时间","在线时长","登录处IP地址","当前状态"};             
		Dao dao = new DaoImplement();
		//表格模型获取数据
		tableModel = new DefaultTableModel (data,columnNames);
		//初始化表格
		table = new JTable(tableModel){
			public boolean isCellEditable(int rowIndex, int ColIndex){
	             return false;
			}
		};                                                              //表格模型实例化,并且设置表格不可被编辑
		table.setAutoCreateRowSorter(true);                             //设置表的排序器
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);    //设置为单选模式
		table.setForeground(Color.BLUE);                                //设置表格前景色
		table.setSelectionForeground(Color.RED);                        //设置被选择行的前景色
	    table.setRowHeight(23);                                         //设置表格行高
	    //把表格添加到滚动面板
	    scrollPane = new JScrollPane(table);
	    scrollPane.setBounds(0,200,1200,480);
	    //将表格添加到jp1
	    jp1.add(scrollPane);
	    //处理存放选择卡片按钮的面板jp1_jp1
	    //jp1_jp1
	    jp1_jp1 = new JPanel();
	    jp1_jp1.setBackground(Color.WHITE);
	    jp1_jp1.setBounds(0, 35, 1200, 80);
	    jp1_jp1.setLayout(null);
	    jp1_jp1_jl1 = new JLabel(new ImageIcon("Image1/Manager/jibengongneng.jpg"));
	    jp1_jp1_jl1.setBounds(40, 3, 131, 52);
	    jp1_jp1.add(jp1_jp1_jl1);
	    jp1_jp1_jl2 = new JLabel(" 管理中心", new ImageIcon("Image1/Manager/diqiu.jpg"), JLabel.LEFT);
	    jp1_jp1_jl2.setFont(new Font("宋体",Font.BOLD, 15));
	    jp1_jp1_jl2.setForeground(Color.BLUE);
	    jp1_jp1_jl2.setBounds(220, 10, 120, 38); 
	    jp1_jp1_jl2.addMouseListener(this);
	    jp1_jp1.add(jp1_jp1_jl2);
	    jp1_jp1_jl3 = new JLabel(" 查询中心", new ImageIcon("Image1/Manager/chaxun.jpg"), JLabel.LEFT);
	    jp1_jp1_jl3.setFont(new Font("宋体",Font.BOLD, 15));
	    jp1_jp1_jl3.setForeground(Color.BLUE);
	    jp1_jp1_jl3.setBounds(370, 10, 120, 38);
	    jp1_jp1_jl3.addMouseListener(this);
	    jp1_jp1.add(jp1_jp1_jl3);
	    //jp1_jp2
	    jp1_jp2 = new JPanel();
	    jp1_jp2.setLayout(null);
	    jp1_jp2.setBounds(0, 115, 1200, 40);
	    jp1_jp2_jl1 = new JLabel(new ImageIcon("Image1/Manager/shousuo.jpg"));
	    jp1_jp2_jl1.setBounds(320, 0, 47, 40);
	    jp1_jp2.add(jp1_jp2_jl1);
	    jp1_jp2_jl2 = new JLabel("账号：");
	    jp1_jp2_jl2.setBounds(390, 0, 40, 40);
	    jp1_jp2.add(jp1_jp2_jl2);
	    uTxt1 = new JTextField(20);
	    uTxt1.setBounds(435, 5, 130, 30);
	    jp1_jp2.add(uTxt1);
	    //jp1_jp2上四个按钮
	    jp1_jb2_btn1 = new JButton(new ImageIcon("Image1/Manager/cx.jpg"));
	    jp1_jb2_btn1.setBounds(595, 5, 55, 31);
	    jp1_jb2_btn2 = new JButton(new ImageIcon("Image1/Manager/zj.jpg"));
	    jp1_jb2_btn2.setBounds(670, 5, 56, 30);
	    jp1_jb2_btn3 = new JButton(new ImageIcon("Image1/Manager/sc.jpg"));
	    jp1_jb2_btn3.setBounds(746, 5, 55, 31);
	    jp1_jb2_btn4 = new JButton(new ImageIcon("Image1/Manager/xg.jpg"));
	    jp1_jb2_btn4.setBounds(820, 5, 55, 31);
	    jp1_jb2_btn1.addActionListener(this);
	    jp1_jb2_btn2.addActionListener(this);
	    jp1_jb2_btn3.addActionListener(this);
	    jp1_jb2_btn4.addActionListener(this);
	    jp1_jp2.add(jp1_jb2_btn1);
	    jp1_jp2.add(jp1_jb2_btn2);
	    jp1_jp2.add(jp1_jb2_btn3);
	    jp1_jp2.add(jp1_jb2_btn4);
	    //jp1_jp3,北边
	    jp1_jp3 = new JPanel();
	    jp1_jp3.setLayout(null);
	    jp1_jp3.setBounds(0, 0, 1200, 35);
	    jp1_jp3_btn1 = new JButton(new ImageIcon("Image1/Manager/tuichu.jpg"));
	    jp1_jp3_btn1.setBorderPainted(false);
	    jp1_jp3_btn1.addActionListener(this);
	    jp1_jp3_btn1.setBounds(1138, 0, 61, 34);
	    jp1_jp3.add(jp1_jp3_btn1);
	    jp1_jp3_jl1 = new JLabel("欢迎您!  " +dao.getManager(id).getName());
	    jp1_jp3_jl1.setBounds(800, 5, 300, 35);
	    jp1_jp3.add(jp1_jp3_jl1);
	    jp1_jp3_jl2 = new JLabel(new ImageIcon("Image1/Manager/biaoti.jpg"));
	    jp1_jp3_jl2.setBounds(0, 0, 170, 28);
	    jp1_jp3.add(jp1_jp3_jl2);
	    //最底下的灰色面板jp1_jp4
	    jp1_jp4 = new JPanel();
	    jp1_jp4.setBounds(0, 680, 1200, 20);
	    jp1_jp4.setBackground(Color.BLUE);
	    //jp1_jl1
	    jp1_jl1 = new JLabel(new ImageIcon("Image1/Manager/lanse.jpg"));
	    jp1_jl1.setBounds(0, 84, 1200, 30);
	    //将jp1_jp1  jp1_jp2 jp1_jp3 jp1_jl1添加到jp1
	    jp1.add(jp1_jl1);
	    jp1.add(jp1_jp2);
	    jp1.add(jp1_jp3);
	    jp1.add(jp1_jp4);
	    jp1.add(jp1_jp1);
	}
	//处理第二张卡片（管理中心）
	public void SecondCard(){
		jp2 = new JPanel();
		jp2.setLayout(null);
		jp2.setBounds(0, 0, 1200, 700);
		//处理jp2北边面板jp2_1
		jp2_1 = new JPanel();
		jp2_1.setLayout(null);
		jp2_1.setBounds(0, 0, 1200, 114);
		jp2_1.setBackground(Color.WHITE);
		jp2.add(jp2_1);
		jp2_1_jl1 = new JLabel(" 基本功能", new ImageIcon("Image1/Manager/jibentubiao.jpg"), JLabel.LEFT);
		jp2_1_jl1.setFont(new Font("宋体",Font.BOLD, 15));
		jp2_1_jl1.setForeground(Color.BLUE);
		jp2_1_jl1.addMouseListener(this);
		jp2_1_jl1.setBounds(40, 42, 120, 38);
		jp2_1.add(jp2_1_jl1);
		jp2_1_jl2 = new JLabel( new ImageIcon("Image1/Manager/guanlizhongxin.jpg"));
		jp2_1_jl2.setBounds(230, 36, 131, 50); 
		jp2_1.add(jp2_1_jl2);
		jp2_1_jl3 = new JLabel(" 查询中心", new ImageIcon("Image1/Manager/chaxun.jpg"), JLabel.LEFT);
		jp2_1_jl3.setFont(new Font("宋体",Font.BOLD, 15));
		jp2_1_jl3.setForeground(Color.BLUE);
		jp2_1_jl3.setBounds(420, 41, 120, 38);
		jp2_1_jl3.addMouseListener(this);
		jp2_1.add(jp2_1_jl3);
		jp2_1_jp1= new JPanel();
		jp2_1_jp1.setLayout(null);
		jp2_1_jp1.setBounds(0, 0, 1200, 37);
		jp2_1.add(jp2_1_jp1);
		jp2_1_jp1_jl1 = new JLabel(new ImageIcon("Image1/Manager/biaoti.jpg"));
		jp2_1_jp1_jl1.setBounds(0, 2, 170, 28);
		jp2_1_jp1.add(jp2_1_jp1_jl1);
		jp2_1_jp1_btn1 = new JButton(new ImageIcon("Image1/Manager/tuichu.jpg"));
		jp2_1_jp1_btn1.setBorderPainted(false);
		jp2_1_jp1_btn1.addActionListener(this);
		jp2_1_jp1_btn1.setBounds(1138, 0, 61, 34);
		jp2_1_jp1.add(jp2_1_jp1_btn1);
		jp2_1_jp1_jl2 = new JLabel("欢迎您!  " +dao.getManager(id).getName());
		jp2_1_jp1_jl2.setBounds(800, 5, 300, 35);
		jp2_1_jp1.add(jp2_1_jp1_jl2);
		jp2_1_jp2= new JPanel();
		jp2_1_jp2.setLayout(null);
		jp2_1_jp2.setBounds(0, 82, 1200, 32);
		jp2_1.add(jp2_1_jp2);
		jp2_1_jp2_jl1 = new JLabel(new ImageIcon("Image1/Manager/lanse.jpg"));
		jp2_1_jp2_jl1.setBounds(0, 0, 1200, 30);
		jp2_1_jp2.add(jp2_1_jp2_jl1);
		//处理西边的jp2_2
		jp2_2 = new JPanel();
		jp2_2.setLayout(null);
		jp2_2.setBounds(0, 114, 270,586 );
		jp2_2.setBackground(Color.WHITE);
		jp2.add(jp2_2);
		jp2_2_jl1 = new JLabel(new ImageIcon("Image1/Manager/tiaowen.jpg"));
		jp2_2_jl1.setBounds(0, 30, 270, 30);
		jp2_2.add(jp2_2_jl1);
		jp2_2_jl2 = new JLabel(new ImageIcon("Image1/Manager/tiaowen.jpg"));
		jp2_2_jl2.setBounds(0, 90, 270, 30);
		jp2_2.add(jp2_2_jl2);
		jp2_2_jl3 = new JLabel(new ImageIcon("Image1/Manager/tiaowen.jpg"));
		jp2_2_jl3.setBounds(0, 150, 270, 30);
		jp2_2.add(jp2_2_jl3);
		jp2_2_jl4 = new JLabel("后台用户管理");
		jp2_2_jl4.setBounds(88, 0, 100, 30);
		jp2_2_jl4.setFont(new Font("宋体",Font.BOLD, 15));
		jp2_2_jl4.setForeground(Color.BLUE);
		jp2_2_jl4.addMouseListener(this);
		jp2_2.add(jp2_2_jl4);
		jp2_2_jl5 = new JLabel("服务器管理");
		jp2_2_jl5.setBounds(88, 60, 100, 30);
		jp2_2_jl5.setFont(new Font("宋体",Font.BOLD, 15));
		jp2_2_jl5.setForeground(Color.BLUE);
		jp2_2_jl5.addMouseListener(this);
		jp2_2.add(jp2_2_jl5);
		jp2_2_jl6 = new JLabel("数据库管理");
		jp2_2_jl6.setBounds(88, 120, 100, 30);
		jp2_2_jl6.setFont(new Font("宋体",Font.BOLD, 15));
		jp2_2_jl6.setForeground(Color.BLUE);
		jp2_2_jl6.addMouseListener(this);
		jp2_2.add(jp2_2_jl6);
		//处理东边的面板jp2_3
		jp2_3 = new JPanel();
		jp2_3.setBounds(270, 114, 930,586 );
		jp2.add(jp2_3);
		jp2_3_jp1 = new JPanel();
		jp2_3_jp2_open = new JButton("打开服务器");
		jp2_3_jp2_open.addActionListener(this);
		jp2_3_jp2_close = new JButton("关闭服务器");	
		jp2_3_jp2_close.addActionListener(this);
		jp2_3_jp1.add(jp2_3_jp2_open);
		jp2_3_jp1.add(jp2_3_jp2_close);
		jp2_3_jp2 = new JPanel();
		jp2_3_jp2.setBackground(Color.red);
		jp2_3_jp3 = new JPanel();
		jp2_3_jp3.setBackground(Color.YELLOW);
		cl1 = new CardLayout();
		jp2_3.setLayout(cl1);
		jp2_3.add(jp2_3_jp1,"1");
		jp2_3.add(jp2_3_jp2,"2");
		jp2_3.add(jp2_3_jp3,"3");
	}
	//处理第三张卡片（查询中心）
	public void ThirdCard(){
		jp3 = new JPanel();
		jp3.setLayout(null);
		jp3.setBounds(0, 0, 1200, 700);
		//处理jp3北边面板jp3_1
		jp3_1 = new JPanel();
		jp3_1.setLayout(null);
		jp3_1.setBounds(0, 0, 1200, 114);
		jp3_1.setBackground(Color.WHITE);
		jp3.add(jp3_1);
		jp3_1_jl1 = new JLabel(" 基本功能", new ImageIcon("Image1/Manager/jibentubiao.jpg"), JLabel.LEFT);
		jp3_1_jl1.setFont(new Font("宋体",Font.BOLD, 15));
		jp3_1_jl1.setForeground(Color.BLUE);
		jp3_1_jl1.addMouseListener(this);
		jp3_1_jl1.setBounds(40, 42, 120, 38);
		jp3_1.add(jp3_1_jl1);
		jp3_1_jl2 = new JLabel(" 管理中心", new ImageIcon("Image1/Manager/diqiu.jpg"), JLabel.LEFT);
		jp3_1_jl2.setBounds(230, 41, 120, 38); 
		jp3_1_jl2.setFont(new Font("宋体",Font.BOLD, 15));
		jp3_1_jl2.setForeground(Color.BLUE);
		jp3_1_jl2.addMouseListener(this);
		jp3_1.add(jp3_1_jl2);
		jp3_1_jl3 = new JLabel( new ImageIcon("Image1/Manager/chaxunzhongxin.jpg"));
		jp3_1_jl3.setBounds(420, 37, 136, 45);
		jp3_1.add(jp3_1_jl3);
		jp3_1_jp1= new JPanel();
		jp3_1_jp1.setLayout(null);
		jp3_1_jp1.setBounds(0, 0, 1200, 37);
		jp3_1.add(jp3_1_jp1);
		jp3_1_jp1_jl1 = new JLabel(new ImageIcon("Image1/Manager/biaoti.jpg"));
		jp3_1_jp1_jl1.setBounds(0, 2, 170, 28);
		jp3_1_jp1.add(jp3_1_jp1_jl1);
		jp3_1_jp1_btn1 = new JButton(new ImageIcon("Image1/Manager/tuichu.jpg"));
		jp3_1_jp1_btn1.setBorderPainted(false);
		jp3_1_jp1_btn1.addActionListener(this);
		jp3_1_jp1_btn1.setBounds(1138, 0, 61, 34);
		jp3_1_jp1.add(jp3_1_jp1_btn1);
		jp3_1_jp1_jl2 = new JLabel("欢迎您!  " +dao.getManager(id).getName());
		jp3_1_jp1_jl2.setBounds(800, 5, 300, 35);
		jp3_1_jp1.add(jp3_1_jp1_jl2);
		jp3_1_jp2= new JPanel();
		jp3_1_jp2.setLayout(null);
		jp3_1_jp2.setBounds(0, 82, 1200, 32);
		jp3_1.add(jp3_1_jp2);
		jp3_1_jp2_jl1 = new JLabel(new ImageIcon("Image1/Manager/lanse.jpg"));
		jp3_1_jp2_jl1.setBounds(0, 0, 1200, 30);
		jp3_1_jp2.add(jp3_1_jp2_jl1);
		//处理西边的jp2_2
		jp3_2 = new JPanel();
		jp3_2.setLayout(null);
		jp3_2.setBounds(0, 114, 270,586 );
		jp3_2.setBackground(Color.WHITE);
		jp3.add(jp3_2);
		jp3_2_jl1 = new JLabel(new ImageIcon("Image1/Manager/tiaowen.jpg"));
		jp3_2_jl1.setBounds(0, 30, 270, 30);
		jp3_2.add(jp3_2_jl1);
		jp3_2_jl2 = new JLabel(new ImageIcon("Image1/Manager/tiaowen.jpg"));
		jp3_2_jl2.setBounds(0, 90, 270, 30);
		jp3_2.add(jp3_2_jl2);
		jp3_2_jl3 = new JLabel(new ImageIcon("Image1/Manager/tiaowen.jpg"));
		jp3_2_jl3.setBounds(0, 150, 270, 30);
		jp3_2.add(jp3_2_jl3);
		jp3_2_jl4 = new JLabel("用户信息");
		jp3_2_jl4.setBounds(88, 0, 100, 30);
		jp3_2_jl4.setFont(new Font("宋体",Font.BOLD, 15));
		jp3_2_jl4.setForeground(Color.BLUE);
		jp3_2_jl4.addMouseListener(this);
		jp3_2.add(jp3_2_jl4);
		jp3_2_jl5 = new JLabel("管理员信息");
		jp3_2_jl5.setBounds(88, 60, 100, 30);
		jp3_2_jl5.setFont(new Font("宋体",Font.BOLD, 15));
		jp3_2_jl5.setForeground(Color.BLUE);
		jp3_2_jl5.addMouseListener(this);
		jp3_2.add(jp3_2_jl5);
		jp3_2_jl6 = new JLabel("系统信息");
		jp3_2_jl6.setBounds(88, 120, 100, 30);
		jp3_2_jl6.setFont(new Font("宋体",Font.BOLD, 15));
		jp3_2_jl6.setForeground(Color.BLUE);
		jp3_2_jl6.addMouseListener(this);
		jp3_2.add(jp3_2_jl6);
		jp3_3 = new JPanel();
		jp3_3.setBounds(270, 114, 930,586 );
		jp3.add(jp3_3);
		jp3_3_jp1 = new JPanel();
		jp3_3_jp1.setBackground(Color.WHITE);
		jp3_3_jp2 = new JPanel();
		jp3_3_jp2.setBackground(Color.red);
		jp3_3_jp3 = new JPanel();
		jp3_3_jp3.setBackground(Color.YELLOW);
		cl2 = new CardLayout();
		jp3_3.setLayout(cl2);
		jp3_3.add(jp3_3_jp1,"1");
		jp3_3.add(jp3_3_jp2,"2");
		jp3_3.add(jp3_3_jp3,"3");
	}
	//事件处理
	public void actionPerformed(ActionEvent e) {
		//第一张卡片按钮
		//查询
		if(e.getSource()==jp1_jb2_btn1){
			if(dao.NumRecord()==0){
				JOptionPane.showMessageDialog(this, "无记录可查询！");
			}else{
				if(uTxt1.getText().equals("")){
					JOptionPane.showMessageDialog(this, "请输入查询信息");
				}else{
					Vector number = new Vector();
					for(int i=0;i<dao.NumRecord();i++){
						if(tableModel.getValueAt(i, 0).toString().equals(uTxt1.getText())){
							number.addElement(new Integer(i));
						}
					}
					if(number.size() == 1){
							int i = ((Integer)(number.get(0))).intValue();
							table.setRowSelectionInterval(i, i);
							table.setSelectionForeground(Color.RED);
							uTxt1.setText("");	
					}
					if(number.size() > 1){
						data = dao.getRecord(uTxt1.getText());
						new ServerManager(data,id);
						uTxt1.setText("");
					}
				}
			}		
		}
		//增加
        if(e.getSource()==jp1_jb2_btn2){
        	int oldNum = dao.NumRecord();                         //原来的记录条数
        	AddRecord add = new AddRecord(oldNum,id);
		}
        //删除
        if(e.getSource()==jp1_jb2_btn3){
        	if(table.getSelectedRow()==-1){
        		JOptionPane.showMessageDialog(this, "请先选择要被删除的记录！");
        	}else{
        		LoginRecord record = new LoginRecord();
        		record.setUserId(tableModel.getValueAt(table.getSelectedRow(), 0).toString());
        		record.setTimeLogin(table.getValueAt(table.getSelectedRow(), 2).toString());
        		dao.deleteRecord(record);
        		this.dispose();
        		if(dao.NumRecord()==0){
        			new ServerManager(dao.getAllRecord(),id);
        			JOptionPane.showMessageDialog(this, "删除成功，当前记录为空！");
        		}else{
        			new ServerManager(dao.getAllRecord(),id);
            		JOptionPane.showMessageDialog(this, "删除成功！");
        		}	
        	}
        }
        //修改
        if(e.getSource()==jp1_jb2_btn4){
        	if(table.getSelectedRow()==-1){
        		JOptionPane.showMessageDialog(this, "请先选择要被修改的记录！");
        	}else{
        		LoginRecord record = new LoginRecord();
        		record.setUserId(tableModel.getValueAt(table.getSelectedRow(), 0).toString());
        		record.setName(tableModel.getValueAt(table.getSelectedRow(), 1).toString());
        		record.setTimeLogin(tableModel.getValueAt(table.getSelectedRow(), 2).toString());
        		record.setTimeLeft(tableModel.getValueAt(table.getSelectedRow(), 3).toString());
        		record.setTimeLength(tableModel.getValueAt(table.getSelectedRow(), 4).toString());
        		record.setIp(tableModel.getValueAt(table.getSelectedRow(), 5).toString());
        		record.setState(tableModel.getValueAt(table.getSelectedRow(), 6).toString());
        		new UpdateRecord(record,id);
            	this.dispose();
        	}	
        }
        //退出界面
        if(e.getSource()==jp1_jp3_btn1){
        	this.dispose();
        }
        //处理第二张卡片按钮
        //退出界面
        if(e.getSource()==jp2_1_jp1_btn1){
        	this.dispose();
        }
        QqServer server = new QqServer();
        //打开服务器
        if(e.getSource()==jp2_3_jp2_open){
			new Thread(server).start();
        }
        //关闭服务器
        if(e.getSource()==jp2_3_jp2_close){
			//new Thread(server).interrupt();
        }
        
        //处理第三张卡片按钮
        if(e.getSource()==jp3_1_jp1_btn1){
        	this.dispose();
        }
	}
	public void mouseClicked(MouseEvent e) {
//第一张卡片按钮
		//管理中心
		if(e.getClickCount()==1&&e.getSource()== jp1_jp1_jl2){
			cl.show(jp, "2");
	     }
		//查询中心
		if(e.getClickCount()==1&&e.getSource()== jp1_jp1_jl3){
			cl.show(jp, "3");
	     }
//处理第二张卡片按钮
		//基本功能
		if(e.getClickCount()==1&&e.getSource()== jp2_1_jl1){
			cl.show(jp, "1");
	     }
		//第二张卡片上的jp2_2上的卡片按钮jp2_2_jl4（后台用户管理）
		if(e.getClickCount()==1&&e.getSource()== jp2_2_jl4){
			cl1.show(jp2_3, "1");
	     }
		//第二张卡片上的jp2_2上的卡片按钮jp2_2_jl5（服务器管理）
		if(e.getClickCount()==1&&e.getSource()== jp2_2_jl5){
			cl1.show(jp2_3, "2");
	     }
		//第二张卡片上的jp2_2上的卡片按钮jp2_2_jl6（数据库管理）
		if(e.getClickCount()==1&&e.getSource()== jp2_2_jl6){
			cl1.show(jp2_3, "3");
	     }
		//查询中心
		if(e.getClickCount()==1&&e.getSource()== jp2_1_jl3){
			cl.show(jp, "3");
	     }
//处理第三张卡片按钮
		if(e.getClickCount()==1&&e.getSource()== jp3_1_jl1){
			cl.show(jp, "1");
	     }
		//查询中心
		if(e.getClickCount()==1&&e.getSource()== jp3_1_jl2){
			cl.show(jp, "2");
	     }
		//第三张卡片上的jp3_2上的卡片按钮jp3_2_jl4（用户信息）
		if(e.getClickCount()==1&&e.getSource()== jp3_2_jl4){
			cl2.show(jp3_3, "1");
	     }
		//第三张卡片上的jp3_2上的卡片按钮jp3_2_jl5（管理员信息）
		if(e.getClickCount()==1&&e.getSource()== jp3_2_jl5){
			cl2.show(jp3_3, "2");
	     }
		//第三张卡片上的jp3_2上的卡片按钮jp3_2_jl6（系统信息）
		if(e.getClickCount()==1&&e.getSource()== jp3_2_jl6){
			cl2.show(jp3_3, "3");
	     }
	}
	public void mouseEntered(MouseEvent e) {
		JLabel j1 = (JLabel)e.getSource();
		j1.setForeground(Color.RED);
	}
	public void mouseExited(MouseEvent e) {
		JLabel j1 = (JLabel)e.getSource();
		j1.setForeground(Color.BLUE);
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
}
