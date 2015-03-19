package Exceptions;

@SuppressWarnings("serial")
public class DBAppException extends Exception {
	
	public DBAppException()
	{
		
	}
	
	public DBAppException(String m)
	{
		super(m);
	}
	
}
