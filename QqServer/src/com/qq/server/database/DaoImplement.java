package com.qq.server.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import com.qq.common.LoginRecord;
import com.qq.common.Manager;
import com.qq.common.User;
import com.qq.server.tools.DaoException;
import com.qq.server.tools.jdbcTools;
/*
 * @���ߣ������
 * @���ܣ��Խӿ�Dao��ʵ�� 
 */
public class DaoImplement implements Dao 
{
/***********************�û����ʽӿڵ�ʵ��***********************/
	//getUser��findUser���õĲ��ִ��룬��ߴ����������
	private User mappingUser(ResultSet rs) throws SQLException
	{
		User user = new User();
		user.setUserId(rs.getString("id"));
		user.setPasswd(rs.getString("password"));
		user.setName(rs.getString("name"));
		user.setSex(rs.getString("sex"));
		user.setAge(rs.getString("age"));
		user.setPwd_protect1(rs.getString("pwd_protect1"));
		user.setPwd_protect2(rs.getString("pwd_protect2"));
		user.setPwd_protect3(rs.getString("pwd_protect3"));
		return user;
	}
	//��ѯָ���û�
	public User getUser(String userId) 
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="select * from user where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,userId);
			//ִ�����
			rs = ps.executeQuery();
			//������
			while(rs.next())	
			{
				user = mappingUser(rs);
			}
	    }
	   catch(SQLException e)
		{
			throw new DaoException(e.getMessage(),e);
		}
	   finally
	   {
		   jdbcTools.free(rs, ps, conn);
	   }
		return user;
    }
	//��ѯ�����û�
	public void getAllUser()
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="select * from user";
			ps = conn.prepareStatement(sql);
			//ִ�����
			rs = ps.executeQuery();
			//������
			while(rs.next())	
			{
				System.out.println(
						rs.getString("id")+"\t"+rs.getString("password")+"\t"+rs.getString("name")
						+"\t"+rs.getString("sex")+"\t"+rs.getString("age")+"\t"+rs.getString("pwd_protect1")
						+"\t"+rs.getString("pwd_protect2")+"\t"+rs.getString("pwd_protect3")
						          );	
			}
	   }
	   catch(SQLException e)
		{
			throw new DaoException(e.getMessage(),e);
		}
	   finally
	   {
			  jdbcTools.free(rs, ps, conn);   
	   }
	}
	//�û���¼�����ң�
	public User findUser(String userId,String userPwd)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="select * from user where id=? and password=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,userId);
			ps.setString(2,userPwd);
			rs = ps.executeQuery();
			//������
			while(rs.next())	
			{
				user = mappingUser(rs);
			}
	   }
	   catch(SQLException e)
		{
			throw new DaoException(e.getMessage(),e);
		}
		finally
		{
			   jdbcTools.free(rs, ps, conn);
		}
	
		return user;
	}
	//����û�
	public void addUser(User user)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="insert into user(id,password,name,sex,age,pwd_protect1,pwd_protect2,pwd_protect3) values(?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1,user.getUserId());
			ps.setString(2, user.getPasswd());
			ps.setString(3,user.getName());
			ps.setString(4, user.getSex());
			ps.setString(5, user.getAge());
			ps.setString(6, user.getPwd_protect1());
			ps.setString(7, user.getPwd_protect2());
			ps.setString(8, user.getPwd_protect3());
			//ִ�����
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			throw new DaoException(e.getMessage(),e);
		}
	   finally
	   {
		   jdbcTools.free(rs, ps, conn);
	   }
	}
	//�޸��û�
	public void updateUser(User user) 
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="update user set password=?,name=?,sex=?,age=?,pwd_protect1=?,pwd_protect2=?,pwd_protect3=? where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getPasswd());
			ps.setString(2, user.getName());
			ps.setString(3, user.getSex());
			ps.setString(4, user.getSex());
			ps.setString(5, user.getPwd_protect1());
			ps.setString(6, user.getPwd_protect2());
			ps.setString(7, user.getPwd_protect3());
			ps.setString(8, user.getUserId());
			//ִ�����
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			throw new DaoException(e.getMessage(),e);
		}
	   finally
	   {
		   jdbcTools.free(rs, ps, conn);
	   }
	}
	//ɾ��ָ���û�
	public void deleteUser(User user)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="delete from user where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,user.getUserId());
			//ִ�����
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			throw new DaoException(e.getMessage(),e);
		}
	   finally
	   {
		   jdbcTools.free(rs, ps, conn);
	   }
	}
	public int SumNum(){
		int Total = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="select count(*) from user";
			ps = conn.prepareStatement(sql);
			//ִ�����
			rs = ps.executeQuery();
			//������
			while(rs.next())	
			{
				Total = rs.getInt(1);
			}
	   }
	   catch(SQLException e)
		{
			throw new DaoException(e.getMessage(),e);
		}
	   finally
	   {
			  jdbcTools.free(rs, ps, conn);   
	   }
		return Total;
	}
