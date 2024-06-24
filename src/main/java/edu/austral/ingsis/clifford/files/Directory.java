package edu.austral.ingsis.clifford.files;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Directory implements Node {
  private final String name;
  private final Directory parent;
  private final List<Node> children = new ArrayList<>();
  private final Date creationDate;

  public Directory(String name, Directory parent) {
    this.name = name;
    parent.addChild(this);
    this.parent = parent;
    this.creationDate = new Date();
  }

  public Directory(String name) {
    this.name = name;
    this.parent = null;
    this.creationDate = new Date();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Directory getParent() {
    return parent;
  }

  public List<Node> getChildren() {
    return children;
  }

  @Override
  public Date getCreationDate() {
    return creationDate;
  }

  public void addChild(Node node) {
    children.add(node);
  }

  public void removeChild(Node node) {
    children.remove(node);
  }

  public String printPath() {
    if (parent == null) {
      return "";
    }
    return parent.printPath() + "/" + name;
  }

  public Directory findDirectory(String substring) {
    if (name.equals(substring)) {
      return this;
    }
    for (Node child : children) {
      if (child instanceof Directory) {
        Directory directory = ((Directory) child).findDirectory(substring);
        if (directory != null) {
          return directory;
        }
      }
    }
    return null;
  }

  public Node getChild(String nodeName) {
    for (Node child : children) {
      if (child.getName().equals(nodeName)) {
        return child;
      }
    }
    return null;
  }
}
