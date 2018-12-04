package com.qq.server.view;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.qq.common.LoginRecord;
import com.qq.server.database.Dao;
import com.qq.server.database.DaoImplement;
public class AddRecord extends JFrame implements MouseListener{
	//�������
	private JLabel jl1;
	private JTextField text1,text2,text3,text4,text5,text6,text7;
	private JButton btn;
	Dao dao = new DaoImplement();
	int num;
	String id;
	//�����������
    //���캯��
	public AddRecord(int num,String id){
        this.num = num;
        this.id = id;
		setTopic();
		setText();
		setButton();
		intiWindows();
	}
	//��ʼ������
	public void intiWindows() {
		this.setSize(800,200);
		this.setLayout(null);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.WHITE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	//��ӱ���
	public void setTopic(){
		jl1 = new JLabel(new ImageIcon("Image1/AddRecord/biaoti.jpg"));
		jl1.setBounds(316, 10, 160, 40);
		this.add(jl1);
	}
	//�����м��ı�������
	public void setText(){
		text1 = new JTextField("�˺�",15);
		text1.setBounds(5,80,90,23);
		text1.addMouseListener(this);
		this.add(text1);
		text2 = new JTextField("����",15);
		text2.setBounds(110,80,90,23);
		text2.addMouseListener(this);
		this.add(text2);
		text3 = new JTextField("��¼ʱ��",20);
		text3.setBounds(215,80,110,23);
		text3.addMouseListener(this);
		this.add(text3);
		text4 = new JTextField("����ʱ��",20);
		text4.setBounds(335,80,110,23);
		text4.addMouseListener(this);
		this.add(text4);
		text5 = new JTextField("����ʱ��",20);
		text5.setBounds(455,80,110,23);
		text5.addMouseListener(this);
		this.add(text5);
		text6 = new JTextField("��¼��IP��ַ",15);
		text6.setBounds(575,80,90,23);
		text6.addMouseListener(this);
		this.add(text6);
		text7 = new JTextField("��ǰ״̬",15);
		text7.setBounds(675,80,90,23);
		text7.addMouseListener(this);
		this.add(text7);
	}
	//����ȷ�ϰ�ť
	public void setButton(){
		btn = new JButton("ȷ��");
		btn.setBounds(316, 130, 160, 30);
		btn.addMouseListener(this);
		this.add(btn);
	}
	//�¼�����
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==1&&e.getSource()== btn){
			if(text1.getText().equals("")||text2.getText().equals("")||text3.getText().equals("")
					||text4.getText().equals("")||text5.getText().equals("")||text6.getText().equals("")
					||text7.getText().equals("")||text1.getText().equals("�˺�")||text2.getText().equals("����")||text3.getText().equals("��¼ʱ��")
					||text4.getText().equals("����ʱ��")||text5.getText().equals("����ʱ��")||text6.getText().equals("��¼��IP��ַ")
					||text7.getText().equals("��ǰ״̬")){
				JOptionPane.showMessageDialog(this, "��Ч��¼���޷���ӣ�");
			}else{
				LoginRecord record = new LoginRecord();
				record.setUserId(text1.getText());
	            record.setName(text2.getText());
	            record.setTimeLogin(text3.getText());
	            record.setTimeLeft(text4.getText());
	            record.setTimeLength(text5.getText());
	            record.setIp(text6.getText());
	            record.setState(text7.getText());
	            dao.addRecord(record);
	            int newNum = dao.NumRecord();
	            System.out.println("��Ӻ�"+newNum);
	            JOptionPane.showMessageDialog(this, "��ӳɹ���");
	            this.dispose();
	            if(newNum>num){
	            	new ServerManager(dao.getAllRecord(),id);
	            }
			}
		}
		if(e.getClickCount()==1&&e.getSource()== text1){
			if(text1.getText().equals("�˺�")){
				text1.setText("");
			}
		}
		if(e.getClickCount()==1&&e.getSource()== text2){
			if(text2.getText().equals("����")){
				text2.setText("");
			}
		}
		if(e.getClickCount()==1&&e.getSource()== text3){
			if(text3.getText().equals("��¼ʱ��")){
				text3.setText("");
			}
		}
		if(e.getClickCount()==1&&e.getSource()== text4){
			if(text4.getText().equals("����ʱ��")){
				text4.setText("");
			}
		}
		if(e.getClickCount()==1&&e.getSource()== text5){
			if(text5.getText().equals("����ʱ��")){
				text5.setText("");
			}
		}
		if(e.getClickCount()==1&&e.getSource()== text6){
			if(text6.getText().equals("��¼��IP��ַ")){
				text6.setText("");
			}
		}
		if(e.getClickCount()==1&&e.getSource()== text7){
			if(text7.getText().equals("��ǰ״̬")){
				text7.setText("");
			}
		}
	}
	public void mouseEntered(MouseEvent e) {
		if(e.getSource()==text3||e.getSource()==text4){
			JTextField text = (JTextField)e.getSource();
			text.setToolTipText("��ʽ��2017-08-16 18:15:30");
		}
		if(e.getSource()==text5){
			JTextField text = (JTextField)e.getSource();
			text.setToolTipText("��ʽ��xxxh ��6h");
		}
	}
	public void mouseExited(MouseEvent e) {	
	}
	public void mousePressed(MouseEvent e) {	
	}
	public void mouseReleased(MouseEvent e) {
	}
}
