package com.test;
import java.util.Date;
import java.text.SimpleDateFormat;
public class NowString {
public static void main(String[] args) { 
SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
String s = df.format(new Date());
System.out.println(s);// new Date()Ϊ��ȡ��ǰϵͳʱ��
}
}



