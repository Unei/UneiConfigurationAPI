package me.unei.configuration.api;

import me.unei.configuration.api.fs.NavigableFile;
import me.unei.configuration.formats.StorageType;

import java.util.List;

public interface IConfiguration extends IFlatConfiguration, NavigableFile {

    /**
     * Returns the top-most parent starting from this configuration.
     *
     * @return this configuration's top-most parent
     */
    public IConfiguration getRoot();

    /**
     * Returns the first parent of this configuration.
     *
     * @return this configuration's first parent
     */
    public IConfiguration getParent();

    /**
     * Returns the direct child element of this configuration which has
     * the specified name, as an {@link IConfiguration IConfiguration}.
     *
     * @param name the name of the child to retrieve
     * @return the direct child element with the specified name as an {@link IConfiguration IConfiguration}
     */
    public IConfiguration getChild(String name);

    /**
     * Returns the name of this configuration section.
     *
     * @return the name of this configuration section
     */
    public String getName();
    
    /**
     * Returns the type of this configuration section.
     * 
     * @return the type of this configuration section.
     */
    public StorageType getType();
    
    /**
     * Sets the type of this configuration section.
     * 
     * @param type the new (or expected) type of this configuration section.
     */
    public void setType(StorageType type);

    /**
     * Returns the element at the specified path, starting at
     * this configuration.
     *
     * @param path the path pointing to the element to retrieve
     * @return the element at the specified path
     */
    public Object get(String path);
/*
    /**
     * Returns the element at the specified path, starting at
     * this configuration, as {@link String String}, if possible.
     *
     * @param path the path pointing to the element to retrieve
     * @return the element at the specified path as {@link String String}
     * /
    public String getString(String path);

    /**
     * Returns the element at the specified path, starting at
     * this configuration, as double, if possible.
     *
     * @param path the path pointing to the element to retrieve
     * @return the element at the specified path as double
     * /
    public double getDouble(String path);

    /**
     * Returns the element at the specified path, starting at
     * this configuration, as boolean, if possible.
     *
     * @param path the path pointing to the element to retrieve
     * @return the element at the specified path as boolean
     * /
    public boolean getBoolean(String path);

    /**
     * Returns the element at the specified path, starting at
     * this configuration, as byte, if possible.
     *
     * @param path the path pointing to the element to retrieve
     * @return the element at the specified path as byte
     * /
    public byte getByte(String path);

    /**
     * Returns the element at the specified path, starting at
     * this configuration, as float, if possible.
     *
     * @param path the path pointing to the element to retrieve
     * @return the element at the specified path as float
     * /
    public float getFloat(String path);

    /**
     * Returns the element at the specified path, starting at
     * this configuration, as int, if possible.
     *
     * @param path the path pointing to the element to retrieve
     * @return the element at the specified path as int
     * /
    public int getInteger(String path);

    /**
     * Returns the element at the specified path, starting at
     * this configuration, as long, if possible.
     *
     * @param path the path pointing to the element to retrieve
     * @return the element at the specified path as long
     * /
    public long getLong(String path);
*/
    /**
     * Returns the element at the specified path, starting at
     * this configuration, as {@link Byte Byte} {@link List List}, if possible.
     *
     * @param path the path pointing to the element to retrieve
     * @return the element at the specified path as {@link Byte Byte} {@link List List}
     */
    public List<Byte> getByteList(String path);

    /**
     * Returns the element at the specified path, starting at
     * this configuration, as {@link Integer Integer} {@link List List}, if possible.
     *
     * @param path the path pointing to the element to retrieve
     * @return the element at the specified path as {@link Integer Integer} {@link List List} list
     */
    public List<Integer> getIntegerList(String path);

    /**
     * Returns the element at the specified path, starting at
     * this configuration, as {@link Long Long} {@link List List}, if possible.
     *
     * @param path the path pointing to the element to retrieve
     * @return the element at the specified path as {@link Long Long} {@link List List}
     */
    public List<Long> getLongList(String path);

    /**
     * Returns the sub-section at the specified path, starting at
     * this configuration.
     *
     * @param path the path pointing to the sub-section to retrieve
     * @return the sub-section at the specified path
     */
    public IConfiguration getSubSection(String path);

    /**
     * Sets the element at the specified path, starting at
     * this configuration.
     *
     * @param path the path pointing to the element to set
     * @param value the element to set at the specified path
     */
    public void set(String path, Object value);
/*
    /**
     * Sets the element at the specified path, starting at
     * this configuration, to the specified {@link String String}.
     *
     * @param path the path pointing to the element to set
     * @param value the {@link String String} to set at the specified path
     * /
    public void setString(String path, String value);

    /**
     * Sets the element at the specified path, starting at
     * this configuration, to the specified double.
     *
     * @param path the path pointing to the element to set
     * @param value the double to set at the specified path
     * /
    public void setDouble(String path, double value);

    /**
     * Sets the element at the specified path, starting at
     * this configuration, to the specified boolean.
     *
     * @param path the path pointing to the element to set
     * @param value the boolean to set at the specified path
     * /
    public void setBoolean(String path, boolean value);

    /**
     * Sets the element at the specified path, starting at
     * this configuration, to the specified byte.
     *
     * @param path the path pointing to the element to set
     * @param value the byte to set at the specified path
     * /
    public void setByte(String path, byte value);

    /**
     * Sets the element at the specified path, starting at
     * this configuration, to the specified float.
     *
     * @param path the path pointing to the element to set
     * @param value the float to set at the specified path
     * /
    public void setFloat(String path, float value);

    /**
     * Sets the element at the specified path, starting at
     * this configuration, to the specified int.
     *
     * @param path the path pointing to the element to set
     * @param value the int to set at the specified path
     * /
    public void setInteger(String path, int value);

    /**
     * Sets the element at the specified path, starting at
     * this configuration, to the specified long.
     *
     * @param path the path pointing to the element to set
     * @param value the long to set at the specified path
     * /
    public void setLong(String path, long value);
*/
    /**
     * Sets the element at the specified path, starting at
     * this configuration, to the specified {@link Byte Byte} {@link List List}.
     *
     * @param path the path pointing to the element to set
     * @param value the {@link Byte Byte} {@link List List} to set at the specified path
     */
    public void setByteList(String path, List<Byte> value);

    /**
     * Sets the element at the specified path, starting at
     * this configuration, to the specified {@link Integer Integer} {@link List List}.
     *
     * @param path the path pointing to the element to set
     * @param value the {@link Integer Integer} {@link List List} to set at the specified path
     */
    public void setIntegerList(String path, List<Integer> value);

    /**
     * Sets the element at the specified path, starting at
     * this configuration, to the specified {@link Long Long} {@link List List}.
     *
     * @param path the path pointing to the element to set
     * @param value the {@link Long Long} {@link List List} to set at the specified path
     */
    public void setLongList(String path, List<Long> value);

    /**
     * Sets the sub-section at the specified path, starting at
     * this configuration.
     *
     * @param path the path pointing to the sub-section to set
     * @param value the sub-section to set at the specified path
     */
    public void setSubSection(String path, IConfiguration value);
/*
    /**
     * Removes any element with the specified path, starting at
     * this configuration.
     *
     * @param path the path of the element to remove
     * /
    public void remove(String path);*/
}
