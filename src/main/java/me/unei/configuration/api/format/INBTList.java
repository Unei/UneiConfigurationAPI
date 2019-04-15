package me.unei.configuration.api.format;

/**
 * Represents a NBT Tag list.
 */
public interface INBTList extends INBTTag {

    /**
     * Returns the type as byte of the elements contained in this list.
     *
     * @return the type as byte of the elements contained in this list
     */
    public byte getTagType();

    /**
     * Adds the specified element at the end of this list.
     *
     * @param tag the element to add at the end of this list
     */
    public void add(INBTTag tag);

    /**
     * Replaces the element at the specified index in this list
     * with the specified element.
     *
     * @param idx the index of the element to replace
     * @param tag the element to add at the end of this list
     */
    public void set(int idx, INBTTag tag);

    /**
     * Returns the element at the specified index in this list.
     *
     * @param idx the index of the element to retrieve
     *
     * @return the element at the specified index in this list
     */
    public INBTTag get(int idx);

    /**
     * Removes the element at the specified index in this list.
     *
     * @param idx the index of the element to remove
     *
     * @return the element previously at the specified index in this list
     */
    public INBTTag remove(int idx);

    /**
     * Returns the number of elements contained in this list.
     *
     * @return the number of elements contained in this list
     */
    public int size();
}
