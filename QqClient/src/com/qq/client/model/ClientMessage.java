/*
 * @���ߣ������
 * @���ܣ��ͻ��˴����û�ͨѶ����
 */
package com.qq.client.model;

import com.qq.common.Message;

public class ClientMessage {
	//������Ϣ
	Message message;
	public Message sendMessage(Message message){
		this.message = message;
		Message m = new ClientConnection().sendInfo(message);
		System.out.println("�ͻ��˷��ͳɹ�");
		return m;
	}
}
