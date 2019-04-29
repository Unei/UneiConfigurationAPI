package me.unei.configuration.formats;

/**
 * The different storage types available for a node.
 * 
 * @author JësFot
 * @since 1.2.8
 */
public enum StorageType
{
	/**
	 * The storage is a mapping (key -> value) using a {@linkplain String} as a key.
	 */
	MAP,
	/**
	 * The storage is an ordered list (index -> value).
	 */
	LIST,
	/**
	 * The storage is a list in which indexes are not necessarily following each others.
	 * <p>
	 * Implementation proposition : A mapping (key -> value) using {@linkplain Integer} as keys.
	 */
	DISCONTINUED_LIST,
	/**
	 * Used to mark when a type of storage is unknown, or when there is no storage at all.
	 */
	UNDEFINED
}