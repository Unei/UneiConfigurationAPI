package me.unei.configuration.api.fs;

import me.unei.configuration.StaticInstance;
import me.unei.configuration.StaticInstance.StaticInstanceExposer;
import me.unei.configuration.api.fs.IPathComponent.IPathComponentsList;
import me.unei.configuration.api.fs.IPathNavigator.PathSymbolsType;

public abstract class FSUtils {

	protected abstract IPathComponentsList internal_parsePath(String path, PathSymbolsType type);
	protected abstract IPathComponentsList internal_cleanPath(IPathComponentsList path);
	protected abstract String internal_escapeComponent(String component, PathSymbolsType symType);
	protected abstract IPathComponent internal_createComponent(PathComponentType type, String value);
	protected abstract IPathComponent internal_createComponent(PathComponentType type, int index);
	protected abstract IPathComponentsList internal_createList(PathSymbolsType symType);
	protected abstract IPathComponentsList internal_createList(IPathComponentsList list);

	protected void setInstance() {
		if (Instance.isEmpty()) {
			Instance.set(this);
		}
	}

	private static FSUtils getInstance() {
		if (!Instance.isEmpty()) {
			return Instance.get();
		}
		Instance.callBuilder();
		if (!Instance.isEmpty()) {
			return Instance.get();
		}
		throw new IllegalStateException("FSUtils is not yet inialized");
	}
	
	private static final StaticInstance<FSUtils> Instance = new StaticInstance<>();
	public static final StaticInstanceExposer<FSUtils> INSTANCE = new StaticInstanceExposer<>(Instance, true);
	
	static
	{
		try {
			Instance.setConstructor(Class.forName("me.unei.configuration.api.fs.FSUtilsImpl"), "init");
		} catch (ClassNotFoundException e) {
			;
		}
	}

	/**
	 * Parse a string, natural, path into multiples, easy to use, components.
	 * 
	 * @param path The path to parse.
	 * @param type The types of the symbols used in the path.
	 * @return Returns the parsed path.
	 */
	public static IPathComponentsList parsePath(String path, PathSymbolsType type) {
		return getInstance().internal_parsePath(path, type);
	}

	/**
	 * Clean the path components.
	 * 
	 * <p>Removes the 'parent' and 'child' components where unneeded.</p>
	 * 
	 * @param path The path to clean.
	 * @return Returns the new {@link IPathComponentsList} after the cleaning.
	 */
	public static IPathComponentsList cleanPath(IPathComponentsList path) {
		return getInstance().internal_cleanPath(path);
	}
	

	/**
	 * Escape a string path using the given symbols type.
	 * 
	 * @param component The text to escape.
	 * @param symType The type of the symbols used in the path.
	 * @return Returns the escaped text.
	 */
	public static String escapeComponent(String component, PathSymbolsType symType) {
		return getInstance().internal_escapeComponent(component, symType);
	}

	/**
	 * Create a new component of the given type with a name.
	 * 
	 * @param type The type of the component.
	 * @param value The name of the component.
	 * @return Returns the newly created path component.
	 */
	public static IPathComponent createComponent(PathComponentType type, String value) {
		return getInstance().internal_createComponent(type, value);
	}

	/**
	 * Create a new component of the given type with a name.
	 * 
	 * @param type The type of the component.
	 * @param index The index of the component.
	 * @return Returns the newly created path component.
	 */
	public static IPathComponent createComponent(PathComponentType type, int index) {
		return getInstance().internal_createComponent(type, index);
	}

	/**
	 * Constructs an empty path with an initial capacity of ten components.
	 * 
	 * @param symType The type of the symbols used in string paths.
	 * @return Returns the newly created {@linkplain IPathComponent path component} list.
	 */
	public static IPathComponentsList createList(PathSymbolsType symType) {
		return getInstance().internal_createList(symType);
	}
	
	/**
	 * Constructs a list containing the elements of the specified path,
	 * in the order they are returned by the collection's iterator.
	 * 
	 * @param list The collection whose elements are to be placed into this list.
	 * @return Returns the newly created {@linkplain IPathComponent path component} list.
	 * 
	 * @throws NullPointerException If the specified collection is null.
	 */
	public static IPathComponentsList createList(IPathComponentsList list) {
		return getInstance().internal_createList(list);
	}
}