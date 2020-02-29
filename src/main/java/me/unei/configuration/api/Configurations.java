package me.unei.configuration.api;

import java.io.File;

import me.unei.configuration.SavedFile;
import me.unei.configuration.StaticInstance;
import me.unei.configuration.StaticInstance.StaticInstanceExposer;
import me.unei.configuration.api.Configurations.ConfigurationType.ConfigurationTypeCls;
import me.unei.configuration.api.fs.IPathNavigator.PathSymbolsType;

/**
 * All the configuration constructors grouped in one single class.
 * 
 * @see FlatConfigurations
 */
public abstract class Configurations {

	/**
	 * The default {@link PathSymbolsType} used for all generator methods of the {@link Configurations} class.
	 * 
	 * <p>Default value: <b>{@link PathSymbolsType#BUKKIT}</b></p>
	 */
	public static PathSymbolsType DefaultPathSymbolsType = PathSymbolsType.BUKKIT;

	/**
	 * Enumeration of the different Configuration types available.
	 * 
	 * @see Configurations#newConfig(ConfigurationType, SavedFile, String)
	 * @see Configurations#newConfig(ConfigurationType, File, String, String)
	 * @see Configurations#newSubConfig(ConfigurationType, IConfiguration, String)
	 */
	public static enum ConfigurationType {

		/**
		 * A Binary Configuration type.
		 */
		Binary("Binary", "bin"),
		/**
		 * A "Comma Separated Values" (CSV) Configuration type.
		 */
		CSV("(Flat) Comma Separated Values", "comma separated values", "csv"),
		/**
		 * A MySQL Configuration type but with 'flat' values allocations.
		 * 
		 * @see ConfigurationType#MySQL
		 */
		FlatMySQL("(Flat) MySQL", "FlatMySQL"),
		/**
		 * A SQLite Configuration type but with 'flat' values allocations.
		 * 
		 * @see ConfigurationType#SQLite
		 */
		FlatSQLite("(Flat) SQLite", "FlatSQLite"),
		/**
		 * A JSON Configuration type.
		 */
		JSON("JSON"),
		/**
		 * A MySQL Configuration type but with 'non-flat' values allocations.
		 * 
		 * @see ConfigurationType#FlatMySQL
		 */
		MySQL("MySQL"),
		/**
		 * A Mojang NBT Tags Configuration type. (Type used for Minecraft data files).
		 */
		NBT("NBT"),
		/**
		 * A Java Properties Configuration type. (Often used for languages configuration files).
		 */
		Properties("(Flat) Properties", "Properties", "lang"),
		/**
		 * A SQLite Configuration type but with 'non-flat' values allocations.
		 * 
		 * @see ConfigurationType#FlatSQLite
		 */
		SQLite("SQLite"),
		/**
		 * A YAML Configuration type. (Type used for CraftBukkit/Spigot/Forge/... configuration files).
		 */
		YAML("YAML"),
		/**
		 * Any Configuration type that is not managed by UNEI Configuration.
		 */
		Other("Other", "Unknown")
		;

		private final String displayName;
		private final String[] aliases;

		private ConfigurationType(String name, String...aliases) {
			this.displayName = name;
			this.aliases = (aliases != null) ? aliases : new String[0];
		}

		/**
		 * Gets a short description (or a long name) of this configuration type.
		 * 
		 * @return Returns a human-readable full name.
		 */
		public String getDescription() {
			return this.displayName;
		}

		/**
		 * Gets the names used for this configuration type.
		 * 
		 * @return Returns a list of names.
		 */
		public String[] getNames() {
			return this.aliases;
		}

		/**
		 * Gets whenever this configuration type is about a flat structure.
		 * 
		 * @return Returns '{@code true}' if this is a flat configuration type, '{@code false}' otherwise. 
		 */
		public boolean isFlat() {
			switch (this) {
				case FlatMySQL:
				case FlatSQLite:
				case Properties:
				case CSV:
					return true;

				default:
					return false;
			}
		}

