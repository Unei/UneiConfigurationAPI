package me.unei.configuration;

import java.io.File;
import java.io.IOException;

/**
 * An abstract representation of file.
 * 
 * @version 1.3.3
 * @since 0.0.0
 */
public final class SavedFile {

    private File folder;
    private String fileName = "ERROR_IN_THIS_FILE";

    private String extension = ".null";

    private boolean dummyFile = false;
    private boolean canAccess;
    private File datFile;

    private boolean initialized;

    /**
     * Create a virtual non-existent file.
     * 
     * <p>Use this if you don't need a real file on the system
     * but the configuration needs a File instance.</p>
     */
    public SavedFile() {
        this(null, null, null);
        this.dummyFile = true;
    }

    /**
     * Create a `SavedFile` instance from a parent {@linkplain File abstract pathname} and a child pathname string.
     * 
     * @param folder The directory containing the configuration file.
     * @param fileName The name of the configuration file (without extension).
     * @param extension The extension (including the dot '.') of the configuration file.
     */
    public SavedFile(File folder, String fileName, String extension) {
        this.folder = (folder == null ? new File(".") : folder);
        if (fileName != null) {
            this.fileName = fileName;
        }
        this.canAccess = false;
        this.datFile = null;
        this.initialized = false;
        if (extension != null) {
            this.extension = extension;
        }
    }
    
    /**
     * Create a `SavedFile` instance from a Java {@link File abstract pathname} representation.
     * 
     * @param file The abstract pathname of the configuration file.
     */
    public SavedFile(File file) {
    	if (file == null) {
    		throw new NullPointerException("file cannot be null");
    	}
    	File tmp = file;
    	try {
			tmp = file.getCanonicalFile();
		} catch (IOException e) {
			tmp = file;
		}
    	this.folder = tmp.getParentFile();
    	this.canAccess = false;
    	this.initialized = false;
    	this.datFile = tmp;
    	String name = tmp.getName();
    	if (!name.contains(".")) {
    		this.fileName = name;
    		this.extension = "";
    	} else {
    		this.fileName = name.substring(0, name.lastIndexOf('.'));
    		this.extension = name.substring(name.lastIndexOf('.'));
    	}
    }

    /**
     * Initiate this configuration file.
     * 
     * <p>Creates parent folders and the file if not exists and not virtual.</p>
     * <p>When `true` is returned, this SavedFile can be used safely.</p>
     * 
     * @return Returns `true` if the initialization was successful, `false` otherwise.
     * 
     * @see #isInitialized()
     */
    public boolean init() {
    	if (this.isInitialized()) {
    		return true;
    	}
        if (this.folder == null || this.fileName == null || this.fileName.isEmpty()) {
            return false;
        }
        if (this.dummyFile) {
            this.canAccess = true;
            this.initialized = true;
            return true;
        }
        if (!this.folder.exists()) {
            UneiConfiguration.getInstance().getLogger().finest("Creating Configuration tree... (" + this.folder.getPath() + ")");
            if (this.folder.mkdirs()) {
                UneiConfiguration.getInstance().getLogger().finest("Successfully created configuration tree. (" + this.folder.getPath() + ")");
            } else {
                UneiConfiguration.getInstance().getLogger().warning("Unable to create the configuration tree : " + this.folder.getAbsolutePath());
                return false;
            }
        }
        if (this.datFile == null) {
        	this.datFile = new File(this.folder, this.fileName + this.extension);
        }
        this.canAccess = true;
        this.initialized = true;
        return true;
    }

    /**
     * Gets whenever this `SavedFile` has been initialized.
     * 
     * @return Returns `true` if {@linkplain #init()} was called before.
     */
    public boolean isInitialized() {
        return this.initialized;
    }

    /**
     * Returns <tt>true</tt> if it is currently possible to access and
     * modify this file.
     *
     * @see SavedFile#lock()
     *
     * @return <tt>true</tt> if this file is accessible
     */
    public boolean canAccess() {
        return this.canAccess;
    }

    /**
     * Locks this file to disable getting it.
     * 
     * <p><b>Warning:</b> this operation is not reversible, use it carefully.</p>
     * 
     * <p>{@link #getFile()} will always return `null` after this operation.</p>
     */
    public void lock() {
        this.canAccess = false;
    }

    /**
     * Gets the Java {@link File abstract pathname} representation of this file if accessible.
     * 
     * @see #canAccess()
     * 
     * @return Returns the Java abstract pathname of this file.
     */
    public File getFile() {
        if (!this.canAccess() || this.dummyFile) {
            return null;
        }
        return this.datFile;
    }

    /**
     * Gets the Java {@link File abstract pathname} representation of the folder containing this file.
     * 
     * @return Returns the Java abstract pathname of this file's parent directory.
     */
    public File getFolder() {
        return this.folder;
    }

    /**
     * Returns the name of the {@link File file} that
     * represents this SavedFile without extension.
     *
     * @return the name of this file
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * Returns the name of the {@link File file} that
     * represents this SavedFile with extension.
     *
     * @return the name of this file
     */
    public String getFullName() {
    	return this.fileName + this.extension;
    }
}