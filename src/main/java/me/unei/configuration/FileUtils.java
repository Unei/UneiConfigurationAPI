package me.unei.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystemLoopException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.logging.Level;

/**
 * Simplifies {@link java.io.File file} management.
 * 
 * @version 1.1.0
 * @since 0.0.0
 */
public final class FileUtils {

	/**
	 * The allocated buffer size for a copy.
	 */
    private static final int BUFFER_SIZE = 12;

    public static int copy(InputStream is, OutputStream os) throws IOException, NullPointerException {
        UneiConfiguration.getInstance().getLogger().log(Level.FINEST, "Copying a stream into another");
        int totalCopyedBytes = 0;
        byte buffer[] = new byte[FileUtils.BUFFER_SIZE];
        int read;
        while ((read = is.read(buffer, 0, FileUtils.BUFFER_SIZE)) > 0) {
            os.write(buffer, 0, read);
            totalCopyedBytes += read;
        }
        os.flush();
        UneiConfiguration.getInstance().getLogger().log(Level.FINE, "Successfully copied " + Integer.toString(totalCopyedBytes) + " bytes.");
        return totalCopyedBytes;
    }
    
    public static int copy(Reader in, Writer out) throws IOException {
        UneiConfiguration.getInstance().getLogger().log(Level.FINEST, "Copying a set of characters into another");
        int totalCopyedBytes = 0;
        char buffer[] = new char[FileUtils.BUFFER_SIZE];
        int read;
        while ((read = in.read(buffer, 0, FileUtils.BUFFER_SIZE)) > 0) {
            out.write(buffer, 0, read);
            totalCopyedBytes += read;
        }
        out.flush();
        UneiConfiguration.getInstance().getLogger().log(Level.FINE, "Successfully copied " + Integer.toString(totalCopyedBytes) + " characters.");
        return totalCopyedBytes;
    }

    public static int copy(InputStream is, File out) throws IOException, NullPointerException, FileNotFoundException, SecurityException {
        int tmp;
        UneiConfiguration.getInstance().getLogger().log(Level.FINEST, "Copying a stream into file " + out.getName());
        OutputStream os = new FileOutputStream(out);
        tmp = FileUtils.copy(is, os);
        os.close();
        return tmp;
    }

    public static int copy(File in, OutputStream os) throws IOException, NullPointerException, FileNotFoundException, SecurityException {
        int tmp;
        UneiConfiguration.getInstance().getLogger().log(Level.FINEST, "Copying file " + in.getName() + " into a stream");
        InputStream is = new FileInputStream(in);
        tmp = FileUtils.copy(is, os);
        is.close();
        return tmp;
    }

    public static int copy(File in, File out) throws IOException, NullPointerException, FileNotFoundException, SecurityException {
        int tmp;
        UneiConfiguration.getInstance().getLogger().log(Level.FINEST, "Copying file " + in.getName() + " into file " + out.getName());
        InputStream is = new FileInputStream(in);
        OutputStream os = new FileOutputStream(out);
        tmp = FileUtils.copy(is, os);
        is.close();
        os.close();
        return tmp;
    }

    /**
     * Creates a file and any of its parent directories if required.
     *
     * @see File#mkdirs()
     * @see File#createNewFile()
     *
     * @param file the file to create
     * @return <tt>true</tt> if the file creation succeeded without any error
     * 
     * @throws  NullPointerException
     *          If the {@link File file} given as parameter is null
     * 
     * @throws  IOException
     *          If an I/O error occurred
     *
     * @throws  SecurityException
     *          If a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkWrite(java.lang.String) SecurityManager.checkWrite(String)}</code>
     *          method denies write access to the file
     */
    public static boolean createFile(File file) throws SecurityException, IOException, NullPointerException {
        if (!file.getParentFile().exists()) {
            UneiConfiguration.getInstance().getLogger().log(Level.FINE, "Creating directory tree for file " + file.getName() + " : " + file.getParentFile().getPath());
            if (!file.getParentFile().mkdirs()) {
                UneiConfiguration.getInstance().getLogger().warning("Error while creating directory tree : " + file.getParentFile().getAbsolutePath());
                return false;
            }
            UneiConfiguration.getInstance().getLogger().fine("Successfully created directory tree.");
        }
        if (!file.exists()) {
            UneiConfiguration.getInstance().getLogger().log(Level.FINE, "Creating file " + file.getName() + " : " + file.getPath());
            if (!file.createNewFile()) {
                UneiConfiguration.getInstance().getLogger().warning("Error while creating file : " + file.getAbsolutePath());
                return false;
            }
            UneiConfiguration.getInstance().getLogger().fine("Successfully created file.");
        }
        return true;
    }
    
    public static boolean copyDirs(File source, File destination) {
    	CopyFileVisitor cfv = new CopyFileVisitor(source.toPath(), destination.toPath());
    	try {
			Files.walkFileTree(source.toPath(), cfv);
			return true;
		} catch (IOException e) {
			return false;
		}
    }
    
    private static class CopyFileVisitor extends SimpleFileVisitor<Path> {
        private final Path source;
        private final Path target;

        public CopyFileVisitor(Path source, Path target) {
            this.source = source;
            this.target = target;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            Path newDirectory= target.resolve(source.relativize(dir));
            try {
                Files.copy(dir, newDirectory);
            } catch (FileAlreadyExistsException ioException) {
                return FileVisitResult.SKIP_SUBTREE;
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
           Path newFile = target.resolve(source.relativize(file));
            try {
                Files.copy(file, newFile);
            } catch (IOException ioException) {
            }
            return FileVisitResult.CONTINUE;

        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
            if (exc instanceof FileSystemLoopException) {
            	return FileVisitResult.SKIP_SUBTREE;
            } else {
                return FileVisitResult.CONTINUE;
            }
        }
    }
}