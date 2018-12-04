package com.qq.common;
import java.io.Serializable;
public class LoginRecord implements Serializable{
	private String userId;
	private String name;
	private String timeLogin;
	private String timeLeft;
	private String timeLength;
	private String ip;
	private String state;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
