/*
 * @���ߣ������
 * @���ܣ��ͷ����������������̨
 */
package com.qq.client.model;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import com.qq.common.Message;
public class ClientConnection {
	//�������������Ϣ�������շ������ķ�����Ϣ
	public Message sendInfo(Object o){
		Message message = new Message();
		try{
		    Socket s = new Socket("127.0.0.1",9999);
			//������Ϣ
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			System.out.println("�ͻ����Ѿ�����������");
			//������Ϣ
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			System.out.println("���գ�����");
			Message ms = (Message)ois.readObject();
			message = ms;
		}catch(Exception e){
			e.printStackTrace();
		}
		return message;
	}	
}
