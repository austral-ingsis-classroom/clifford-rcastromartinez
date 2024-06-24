package edu.austral.ingsis.clifford.files;

import java.util.Date;

public interface Node {
  String getName();

  Directory getParent();

  Date getCreationDate();
}
