package me.unei.configuration.plugin;

import java.util.Date;

public interface IUpdater {
	
	public IUpdater.Result checkVersion();
	
	public void checkVersionAsync(final IUpdater.ICallback callback);
	
	public String getCurrentVersion();
	
	public String getLatestVersion();
	
	public Date getLastUpdateTime();
	
	public String getReadableLastUpdateTime();
	
	public static interface ICallback {
		
		public void run(IUpdater updater, IUpdater.Result result);
	}

	public static enum Result {
		
		NO_UPDATE,
		UPDATE_AVAILABLE,
		FAILED;
	}
}