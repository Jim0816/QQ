/*
 * @作者：李际明
 * @功能:涉及用户信息的管理
 */
package com.qq.client.model;
import com.qq.common.Message;
import com.qq.common.User;
public class ClientUser {
	//验证登录用户信息
	public User CheckUser(User user){
		User u = new User();
		Message message = new ClientConnection().sendInfo(user);
		//System.out.println("客户检验："+message.getUserId());
        //登录成功后服务器把用户信息给用户
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
	//发送新用户的注册信息
	public String CheckNewUser(User user){
		Message message = new Message();
		String id;
		message = new ClientConnection().sendInfo(user);
		id = message.getUserId();
		return id;
	}
}
