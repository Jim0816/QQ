/*
 * @���ߣ������
 * @����:�漰�û���Ϣ�Ĺ���
 */
package com.qq.client.model;
import com.qq.common.Message;
import com.qq.common.User;
public class ClientUser {
	//��֤��¼�û���Ϣ
	public User CheckUser(User user){
		User u = new User();
		Message message = new ClientConnection().sendInfo(user);
		//System.out.println("�ͻ����飺"+message.getUserId());
        //��¼�ɹ�����������û���Ϣ���û�
		u.setUserId(message.getUserId());
		u.setPasswd(message.getPasswd());
		u.setName(message.getName());
		u.setSex(message.getSex());
		u.setAge(message.getAge());
		u.setPwd_protect1(message.getPwd_protect1());
		u.setPwd_protect2(message.getPwd_protect2());
		u.setPwd_protect3(message.getPwd_protect3());
		u.setState(message.getState());
		u.setTimeLogin(message.getTimeLogin());
	    return u;
	}
	//�������û���ע����Ϣ
	public String CheckNewUser(User user){
		Message message = new Message();
		String id;
		message = new ClientConnection().sendInfo(user);
		id = message.getUserId();
		return id;
	}
}
