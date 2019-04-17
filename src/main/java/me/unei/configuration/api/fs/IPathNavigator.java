package me.unei.configuration.api.fs;

import java.util.function.Consumer;

import me.unei.configuration.api.fs.IPathComponent.IPathComponentsList;

/**
 * Section navigator.
 *
 * @param <T> The section type.
 */
public interface IPathNavigator<T extends NavigableFile> {

	/**
	 * Navigate to the root section.
	 */
	public void goToRoot();

	/**
	 * Navigate to the parent section.
	 */
	public void goToParent();

	/**
	 * Navigate to the `name` child section.
	 * 
	 * @param name The name of the child section to go to.
	 */
	public void goToChild(String name);

	/**
	 * Navigate to the `index` element section.
	 * 
	 * @param index The index of the child section to go to.
	 */
	public void goToIndex(int index);

	/**
	 * Gets the current path.
	 * 
	 * @return Returns the path as a string.
	 */
	public String getCurrentPath();

	/**
	 * Gets the current section.
	 * 
	 * @return Returns the actual node.
	 */
	public T getCurrentNode();

	/**
	 * Navigate following the given path and execute an action on each nodes.
	 * 
	 * @param path The path of the destination.
	 * @param action An action to execute on each node of the path.
	 * 
	 * @throws NullPointerException If the `action` parameter is null.
	 */
	public void followAndApply(IPathComponentsList path, Consumer<T> action);

	/**
	 * Navigate following the given `path`.
	 * 
	 * @param path The path of the destination.
	 * @return Returns `true` if navigation was successful, `false` otherwise.
	 * 
	 * @see #navigate(String, PathSymbolsType)
	 */
	public boolean followPath(IPathComponentsList path);

	/**
	 * Navigate following the given `path`.
	 * 
	 * @param path The path of the destination.
	 * @param type The types of the symbols used in the path.
	 * @return Returns `true` if navigation was successful, `false` otherwise.
	 * 
	 * @see #followPath(IPathComponentsList)
	 */
	public boolean navigate(String path, PathSymbolsType type);

	/**
	 * Represents the different types of symbols used in string paths.
	 */
	public static enum PathSymbolsType {
		/**
		 * The default Bukkit path symbols type.
		 * 
		 * <ul>
		 * <li>Escape char: &nbsp; &nbsp; &nbsp; 			<tt>'\'</tt></li>
		 * <li>Separator char:&nbsp;						<tt>'.'</tt></li>
		 * <li>Root char: &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<tt>'.'</tt></li>
		 * <li>Parent string: &nbsp; &nbsp; 				<tt>'..'</tt></li>
		 * <li>Index prefix: &nbsp; &nbsp; &nbsp; 			<tt>'['</tt></li>
		 * <li>Index suffix: &nbsp; &nbsp; &nbsp;&nbsp; 	<tt>']'</tt></li>
		 * </ul>
		 */
		BUKKIT('\\', '.', '.', "..", '[', ']', false),
		/**
		 * The default Unix path symbols type.
		 * 
		 * <ul>
		 * <li>Escape char: &nbsp; &nbsp; &nbsp; 			<tt>'\'</tt></li>
		 * <li>Separator char:&nbsp;						<tt>'/'</tt></li>
		 * <li>Root char: &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<tt>'/'</tt></li>
		 * <li>Parent string: &nbsp; &nbsp; 				<tt>'..'</tt></li>
		 * <li>Index prefix: &nbsp; &nbsp; &nbsp; 			<tt>'['</tt></li>
		 * <li>Index suffix: &nbsp; &nbsp; &nbsp;&nbsp; 	<tt>']'</tt></li>
		 * </ul>
		 */
		UNIX('\\', '/', '/', "..", '[', ']', true);

		public final char escape;
		public final char separator;
		public final char root;
		public final String parent;
		public final char indexerPrefix;
		public final char indexerSuffix;

		public final boolean wrapParent;

		private PathSymbolsType(char p_escape, char p_separator, char p_root, String p_parent, char idxPre, char idxSuf, boolean wParent) {
			this.escape = p_escape;
			this.separator = p_separator;
			this.root = p_root;
			this.parent = p_parent;
			this.indexerPrefix = idxPre;
			this.indexerSuffix = idxSuf;
			this.wrapParent = wParent;
		}

		/**
		 * Try to detect the type of symbols used in the given 'apath'.
		 * 
		 * @param apath A path to test.
		 * @return Returns a PathSymbolsType detected in the path.
		 * 
		 * @deprecated This won't detect the type for sure and can lead to data-reading errors.
		 */
		@Deprecated
		public static PathSymbolsType tryDetectType(String apath) {
			int li = apath.lastIndexOf(UNIX.parent);
			if (li >= 0) {
				if (li > 0 && apath.charAt(li - 1) == UNIX.separator) {
					return PathSymbolsType.UNIX;
				} else if ((li + 2) < apath.length() && apath.charAt(li + 2) == UNIX.separator) {
					return PathSymbolsType.UNIX;
				}
			}
			return PathSymbolsType.BUKKIT;
		}
	}
}
