package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.files.Directory;
import edu.austral.ingsis.clifford.files.Node;

import java.util.List;
import java.util.Objects;

public class Rm implements Command {
    private final FileSystem fileSystem;

    public Rm(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    @Override
    public String execute(String[] args) {
        if (args.length == 0) return "rm: missing operand";
        String nodeName = args[0];
        boolean recursive = args.length > 1 && args[0].equals("--recursive");
        if(recursive) {
            nodeName = args[1];
        }
        Node node = fileSystem.getCurrentDirectory().getChild(nodeName);
        if (node == null && !args[0].equals("--recursive")) {
            return "'" + nodeName + "' does not exist";
        } else if (node instanceof Directory && !recursive) {
            return "cannot remove '" + nodeName + "', is a directory";
        } else {
            fileSystem.getCurrentDirectory().removeChild(node);
            return "'" + nodeName + "' removed";
        }
    }
}