package me.unei.configuration.api;

import java.util.Set;

import me.unei.configuration.SavedFile;
import me.unei.configuration.api.exceptions.FileFormatException;

public interface IFlatConfiguration {

    /**
     * Returns the {@link SavedFile SavedFile} that represents this
     * configuration's file location.
     *
     * @return this configuration's {@link SavedFile SavedFile}
     */
    public SavedFile getFile();

    /**
     * Returns the name of the {@link SavedFile SavedFile} that
     * represents this configuration's file location.
     *
     * @return the name of this configuration's {@link SavedFile SavedFile}
     */
    public String getFileName();

    /**
     * Returns the name of this configuration.
     *
     * Usually equivalent to {@link IFlatConfiguration#getFileName()}.
     *
     * @return the name of this configuration's {@link SavedFile SavedFile}
     */
    public String getName();

    /**
     * Returns <tt>true</tt> if it is currently possible to access and
     * modify this configuration.
     *
     * @return <tt>true</tt> if this configuration is accessible
     */
    public boolean canAccess();

    /**
     * Locks this configuration and the file that represents it if applicable.
     */
    public void lock();

    /**
     * Saves all the modifications made to this configuration to the
     * appropriate location.
     *
     * Usually saves this configuration's data to its file location.
     */
    public void save();

    /**
     * Reloads this configuration contents with the one stored, deleting
     * any unsaved modification.
     *
     * @throws FileFormatException if the format of the read file is invalid
     */
    public void reload() throws FileFormatException;

    /**
     * Returns a set of the keys of all the direct children of this configuration.
     *
     * @return a set of the keys of all the direct children of this configuration
     */
    public Set<String> getKeys();

    /**
     * Returns <tt>true</tt> if this configuration contains a direct child with
     * the specified name.
     *
     * @param key the name of the child to check
     * @return <tt>true</tt> if this configuration contains a direct child with the specified name
     */
    public boolean contains(String key);

    /**
     * Returns the direct child element of this configuration which has
     * the specified key as name as {@link String String}, if possible.
     *
     * @param key the name of the child to retrieve
     * @return the direct child element with the specified name as {@link String String}
     */
    public String getString(String key);

    /**
     * Returns the direct child element of this configuration which has
     * the specified key as name as double, if possible.
     *
     * @param key the name of the child to retrieve
     * @return the direct child element with the specified name as double
     */
    public double getDouble(String key);

    /**
     * Returns the direct child element of this configuration which has
     * the specified key as name as boolean, if possible.
     *
     * @param key the name of the child to retrieve
     * @return the direct child element with the specified name as boolean
     */
    public boolean getBoolean(String key);

    /**
     * Returns the direct child element of this configuration which has
     * the specified key as name as byte, if possible.
     *
     * @param key the name of the child to retrieve
     * @return the direct child element with the specified name as byte
     */
    public byte getByte(String key);

    /**
     * Returns the direct child element of this configuration which has
     * the specified key as name as short, if possible.
     *
     * @param key the name of the child to retrieve
     * @return the direct child element with the specified name as short
     */
    public short getShort(String key);

    /**
     * Returns the direct child element of this configuration which has
     * the specified key as name as float, if possible.
     *
     * @param key the name of the child to retrieve
     * @return the direct child element with the specified name as float
     */
    public float getFloat(String key);

    /**
     * Returns the direct child element of this configuration which has
     * the specified key as name as int, if possible.
     *
     * @param key the name of the child to retrieve
     * @return the direct child element with the specified name as int
     */
    public int getInteger(String key);

    /**
     * Returns the direct child element of this configuration which has
     * the specified key as name as long, if possible.
     *
     * @param key the name of the child to retrieve
     * @return the direct child element with the specified name as long
     */
    public long getLong(String key);

    /**
     * Sets the specified {@link String String} as a child of this configuration with
     * the specified key as name.
     *
     * @param key the name to give to the child
     * @param value the {@link String String} to set as child
     */
    public void setString(String key, String value);

    /**
     * Sets the specified double as a child of this configuration with
     * the specified key as name.
     *
     * @param key the name to give to the child
     * @param value the double to set as child
     */
    public void setDouble(String key, double value);

    /**
     * Sets the specified boolean as a child of this configuration with
     * the specified key as name.
     *
     * @param key the name to give to the child
     * @param value the boolean to set as child
     */
    public void setBoolean(String key, boolean value);

    /**
     * Sets the specified byte as a child of this configuration with
     * the specified key as name.
     *
     * @param key the name to give to the child
     * @param value the byte to set as child
     */
    public void setByte(String key, byte value);

    /**
     * Sets the specified short as a child of this configuration with
     * the specified key as name.
     *
     * @param key the name to give to the child
     * @param value the short to set as child
     */
    public void setShort(String key, short value);

    /**
     * Sets the specified float as a child of this configuration with
     * the specified key as name.
     *
     * @param key the name to give to the child
     * @param value the float to set as child
     */
    public void setFloat(String key, float value);

    /**
     * Sets the specified int as a child of this configuration with
     * the specified key as name.
     *
     * @param key the name to give to the child
     * @param value the int to set as child
     */
    public void setInteger(String key, int value);

    /**
     * Sets the specified long as a child of this configuration with
     * the specified key as name.
     *
     * @param key the name to give to the child
     * @param value the long to set as child
     */
    public void setLong(String key, long value);

    /**
     * Removes any direct child element of this configuration which has
     * the specified key as name.
     *
     * @param key the name of the child to remove
     */
    public void remove(String key);
}
