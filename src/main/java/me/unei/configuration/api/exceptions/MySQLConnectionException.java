package me.unei.configuration.api.exceptions;

/**
 * An exception thrown when a MySQL link could not be established.
 */
public class MySQLConnectionException extends FileFormatException
{
	private static final long serialVersionUID = 4921924935531636118L;

	public MySQLConnectionException(String address, int port, String tableName, String message)
	{
		super("The database " + address + ":" + port + " is not accessible for the table '" + tableName + "' : " + (message != null ? message : ""));
	}
	
	public MySQLConnectionException(String address, int port, String tableName, String message, Throwable t)
	{
		super("The database " + address + ":" + port + " is not accessible for the table '" + tableName + "' : " + (message != null ? message : ""), t);
	}
}