		public static ConfigurationType getByName(String name) {
			for (ConfigurationType type : ConfigurationType.values()) {
				if (type.getDescription().equalsIgnoreCase(name) || type.name().equalsIgnoreCase(name)) {
					return type;
				}
			}
			for (ConfigurationType type : ConfigurationType.values()) {
				for (String alias : type.getNames()) {
					if (alias.equalsIgnoreCase(name)) return type;
				}
			}
			return null;
		}

		public static ConfigurationType getByOrdinal(int ord) {
			if (ord < 0 || ord >= ConfigurationType.values().length) {
				return null;
			}
			return ConfigurationType.values()[ord];
		}

		public static class ConfigurationTypeCls<T extends IFlatConfiguration> {
			public static final ConfigurationTypeCls<INBTConfiguration>  NBT_CONFIG = new ConfigurationTypeCls<>(NBT, INBTConfiguration.class);
			public static final ConfigurationTypeCls<IConfiguration> BINARY_CONFIG = new ConfigurationTypeCls<>(Binary, IConfiguration.class);
			public static final ConfigurationTypeCls<IYAMLConfiguration> YAML_CONFIG = new ConfigurationTypeCls<>(YAML, IYAMLConfiguration.class);
			public static final ConfigurationTypeCls<IJSONConfiguration> JSON_CONFIG = new ConfigurationTypeCls<>(JSON, IJSONConfiguration.class);
			public static final ConfigurationTypeCls<ISQLiteConfiguration> SQLITE_CONFIG = new ConfigurationTypeCls<>(SQLite, ISQLiteConfiguration.class);
			public static final ConfigurationTypeCls<IMySQLConfiguration> MYSQL_CONFIG = new ConfigurationTypeCls<>(MySQL, IMySQLConfiguration.class);

			public static final ConfigurationTypeCls<IFlatPropertiesConfiguration> PROPERTIES_CONFIG = new ConfigurationTypeCls<>(Properties, IFlatPropertiesConfiguration.class);
			public static final ConfigurationTypeCls<IFlatCSVConfiguration> CSV_CONFIG = new ConfigurationTypeCls<>(CSV, IFlatCSVConfiguration.class);
			public static final ConfigurationTypeCls<IFlatMySQLConfiguration> FLAT_MYSQL_CONFIG = new ConfigurationTypeCls<>(FlatMySQL, IFlatMySQLConfiguration.class);
			public static final ConfigurationTypeCls<IFlatSQLiteConfiguration> FLAT_SQLITE_CONFIG = new ConfigurationTypeCls<>(FlatSQLite, IFlatSQLiteConfiguration.class);


			private final ConfigurationType type;
			private final Class<T> clazz;

			private ConfigurationTypeCls(ConfigurationType type, Class<T> clazz) {
				this.type = type;
				this.clazz = clazz;
			}
			
			public ConfigurationTypeCls(Class<T> clazz) {
				this(ConfigurationType.Other, clazz);
			}

			/**
			 * Gets the type (as enum value) of this configuration type
			 * representation. Should be {@link ConfigurationType#Other}
			 * for any external implementations.
			 * 
			 * @return the implemented configuration type.
			 */
			public ConfigurationType getType() {
				return this.type;
			}
			
			/**
			 * For external implementation of configuration types,
			 * build a new configuration instance with provided informations.
			 * 
			 * @param file the representation of the configuration file.
			 * @param path the path within the configuration (non-flat), may be null.
			 * @return Returns the new configuration instance, may return null.
			 */
			protected T buildNew(SavedFile file, String path) {
				return null;
			}
			
			/**
			 * For external implementation of configuration types,
			 * returns the default file name extension for this type of configuration format.
			 * 
			 * @return Returns the default file extension, or null if no default is provided.
			 */
			protected String getExtension() {
				return null;
			}

			public boolean isType(IFlatConfiguration cfg) {
				if (cfg != null) {
					return this.clazz.isAssignableFrom(cfg.getClass());
				}
				return false;
			}

			@SuppressWarnings("unchecked")
			public T safeCast(IFlatConfiguration ifc) {
				if (!this.isType(ifc)) {
					return null;
				}
				try {
					return (T) ifc;
				} catch (Throwable t) {
					return null;
				}
			}
		}
	}

