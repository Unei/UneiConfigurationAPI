package me.unei.configuration.api.exceptions;

import me.unei.configuration.SavedFile;

/**
 * An exception thrown when an element of a configuration instance could not be found.
 * 
 * @see me.unei.configuration.api.IFlatConfiguration#contains(String)
 * 
 * @author JësFot
 * @since 1.5
 * @version 1.0
 */
public class NoFieldException extends Exception {
	private static final long serialVersionUID = 5787167801078410025L;

	/** Constructs a new exception with the specified detail message.
	 * The cause is not initialized, and may subsequently be initialized by a
	 * call to {@link #initCause}.
	 *
	 * @param   message   the detail message. The detail message is saved for
	 *          later retrieval by the {@link #getMessage()} method.
	 */
	NoFieldException(String message) {
		super(message);
	}

	/**
	 * Constructs a new exception with the specified detail message and
	 * cause.  <p>Note that the detail message associated with
	 * {@code cause} is <i>not</i> automatically incorporated in
	 * this exception's detail message.
	 *
	 * @param  message the detail message (which is saved for later retrieval
	 *         by the {@link #getMessage()} method).
	 * @param  t the cause (which is saved for later retrieval by the
	 *         {@link #getCause()} method).  (A <tt>null</tt> value is
	 *         permitted, and indicates that the cause is nonexistent or
	 *         unknown.)
	 */
	NoFieldException(String message, Throwable t) {
		super(message, t);
	}

	/** Constructs a new no field exception with the specified detail message.
	 * The cause is not initialized, and may subsequently be initialized by a
	 * call to {@link #initCause}.
	 * 
	 * @param requestedKey The key of the field that is not available.
	 * @param file The file in i witch the key does not exists.
	 * @param message The detail message. It is added to the end of the generated message.
	 */
	public NoFieldException(String requestedKey, SavedFile file, String message) {
		super("The key " + requestedKey + " does not exists in file " + file.getFullName() + " : " + message);
	}
}