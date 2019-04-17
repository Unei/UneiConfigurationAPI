package me.unei.configuration.plugin;

import java.io.File;
import java.io.InputStream;
import java.util.logging.Logger;

public interface IBasicPlugin {

    /**
     * Returns the folder that the plugin data's files are located in. The folder may not yet exist.
     * <p>
     * See <tt>org.bukkit.plugin.Plugin#getDataFolder()</tt> within Bukkit<br>
     * See <tt>net.md_5.bungee.api.plugin.Plugin#getDataFolder()</tt> within BungeeCord API
     *
     * @return the data folder of this plugin
     */
    public File getDataFolder();

    /**
     * Returns the plugin logger associated with this server's logger. The returned logger automatically tags all log messages with the plugin's name.
     * <p>
     * See <tt>org.bukkit.plugin.Plugin#getLogger()</tt> within Bukkit<br>
     * See <tt>net.md_5.bungee.api.plugin.Plugin#getLogger()</tt> within BungeeCord API
     *
     * @return Logger associated with this plugin
     */
    public Logger getLogger();

    /**
     * Gets an embedded resource in this plugin, within the jar or container.
     * Care must be taken to close the returned stream.
     * <p>
     * See <tt>org.bukkit.plugin.Plugin#getResource(String)</tt> within Bukkit<br>
     * See <tt>net.md_5.bungee.api.plugin.Plugin#getResourceAsStream(String)</tt> within BungeeCord API
     *
     * @param path the full path name of this resource
     *
     * @return the stream for getting this resource, or null if it does not exist
     */
    public InputStream getResource(String path);

    /**
     * Returns the type of plugin this is.
     *
     * @return the type of plugin this is
     */
    public IBasicPlugin.Type getType();

    /**
     * Plugin types.
     */
    public static enum Type {

        /**
         * @see <a href="https://bukkit.org">https://bukkit.org</a>
         */
        BUKKIT,

        /**
         * @see <a href="https://www.spigotmc.org/wiki/bungeecord/">https://www.spigotmc.org/wiki/bungeecord/</a>
         */
        BUNGEECORD,

        /**
         * @see <a href="https://www.spongepowered.org">https://www.spongepowered.org</a>
         */
        SPONGE,

        /**
         * @see <a href="http://www.minecraftforge.net/forum/">http://www.minecraftforge.net/forum/</a>
         */
        FORGE,

        /**
         * @see <a href="https://en.wikipedia.org/wiki/Standalone_software">https://en.wikipedia.org/wiki/Standalone_software</a>
         */
        STANDALONE;

    }
}
