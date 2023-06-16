package com.project.aoc.structures;

import java.util.HashSet;
import java.util.Set;

public class Node {

    private final int data;

    private final String parent;

    private Set<String> children = new HashSet<>();

    public Node(int data, String parent) {
        this.data = data;
        this.parent = parent;
    }

    public int getData() {
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
