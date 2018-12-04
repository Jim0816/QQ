package com.qq.server.database;
import com.qq.common.LoginRecord;
public class Test {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Dao dao = new DaoImplement();
		LoginRecord record = new LoginRecord();
		record.setUserId("1");
		record.setName("888");
		record.setTimeLogin("2");
		record.setTimeLeft("1");
		record.setTimeLength("888");
		record.setIp("888");
		record.setState("999");
		dao.updateRecordLeft(record);
	}
}
