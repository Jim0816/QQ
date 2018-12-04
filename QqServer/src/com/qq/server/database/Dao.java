/*
 * @作者：李际明
 * @功能：定义对数据库的公共访问接口
 */
package com.qq.server.database;
import java.util.ArrayList;
import java.util.Vector;

import com.qq.common.LoginRecord;
import com.qq.common.Manager;
import com.qq.common.User;
public interface Dao {
/***********************用户访问接口***********************/
	//查询指定用户
	public User getUser(String userId);
	//查询所有用户
	public void getAllUser();
	//用户登录（查找）
	public User findUser(String userId,String userPwd);
	//添加用户
	public void addUser(User user);
    //修改用户
	public void updateUser(User user);
	//删除指定用户
	public void deleteUser(User user);	
	//统计用户数量，注册账号按顺序分配
	public int SumNum();
/***********************登录记录访问接口***********************/
	//通过用户ID查询指定用户的登录记录
	public Object[][] getRecord(String userId);
	//通过用户ID查询指定用户的登录记录，放入Vector，方便遍历
	public Vector  getRecordToLogin(String userId);
	//提取所有用户的登录记录
	public  Object[][] getAllRecord();
    //添加用户的登录记录
	public void addRecord(LoginRecord record);
	//服务器后台修改用户的登录记录
	public void updateRecord(LoginRecord record);
	//用户下线时修改用户的登录记录
	public void updateRecordLeft(LoginRecord record);
	//删除用户的登录记录
	public void deleteRecord(LoginRecord record);
	//统计记录数量
	public int NumRecord();
	/***********************管理员访问接口***********************/
	//查询指定管理员
	public Manager getManager(String id);
	//添加管理员
	public void addManager(Manager manager);
    //修改管理员
	public void updateManager(Manager manager);
	//删除管理员
	public void deleteManager(Manager manager);	
}
