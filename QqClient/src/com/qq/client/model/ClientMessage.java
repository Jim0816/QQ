/*
 * @作者：李际明
 * @功能：客户端处理用户通讯问题
 */
package com.qq.client.model;

import com.qq.common.Message;

public class ClientMessage {
	//发送消息
	Message message;
	public Message sendMessage(Message message){
		this.message = message;
		Message m = new ClientConnection().sendInfo(message);
		System.out.println("客户端发送成功");
		return m;
	}
}
