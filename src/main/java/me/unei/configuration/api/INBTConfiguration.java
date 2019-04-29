package me.unei.configuration.api;

import me.unei.configuration.api.format.INBTTag;

public interface INBTConfiguration extends IConfiguration {

    /**
     * Returns a copy of the tag that represents this configuration
     * and therefore containing all the data in this configuration.
     *
     * @return a copy of the tag that represents this configuration
     */
    public INBTTag getTagCopy();

    /**
     * Sets the current configuration representation to a
     * copy of the specified {@link INBTTag tag}.
     *
     * @param tag the tag from which a copy will be made
     */
    public void setTagCopy(INBTTag tag);
}
