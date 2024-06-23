package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.files.Directory;
import edu.austral.ingsis.clifford.files.Node;

import java.util.List;
import java.util.Objects;

public class Rm implements Command {
    private FileSystem fileSystem;

    public Rm (FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    @Override
    public String execute(String[] args) {
        if (args.length == 0) {
            return "Invalid command, missing file operand";
        }

        String path = args[0];
        List<Node> node = fileSystem.getCurrentDirectory().getChildren();

        if (node == null) {
            return "No such file or directory: " + path;
        }
        for(Node child : node) {
            if (child instanceof Directory) {
                return "Cannot remove '" + path + "', is a directory";
            }
            if (node instanceof Directory) {
                if (Objects.equals(args[0], "--recursive")) {
                    fileSystem.getCurrentDirectory().removeChild(child);
                    return "'" + path + "' removed";
                }
                return "Cannot remove '" + path + "', is a directory";
            }
        }
        fileSystem.getCurrentDirectory().removeChild(node.get(0));
        return "'" + path + "' removed";
    }

}