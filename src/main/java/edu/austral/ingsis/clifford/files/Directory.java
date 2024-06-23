package edu.austral.ingsis.clifford.files;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Directory implements Node{
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
            return "/" + name;
        }
        return parent.printPath() + name;
    }

    public String listFilesAsc() {
        StringBuilder files = new StringBuilder();
        for (Node child : children) {
            files.append(child.getName()).append("\n");
        }
        return files.toString();
    }

    public String listFilesDesc() {
        StringBuilder files = new StringBuilder();
        for (int i = children.size() - 1; i >= 0; i--) {
            files.append(children.get(i).getName()).append("\n");
        }
        return files.toString();
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
}