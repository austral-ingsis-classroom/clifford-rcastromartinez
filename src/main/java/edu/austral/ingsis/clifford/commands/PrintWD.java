package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.FileSystem;

public class PrintWD implements Command {
    private final FileSystem fileSystem;

    public PrintWD(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    @Override
    public String execute(String[] path) {
        return fileSystem.getCurrentDirectory().printPath();
    }
}
