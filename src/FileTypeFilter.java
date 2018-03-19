import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FileTypeFilter extends FileFilter{

	// Assign variables
	private final String extension;
	private final String description;
	
	// get file type
	public FileTypeFilter(String extension, String description) {	
		this.extension = extension;
		this.description = description;
	}
	
	// Find files with same file typw
	@Override
	public boolean accept(File file) {
		if (file.isDirectory()){
			return true;
		}
		
		// Return file type in search
		return file.getName().endsWith(extension);
	}

	@Override
	public String getDescription() {
		return description + String.format(" (*%s)", extension);
	}
}
