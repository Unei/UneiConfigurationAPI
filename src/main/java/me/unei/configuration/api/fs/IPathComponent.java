package me.unei.configuration.api.fs;

import java.io.Serializable;
import java.util.List;
import java.util.RandomAccess;

import me.unei.configuration.api.fs.IPathNavigator.PathSymbolsType;
import me.unei.configuration.formats.StorageType;
import me.unei.configuration.formats.Storage.Key;

/**
 * Represent a component of an abstract path.
 * 
 * <p>could be Root, Parent, Child (named) or Index (indexed).</p>
 */
public interface IPathComponent {

	/**
	 * Gets the component type.
	 * 
	 * @return Returns the type of this component.
	 */
	public PathComponentType getType();

	/**
	 * Gets the component name.
	 * 
	 * @return Returns the component name.
	 */
	public String getValue();

	/**
	 * Gets the component index (if type of {@link PathComponentType#INDEX}).
	 * 
	 * @return Returns the component index.
	 */
	public int getIndex();
	
	/**
	 * Gets the component {@link Key} (index or value depending of the type).
	 * 
	 * @param indice An hint for the key to select, pass <tt>null</tt> to use the {@linkplain PathComponent} type.
	 * @return Returns the best found, or <tt>null</tt> if type is neither Child nor Index.
	 */
	public Key getKey(StorageType indice);

	/**
	 * Array of {@link IPathComponent} used to construct a full path.
	 */
	public static interface IPathComponentsList extends List<IPathComponent>, RandomAccess, Cloneable, Serializable {
		
		/**
		 * Appends the path component at the end of the existing path.
		 * 
		 * @throws NullPointerException If element is null.
		 */
		public boolean add(IPathComponent element);

		/**
		 * Gets the type of the symbols used in string paths.
		 * 
		 * @return Returns the string path symbols type.
		 */
		public PathSymbolsType getSymbolsType();

		/**
		 * Appends a component of the specified type and with the given name.
		 * 
		 * @see PathComponent#PathComponent(PathComponentType, String)
		 * 
		 * @param type The type of the component.
		 * @param value The name of the component.
		 * @return Returns `true`.
		 */
		public boolean appendComponent(PathComponentType type, String value);

		/**
		 * Appends a child component to this path.
		 * 
		 * @param name The child name.
		 * @return Returns `true`.
		 */
		public boolean appendChild(String name);

		/**
		 * Appends a table element component to this path.
		 * 
		 * @param index The element index.
		 * @return Returns `true`.
		 */
		public boolean appendIndex(int index);

		/**
		 * Appends a root component to this path.
		 * 
		 * @return Returns `true`.
		 */
		public boolean appendRoot();

		/**
		 * Appends a parent component to this path.
		 * 
		 * @return Returns `true`.
		 */
		public boolean appendParent();

		/**
		 * Gets the last component of the path.
		 * 
		 * @return Returns the last component.
		 */
		public IPathComponent last();

		/**
		 * Gets the last component of the path, if it is a 'child' one.
		 * 
		 * @return Returns the last component or null.
		 */
		public String lastChild();

		/**
		 * Gets the last component of the path, if it is an 'index' one.
		 * 
		 * @return Returns the last component or -1.
		 */
		public int lastIndex();

		/**
		 * Removes the last component of the path.
		 * 
		 * @return Returns the removed component.
		 */
		public IPathComponent removeLast();

		/**
		 * Clean the path components. 
		 *
		 * <p>Removes the 'parent' and 'child' components where unneeded.</p>
		 *
		 * @deprecated use
		 *             {@link FSUtils#cleanPath(IPathComponent.IPathComponentsList)}
		 *             instead
		 */
		@Deprecated
		public void cleanPath();

		/**
		 * Returns the string representation of this path.
		 */
		@Override
		public String toString();

		/**
		 * Clone this path components to another list.
		 */
		public IPathComponentsList clone();
	}
}
