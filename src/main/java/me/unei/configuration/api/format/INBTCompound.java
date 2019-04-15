package me.unei.configuration.api.format;

import java.util.Set;

/**
 * Represents a NBT Compound Tag.
 */
public interface INBTCompound extends INBTTag {

    /**
     * Returns a set of the keys of all the direct children of this tag.
     *
     * @return a set of the keys of all the direct children of this tag
     */
    public Set<String> keySet();

    /**
     * Returns the number of direct children of this tag.
     *
     * @return the number of direct children of this tag
     */
    public int size();

    /**
     * Sets the specified element as a child of this tag with
     * the specified key as name.
     *
     * @param key the name to give to the child
     * @param elem the element to set as child
     */
    public void set(String key, INBTTag elem);

    /**
     * Sets the specified byte as a child of this tag with
     * the specified key as name.
     *
     * @param key the name to give to the child
     * @param value the byte to set as child
     */
    public void setByte(String key, byte value);

    /**
     * Sets the specified short as a child of this tag with
     * the specified key as name.
     *
     * @param key the name to give to the child
     * @param value the short to set as child
     */
    public void setShort(String key, short value);

    /**
     * Sets the specified int as a child of this tag with
     * the specified key as name.
     *
     * @param key the name to give to the child
     * @param value the int to set as child
     */
    public void setInt(String key, int value);

    /**
     * Sets the specified long as a child of this tag with
     * the specified key as name.
     *
     * @param key the name to give to the child
     * @param value the long to set as child
     */
    public void setLong(String key, long value);

    /**
     * Sets the specified float as a child of this tag with
     * the specified key as name.
     *
     * @param key the name to give to the child
     * @param value the float to set as child
     */
    public void setFloat(String key, float value);

    /**
     * Sets the specified double as a child of this tag with
     * the specified key as name.
     *
     * @param key the name to give to the child
     * @param value the double to set as child
     */
    public void setDouble(String key, double value);

    /**
     * Sets the specified {@link String String} as a child of this tag with
     * the specified key as name.
     *
     * @param key the name to give to the child
     * @param value the {@link String String} to set as child
     */
    public void setString(String key, String value);

    /**
     * Sets the specified byte array as a child of this tag with
     * the specified key as name.
     *
     * @param key the name to give to the child
     * @param value the byte array to set as child
     */
    public void setByteArray(String key, byte[] value);

    /**
     * Sets the specified int array as a child of this tag with
     * the specified key as name.
     *
     * @param key the name to give to the child
     * @param value the int array to set as child
     */
    public void setIntArray(String key, int[] value);

    /**
     * Sets the specified long array as a child of this tag with
     * the specified key as name.
     *
     * @param key the name to give to the child
     * @param value the long array to set as child
     */
    public void setLongArray(String key, long[] value);

    /**
     * Sets the specified boolean as a child of this tag with
     * the specified key as name.
     *
     * @param key the name to give to the child
     * @param value the boolean to set as child
     */
    public void setBoolean(String key, boolean value);

    /**
     * Returns the direct child element of this tag which has
     * the specified key as name.
     *
     * @param key the name of the child to retrieve
     * @return the direct child element with the specified name
     */
    public INBTTag get(String key);

    /**
     * Returns the type as byte of the child element of this tag
     * which has the specified key as name.
     *
     * @param key the name of the child from which to retrieve the type
     * @return the type as byte of the direct child element with the specified name
     */
    public byte getTypeOf(String key);

    /**
     * Returns <tt>true</tt> if this tag contains a direct child with
     * the specified name.
     *
     * @param key the name of the child to check
     * @return <tt>true</tt> if this tag contains a direct child with the specified name
     */
    public boolean hasKey(String key);

    /**
     * Returns the direct child element of this tag which has
     * the specified key as name as byte, if possible.
     *
     * @param key the name of the child to retrieve
     * @return the direct child element with the specified name as byte
     */
    public byte getByte(String key);

    /**
     * Returns the direct child element of this tag which has
     * the specified key as name as short, if possible.
     *
     * @param key the name of the child to retrieve
     * @return the direct child element with the specified name as short
     */
    public short getShort(String key);

    /**
     * Returns the direct child element of this tag which has
     * the specified key as name as int, if possible.
     *
     * @param key the name of the child to retrieve
     * @return the direct child element with the specified name as int
     */
    public int getInt(String key);

    /**
     * Returns the direct child element of this tag which has
     * the specified key as name as long, if possible.
     *
     * @param key the name of the child to retrieve
     * @return the direct child element with the specified name as long
     */
    public long getLong(String key);

    /**
     * Returns the direct child element of this tag which has
     * the specified key as name as float, if possible.
     *
     * @param key the name of the child to retrieve
     * @return the direct child element with the specified name as float
     */
    public float getFloat(String key);

    /**
     * Returns the direct child element of this tag which has
     * the specified key as name as double, if possible.
     *
     * @param key the name of the child to retrieve
     * @return the direct child element with the specified name as double
     */
    public double getDouble(String key);

    /**
     * Returns the direct child element of this tag which has
     * the specified key as name as {@link String String}, if possible.
     *
     * @param key the name of the child to retrieve
     * @return the direct child element with the specified name as {@link String String}
     */
    public String getString(String key);

    /**
     * Returns the direct child element of this tag which has
     * the specified key as byte array as byte, if possible.
     *
     * @param key the name of the child to retrieve
     * @return the direct child element with the specified name as byte array
     */
    public byte[] getByteArray(String key);

    /**
     * Returns the direct child element of this tag which has
     * the specified key as name as int array, if possible.
     *
     * @param key the name of the child to retrieve
     * @return the direct child element with the specified name as int array
     */
    public int[] getIntArray(String key);

    /**
     * Returns the direct child element of this tag which has
     * the specified key as name as long array, if possible.
     *
     * @param key the name of the child to retrieve
     * @return the direct child element with the specified name as long array
     */
    public long[] getLongArray(String key);

    /**
     * Returns the direct child element of this tag which has
     * the specified key as name as an {@link INBTCompound INBTCompound}, if possible.
     *
     * @param key the name of the child to retrieve
     * @return the direct child element with the specified name as an {@link INBTCompound INBTCompound}
     */
    public INBTCompound getCompound(String key);

    /**
     * Returns the direct child element of this tag which has
     * the specified key as name as boolean, if possible.
     *
     * @param key the name of the child to retrieve
     * @return the direct child element with the specified name as boolean
     */
    public boolean getBoolean(String key);

    /**
     * Returns the direct child element of this tag which has
     * the specified key as name as an {@link INBTList INBTList}, if possible.
     *
     * @param key the name of the child to retrieve
     * @param type the type of the elements of the list to retrieve
     * @return the direct child element with the specified name as an {@link INBTList INBTList}
     */
    public INBTList getList(String key, byte type);

    /**
     * Removes any direct child element of this tag which has
     * the specified key as name.
     *
     * @param key the name of the child to remove
     */
    public void remove(String key);
}
