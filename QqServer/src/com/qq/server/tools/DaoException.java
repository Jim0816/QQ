/*
 * ���ߣ������
 * ���ܣ�����UserDao����쳣
 */
package com.qq.server.tools;
/*
 * ���ߣ������
 * ���ܣ�����UserDao����쳣
 */
public class DaoException extends RuntimeException 
{
	private static final long serialVersionUID = 1L;
	public DaoException() 
	{
	}
	public DaoException(String message) 
	{
		super(message);
	}
	public DaoException(Throwable cause) 
	{
		super(cause);
	}
	public DaoException(String message, Throwable cause) 
	{
		super(message, cause);
	}
	public DaoException(String message, Throwable cause, boolean arg2, boolean arg3)
	{
		super(message, cause);
	}
}