/***********************��¼��¼���ʽӿڵ�ʵ��***********************/
	//ȷ�������ݿ��ܲ�ѯ���ĵ�¼��¼�������˴������ܲ�ѯ���ݿ�ǰ1000��
	private int numberCount()
	{
		int n;
		for(n=0;n<300;n++)
		{
		}
		return n;
	}
	//
	private LoginRecord mappingRecord(ResultSet rs) throws SQLException
	{
		LoginRecord record = new LoginRecord();
		record.setUserId(rs.getString("id"));
		record.setName(rs.getString("name"));
		record.setTimeLogin(rs.getString("time_login"));
		record.setTimeLeft(rs.getString("time_left"));
		record.setTimeLength(rs.getString("time_length"));
		record.setIp(rs.getString("ip"));
		record.setState(rs.getString("state"));
		return record;
	}
	//ͨ���û�ID��ѯָ���û��ĵ�¼��¼
	public Object[][] getRecord(String userId){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
    	Object[][] data = new Object[0][];
    	try
    	{
    		conn = jdbcTools.getConnection();
    		int n = numberCount();
    		data = new Object[n][];
    		String sql = "select * from login_record where id=?";
    		ps = conn.prepareStatement(sql);
			ps.setString(1,userId);
    		rs = ps.executeQuery();
    		int i = 0;
    		while(rs.next())
    		{
    			Object[] recordData = new Object[] {rs.getString("id"),
    					rs.getString("name"),rs.getString("time_login"),rs.getString("time_left"),rs.getString("time_length")
    					,rs.getString("ip"),rs.getString("state")};
    			data[i] = recordData;
        		i++;
    		}
    	}
    	catch(SQLException e)
		{
			throw new DaoException(e.getMessage(),e);
		}
	    finally
	   {
		   jdbcTools.free(rs, ps, conn);
	   }
    	
		return data;	
	}
	//ͨ���û�ID��ѯָ���û��ĵ�¼��¼������Vector���������
	public Vector getRecordToLogin(String userId){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		LoginRecord record = null;
		Vector checkList = new Vector<LoginRecord>();
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="select * from login_record where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,userId);
			//ִ�����
			rs = ps.executeQuery();
			//������
			while(rs.next())	
			{
				record = mappingRecord(rs);
				checkList.add(record);
			}
	    }
	   catch(SQLException e)
		{
			throw new DaoException(e.getMessage(),e);
		}
	   finally
	   {
		   jdbcTools.free(rs, ps, conn);
	   }
		return checkList;
	}
	//��ȡ���е�¼��¼
	public  Object[][] getAllRecord(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
    	Object[][] data = new Object[0][];
    	try
    	{
    		conn = jdbcTools.getConnection();
    		int n = numberCount();
    		data = new Object[n][];
    		String sql = "select * from login_record ";
    		ps = conn.prepareStatement(sql);
    		rs = ps.executeQuery();
    		int i = 0;
    		while(rs.next())
    		{
    			Object[] recordData = new Object[] {rs.getString("id"),
    					rs.getString("name"),rs.getString("time_login"),rs.getString("time_left"),rs.getString("time_length")
    					,rs.getString("ip"),rs.getString("state")};
    			data[i] = recordData;
        		i++;
    		}
    	}
    	catch(SQLException e)
		{
			throw new DaoException(e.getMessage(),e);
		}
	    finally
	   {
		   jdbcTools.free(rs, ps, conn);
	   }
    	
		return data;	
	}
    //����û���¼��¼
	public void addRecord(LoginRecord record){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="insert into login_record(id,name,time_login,time_left,time_length,ip,state) values(?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, record.getUserId());
			ps.setString(2, record.getName());
			ps.setString(3,record.getTimeLogin());
			ps.setString(4, record.getTimeLeft());
			ps.setString(5, record.getTimeLength());
			ps.setString(6, record.getIp());
			ps.setString(7, record.getState());
			//ִ�����
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			throw new DaoException(e.getMessage(),e);
		}
	   finally
	   {
		   jdbcTools.free(rs, ps, conn);
	   }
	}
	//��������̨�޸��û���¼��¼
	public void updateRecord(LoginRecord record){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="update login_record set name=?,time_login=?,time_left=?,time_length=?,ip=?,state=? where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, record.getName());
			ps.setString(2,record.getTimeLogin());
			ps.setString(3, record.getTimeLeft());
			ps.setString(4, record.getTimeLength());
			ps.setString(5, record.getIp());
			ps.setString(6, record.getState());
			ps.setString(7, record.getUserId());
			//ִ�����
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			throw new DaoException(e.getMessage(),e);
		}
	   finally
	   {
		   jdbcTools.free(rs, ps, conn);
	   }
	}
	//�û�����ʱ�޸��û��ĵ�¼��¼
	public void updateRecordLeft(LoginRecord record){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="update login_record set name=?,time_left=?,time_length=?,ip=?,state=? where id=? and time_login=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, record.getName());
			ps.setString(2, record.getTimeLeft());
			ps.setString(3, record.getTimeLength());
			ps.setString(4, record.getIp());
			ps.setString(5, record.getState());
			ps.setString(6, record.getUserId());
			ps.setString(7, record.getTimeLogin());
			//ִ�����
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			throw new DaoException(e.getMessage(),e);
		}
	   finally
	   {
		   jdbcTools.free(rs, ps, conn);
	   }
	}
	//ɾ���û���¼��¼(��ֹɾ��ID��ͬ�ļ�¼�����¼�ĵ�¼ʱ�䲻ͬ���ʼ���time_login=?����)
	public void deleteRecord(LoginRecord record){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="delete from login_record where id=? and time_login=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,record.getUserId());
			ps.setString(2,record.getTimeLogin());
			//ִ�����
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			throw new DaoException(e.getMessage(),e);
		}
	   finally
	   {
		   jdbcTools.free(rs, ps, conn);
	   }
	}
	@Override
	public int NumRecord() {
		int Total = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="select count(*) from login_record";
			ps = conn.prepareStatement(sql);
			//ִ�����
			rs = ps.executeQuery();
			//������
			while(rs.next())	
			{
				Total = rs.getInt(1);
			}
	   }
	   catch(SQLException e)
		{
			throw new DaoException(e.getMessage(),e);
		}
	   finally
	   {
			  jdbcTools.free(rs, ps, conn);   
	   }
		return Total;
	}