	/**
	 * All the flat configuration constructors grouped in one single class.
	 * 
	 * @see Configurations
	 */
	public static abstract class FlatConfigurations
	{
		protected abstract IFlatPropertiesConfiguration internal_newPropertiesConfig(File folder, String fileName);
		protected abstract IFlatPropertiesConfiguration internal_newPropertiesConfig(SavedFile file);
		
		protected abstract IFlatCSVConfiguration internal_newCSVConfig(SavedFile file);
		protected abstract IFlatCSVConfiguration internal_newCSVConfig(File folder, String fileName);
		
		protected abstract IFlatSQLiteConfiguration internal_newFlatSQLiteConfig(SavedFile file, String tableName);
		protected abstract IFlatSQLiteConfiguration internal_newFlatSQLiteConfig(File folder, String fileName, String tableName);
		
		protected abstract IFlatMySQLConfiguration internal_newFlatMySQLConfig(String host, int port, String base, String user, String pass, String tableName);

		public static IFlatPropertiesConfiguration newPropertiesConfig(SavedFile file)
		{
			return instance().internal_newPropertiesConfig(file);
		}

		public static IFlatPropertiesConfiguration newPropertiesConfig(File folder, String fileName)
		{
			return instance().internal_newPropertiesConfig(folder, fileName);
		}

		public static IFlatCSVConfiguration newCSVConfig(SavedFile file)
		{
			return instance().internal_newCSVConfig(file);
		}

		public static IFlatCSVConfiguration newCSVConfig(File folder, String fileName)
		{
			return instance().internal_newCSVConfig(folder, fileName);
		}

		public static IFlatSQLiteConfiguration newFlatSQLiteConfig(SavedFile file, String tableName)
		{
			return instance().internal_newFlatSQLiteConfig(file, tableName);
		}

		public static IFlatSQLiteConfiguration newFlatSQLiteConfig(File folder, String fileName, String tableName)
		{
			return instance().internal_newFlatSQLiteConfig(folder, fileName, tableName);
		}

		/**
		 * Create a new Flat MySQL configuration link instance.
		 * 
		 * @param host The MySQL connection host name.
		 * @param port The MySQL connection port.
		 * @param base The name of the MySQL base.
		 * @param user The user name used to connect to the base.
		 * @param pass The user password.
		 * @param tableName The name of the table to access.
		 * @return Returns a FlatMySQL configuration abstraction.
		 */
		public static IFlatMySQLConfiguration newFlatMySQLConfig(String host, int port, String base, String user, String pass, String tableName)
		{
			return instance().internal_newFlatMySQLConfig(host, port, base, user, pass, tableName);
		}

		protected final void setInstance() {
			if (Instance.isEmpty()) {
				Instance.set(this);
			}
		}

		private static FlatConfigurations instance() {
			if (!Instance.isEmpty()) {
				return Instance.get();
			}
			Instance.callBuilder();
			if (!Instance.isEmpty()) {
				return Instance.get();
			}
			throw new IllegalStateException("UneiConfiguration has yet to be enabled");
		}

		private static final StaticInstance<FlatConfigurations> Instance = new StaticInstance<>();
		public static final StaticInstanceExposer<FlatConfigurations> INSTANCE = new StaticInstanceExposer<>(Instance, true);

		static
		{
			try {
				Instance.setConstructor(Class.forName("me.unei.configuration.api.ConfigurationsImpl.FlatConfigurationsImpl"), "init");
			} catch (ClassNotFoundException e) {
				;
			}
		}
	}
	
	// -- Methods for internal/obfuscated implementation

	protected abstract IJSONConfiguration internal_newJSONConfig(SavedFile file);
	protected abstract IJSONConfiguration internal_newJSONConfig(File folder, String fileName);
	protected abstract IJSONConfiguration internal_newJSONConfigFromRawData(String data);
	protected abstract IJSONConfiguration internal_newJSONConfig(File folder, String fileName, String path);
	protected abstract IJSONConfiguration internal_newJSONConfig(SavedFile file, String path);
	protected abstract IJSONConfiguration internal_newJSONConfig(IConfiguration root, String path);

