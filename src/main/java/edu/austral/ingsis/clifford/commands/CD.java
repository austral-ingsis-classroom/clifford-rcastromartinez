package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.files.Directory;
import edu.austral.ingsis.clifford.files.Node;

public class CD implements Command {
    private final FileSystem fileSystem;

    public CD(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    @Override
    public String execute(String[] path) {
        if (path.length == 0) {
            return "Invalid command, missing directory operand";
        }

        String directory = path[0];

        if (directory.equals("..")) {
            if (fileSystem.getCurrentDirectory().getParent() == null) {
                return "Already in root directory";
            }
            Directory parent = fileSystem.getCurrentDirectory().getParent();
            fileSystem.moveToParentDirectory(parent);
            return "moved to directory: '" + fileSystem.getCurrentDirectory().getName() + "'";
        } else if (directory.equals(".")) {
            return "moved to directory: '" + fileSystem.getCurrentDirectory().getName() + "'";
        } else if (directory.startsWith("/")) {

            boolean success = fileSystem.moveToDirectory(directory.substring(1));
            if (success) {
                return "moved to directory: '" + fileSystem.getCurrentDirectory().getName() + "'";
            } else {
                return "No such directory: " + directory;
            }


        } else {
            boolean success = fileSystem.moveToChildDirectory(directory);
            if (success) {
                return "moved to directory '" + fileSystem.getCurrentDirectory().getName() + "'";
            } else {
                return "No such directory: " + directory;
            }
        }
    }
}