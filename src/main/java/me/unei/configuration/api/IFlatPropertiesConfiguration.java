package me.unei.configuration.api;

public interface IFlatPropertiesConfiguration extends IFlatConfiguration {

	/**
	 * Returns the value relative to the specified key as {@link String String},
	 * or if it not defined, the given default value.
	 *
	 * @param key the key of the value to retrieve
	 * @param defaultValue the value to return if the value is not found
	 * @return the {@link String String} relative to the specified key, or defaultValue
	 */
	public String get(String key, String defaultValue);
}
