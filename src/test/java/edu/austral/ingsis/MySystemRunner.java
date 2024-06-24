package edu.austral.ingsis;

import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.commands.*;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;

public class MySystemRunner implements FileSystemRunner{
    FileSystem fileSystem= new FileSystem();

    @Override
    public List<String> executeCommands(List<String> commands) {
        List<String> results = new ArrayList<>();
        for (String command : commands) {
            Command cmd = strToCommand(command, fileSystem);
            List<String> commandParts = List.of(command.split(" "));
            results.add(fileSystem.executeCommand(cmd, commandParts.subList(1, commandParts.size()).toArray(new String[0])));
        }
        return results;
    }

    private Command strToCommand(String command, FileSystem fileSystem) {
        List<String> commandParts = List.of(command.split(" "));
        if (commandParts.get(0).equals("ls")) {
            if (commandParts.size() > 1) {
                return new LS(fileSystem);
            }
            return new LS(fileSystem);
        }
        if (commandParts.get(0).equals("mkdir")) {
            return new MkDir(fileSystem);
        }
        if (commandParts.get(0).equals("cd")) {
            return new CD(fileSystem);
        }
        if (commandParts.get(0).equals("pwd")) {
            return new PrintWD(fileSystem);
        }
        if (commandParts.get(0).equals("touch")) {
            return new Touch(fileSystem);
        }
        if (commandParts.get(0).equals("rm")) {
            return new Rm(fileSystem);
        }
        throw new IllegalFormatCodePointException(0);
    }
}
