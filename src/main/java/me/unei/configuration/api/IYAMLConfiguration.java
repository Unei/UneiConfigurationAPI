package me.unei.configuration.api;

public interface IYAMLConfiguration extends IHRConfiguration {

    /**
     * Returns this configuration's YAML representation as text.
     * <p>
     * {@inheritDoc}
     *
     * @return this configuration's YAML representation
     */
    public String saveToString();

    /**
     * Loads the provided YAML data into this YAMLConfig.
     * <p>
     * {@inheritDoc}
     *
     * @param data The YAML data as text
     */
    public void loadFromString(String data);
}
