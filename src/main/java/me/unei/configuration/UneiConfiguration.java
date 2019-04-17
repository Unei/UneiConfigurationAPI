package me.unei.configuration;

import me.unei.configuration.plugin.IBasicPlugin;
import me.unei.configuration.plugin.IUpdater;

public abstract class UneiConfiguration implements IBasicPlugin
{
	private static UneiConfiguration Instance;
	
	protected final void setInstance() {
		if (Instance == null) {
			Instance = this;
		}
	}
	
	public static UneiConfiguration getInstance()
	{
		return Instance;
	}
	
	public static UneiConfiguration tryInstanciate()
	{
		try
		{
			Class<?> clz = Class.forName("me.unei.configuration.plugin.UneiConfiguration");
			if (clz != null)
			{
				clz.getDeclaredMethod("getInstance").invoke(null);
			}
		}
		catch (Exception ignored)
		{ }
		return getInstance();
	}
	
	public abstract IUpdater getUpdater();
	
	public abstract IUpdater.Result checkVersion();
	
	public abstract void checkVerionAsync(IUpdater.ICallback callback);
}