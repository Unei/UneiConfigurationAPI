package me.unei.configuration.api.exceptions;

/**
 * An exception thrown when a configuration node is no more valid.
 */
public class InvalidNodeException extends RuntimeException
{
	private static final long serialVersionUID = -1164490659799147312L;

    /** Constructs a new invalid node exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
	public InvalidNodeException()
	{
		super();
	}

    /** Constructs a new invalid node exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param   message   the detail message. The detail message is saved for
     *          later retrieval by the {@link #getMessage()} method.
     */
	public InvalidNodeException(String message)
	{
		super(message);
	}

    /**
     * Constructs a new invalid node exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this invalid node exception's detail message.
     *
     * @param  message the detail message (which is saved for later retrieval
     *         by the {@link #getMessage()} method).
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A <tt>null</tt> value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     */
	public InvalidNodeException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
