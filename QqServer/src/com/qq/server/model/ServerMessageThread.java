/*
 * @���ߣ������
 * @���ܣ���������ͷ���ͨѶ�߳���
 */
package com.qq.server.model;

import java.net.*;
import java.io.*;
import java.net.Socket;
import com.qq.common.Message;

public class ServerMessageThread extends Thread{
	Socket s;
	//���캯��
	public ServerMessageThread(Socket s){
		this.s = s;
	}
	//run����
	public void run(){
		while(true){
			try {
				System.out.println("�Ѿ������߳�");
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				System.out.println("��һ��");
				Message m = (Message)ois.readObject();
				System.out.println("�ڶ���");
				System.out.println(m.getSender()+" �� "+m.getGetter()+" ˵ "+m.getContent());
				//ת������.....
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
