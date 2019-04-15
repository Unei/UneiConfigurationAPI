package me.unei.configuration.api.exceptions;

import java.io.File;

/**
 * An exception thrown when a file format is not the one expected.
 */
public class FileFormatException extends Exception
{
	private static final long serialVersionUID = 3594972910118569236L;

    /** Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param   message   the detail message. The detail message is saved for
     *          later retrieval by the {@link #getMessage()} method.
     */
	FileFormatException(String message)
	{
		super(message);
	}

    /**
     * Constructs a new runtime exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this runtime exception's detail message.
     *
     * @param  message the detail message (which is saved for later retrieval
     *         by the {@link #getMessage()} method).
     * @param  t the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A <tt>null</tt> value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     */
	FileFormatException(String message, Throwable t)
	{
		super(message, t);
	}

    /** Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
	 * 
	 * @param requestedFormat The format expected from the file.
	 * @param file The file in the wrong format (cause of this error).
     * @param   message   the detail message. The detail message is saved for
     *          later retrieval by the {@link #getMessage()} method.
	 */
	public FileFormatException(String requestedFormat, File file, String message)
	{
		super("The file " + file.getAbsolutePath() + " is not in format " + requestedFormat + " : " + message);
	}

    /**
     * Constructs a new runtime exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this runtime exception's detail message.
	 * 
	 * @param requestedFormat The format expected from the file.
	 * @param file The file in the wrong format (cause of this error).
     * @param  message the detail message (which is saved for later retrieval
     *         by the {@link #getMessage()} method).
     * @param  t the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A <tt>null</tt> value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
	 */
	public FileFormatException(String requestedFormat, File file, String message, Throwable t)
	{
		super("The file " + file.getAbsolutePath() + " is not in format " + requestedFormat + " : " + message, t);
	}
}