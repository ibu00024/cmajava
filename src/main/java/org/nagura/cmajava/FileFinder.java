package org.nagura.cmajava;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class FileFinder {
    
    /**
     * Retrieves a list of file paths from a specified directory that match a given file suffix.
     * This method searches the directory recursively and includes all matching files in the result.
     *
     * @param filePath The directory path to start the search from.
     * @param suffix The file suffix to match. Only files ending with this suffix will be included.
     * @return A list of file paths that match the given suffix. The paths are absolute.
     * @throws FileNotFoundException If the provided directory path does not exist.
     */
    public static List<String> getFileFullName(String filePath, String suffix) throws FileNotFoundException {
        return getFileFullName(filePath, suffix, new ArrayList<>());
    }

    /**
     * Helper method to recursively search for files with a specific suffix within a directory.
     * This method adds matching file paths to the provided list.
     *
     * @param filePath The directory path to search in.
     * @param suffix The file suffix to match. Files ending with this suffix are added to the list.
     * @param fileNameList The list to accumulate the matching file paths. Paths are added to this list.
     * @return The updated list containing the paths of files that match the given suffix.
     * @throws FileNotFoundException If a directory in the search path does not exist.
     */
    final public static List<String> getFileFullName(String filePath, String suffix, List<String> fileNameList) throws FileNotFoundException{
        File dir = new File(filePath);
        File files[] = dir.listFiles();

        if (files == null) {
            throw new FileNotFoundException("Directory not found:" + filePath);
        }

        for (File file : files) {
            if (!file.isHidden() && file.getName().endsWith(suffix)) {
                fileNameList.add(file.getAbsolutePath());
            }

            if (file.isDirectory()) {
                getFileFullName(file.getPath(), suffix, fileNameList);
            }
        }

        return fileNameList;
    }
}