	protected abstract INBTConfiguration internal_newNBTConfig(SavedFile file);
	protected abstract INBTConfiguration internal_newNBTConfig(File folder, String fileName);
	protected abstract INBTConfiguration internal_newNBTConfig(File folder, String fileName, String path);
	protected abstract INBTConfiguration internal_newNBTConfig(SavedFile file, String path);
	protected abstract INBTConfiguration internal_newNBTConfig(IConfiguration root, String path);

	protected abstract IYAMLConfiguration internal_newYAMLConfig(SavedFile file);
	protected abstract IYAMLConfiguration internal_newYAMLConfig(File folder, String fileName);
	protected abstract IYAMLConfiguration internal_newYAMLConfigFromRawData(String data);
	protected abstract IYAMLConfiguration internal_newYAMLConfig(File folder, String fileName, String path);
	protected abstract IYAMLConfiguration internal_newYAMLConfig(SavedFile file, String path);
	protected abstract IYAMLConfiguration internal_newYAMLConfig(IConfiguration root, String path);

	protected abstract IConfiguration internal_newBinaryConfig(SavedFile file);
	protected abstract IConfiguration internal_newBinaryConfig(File folder, String fileName);
	protected abstract IConfiguration internal_newBinaryConfig(File folder, String fileName, String path);
	protected abstract IConfiguration internal_newBinaryConfig(SavedFile file, String path);
	protected abstract IConfiguration internal_newBinaryConfig(IConfiguration root, String path);

	protected abstract ISQLiteConfiguration internal_newSQLiteConfig(SavedFile file, String tableName);
	protected abstract ISQLiteConfiguration internal_newSQLiteConfig(File folder, String fileName, String tableName);
	protected abstract ISQLiteConfiguration internal_newSQLiteConfig(File folder, String fileName, String tableName, String path);
	protected abstract ISQLiteConfiguration internal_newSQLiteConfig(SavedFile file, String tableName, String path);
	protected abstract ISQLiteConfiguration internal_newSQLiteConfig(IConfiguration root, String path);

	protected abstract IMySQLConfiguration internal_newMySQLConfig(String host, int port, String base, String user, String pass, String tableName);
	protected abstract IMySQLConfiguration internal_newMySQLConfig(String host, int port, String base, String user, String pass, String tableName, String path);
	protected abstract IMySQLConfiguration internal_newMySQLConfig(IConfiguration root, String path);

	// -- Public methods
	
	public static IJSONConfiguration newJSONConfig(SavedFile file)
	{
		return instance().internal_newJSONConfig(file);
	}

	public static IJSONConfiguration newJSONConfig(File folder, String fileName)
	{
		return instance().internal_newJSONConfig(folder, fileName);
	}

	public static IJSONConfiguration newJSONConfigFromRawData(String data)
	{
		return instance().internal_newJSONConfigFromRawData(data);
	}

	public static IJSONConfiguration newJSONConfig(File folder, String fileName, String path)
	{
		return instance().internal_newJSONConfig(folder, fileName, path);
	}

	public static IJSONConfiguration newJSONConfig(SavedFile file, String path)
	{
		return instance().internal_newJSONConfig(file, path);
	}

	public static IJSONConfiguration newJSONConfig(IConfiguration root, String path)
	{
		return instance().internal_newJSONConfig(root, path);
	}

	public static INBTConfiguration newNBTConfig(SavedFile file)
	{
		return instance().internal_newNBTConfig(file);
	}

	public static INBTConfiguration newNBTConfig(File folder, String fileName)
	{
		return instance().internal_newNBTConfig(folder, fileName);
	}

	public static INBTConfiguration newNBTConfig(File folder, String fileName, String path)
	{
		return instance().internal_newNBTConfig(folder, fileName, path);
	}

	public static INBTConfiguration newNBTConfig(SavedFile file, String path)
	{
		return instance().internal_newNBTConfig(file, path);
	}

	public static INBTConfiguration newNBTConfig(IConfiguration root, String path)
	{
		return instance().internal_newNBTConfig(root, path);
	}

	public static IYAMLConfiguration newYAMLConfig(SavedFile file)
	{
		return instance().internal_newYAMLConfig(file);
	}

