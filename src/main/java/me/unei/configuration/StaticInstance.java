package me.unei.configuration;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.function.Consumer;

public final class StaticInstance<T>
{
	private T instance;
	private Reference<T> gcFreeBackupInstance;
	private Class<?> originClass;
	private Method createInstance;
	
	private Consumer<T> dropInstanceCallback;
	
	public StaticInstance(T instance)
	{
		this.instance = instance;
	}
	
	public StaticInstance()
	{
		this(null);
	}
	
	public boolean setConstructor(Class<?> ctrClz, String methodName)
	{
		this.originClass = ctrClz;
		try {
			this.createInstance = ctrClz.getDeclaredMethod(methodName);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void setDropListener(Consumer<T> dropListener)
	{
		this.dropInstanceCallback = dropListener;
	}
	
	public void set(T instance)
	{
		if (this.instance != null && this.dropInstanceCallback != null)
		{
			this.dropInstanceCallback.accept(this.instance);
		}
		this.instance = instance;
		this.gcFreeBackupInstance = null;
	}
	
	public void callBuilder()
	{
		if (this.instance == null && this.originClass != null && this.createInstance != null)
		{
			try {
				this.createInstance.invoke(null);
			} catch (Exception e) {
				this.instance = null;
			}
		}
	}
	
	public T get()
	{
		return this.instance;
	}
	
	public boolean isEmpty()
	{
		return this.instance == null;
	}
	
	public boolean hasBackup()
	{
		return this.isEmpty() && this.gcFreeBackupInstance != null;
	}
	
	public void remove(boolean keepsLonger)
	{
		if (this.instance != null)
		{
			if (this.dropInstanceCallback != null)
			{
				this.dropInstanceCallback.accept(this.instance);
			}
			this.gcFreeBackupInstance = keepsLonger ? new SoftReference<T>(instance) : new WeakReference<T>(instance);
			this.instance = null;
		}
	}
	
	public void loadBackupIfAny()
	{
		this.instance = this.gcFreeBackupInstance.get();
		this.gcFreeBackupInstance.clear();
		this.gcFreeBackupInstance = null;
	}
	
	/**
	 * Just a static instance holder.
	 * 
	 * @param <T> The type of the static instance hold by this class.
	 */
	public final static class StaticInstanceExposer<T>
	{
		private final StaticInstance<T> instance;
		private final boolean defaultKeepsLonger;
		
		public StaticInstanceExposer(StaticInstance<T> instance, boolean defaulKeepsLonger)
		{
			this.instance = instance;
			this.defaultKeepsLonger = defaulKeepsLonger;
		}
		
		/**
		 * Try to forcefully create an instance of the {@link T &lt;T&gt;} type.
		 */
		public void callBuilder()
		{
			this.instance.callBuilder();
		}
		
		public T get()
		{
			return this.instance.get();
		}

		/**
		 * This method is designed to clear a resource, use it at your own risks.
		 * <p>
		 * The newly unsaved instance is kept in a {@link Reference reference},
		 * use {@linkplain #backup()} if you want to try to retrieve it afterwards.
		 * 
		 * @deprecated You should never call this function unless you known
		 * exactly what you are doing.
		 */
		@Deprecated
		public void clear()
		{
			this.instance.remove(defaultKeepsLonger);
		}

		/**
		 * This method is designed to get back a cleared instance by {@linkplain #clear()}.
		 * <p>
		 * The lost instance is kept in a {@link Reference reference},
		 * there is absolutely <b>NO</b> guaranty that the instance would be available.
		 * 
		 * @return the restored instance if any.
		 * 
		 * @deprecated You should never call this function unless you known
		 * exactly what you are doing.
		 */
		@Deprecated
		public T backup()
		{
			this.instance.loadBackupIfAny();
			return get();
		}
	}
}