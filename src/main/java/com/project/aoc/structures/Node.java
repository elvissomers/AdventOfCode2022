package com.project.aoc.structures;

import java.util.Set;

public class Node {

    private final String name;

    private final Object data;

    private final Node parent;

    private Set<Node> children;

    public Node(String name, Object data, Node parent) {
        this.name = name;
        this.data = data;
        this.parent = parent;
    }

    public Object getData() {
        return data;
    }

    public Node getParent() {
        return parent;
    }

    public Set<Node> getChildren() {
        return children;
    }

    public void addChild(Node child) {
        children.add(child);
    }
}
