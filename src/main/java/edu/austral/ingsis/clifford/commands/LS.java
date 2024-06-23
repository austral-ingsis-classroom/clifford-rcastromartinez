package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.FileSystem;

public class LS implements Command{
    private final FileSystem fileSystem;

    public LS(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    @Override
    public String execute(String[] path) {
        if(path[0].equals("--ord==asc")){
            return fileSystem.getCurrentDirectory().listFilesAsc();
        }else if(path[0].equals("--ord==desc")){
            return fileSystem.getCurrentDirectory().listFilesDesc();
        }
        return "Error: invalid argument";
    }
}
