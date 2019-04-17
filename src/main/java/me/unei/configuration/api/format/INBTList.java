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
     * Adds the specified byte at the end of this list.
     *
     * @param value the element to add at the end of this list
     */
    public void addByte(byte value);

    /**
     * Adds the specified short at the end of this list.
     *
     * @param value the element to add at the end of this list
     */
    public void addShort(short value);

    /**
     * Adds the specified integer at the end of this list.
     *
     * @param value the element to add at the end of this list
     */
    public void addInt(int value);

    /**
     * Adds the specified long at the end of this list.
     *
     * @param value the element to add at the end of this list
     */
    public void addLong(long value);

    /**
     * Adds the specified float at the end of this list.
     *
     * @param value the element to add at the end of this list
     */
    public void addFloat(float value);

    /**
     * Adds the specified double at the end of this list.
     *
     * @param value the element to add at the end of this list
     */
    public void addDouble(double value);

    /**
     * Adds the specified {@link String String} at the end of this list.
     *
     * @param value the element to add at the end of this list
     */
    public void addString(String value);

    /**
     * Adds the specified byte array at the end of this list.
     *
     * @param value the element to add at the end of this list
     */
    public void addByteArray(byte[] value);

    /**
     * Adds the specified integer array at the end of this list.
     *
     * @param value the element to add at the end of this list
     */
    public void addIntArray(int[] value);

    /**
     * Adds the specified long array at the end of this list.
     *
     * @param value the element to add at the end of this list
     */
    public void addLongArray(long[] value);

    /**
     * Adds the specified boolean at the end of this list.
     *
     * @param value the element to add at the end of this list
     */
    public void addBoolean(boolean value);

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
     * Returns the type as byte of the child element of this tag
     * at the specified index.
     *
     * @param idx the index of the element to retrieve
     * @return the type as byte of the direct child element at the specified index
     */
    public byte getTypeOf(int idx);

    /**
     * Returns the direct child element of this tag at
     * the specified index as byte, if possible.
     *
     * @param idx the index of the element to retrieve
     * @return the direct child element with the specified index as byte
     */
    public byte getByte(int idx);

    /**
     * Returns the direct child element of this tag at
     * the specified index as short, if possible.
     *
     * @param idx the index of the element to retrieve
     * @return the direct child element with the specified index as short
     */
    public short getShort(int idx);

    /**
     * Returns the direct child element of this tag at
     * the specified index as integer, if possible.
     *
     * @param idx the index of the element to retrieve
     * @return the direct child element with the specified index as integer
     */
    public int getInt(int idx);

    /**
     * Returns the direct child element of this tag at
     * the specified index as long, if possible.
     *
     * @param idx the index of the element to retrieve
     * @return the direct child element with the specified index as long
     */
    public long getLong(int idx);

    /**
     * Returns the direct child element of this tag at
     * the specified index as float, if possible.
     *
     * @param idx the index of the element to retrieve
     * @return the direct child element with the specified index as float
     */
    public float getFloat(int idx);

    /**
     * Returns the direct child element of this tag at
     * the specified index as double, if possible.
     *
     * @param idx the index of the element to retrieve
     * @return the direct child element with the specified index as double
     */
    public double getDouble(int idx);

    /**
     * Returns the direct child element of this tag at
     * the specified index as {@link String String}, if possible.
     *
     * @param idx the index of the element to retrieve
     * @return the direct child element with the specified index as {@link String String}
     */
    public String getString(int idx);

    /**
     * Returns the direct child element of this tag at
     * the specified key as byte array as byte, if possible.
     *
     * @param idx the index of the element to retrieve
     * @return the direct child element with the specified index as byte array
     */
    public byte[] getByteArray(int idx);

    /**
     * Returns the direct child element of this tag at
     * the specified index as integer array, if possible.
     *
     * @param idx the index of the element to retrieve
     * @return the direct child element with the specified index as integer array
     */
    public int[] getIntArray(int idx);

    /**
     * Returns the direct child element of this tag at
     * the specified index as long array, if possible.
     *
     * @param idx the index of the element to retrieve
     * @return the direct child element with the specified index as long array
     */
    public long[] getLongArray(int idx);

    /**
     * Returns the direct child element of this tag at
     * the specified index as an {@link INBTCompound INBTCompound}, if possible.
     *
     * @param idx the index of the element to retrieve
     * @return the direct child element with the specified index as an {@link INBTCompound INBTCompound}
     */
    public INBTCompound getCompound(int idx);

    /**
     * Returns the direct child element of this tag at
     * the specified index as boolean, if possible.
     *
     * @param idx the index of the element to retrieve
     * @return the direct child element with the specified index as boolean
     */
    public boolean getBoolean(int idx);

    /**
     * Returns the direct child element of this tag at
     * the specified index as an {@link INBTList INBTList}, if possible.
     *
     * @param idx the index of the element to retrieve
     * @param type the type of the elements of the list to retrieve
     * @return the direct child element with the specified index as an {@link INBTList INBTList}
     */
    public INBTList getList(int idx, byte type);

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
