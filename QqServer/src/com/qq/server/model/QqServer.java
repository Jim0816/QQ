/*
 * @���ߣ������
 * @���ܣ�QQ���������ȴ��ͷ�����
 */
package com.qq.server.model;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import com.qq.common.Message;
import com.qq.common.User;
import com.qq.server.database.Dao;
import com.qq.server.database.DaoImplement;
public class QqServer implements Runnable{
	Message message = new Message();
	public static void main(String[] args) {
		QqServer server = new QqServer();
		new Thread(server).start();
	}
	//���캯��
	public QqServer(){
	}
	//���տͷ����˵ĵ�¼��Ϣ,��������Ϣ
	public void ReceiveInfo(){
		String ip = null;
		try{
			System.out.println("��������9999�˿ڵȴ�����...");
			ServerSocket ss = new ServerSocket(9999);
			while(true){
				Socket s = ss.accept(); 
				System.out.println(s.getInetAddress().getHostAddress()+"�ͻ��˳ɹ����ӵ�������");
				ip = s.getInetAddress().getHostAddress();
				//���ܿͷ��˵���Ϣ
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				User user = (User)ois.readObject();
				//������Ϣ
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				    //ע��
					if(user.getType().equals("0")){
						oos.writeObject(new ServerCheck().NewId(user));
					    s.close();
					}
					//��¼
					if(user.getType().equals("1")){
						oos.writeObject(new ServerCheck().LoginCheck(user,ip));
					    s.close();
					}
					//�����Ƿ����ظ���¼���������ߣ�
					if(user.getType().equals("2")){
						System.out.println("���ڼ��");
						oos.writeObject(new ServerCheck().Checkme(user,ip));
						s.close();
					}
					//��������
					if(user.getType().equals("3")){
						oos.writeObject(new ServerCheck().LoginCheck(user,ip));
					    s.close();
					}
					if(user.getType().equals("4")){
						//ͨѶ�߳�
						ServerMessageThread smt = new ServerMessageThread(s);
						smt.start();
						System.out.println("�߳�������");
					}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void run() {
		ReceiveInfo();
	}
}
