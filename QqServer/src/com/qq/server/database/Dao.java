/*
 * @���ߣ������
 * @���ܣ���������ݿ�Ĺ������ʽӿ�
 */
package com.qq.server.database;
import java.util.ArrayList;
import java.util.Vector;

import com.qq.common.LoginRecord;
import com.qq.common.Manager;
import com.qq.common.User;
public interface Dao {
/***********************�û����ʽӿ�***********************/
	//��ѯָ���û�
	public User getUser(String userId);
	//��ѯ�����û�
	public void getAllUser();
	//�û���¼�����ң�
	public User findUser(String userId,String userPwd);
	//����û�
	public void addUser(User user);
    //�޸��û�
	public void updateUser(User user);
	//ɾ��ָ���û�
	public void deleteUser(User user);	
	//ͳ���û�������ע���˺Ű�˳�����
	public int SumNum();
/***********************��¼��¼���ʽӿ�***********************/
	//ͨ���û�ID��ѯָ���û��ĵ�¼��¼
	public Object[][] getRecord(String userId);
	//ͨ���û�ID��ѯָ���û��ĵ�¼��¼������Vector���������
	public Vector  getRecordToLogin(String userId);
	//��ȡ�����û��ĵ�¼��¼
	public  Object[][] getAllRecord();
    //����û��ĵ�¼��¼
	public void addRecord(LoginRecord record);
	//��������̨�޸��û��ĵ�¼��¼
	public void updateRecord(LoginRecord record);
	//�û�����ʱ�޸��û��ĵ�¼��¼
	public void updateRecordLeft(LoginRecord record);
	//ɾ���û��ĵ�¼��¼
	public void deleteRecord(LoginRecord record);
	//ͳ�Ƽ�¼����
	public int NumRecord();
	/***********************����Ա���ʽӿ�***********************/
	//��ѯָ������Ա
	public Manager getManager(String id);
	//��ӹ���Ա
	public void addManager(Manager manager);
    //�޸Ĺ���Ա
	public void updateManager(Manager manager);
	//ɾ������Ա
	public void deleteManager(Manager manager);	
}
