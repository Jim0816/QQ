/*
 * @作者：李际明
 * @功能：QQ服务器，等待客服连接
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
	//构造函数
	public QqServer(){
	}
	//接收客服户端的登录信息,并返回信息
	public void ReceiveInfo(){
		String ip = null;
		try{
			System.out.println("服务器在9999端口等待连接...");
			ServerSocket ss = new ServerSocket(9999);
			while(true){
				Socket s = ss.accept(); 
				System.out.println(s.getInetAddress().getHostAddress()+"客户端成功连接到服务器");
				ip = s.getInetAddress().getHostAddress();
				//接受客服端的信息
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				User user = (User)ois.readObject();
				//发出信息
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				    //注册
					if(user.getType().equals("0")){
						oos.writeObject(new ServerCheck().NewId(user));
					    s.close();
					}
					//登录
					if(user.getType().equals("1")){
						oos.writeObject(new ServerCheck().LoginCheck(user,ip));
					    s.close();
					}
					//检验是否有重复登录（被迫下线）
					if(user.getType().equals("2")){
						System.out.println("我在检查");
						oos.writeObject(new ServerCheck().Checkme(user,ip));
						s.close();
					}
					//自行下线
					if(user.getType().equals("3")){
						oos.writeObject(new ServerCheck().LoginCheck(user,ip));
					    s.close();
					}
					if(user.getType().equals("4")){
						//通讯线程
						ServerMessageThread smt = new ServerMessageThread(s);
						smt.start();
						System.out.println("线程已启动");
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
