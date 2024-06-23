package edu.austral.ingsis.clifford;

import edu.austral.ingsis.clifford.files.Directory;

public class FileSystem {
    private Directory root;
    private Directory currentDirectory;

    public FileSystem() {
        root = new Directory("root", null);
        currentDirectory = root;
    }

    public Directory getCurrentDirectory() {
        return currentDirectory;
    }

    public void moveToParentDirectory(Directory parent) {
        currentDirectory = parent;
    }


    public boolean moveToDirectory(String substring) {
        Directory newDirectory = root.findDirectory(substring);
        if (newDirectory != null) {
            currentDirectory = newDirectory;
            return true;
        }
        return false;
    }
}
