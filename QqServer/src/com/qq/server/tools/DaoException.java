/*
 * 作者：李际明
 * 功能：处理UserDao类的异常
 */
package com.qq.server.tools;
/*
 * 作者：李际明
 * 功能：处理UserDao类的异常
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
