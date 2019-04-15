package me.unei.configuration.api;

public interface IJSONConfiguration extends IHRConfiguration {

    /**
     * Returns this configuration's JSON representation as text.
     *
     * @return this configuration's JSON representation
     */
    public String saveToString();

    /**
     * Loads the provided JSON data into this JSONConfig.
     *
     * @param data The JSON data as text
     */
    public void loadFromString(String data);
}
