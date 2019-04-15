package me.unei.configuration.formats;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public interface Storage<V> extends Iterable<V>
{
	public StorageType getStorageType();
	
	public int size();
	
	public boolean isEmpty();
	
	public void clear();
	
	public V get(Key key);
	
	public void set(Key key, V value);
	
	public void remove(Key key);
	
	public boolean has(Key key);
	
	public Set<String> getKeys();
	
	public static class Key
	{
		private final AtomicInteger keyAtomicInt;
		private final String keyString;
		private final StorageType type;
		
		public Key(int key)
		{
			this.keyAtomicInt = new AtomicInteger(key);
			this.keyString = null;
			this.type = StorageType.LIST;
		}
		
		public Key(String key)
		{
			this.keyAtomicInt = null;
			this.keyString = key;
			this.type = StorageType.MAP;
		}
		
		public Key(Object key)
		{
			if (key instanceof CharSequence)
			{
				this.keyAtomicInt = null;
				this.keyString = key.toString();
				this.type = StorageType.MAP;
			}
			else if (key instanceof AtomicInteger)
			{
				this.keyAtomicInt = (AtomicInteger) key;
				this.keyString = null;
				this.type = StorageType.LIST;
			}
			else if (key instanceof Number)
			{
				this.keyAtomicInt = new AtomicInteger(((Number) key).intValue());
				this.keyString = null;
				this.type = StorageType.LIST;
			}
			else
			{
				throw new IllegalArgumentException("key must be either an integer or a string. Not a type of: " + key.getClass().getName());
			}
		}
		
		public static Key of(StorageType type, int idx, String name)
		{
			switch (type) {
			case MAP:
				return new Key(name);
				
			case LIST:
				return new Key(idx);
				
			default:
				return null;
			}
		}
		
		public static Key of(StorageType type, AtomicInteger idx, String name)
		{
			switch (type) {
			case MAP:
				return new Key(name);
				
			case LIST:
				return new Key(idx);
				
			default:
				return null;
			}
		}
		
		public int getKeyInt()
		{
			return (this.keyAtomicInt != null) ? this.keyAtomicInt.get() : -1;
		}
		
		public AtomicInteger getKeyAtomicInt()
		{
			return this.keyAtomicInt;
		}
		
		public String getKeyString()
		{
			return this.keyString;
		}
		
		public StorageType getType()
		{
			return this.type;
		}
	}
}