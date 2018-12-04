/*
 * @���ߣ������
 * @���ܣ�����ͻ��˴���������(�����ݿ���н���)
 */
package com.qq.server.model;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Vector;
import com.qq.common.LoginRecord;
import com.qq.common.Message;
import com.qq.common.User;
import com.qq.server.database.Dao;
import com.qq.server.database.DaoImplement;
import com.qq.server.tools.GetTimeLength;
public class ServerCheck {
	//������������
	Dao dao = new DaoImplement();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
	String time=df.format(new Date());
	String ip;
	//�����¼��Ϣ
	public Message LoginCheck(User user,String ip){
		this.ip = ip;
		Message message = new Message(); 
		int num = dao.getRecordToLogin(user.getUserId()).size();       //���û���¼��¼��
		if(dao.findUser(user.getUserId(), user.getPasswd())==null){
			message.setState("false");
		}
		else{
			//�û���������
			if(user.getState().equals("true")){
			        //�������û���ǰ�Ƿ������߼�¼
					for(int i=0;i<num;i++){
						LoginRecord record =(LoginRecord) dao.getRecordToLogin(user.getUserId()).get(i);
						if(record.getState().equals("true")){
							//��������
							record.setTimeLeft(time);
							try {
								record.setTimeLength(new GetTimeLength().GetTime(record.getTimeLogin(), time) );
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							record.setState("false");
							dao.updateRecordLeft(record);	
					    }	
				  }
				//��Ӽ�¼�����ݿ�
				User u = new User();
				u = dao.findUser(user.getUserId(), user.getPasswd());
				LoginRecord Newrecord = new LoginRecord();
				Newrecord.setUserId(user.getUserId());
				Newrecord.setName(u.getName());
				Newrecord.setTimeLogin(time);
				Newrecord.setTimeLeft(" Loading...");
				Newrecord.setTimeLength(" Loading...");
				Newrecord.setIp(ip);
				Newrecord.setState("true");
				dao.addRecord(Newrecord);
				//��¼����ӣ��ظ��ͻ��˵�¼�ɹ�
				message.setUserId(u.getUserId());
				message.setPasswd(u.getPasswd());
				message.setName(u.getName());
				message.setSex(u.getSex());
				message.setAge(u.getAge());
				message.setPwd_protect1(u.getPwd_protect1());
				message.setPwd_protect2(u.getPwd_protect2());
				message.setPwd_protect3(u.getPwd_protect3());
				message.setState("true");	
			    message.setTimeLogin(time);
		    }
			//�û���������
			if(user.getState().equals("false")){
				LoginRecord record_2 = new LoginRecord();
				record_2.setUserId(user.getUserId());
				record_2.setName(user.getName());
				record_2.setTimeLogin(user.getTimeLogin());
				record_2.setTimeLeft(time);
				try {
					 GetTimeLength getLength = new GetTimeLength();
					record_2.setTimeLength(getLength.GetTime(user.getTimeLogin(), time));
				} catch (Exception e) {
					e.printStackTrace();
				}
				record_2.setIp(ip);
				record_2.setState("false");
				dao.updateRecordLeft(record_2);
				System.out.println("�޸ĳɹ�");
			}
		}
		return message;
	}
	//��������û����˺�,ͬʱ��ӵ����ݿ�
	public Message NewId(User user){
		Message message = new Message();
		int n = dao.SumNum();
		String id = String.valueOf(n+1);
		user.setUserId(id);
		user.setPasswd(user.getPasswd());
		user.setName(user.getName());
		dao.addUser(user);
		message.setUserId(id);
		return message;
	}
	//���û��û���ѯ�Լ��Ƿ��ظ���¼
	public Message Checkme(User user,String ip){
		this.ip = ip;
		System.out.println("��ʼ���");
		int flag = 0;               //����̨ǿ�����ߵı�־
		Message message = new Message(); 
		int num = dao.getRecordToLogin(user.getUserId()).size();       //���û���¼��¼��
		for(int i=0;i<num;i++){
			LoginRecord record =(LoginRecord) dao.getRecordToLogin(user.getUserId()).get(i);
			if(record.getState().equals("true")){
				flag = 1;
				if(record.getTimeLogin().equals(user.getTimeLogin())){
					message.setState("true");
				}else{
					message.setState("false");
				}
			  }
			}
		System.out.println("flag:"+flag);
		if(flag == 0){
			LoginRecord record_3 = new LoginRecord();
			record_3.setUserId(user.getUserId());
			record_3.setName(user.getName());
			record_3.setTimeLogin(user.getTimeLogin());
			record_3.setTimeLeft(time);
			try {
				 GetTimeLength getLength = new GetTimeLength();
				record_3.setTimeLength(getLength.GetTime(user.getTimeLogin(), time));
			} catch (Exception e) {
				e.printStackTrace();
			}
			record_3.setIp(ip);
			record_3.setState("false");
			dao.updateRecordLeft(record_3);
			message.setState("false");
		}
		return message;
	}
}
