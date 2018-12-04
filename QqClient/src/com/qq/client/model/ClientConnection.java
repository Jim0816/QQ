/*
 * @作者：李际明
 * @功能：客服端与服务器交互后台
 */
package com.qq.client.model;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import com.qq.common.Message;
public class ClientConnection {
	//向服务器发送信息，并接收服务器的返回信息
	public Message sendInfo(Object o){
		Message message = new Message();
		try{
		    Socket s = new Socket("127.0.0.1",9999);
			//发出信息
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			System.out.println("客户端已经发出！！！");
			//接收信息
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			System.out.println("接收！！！");
			Message ms = (Message)ois.readObject();
			message = ms;
		}catch(Exception e){
			e.printStackTrace();
		}
		return message;
	}	
}
