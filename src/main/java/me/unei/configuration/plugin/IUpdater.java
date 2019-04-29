package me.unei.configuration.plugin;

import java.util.Date;

/**
 * Represent the updater behind UneiConfiguration.
 */
public interface IUpdater {

	/**
	 * Synchronously check for a new version of UneiConfiguration.
	 * 
	 * @return {@link IUpdater.Result#UPDATE_AVAILABLE} if an update is available ;<br>
	 *  {@link IUpdater.Result#NO_UPDATE} if you the latest version ;<br>
	 *  {@link IUpdater.Result#FAILED} if the updater was unable to check for a newer version.
	 */
	public IUpdater.Result checkVersion();

	/**
	 * Asynchronously check for a new version of UneiConfiguration.
	 * 
	 * @see #checkVersion()
	 * 
	 * @param callback The callback witch will be called upon version check completion.
	 */
	public void checkVersionAsync(final IUpdater.ICallback callback);
	
	/**
	 * Gets the current version string  of UneiConfiguration.
	 * 
	 * @return the current version.
	 */
	public String getCurrentVersion();
	
	/**
	 * Gets the latest available version string of UneiConfiguration, if retrieved successfully.
	 * 
	 * @return The latest version string.
	 */
	public String getLatestVersion();
	
	/**
	 * Gets the last time the online version of UneiConfiguration was updated.
	 * 
	 * @return the last update time.
	 */
	public Date getLastUpdateTime();
	
	/**
	 * Gets the last time the online version of UneiConfiguration was updated.
	 * <p>
	 * This will return a pre-formatted string version of the date.
	 * 
	 * @see #getLastUpdateTime()
	 * 
	 * @return the last update time.
	 */
	public String getReadableLastUpdateTime();
	
	/**
	 * Call back class used when calling the updater asynchronously.
	 */
	public static interface ICallback {
		
		/**
		 * Method called upon version checking completion.
		 * 
		 * @param updater The updater instance (if you want to re-run things).
		 * @param result The update result.
		 */
		public void run(IUpdater updater, IUpdater.Result result);
	}

	/**
	 * Version checking result.
	 * <p>
	 * Could be :
	 * <ul>
	 * <li>{@linkplain Result#NO_UPDATE} - You are currently using the latest version available ;</li>
	 * <li>{@linkplain Result#UPDATE_AVAILABLE} - An update is available ;</li>
	 * <li>{@linkplain Result#FAILED} - The updater failed to retrieve online version</li>
	 * </ul>
	 */
	public static enum Result {
		
		/**
		 * The updater could get online version information,
		 * but the currently running version is either same
		 * or greater than the online one.
		 * <p>
		 * NO new version of UneiConfiguration is available.
		 */
		NO_UPDATE,
		/**
		 * The updater could get online version information,
		 * and the currently running version is lesser than the online one.
		 * <p>
		 * An new version of UneiConfiguration is available. 
		 */
		UPDATE_AVAILABLE,
		/**
		 * The updater could not get online version information
		 * and is not able to check for newer version.
		 */
		FAILED;
	}
}