/*
 * @���ߣ������
 * @���ܣ��û���Ϣ��
 */
package com.qq.common;
import java.io.Serializable;
public class User implements Serializable{
	private String userId;
	private String passwd;
	private String name;
	private String sex;
	private String age;
	private String pwd_protect1;
	private String pwd_protect2;
	private String pwd_protect3;
	private String state;
	private String timeLogin;
	private String type;
	//�û�ID
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	//�û�����
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	//�û���
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//�Ա�
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	//����
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	//�����ܱ�����Ĵ�
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
	//����״̬
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	//��¼ʱ��
	public String getTimeLogin() {
		return timeLogin;
	}
	public void setTimeLogin(String timeLogin) {
		this.timeLogin = timeLogin;
	}
	//�û���������
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
