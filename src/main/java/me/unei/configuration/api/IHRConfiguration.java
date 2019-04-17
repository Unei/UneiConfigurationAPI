package me.unei.configuration.api;

public interface IHRConfiguration extends IConfiguration {

	/**
	 * Returns the representation as text of the data in this
	 * configuration, formatted as nicely as possible and in a
	 * way that is easy to read for a human.
	 *
	 * @return the formatted text representation of the data
	 */
	public String toFormattedString();

	/**
	 * Returns the representation as text of the data in this
	 * configuration, as short as possible and in a way that
	 * it is the smallest as possible, but still readable by
	 * a computer.
	 *
	 * @return the minimized text representation of the data
	 */
	public String toMinimizedString();

	/**
	 * Returns the representation as text of the data in this
	 * configuration, appointed for being saved inside a file
	 * and without indication on whether it should be formatted
	 * or minimized.
	 * <p>
	 * Usually, will be the formatted version to allow humans
	 * to read the contents of the file easily, but might be
	 * a minimized version or even one between both.
	 *
	 * @return the text representation of the data
	 */
	public String saveToString();

	/**
	 * Reads the required data from the specified string.
	 *
	 * The string can be the result of <tt>saveToString</tt>,
	 * <tt>toFormattedString</tt> or <tt>toMinimizedString</tt>,
	 * or can come from any other source provided that its format
	 * is correct and readable by a computer.
	 *
	 * @param data the text representation of the data
	 */
	public void loadFromString(String data);
}