/***********************����Ա���ʽӿڵ�ʵ��***********************/
	//��ѯָ������Ա
	public Manager getManager(String id){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Manager manager = new Manager();
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="select * from manager where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,id);
			//ִ�����
			rs = ps.executeQuery();
			//������
			while(rs.next())	
			{
				manager.setId(rs.getString("id"));
				manager.setPassword(rs.getString("password"));
				manager.setName(rs.getString("name"));
			}
	    }
	   catch(SQLException e)
		{
			throw new DaoException(e.getMessage(),e);
		}
	   finally
	   {
		   jdbcTools.free(rs, ps, conn);
	   }
		return manager;
	}
	//��ӹ���Ա
	public void addManager(Manager manager){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="insert into manager(id,password,name) values(?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1,manager.getId());
			ps.setString(2, manager.getPassword());
			ps.setString(3, manager.getName());
			//ִ�����
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			throw new DaoException(e.getMessage(),e);
		}
	   finally
	   {
		   jdbcTools.free(rs, ps, conn);
	   }
	}
    //�޸Ĺ���Ա
	public void updateManager(Manager manager){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="update manager set password=?,name=? where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, manager.getPassword());
			ps.setString(2, manager.getName());
			ps.setString(3, manager.getId());
			//ִ�����
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			throw new DaoException(e.getMessage(),e);
		}
	   finally
	   {
		   jdbcTools.free(rs, ps, conn);
	   }
	}
	//ɾ������Ա
	public void deleteManager(Manager manager){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="delete from manager where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,manager.getId());
			//ִ�����
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			throw new DaoException(e.getMessage(),e);
		}
	   finally
	   {
		   jdbcTools.free(rs, ps, conn);
	   }
	}
}