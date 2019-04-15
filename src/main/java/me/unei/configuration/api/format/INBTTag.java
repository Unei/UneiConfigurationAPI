package me.unei.configuration.api.format;

/**
 * Represents a NBT Tag.
 */
public interface INBTTag {

    /**
     * Get the type of this tag.
     *
     * @return the id of the type of this tag.
     */
    public byte getTypeId();

    /**
     * Returns <tt>true</tt> if this tag contains no key-value mappings.
     *
     * @return <tt>true</tt> if this tag contains no key-value mappings.
     */
    public boolean isEmpty();

    public abstract INBTTag clone();
}
