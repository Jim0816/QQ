/*
 * @作者：李际明
 * @功能：信息类
 */
package com.qq.common;
import java.io.Serializable;
public class Message implements Serializable{
	/*
	 * type为0：注册请求
	 * type为1：登录请求
	 * type为2：检查该用户是否重复登录
	 * type为3：用户自行下线
	 * type为4：用户通讯
	 */
	private User user;
	//用户信息、登录记录
	private String userId;
	private String name;
	private String type;
	//用户信息
	private String passwd;
	private String sex;
	private String age;
	private String pwd_protect1;
	private String pwd_protect2;
	private String pwd_protect3;
	//登录记录
	private String timeLogin;
	private String timeLeft;
	private String timeLength;
	private String ip;
	private String state;
	//通讯属性
	private String sender;
	private String getter;
	private String content;
	private String sendTime;
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getGetter() {
		return getter;
	}
	public void setGetter(String getter) {
		this.getter = getter;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getTimeLogin() {
		return timeLogin;
	}
	public void setTimeLogin(String timeLogin) {
		this.timeLogin = timeLogin;
	}
	public String getTimeLeft() {
		return timeLeft;
	}
	public void setTimeLeft(String timeLeft) {
		this.timeLeft = timeLeft;
	}
	public String getTimeLength() {
		return timeLength;
	}
	public void setTimeLength(String timeLength) {
		this.timeLength = timeLength;
	}
	public String getIp() {
		return ip;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getPwd_protect1() {
		return pwd_protect1;
	}
	public void setPwd_protect1(String pwdProtect1) {
		pwd_protect1 = pwdProtect1;
	}
	public String getPwd_protect2() {
		return pwd_protect2;
	}
	public void setPwd_protect2(String pwdProtect2) {
		pwd_protect2 = pwdProtect2;
	}
	public String getPwd_protect3() {
		return pwd_protect3;
	}
	public void setPwd_protect3(String pwdProtect3) {
		pwd_protect3 = pwdProtect3;
	}
}