package me.unei.configuration.api.fs;

/**
 * Types of path component.
 */
public enum PathComponentType {
	
	/**
	 * A root component (the first '/' with Unix).
	 */
	ROOT,
	/**
	 * A parent component ('..' with Unix).
	 */
	PARENT,
	/**
	 * A child component ('/name/' with Unix).
	 * 
	 * <p>A child is always accompanied with a name.</p>
	 */
	CHILD,
	/**
	 * A table element component ('[index]').
	 * 
	 * <p>An index is always accompanied with an integer.</p>
	 */
	INDEX;
}