	public static IYAMLConfiguration newYAMLConfig(File folder, String fileName)
	{
		return instance().internal_newYAMLConfig(folder, fileName);
	}

	public static IYAMLConfiguration newYAMLConfigFromRawData(String data)
	{
		return instance().internal_newYAMLConfigFromRawData(data);
	}

	public static IYAMLConfiguration newYAMLConfig(File folder, String fileName, String path)
	{
		return instance().internal_newYAMLConfig(folder, fileName, path);
	}

	public static IYAMLConfiguration newYAMLConfig(SavedFile file, String path)
	{
		return instance().internal_newYAMLConfig(file, path);
	}

	public static IYAMLConfiguration newYAMLConfig(IConfiguration root, String path)
	{
		return instance().internal_newYAMLConfig(root, path);
	}

	public static IConfiguration newBinaryConfig(SavedFile file)
	{
		return instance().internal_newBinaryConfig(file);
	}

	public static IConfiguration newBinaryConfig(File folder, String fileName)
	{
		return instance().internal_newBinaryConfig(folder, fileName);
	}

	public static IConfiguration newBinaryConfig(File folder, String fileName, String path)
	{
		return instance().internal_newBinaryConfig(folder, fileName, path);
	}

	public static IConfiguration newBinaryConfig(SavedFile file, String path)
	{
		return instance().internal_newBinaryConfig(file, path);
	}

	public static IConfiguration newBinaryConfig(IConfiguration root, String path)
	{
		return instance().internal_newBinaryConfig(root, path);
	}

	public static ISQLiteConfiguration newSQLiteConfig(SavedFile file, String tableName)
	{
		return instance().internal_newSQLiteConfig(file, tableName);
	}

	public static ISQLiteConfiguration newSQLiteConfig(File folder, String fileName, String tableName)
	{
		return instance().internal_newSQLiteConfig(folder, fileName, tableName);
	}

	public static ISQLiteConfiguration newSQLiteConfig(File folder, String fileName, String tableName, String path)
	{
		return instance().internal_newSQLiteConfig(folder, fileName, path, tableName);
	}

	public static ISQLiteConfiguration newSQLiteConfig(SavedFile file, String tableName, String path)
	{
		return instance().internal_newSQLiteConfig(file, tableName, path);
	}

	public static ISQLiteConfiguration newSQLiteConfig(IConfiguration root, String path)
	{
		return instance().internal_newSQLiteConfig(root, path);
	}

	/**
	 * Create a new MySQL configuration link instance.
	 * 
	 * @param host The MySQL connection host name.
	 * @param port The MySQL connection port.
	 * @param base The name of the MySQL base.
	 * @param user The user name used to connect to the base.
	 * @param pass The user password.
	 * @param tableName The name of the table to access.
	 * @return Returns a MySQL configuration abstraction.
	 */
	public static IMySQLConfiguration newMySQLConfig(String host, int port, String base, String user, String pass, String tableName)
	{
		return instance().internal_newMySQLConfig(host, port, base, user, pass, tableName);
	}

	/**
	 * Create a new MySQL configuration section link instance.
	 * 
	 * @param host The MySQL connection host name.
	 * @param port The MySQL connection port.
	 * @param base The name of the MySQL base.
	 * @param user The user name used to connect to the base.
	 * @param pass The user password.
	 * @param tableName The name of the table to access.
	 * @param path The configuration section path.
	 * @return Returns a MySQL configuration abstraction.
	 */
	public static IMySQLConfiguration newMySQLConfig(String host, int port, String base, String user, String pass, String tableName, String path)
	{
		return instance().internal_newMySQLConfig(host, port, base, user, pass, tableName, path);
	}

	/**
	 * Gets a MySQL configuration sub-section at the given path.
	 * 
	 * @param root The MySQLConfig parent section.
	 * @param path The path relative to the `root` section.
	 * @return Returns a configuration sub-section.
	 */
	public static IMySQLConfiguration newMySQLConfig(IConfiguration root, String path)
	{
		return instance().internal_newMySQLConfig(root, path);
	}

