package edu.austral.ingsis.clifford.files;

import java.util.Date;
import java.util.List;

public interface Node {
    String getName();
    Directory getParent();
    Date getCreationDate();
}
