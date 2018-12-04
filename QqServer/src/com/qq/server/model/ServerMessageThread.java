/*
 * @作者：李际明
 * @功能：服务器与客服的通讯线程类
 */
package com.qq.server.model;

import java.net.*;
import java.io.*;
import java.net.Socket;
import com.qq.common.Message;

public class ServerMessageThread extends Thread{
	Socket s;
	//构造函数
	public ServerMessageThread(Socket s){
		this.s = s;
	}
	//run函数
	public void run(){
		while(true){
			try {
				System.out.println("已经进入线程");
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				System.out.println("第一步");
				Message m = (Message)ois.readObject();
				System.out.println("第二步");
				System.out.println(m.getSender()+" 给 "+m.getGetter()+" 说 "+m.getContent());
				//转发任务.....
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
