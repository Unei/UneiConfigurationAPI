package me.unei.configuration;

import me.unei.configuration.StaticInstance.StaticInstanceExposer;
import me.unei.configuration.plugin.IBasicPlugin;
import me.unei.configuration.plugin.IUpdater;

public abstract class UneiConfiguration implements IBasicPlugin
{
	private static final StaticInstance<UneiConfiguration> Instance = new StaticInstance<>();
	public static final StaticInstanceExposer<UneiConfiguration> INSTANCE = new StaticInstanceExposer<>(Instance, false);
	
	static
	{
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
	
	public static UneiConfiguration getInstance()
	{
		return INSTANCE.get();
	}
	
	/**
	 * Try to load the UneiConfiguration implementation forcefully (useful when using it in stand-alone mode).
	 * 
	 * @return Returns this API instance if loaded successfully.
	 */
	public static UneiConfiguration tryInstanciate()
	{
		Instance.callBuilder();
		return getInstance();
	}
	
	public abstract IUpdater getUpdater();
	
	public abstract IUpdater.Result checkVersion();
	
	public abstract void checkVerionAsync(IUpdater.ICallback callback);
}