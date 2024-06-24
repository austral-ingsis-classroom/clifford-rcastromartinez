package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.files.Node;
import edu.austral.ingsis.clifford.FileSystem;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LS implements Command {
    private final FileSystem fileSystem;

    public LS(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    @Override
    public String execute(String[] args) {
        List<Node> children = fileSystem.getCurrentDirectory().getChildren();

        if (args.length > 0 && hasOrdFlag(args)) {
            String order = getOrdValue(args);
            if (order.equals("asc")) {
                children = children.stream()
                        .sorted(Comparator.comparing(Node::getName))
                        .collect(Collectors.toList());
            } else if (order.equals("desc")) {
                children = children.stream()
                        .sorted(Comparator.comparing(Node::getName).reversed())
                        .collect(Collectors.toList());
            }
        }

        return children.stream()
                .map(Node::getName)
                .collect(Collectors.joining(" "));
    }

    private boolean hasOrdFlag(String[] args) {
        for (String arg : args) {
            if (arg.startsWith("--ord=")) {
                return true;
            }
        }
        return false;
    }

    private String getOrdValue(String[] args) {
        for (String arg : args) {
            if (arg.startsWith("--ord=")) {
                return arg.split("=")[1];
            }
        }
        return "";
    }
}