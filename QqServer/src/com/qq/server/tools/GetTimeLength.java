/*
 * @���ߣ������
 * @���ܣ������¼ʱ�������ʱ�䣬������û�����ʱ��ʱ��
 */
package com.qq.server.tools;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class GetTimeLength  {
    String timeLogin,timeLeft;
    String timeLength = null;
	public String GetTime(String timeLogin,String timeLeft ) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 = sdf.parse(timeLogin);
		Date date2 = sdf.parse(timeLeft);
		long l = date2.getTime() - date1.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
	    timeLength = day + "��" + hour + "Сʱ" + min +"��" + s + "��";
		return timeLength;
	}
}

