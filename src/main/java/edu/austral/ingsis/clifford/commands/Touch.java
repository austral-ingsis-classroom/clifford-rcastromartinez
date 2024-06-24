package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.files.File;

public class Touch implements Command {

  private final FileSystem fileSystem;

  public Touch(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }

  @Override
  public String execute(String[] path) {
    if (path.length == 0) return "touch: missing file operand";
    String fileName = path[0];
    File newFile = new File(fileName, fileSystem.getCurrentDirectory());
    fileSystem.getCurrentDirectory().addChild(newFile);
    return "'" + fileName + "' file created";
  }
}
