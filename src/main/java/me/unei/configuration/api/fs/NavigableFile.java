package me.unei.configuration.api.fs;

/**
 * Represent a section with a parent section and a child section.
 */
public interface NavigableFile {

    /**
     * Returns the path to get from this navigable file's
     * root to the current node.
     *
     * @return the current path of this navigable file
     */
    public IPathComponent.IPathComponentsList getRealListPath();

    /**
     * Returns the path to get from this navigable file's
     * root to the current node.
     *
     * @return the current path of this navigable file
     */
    public String getCurrentPath();

    /**
     * Returns this navigable file's direct (first) parent,
     * if it has one, or itself if it doesn't have any parent.
     *
     * @return this navigable file's direct parent
     */
    public NavigableFile getParent();

    /**
     * Gets this navigable file's root (last parent).
     * It returns itself if it doesn't have any parent.
     *
     * @return this navigable file's root
     */
    public NavigableFile getRoot();

    /**
     * Returns the child of this navigable file with the
     * provided name, or <tt>null</tt> if it doesn't exist
     * and if it can't be created.
     *
     * @param name The name of the child to return
     * @return this navigable file's child with the provided name
     */
    public NavigableFile getChild(String name);
}