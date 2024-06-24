package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.files.Directory;

public class MkDir implements Command {
  private final FileSystem fileSystem;

  public MkDir(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }

  @Override
  public String execute(String[] path) {
    if (path.length == 0) return "mkdir: missing operand";
    String dirName = path[0];
    new Directory(dirName, fileSystem.getCurrentDirectory());
    return "'" + dirName + "' directory created";
  }
}
