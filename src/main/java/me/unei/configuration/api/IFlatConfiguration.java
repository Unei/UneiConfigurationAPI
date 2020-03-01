package me.unei.configuration.api;

import java.util.Set;

import me.unei.configuration.SavedFile;
import me.unei.configuration.api.Configurations.ConfigurationType;
import me.unei.configuration.api.exceptions.FileFormatException;
import me.unei.configuration.api.exceptions.NoFieldException;

public interface IFlatConfiguration {

	/**
	 * Get the type of this configuration node instance.
	 * 
	 * @return this configuration's type.
	 */
	public ConfigurationType getConfigurationType();

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
	 * the specified key as name as {@link String String}, if possible or null.
	 *
	 * @param key the name of the child to retrieve
	 * @return the direct child element with the specified name as {@link String String}
	 */
	public String getString(String key);

	/**
	 * Tries to get the string associated with the specified key as name,
	 * if possible. In case that name does not refer to a string value
	 * a {@link NoFieldException} will be thrown.
	 * 
	 * @param key the name of the value to retrieve.
	 * @return the string value with the specified name.
	 * @throws NoFieldException if the specified name does not refer to a string value.
	 */
	public String tryGetString(String key) throws NoFieldException;

	/**
	 * Returns the the string value associated with the specified key as name,
	 * if possible. In case that name does not refer to a string value the
	 * specified default value will be returned (but not stored !).
	 * 
	 * @param key the name of the value to retrieve.
	 * @param def the default value to return of none available.
	 * @return the string value with the specified name.
	 */
	public String getString(String key, String def);

	/**
	 * Returns the direct child element of this configuration which has
	 * the specified key as name as double, if possible or null.
	 *
	 * @param key the name of the child to retrieve
	 * @return the direct child element with the specified name as double
	 */
	public Double getDouble(String key);

	/**
	 * Tries to get the double number associated with the specified key as name,
	 * if possible. In case that name does not refer to a double number value
	 * a {@link NoFieldException} will be thrown.
	 * 
	 * @param key the name of the value to retrieve.
	 * @return the double number value with the specified name.
	 * @throws NoFieldException if the specified name does not refer to a double number value.
	 */
	public double tryGetDouble(String key) throws NoFieldException;

	/**
	 * Returns the the double number value associated with the specified key as name,
	 * if possible. In case that name does not refer to a double number value the
	 * specified default value will be returned (but not stored !).
	 * 
	 * @param key the name of the value to retrieve.
	 * @param def the default value to return of none available.
	 * @return the double number value with the specified name.
	 */
	public double getDouble(String key, double def);

	/**
	 * Returns the direct child element of this configuration which has
	 * the specified key as name as boolean, if possible or null.
	 *
	 * @param key the name of the child to retrieve
	 * @return the direct child element with the specified name as boolean
	 */
	public Boolean getBoolean(String key);

	/**
	 * Tries to get the boolean associated with the specified key as name,
	 * if possible. In case that name does not refer to a boolean value
	 * a {@link NoFieldException} will be thrown.
	 * 
	 * @param key the name of the value to retrieve.
	 * @return the boolean value with the specified name.
	 * @throws NoFieldException if the specified name does not refer to a boolean value.
	 */
	public boolean tryGetBoolean(String key) throws NoFieldException;

	/**
	 * Returns the the boolean value associated with the specified key as name,
	 * if possible. In case that name does not refer to a boolean value the
	 * specified default value will be returned (but not stored !).
	 * 
	 * @param key the name of the value to retrieve.
	 * @param def the default value to return of none available.
	 * @return the boolean value with the specified name.
	 */
	public boolean getBoolean(String key, boolean def);

	/**
	 * Returns the direct child element of this configuration which has
	 * the specified key as name as byte, if possible or null.
	 *
	 * @param key the name of the child to retrieve
	 * @return the direct child element with the specified name as byte
	 */
	public Byte getByte(String key);

	/**
	 * Tries to get the byte associated with the specified key as name,
	 * if possible. In case that name does not refer to a byte value
	 * a {@link NoFieldException} will be thrown.
	 * 
	 * @param key the name of the value to retrieve.
	 * @return the byte value with the specified name.
	 * @throws NoFieldException if the specified name does not refer to a byte value.
	 */
	public byte tryGetByte(String key) throws NoFieldException;

