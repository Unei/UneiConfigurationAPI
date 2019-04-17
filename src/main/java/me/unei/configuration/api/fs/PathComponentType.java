package me.unei.configuration.api.fs;

/**
 * Types of path component.
 */
public enum PathComponentType {

	/**
	 * A root component (the first '/' with Unix).
	 */
	ROOT(false),
	/**
	 * A parent component ('..' with Unix).
	 */
	PARENT(false),
	/**
	 * A child component ('/name/' with Unix).
	 * 
	 * <p>A child is always accompanied with a name.</p>
	 */
	CHILD(true),
	/**
	 * A table element component ('[index]').
	 * 
	 * <p>An index is always accompanied with an integer.</p>
	 */
	INDEX(false);
	
	public final boolean valuable;
	
	private PathComponentType(boolean val)
	{
		this.valuable = val;
	}

}