	public static IFlatConfiguration newConfig(ConfigurationType type, SavedFile file, String tableName) {
		switch (type) {
			case NBT:
				return Configurations.newNBTConfig(file);
			case JSON:
				return Configurations.newJSONConfig(file);
			case SQLite:
				return Configurations.newSQLiteConfig(file, tableName);
			case Binary:
				return Configurations.newBinaryConfig(file);
			case YAML:
				return Configurations.newYAMLConfig(file);
			case CSV:
				return FlatConfigurations.newCSVConfig(file);
			case FlatSQLite:
				return FlatConfigurations.newFlatSQLiteConfig(file, tableName);
			case Properties:
				return FlatConfigurations.newPropertiesConfig(file);
			case MySQL:
			case FlatMySQL:
				throw new IllegalArgumentException("Could not create a MySQL configuration type with those arguments");
			default:
				return null;
		}
	}

	public static IFlatConfiguration newConfig(ConfigurationType type, File folder, String fileName, String tableName) {
		switch (type) {
			case NBT:
				return Configurations.newNBTConfig(folder, fileName);
			case JSON:
				return Configurations.newJSONConfig(folder, fileName);
			case SQLite:
				return Configurations.newSQLiteConfig(folder, fileName, tableName);
			case Binary:
				return Configurations.newBinaryConfig(folder, fileName);
			case YAML:
				return Configurations.newYAMLConfig(folder, fileName);
			case CSV:
				return FlatConfigurations.newCSVConfig(folder, fileName);
			case FlatSQLite:
				return FlatConfigurations.newFlatSQLiteConfig(folder, fileName, tableName);
			case Properties:
				return FlatConfigurations.newPropertiesConfig(folder, fileName);
			case MySQL:
			case FlatMySQL:
				throw new IllegalArgumentException("Could not create a MySQL configuration type with those arguments");
			default:
				return null;
		}
	}

	public static <T extends IFlatConfiguration> T newConfig(ConfigurationTypeCls<T> type, File folder, String fileName, String tableName) {
		if (type.getType() == ConfigurationType.Other) {
			return type.buildNew(new SavedFile(folder, fileName, type.getExtension()), tableName);
		}
		return type.safeCast(newConfig(type.getType(), folder, fileName, tableName));
	}

	/**
	 * Gets a configuration sub-section at the given path.
	 * 
	 * @param type The type of the configuration.
	 * @param root The configuration parent section.
	 * @param path The path relative to the `root` section.
	 * @return Returns a configuration sub-section.
	 * 
	 * @throws IllegalArgumentException If `root` is a flat configuration type (flat = no sections).
	 * @throws IllegalArgumentException If `type` is MySQL or FlatMySQL (they need special arguments).
	 */
	public static IConfiguration newSubConfig(ConfigurationType type, IConfiguration root, String path) {
		switch (type) {
			case NBT:
				return Configurations.newNBTConfig(root, path);
			case JSON:
				return Configurations.newJSONConfig(root, path);
			case SQLite:
				return Configurations.newSQLiteConfig(root, path);
			case Binary:
				return Configurations.newBinaryConfig(root, path);
			case YAML:
				return Configurations.newYAMLConfig(root, path);
			case CSV:
			case FlatSQLite:
			case Properties:
				throw new IllegalArgumentException("Could not use a path on a flat configuration");
			case MySQL:
			case FlatMySQL:
				throw new IllegalArgumentException("Could not create a MySQL configuration type with those arguments");
			default:
				return null;
		}
	}

	protected final void setInstance() {
		if (Instance.isEmpty()) {
			Instance.set(this);
		}
	}

	private static Configurations instance() {
		if (!Instance.isEmpty()) {
			return Instance.get();
		}
		Instance.callBuilder();
		if (!Instance.isEmpty()) {
			return Instance.get();
		}
		throw new IllegalStateException("UneiConfiguration has yet to be initialized");
	}

	private static final StaticInstance<Configurations> Instance = new StaticInstance<>();
	public static final StaticInstanceExposer<Configurations> INSTANCE = new StaticInstanceExposer<>(Instance, true);

	static
	{
		try {
			Instance.setConstructor(Class.forName("me.unei.configuration.api.ConfigurationsImpl"), "init");
		} catch (ClassNotFoundException e) {
			;
		}
	}
}