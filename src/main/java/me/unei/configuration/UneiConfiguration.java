package me.unei.configuration;

import me.unei.configuration.StaticInstance.StaticInstanceExposer;
import me.unei.configuration.plugin.IBasicPlugin;
import me.unei.configuration.plugin.IUpdater;

public abstract class UneiConfiguration implements IBasicPlugin {
	private static final StaticInstance<UneiConfiguration> Instance = new StaticInstance<>();
	public static final StaticInstanceExposer<UneiConfiguration> INSTANCE = new StaticInstanceExposer<>(Instance, false);

	static {
		try {
			Instance.setConstructor(Class.forName("me.unei.configuration.plugin.UneiConfiguration"), "getInstance");
		} catch (ClassNotFoundException e) {
			;
		}
	}

	protected final void setInstance() {
		if (Instance.isEmpty()) {
			Instance.set(this);
		}
	}

	protected static final StaticInstance<UneiConfiguration> getStaticHolder() {
		return Instance;
	}

	/**
	 * Gets  an instance of the running UneiConfiguration plugin.
	 * 
	 * @return Returns an instance of a UneiConfiguration.
	 */
	public static UneiConfiguration getInstance() {
		return INSTANCE.get();
	}

	/**
	 * Sends the given name at BStats.
	 * <p>
	 * The name should be the name of the plugin using UneiConfiguration.
	 * <p>
	 * This is purely optional, it's mainly to see how many plugin is using our API.
	 * 
	 * @param name The name of the plugin depending on UneiConfiguration.
	 */
	public abstract void registerMyNameInBStats(String name);

	/**
	 * Try to load the UneiConfiguration implementation forcefully (useful when using it in stand-alone mode).
	 * 
	 * @return Returns this API instance if loaded successfully.
	 */
	public static UneiConfiguration tryInstanciate() {
		Instance.callBuilder();
		return getInstance();
	}

	/**
	 * Gets the UneiConfiguration updater interface.
	 * 
	 * @return Returns the Updater.
	 */
	public abstract IUpdater getUpdater();

	/**
	 * Synchronously check for a new version of UneiConfiguration.
	 * <p>
	 * This is the same as calling {@link IUpdater#checkVersion()}.
	 * 
	 * 
	 * @return {@link IUpdater.Result#UPDATE_AVAILABLE} if an update is available ;<br>
	 *  {@link IUpdater.Result#NO_UPDATE} if you the latest version ;<br>
	 *  {@link IUpdater.Result#FAILED} if the updater was unable to check for a newer version.
	 */
	public abstract IUpdater.Result checkVersion();

	/**
	 * Asynchronously check for a new version of UneiConfiguration.
	 * <p>
	 * This is the same as calling {@link IUpdater#checkVersionAsync(me.unei.configuration.plugin.IUpdater.ICallback)}.
	 * 
	 * @see #checkVersion()
	 * 
	 * @param callback The callback witch will be called upon version check completion.
	 */
	public abstract void checkVerionAsync(IUpdater.ICallback callback);
}