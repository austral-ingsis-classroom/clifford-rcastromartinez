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

        if(directory.equals("/")) {
            fileSystem.moveToParentDirectory(fileSystem.getRoot());
            return "moved to directory '/'";
        }

        if (directory.equals("..")) {
            if (fileSystem.getCurrentDirectory().getParent() == null) {
                return "moved to directory '/'";
            }
            Directory parent = fileSystem.getCurrentDirectory().getParent();
            fileSystem.moveToParentDirectory(parent);
            if (parent == fileSystem.getRoot()) {
                return "moved to directory '/'";
            }

            return "moved to directory '" + fileSystem.getCurrentDirectory().getName() + "'";
        } else if (directory.equals(".")) {
            return "moved to directory '" + fileSystem.getCurrentDirectory().getName() + "'";
        } else {
            String[] directories = directory.split("/");
            for (String dir : directories) {
                boolean success = fileSystem.moveToChildDirectory(dir);
                if (!success) {
                    return "'" + dir + "' directory does not exist";
                }
            }
            return "moved to directory '" + fileSystem.getCurrentDirectory().getName() + "'";
        }
    }
}
