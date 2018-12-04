/*
 * @���ߣ������
 * @���ܣ���������̨
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
	//�������
    private CardLayout cl;
    private JPanel jp;
	//��һ�ſ�Ƭ
	private JPanel jp1;
	private JPanel jp1_jp1,jp1_jp2,jp1_jp3,jp1_jp4;
	private JLabel jp1_jl1,jp1_jp1_jl1,jp1_jp1_jl2,jp1_jp1_jl3;
	private JLabel jp1_jp2_jl1,jp1_jp2_jl2,jp1_jp3_jl1,jp1_jp3_jl2;
	private JTextField uTxt1;
	private JButton jp1_jb2_btn1,jp1_jb2_btn2,jp1_jb2_btn3,jp1_jb2_btn4,jp1_jp3_btn1;
	private JTable table;
	public JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	//�ڶ��ſ�Ƭ
	private CardLayout cl1;
	private JPanel jp2;
	private JPanel jp2_1,jp2_2,jp2_3;
	private JLabel jp2_1_jl1,jp2_1_jl2,jp2_1_jl3;
	private JPanel jp2_1_jp1,jp2_1_jp2;            //jp2_1���������
	private JLabel jp2_1_jp1_jl1,jp2_1_jp1_jl2,jp2_1_jp2_jl1;
	private JButton jp2_1_jp1_btn1;
	private JPanel jp2_3_jp1,jp2_3_jp2,jp2_3_jp3;        //jp2_3�����ſ�Ƭ
	private JButton jp2_3_jp2_open,jp2_3_jp2_close;
	//private JButton jp2_1_btn1,jp2_1_btn2,jp2_1_btn3;    //jp2_3�ϵ�������ť
	private JLabel jp2_2_jl1,jp2_2_jl2,jp2_2_jl3,jp2_2_jl4,jp2_2_jl5,jp2_2_jl6;
	//�����ſ�Ƭ
	private CardLayout cl2;
	private JPanel jp3;
	private JPanel jp3_1,jp3_2,jp3_3;
	private JLabel jp3_1_jl1,jp3_1_jl2,jp3_1_jl3;
	private JPanel jp3_1_jp1,jp3_1_jp2;            //jp3_1���������
	private JLabel jp3_1_jp1_jl1,jp3_1_jp1_jl2,jp3_1_jp2_jl1;
	private JButton jp3_1_jp1_btn1;
	private JPanel jp3_3_jp1,jp3_3_jp2,jp3_3_jp3;        //jp3_3�����ſ�Ƭ
	//private JButton jp3_1_btn1,jp3_1_btn2,jp3_1_btn3;    //jp3_3�ϵ�������ť
	private JLabel jp3_2_jl1,jp3_2_jl2,jp3_2_jl3,jp3_2_jl4,jp3_2_jl5,jp3_2_jl6;
	//�������ñ���
	int xOld,yOld;	
	Dao dao = new DaoImplement();
	Object[][] data; 
	String id;
	public static void main(String[] args){
		Dao dao = new DaoImplement();
		new ServerManager(dao.getAllRecord(),"1");
	}
	//���캯��
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
	//��ʼ������
	public void intiControlWindows() {
		this.setSize(1200,700);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	//����߿򷽷�
	public void JFrameNoBorder() { 
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		//img.setImageObserver(null);
		//this.setSize(( img).getIconWidth(),img.getIconHeight()); 
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
		ServerManager.this.setLocation(xx, yy);//������ק�󣬴��ڵ�λ��
		    }
		   });
		   this.setUndecorated(true);
		 }
	//�����һ�ſ�Ƭ���������ܣ�
	public void firstCard(){
		jp1 = new JPanel();
		jp1.setLayout(null);
		jp1.setBounds(0, 0, 1200, 700);
		jp1.setBackground(Color.WHITE);
		//������
		//������������
		String[] columnNames = {"�˺�","����","��¼ʱ��","����ʱ��","����ʱ��","��¼��IP��ַ","��ǰ״̬"};             
		Dao dao = new DaoImplement();
		//���ģ�ͻ�ȡ����
		tableModel = new DefaultTableModel (data,columnNames);
		//��ʼ�����
		table = new JTable(tableModel){
			public boolean isCellEditable(int rowIndex, int ColIndex){
	             return false;
			}
		};                                                              //���ģ��ʵ����,�������ñ�񲻿ɱ��༭
		table.setAutoCreateRowSorter(true);                             //���ñ��������
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);    //����Ϊ��ѡģʽ
		table.setForeground(Color.BLUE);                                //���ñ��ǰ��ɫ
		table.setSelectionForeground(Color.RED);                        //���ñ�ѡ���е�ǰ��ɫ
	    table.setRowHeight(23);                                         //���ñ���и�
	    //�ѱ����ӵ��������
	    scrollPane = new JScrollPane(table);
	    scrollPane.setBounds(0,200,1200,480);
	    //�������ӵ�jp1
	    jp1.add(scrollPane);
	    //������ѡ��Ƭ��ť�����jp1_jp1
	    //jp1_jp1
	    jp1_jp1 = new JPanel();
	    jp1_jp1.setBackground(Color.WHITE);
	    jp1_jp1.setBounds(0, 35, 1200, 80);
	    jp1_jp1.setLayout(null);
	    jp1_jp1_jl1 = new JLabel(new ImageIcon("Image1/Manager/jibengongneng.jpg"));
	    jp1_jp1_jl1.setBounds(40, 3, 131, 52);
	    jp1_jp1.add(jp1_jp1_jl1);
	    jp1_jp1_jl2 = new JLabel(" ��������", new ImageIcon("Image1/Manager/diqiu.jpg"), JLabel.LEFT);
	    jp1_jp1_jl2.setFont(new Font("����",Font.BOLD, 15));
	    jp1_jp1_jl2.setForeground(Color.BLUE);
	    jp1_jp1_jl2.setBounds(220, 10, 120, 38); 
	    jp1_jp1_jl2.addMouseListener(this);
	    jp1_jp1.add(jp1_jp1_jl2);
	    jp1_jp1_jl3 = new JLabel(" ��ѯ����", new ImageIcon("Image1/Manager/chaxun.jpg"), JLabel.LEFT);
	    jp1_jp1_jl3.setFont(new Font("����",Font.BOLD, 15));
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
	    jp1_jp2_jl2 = new JLabel("�˺ţ�");
	    jp1_jp2_jl2.setBounds(390, 0, 40, 40);
	    jp1_jp2.add(jp1_jp2_jl2);
	    uTxt1 = new JTextField(20);
	    uTxt1.setBounds(435, 5, 130, 30);
	    jp1_jp2.add(uTxt1);
	    //jp1_jp2���ĸ���ť
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
	    //jp1_jp3,����
	    jp1_jp3 = new JPanel();
	    jp1_jp3.setLayout(null);
	    jp1_jp3.setBounds(0, 0, 1200, 35);
	    jp1_jp3_btn1 = new JButton(new ImageIcon("Image1/Manager/tuichu.jpg"));
	    jp1_jp3_btn1.setBorderPainted(false);
	    jp1_jp3_btn1.addActionListener(this);
	    jp1_jp3_btn1.setBounds(1138, 0, 61, 34);
	    jp1_jp3.add(jp1_jp3_btn1);
	    jp1_jp3_jl1 = new JLabel("��ӭ��!  " +dao.getManager(id).getName());
	    jp1_jp3_jl1.setBounds(800, 5, 300, 35);
	    jp1_jp3.add(jp1_jp3_jl1);
	    jp1_jp3_jl2 = new JLabel(new ImageIcon("Image1/Manager/biaoti.jpg"));
	    jp1_jp3_jl2.setBounds(0, 0, 170, 28);
	    jp1_jp3.add(jp1_jp3_jl2);
	    //����µĻ�ɫ���jp1_jp4
	    jp1_jp4 = new JPanel();
	    jp1_jp4.setBounds(0, 680, 1200, 20);
	    jp1_jp4.setBackground(Color.BLUE);
	    //jp1_jl1
	    jp1_jl1 = new JLabel(new ImageIcon("Image1/Manager/lanse.jpg"));
	    jp1_jl1.setBounds(0, 84, 1200, 30);
	    //��jp1_jp1  jp1_jp2 jp1_jp3 jp1_jl1��ӵ�jp1
	    jp1.add(jp1_jl1);
	    jp1.add(jp1_jp2);
	    jp1.add(jp1_jp3);
	    jp1.add(jp1_jp4);
	    jp1.add(jp1_jp1);
	}
	//����ڶ��ſ�Ƭ���������ģ�
	public void SecondCard(){
		jp2 = new JPanel();
		jp2.setLayout(null);
		jp2.setBounds(0, 0, 1200, 700);
		//����jp2�������jp2_1
		jp2_1 = new JPanel();
		jp2_1.setLayout(null);
		jp2_1.setBounds(0, 0, 1200, 114);
		jp2_1.setBackground(Color.WHITE);
		jp2.add(jp2_1);
		jp2_1_jl1 = new JLabel(" ��������", new ImageIcon("Image1/Manager/jibentubiao.jpg"), JLabel.LEFT);
		jp2_1_jl1.setFont(new Font("����",Font.BOLD, 15));
		jp2_1_jl1.setForeground(Color.BLUE);
		jp2_1_jl1.addMouseListener(this);
		jp2_1_jl1.setBounds(40, 42, 120, 38);
		jp2_1.add(jp2_1_jl1);
		jp2_1_jl2 = new JLabel( new ImageIcon("Image1/Manager/guanlizhongxin.jpg"));
		jp2_1_jl2.setBounds(230, 36, 131, 50); 
		jp2_1.add(jp2_1_jl2);
		jp2_1_jl3 = new JLabel(" ��ѯ����", new ImageIcon("Image1/Manager/chaxun.jpg"), JLabel.LEFT);
		jp2_1_jl3.setFont(new Font("����",Font.BOLD, 15));
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
		jp2_1_jp1_jl2 = new JLabel("��ӭ��!  " +dao.getManager(id).getName());
		jp2_1_jp1_jl2.setBounds(800, 5, 300, 35);
		jp2_1_jp1.add(jp2_1_jp1_jl2);
		jp2_1_jp2= new JPanel();
		jp2_1_jp2.setLayout(null);
		jp2_1_jp2.setBounds(0, 82, 1200, 32);
		jp2_1.add(jp2_1_jp2);
		jp2_1_jp2_jl1 = new JLabel(new ImageIcon("Image1/Manager/lanse.jpg"));
		jp2_1_jp2_jl1.setBounds(0, 0, 1200, 30);
		jp2_1_jp2.add(jp2_1_jp2_jl1);
		//�������ߵ�jp2_2
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
		jp2_2_jl4 = new JLabel("��̨�û�����");
		jp2_2_jl4.setBounds(88, 0, 100, 30);
		jp2_2_jl4.setFont(new Font("����",Font.BOLD, 15));
		jp2_2_jl4.setForeground(Color.BLUE);
		jp2_2_jl4.addMouseListener(this);
		jp2_2.add(jp2_2_jl4);
		jp2_2_jl5 = new JLabel("����������");
		jp2_2_jl5.setBounds(88, 60, 100, 30);
		jp2_2_jl5.setFont(new Font("����",Font.BOLD, 15));
		jp2_2_jl5.setForeground(Color.BLUE);
		jp2_2_jl5.addMouseListener(this);
		jp2_2.add(jp2_2_jl5);
		jp2_2_jl6 = new JLabel("���ݿ����");
		jp2_2_jl6.setBounds(88, 120, 100, 30);
		jp2_2_jl6.setFont(new Font("����",Font.BOLD, 15));
		jp2_2_jl6.setForeground(Color.BLUE);
		jp2_2_jl6.addMouseListener(this);
		jp2_2.add(jp2_2_jl6);
		//�����ߵ����jp2_3
		jp2_3 = new JPanel();
		jp2_3.setBounds(270, 114, 930,586 );
		jp2.add(jp2_3);
		jp2_3_jp1 = new JPanel();
		jp2_3_jp2_open = new JButton("�򿪷�����");
		jp2_3_jp2_open.addActionListener(this);
		jp2_3_jp2_close = new JButton("�رշ�����");	
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
	//��������ſ�Ƭ����ѯ���ģ�
	public void ThirdCard(){
		jp3 = new JPanel();
		jp3.setLayout(null);
		jp3.setBounds(0, 0, 1200, 700);
		//����jp3�������jp3_1
		jp3_1 = new JPanel();
		jp3_1.setLayout(null);
		jp3_1.setBounds(0, 0, 1200, 114);
		jp3_1.setBackground(Color.WHITE);
		jp3.add(jp3_1);
		jp3_1_jl1 = new JLabel(" ��������", new ImageIcon("Image1/Manager/jibentubiao.jpg"), JLabel.LEFT);
		jp3_1_jl1.setFont(new Font("����",Font.BOLD, 15));
		jp3_1_jl1.setForeground(Color.BLUE);
		jp3_1_jl1.addMouseListener(this);
		jp3_1_jl1.setBounds(40, 42, 120, 38);
		jp3_1.add(jp3_1_jl1);
		jp3_1_jl2 = new JLabel(" ��������", new ImageIcon("Image1/Manager/diqiu.jpg"), JLabel.LEFT);
		jp3_1_jl2.setBounds(230, 41, 120, 38); 
		jp3_1_jl2.setFont(new Font("����",Font.BOLD, 15));
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
		jp3_1_jp1_jl2 = new JLabel("��ӭ��!  " +dao.getManager(id).getName());
		jp3_1_jp1_jl2.setBounds(800, 5, 300, 35);
		jp3_1_jp1.add(jp3_1_jp1_jl2);
		jp3_1_jp2= new JPanel();
		jp3_1_jp2.setLayout(null);
		jp3_1_jp2.setBounds(0, 82, 1200, 32);
		jp3_1.add(jp3_1_jp2);
		jp3_1_jp2_jl1 = new JLabel(new ImageIcon("Image1/Manager/lanse.jpg"));
		jp3_1_jp2_jl1.setBounds(0, 0, 1200, 30);
		jp3_1_jp2.add(jp3_1_jp2_jl1);
		//�������ߵ�jp2_2
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
		jp3_2_jl4 = new JLabel("�û���Ϣ");
		jp3_2_jl4.setBounds(88, 0, 100, 30);
		jp3_2_jl4.setFont(new Font("����",Font.BOLD, 15));
		jp3_2_jl4.setForeground(Color.BLUE);
		jp3_2_jl4.addMouseListener(this);
		jp3_2.add(jp3_2_jl4);
		jp3_2_jl5 = new JLabel("����Ա��Ϣ");
		jp3_2_jl5.setBounds(88, 60, 100, 30);
		jp3_2_jl5.setFont(new Font("����",Font.BOLD, 15));
		jp3_2_jl5.setForeground(Color.BLUE);
		jp3_2_jl5.addMouseListener(this);
		jp3_2.add(jp3_2_jl5);
		jp3_2_jl6 = new JLabel("ϵͳ��Ϣ");
		jp3_2_jl6.setBounds(88, 120, 100, 30);
		jp3_2_jl6.setFont(new Font("����",Font.BOLD, 15));
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
	//�¼�����
	public void actionPerformed(ActionEvent e) {
		//��һ�ſ�Ƭ��ť
		//��ѯ
		if(e.getSource()==jp1_jb2_btn1){
			if(dao.NumRecord()==0){
				JOptionPane.showMessageDialog(this, "�޼�¼�ɲ�ѯ��");
			}else{
				if(uTxt1.getText().equals("")){
					JOptionPane.showMessageDialog(this, "�������ѯ��Ϣ");
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
		//����
        if(e.getSource()==jp1_jb2_btn2){
        	int oldNum = dao.NumRecord();                         //ԭ���ļ�¼����
        	AddRecord add = new AddRecord(oldNum,id);
		}
        //ɾ��
        if(e.getSource()==jp1_jb2_btn3){
        	if(table.getSelectedRow()==-1){
        		JOptionPane.showMessageDialog(this, "����ѡ��Ҫ��ɾ���ļ�¼��");
        	}else{
        		LoginRecord record = new LoginRecord();
        		record.setUserId(tableModel.getValueAt(table.getSelectedRow(), 0).toString());
        		record.setTimeLogin(table.getValueAt(table.getSelectedRow(), 2).toString());
        		dao.deleteRecord(record);
        		this.dispose();
        		if(dao.NumRecord()==0){
        			new ServerManager(dao.getAllRecord(),id);
        			JOptionPane.showMessageDialog(this, "ɾ���ɹ�����ǰ��¼Ϊ�գ�");
        		}else{
        			new ServerManager(dao.getAllRecord(),id);
            		JOptionPane.showMessageDialog(this, "ɾ���ɹ���");
        		}	
        	}
        }
        //�޸�
        if(e.getSource()==jp1_jb2_btn4){
        	if(table.getSelectedRow()==-1){
        		JOptionPane.showMessageDialog(this, "����ѡ��Ҫ���޸ĵļ�¼��");
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
        //�˳�����
        if(e.getSource()==jp1_jp3_btn1){
        	this.dispose();
        }
        //����ڶ��ſ�Ƭ��ť
        //�˳�����
        if(e.getSource()==jp2_1_jp1_btn1){
        	this.dispose();
        }
        QqServer server = new QqServer();
        //�򿪷�����
        if(e.getSource()==jp2_3_jp2_open){
			new Thread(server).start();
        }
        //�رշ�����
        if(e.getSource()==jp2_3_jp2_close){
			//new Thread(server).interrupt();
        }
        
        //��������ſ�Ƭ��ť
        if(e.getSource()==jp3_1_jp1_btn1){
        	this.dispose();
        }
	}
	public void mouseClicked(MouseEvent e) {
//��һ�ſ�Ƭ��ť
		//��������
		if(e.getClickCount()==1&&e.getSource()== jp1_jp1_jl2){
			cl.show(jp, "2");
	     }
		//��ѯ����
		if(e.getClickCount()==1&&e.getSource()== jp1_jp1_jl3){
			cl.show(jp, "3");
	     }
//����ڶ��ſ�Ƭ��ť
		//��������
		if(e.getClickCount()==1&&e.getSource()== jp2_1_jl1){
			cl.show(jp, "1");
	     }
		//�ڶ��ſ�Ƭ�ϵ�jp2_2�ϵĿ�Ƭ��ťjp2_2_jl4����̨�û�����
		if(e.getClickCount()==1&&e.getSource()== jp2_2_jl4){
			cl1.show(jp2_3, "1");
	     }
		//�ڶ��ſ�Ƭ�ϵ�jp2_2�ϵĿ�Ƭ��ťjp2_2_jl5������������
		if(e.getClickCount()==1&&e.getSource()== jp2_2_jl5){
			cl1.show(jp2_3, "2");
	     }
		//�ڶ��ſ�Ƭ�ϵ�jp2_2�ϵĿ�Ƭ��ťjp2_2_jl6�����ݿ����
		if(e.getClickCount()==1&&e.getSource()== jp2_2_jl6){
			cl1.show(jp2_3, "3");
	     }
		//��ѯ����
		if(e.getClickCount()==1&&e.getSource()== jp2_1_jl3){
			cl.show(jp, "3");
	     }
//��������ſ�Ƭ��ť
		if(e.getClickCount()==1&&e.getSource()== jp3_1_jl1){
			cl.show(jp, "1");
	     }
		//��ѯ����
		if(e.getClickCount()==1&&e.getSource()== jp3_1_jl2){
			cl.show(jp, "2");
	     }
		//�����ſ�Ƭ�ϵ�jp3_2�ϵĿ�Ƭ��ťjp3_2_jl4���û���Ϣ��
		if(e.getClickCount()==1&&e.getSource()== jp3_2_jl4){
			cl2.show(jp3_3, "1");
	     }
		//�����ſ�Ƭ�ϵ�jp3_2�ϵĿ�Ƭ��ťjp3_2_jl5������Ա��Ϣ��
		if(e.getClickCount()==1&&e.getSource()== jp3_2_jl5){
			cl2.show(jp3_3, "2");
	     }
		//�����ſ�Ƭ�ϵ�jp3_2�ϵĿ�Ƭ��ťjp3_2_jl6��ϵͳ��Ϣ��
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
