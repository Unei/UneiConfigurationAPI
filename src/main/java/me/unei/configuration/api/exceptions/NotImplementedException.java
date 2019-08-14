package me.unei.configuration.api.exceptions;

public class NotImplementedException extends RuntimeException
{
	private static final long serialVersionUID = -4743007127878886801L;

	public NotImplementedException() {
	}
	
	public NotImplementedException(final String message) {
		super(message);
	}
	
	public NotImplementedException(final Throwable cause) {
		super(cause);
	}
	
	public NotImplementedException(final String message, final Throwable cause) {
		super(message, cause);
	}
}