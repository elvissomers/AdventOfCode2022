package com.project.aoc.structures;

import java.util.Set;

public class Node {

    private final Object data;

    private final String parent;

    private Set<String> children;

    public Node(Object data, String parent) {
        this.data = data;
        this.parent = parent;
    }

    public Object getData() {
        return data;
    }

    public String getParent() {
        return parent;
    }

    public Set<String> getChildren() {
        return children;
    }

    public void addChild(String child) {
        children.add(child);
    }
}
