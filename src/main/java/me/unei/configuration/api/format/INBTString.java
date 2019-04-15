package me.unei.configuration.api.format;

/**
 * Represents a NBT String.
 */
public interface INBTString extends INBTTag {

    /**
     * Returns this {@link String String} tag's value.
     *
     * @return the {@link String String} value of this tag
     */
    public String getString();
}