	/**
	 * Returns the the byte value associated with the specified key as name,
	 * if possible. In case that name does not refer to a byte value the
	 * specified default value will be returned (but not stored !).
	 * 
	 * @param key the name of the value to retrieve.
	 * @param def the default value to return of none available.
	 * @return the byte value with the specified name.
	 */
	public byte getByte(String key, byte def);

	/**
	 * Returns the direct child element of this configuration which has
	 * the specified key as name as short, if possible or null.
	 *
	 * @param key the name of the child to retrieve
	 * @return the direct child element with the specified name as short
	 */
	public Short getShort(String key);

	/**
	 * Tries to get the short associated with the specified key as name,
	 * if possible. In case that name does not refer to a short value
	 * a {@link NoFieldException} will be thrown.
	 * 
	 * @param key the name of the value to retrieve.
	 * @return the short value with the specified name.
	 * @throws NoFieldException if the specified name does not refer to a short value.
	 */
	public short tryGetShort(String key) throws NoFieldException;

	/**
	 * Returns the the short value associated with the specified key as name,
	 * if possible. In case that name does not refer to a short value the
	 * specified default value will be returned (but not stored !).
	 * 
	 * @param key the name of the value to retrieve.
	 * @param def the default value to return of none available.
	 * @return the short value with the specified name.
	 */
	public short getShort(String key, short def);

	/**
	 * Returns the direct child element of this configuration which has
	 * the specified key as name as float, if possible or null.
	 *
	 * @param key the name of the child to retrieve
	 * @return the direct child element with the specified name as float
	 */
	public Float getFloat(String key);

	/**
	 * Tries to get the float associated with the specified key as name,
	 * if possible. In case that name does not refer to a float value
	 * a {@link NoFieldException} will be thrown.
	 * 
	 * @param key the name of the value to retrieve.
	 * @return the float value with the specified name.
	 * @throws NoFieldException if the specified name does not refer to a float value.
	 */
	public float tryGetFloat(String key) throws NoFieldException;

	/**
	 * Returns the the float value associated with the specified key as name,
	 * if possible. In case that name does not refer to a float value the
	 * specified default value will be returned (but not stored !).
	 * 
	 * @param key the name of the value to retrieve.
	 * @param def the default value to return of none available.
	 * @return the float value with the specified name.
	 */
	public float getFloat(String key, float def);

	/**
	 * Returns the direct child element of this configuration which has
	 * the specified key as name as int, if possible or null.
	 *
	 * @param key the name of the child to retrieve
	 * @return the direct child element with the specified name as int
	 */
	public Integer getInteger(String key);

	/**
	 * Tries to get the integer associated with the specified key as name,
	 * if possible. In case that name does not refer to a integer value
	 * a {@link NoFieldException} will be thrown.
	 * 
	 * @param key the name of the value to retrieve.
	 * @return the integer value with the specified name.
	 * @throws NoFieldException if the specified name does not refer to a integer value.
	 */
	public int tryGetInteger(String key) throws NoFieldException;

	/**
	 * Returns the the integer value associated with the specified key as name,
	 * if possible. In case that name does not refer to a integer value the
	 * specified default value will be returned (but not stored !).
	 * 
	 * @param key the name of the value to retrieve.
	 * @param def the default value to return of none available.
	 * @return the integer value with the specified name.
	 */
	public int getInteger(String key, int def);

	/**
	 * Returns the direct child element of this configuration which has
	 * the specified key as name as long, if possible or null.
	 *
	 * @param key the name of the child to retrieve
	 * @return the direct child element with the specified name as long
	 */
	public Long getLong(String key);

	/**
	 * Tries to get the long associated with the specified key as name,
	 * if possible. In case that name does not refer to a long value
	 * a {@link NoFieldException} will be thrown.
	 * 
	 * @param key the name of the value to retrieve.
	 * @return the boolean value with the specified name.
	 * @throws NoFieldException if the specified name does not refer to a long value.
	 */
	public long tryGetLong(String key) throws NoFieldException;

	/**
	 * Returns the the long value associated with the specified key as name,
	 * if possible. In case that name does not refer to a long value the
	 * specified default value will be returned (but not stored !).
	 * 
	 * @param key the name of the value to retrieve.
	 * @param def the default value to return of none available.
	 * @return the long value with the specified name.
	 */
	public long getLong(String key, long def);

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
