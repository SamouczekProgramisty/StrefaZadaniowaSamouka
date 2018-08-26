package pl.samouczekprogramisty.szs.filtering;

import java.util.LinkedList;
import java.util.List;


public class MyStructure implements IMyStructure {
    private final List<INode> nodes = new LinkedList<>();

    @Override
    public INode findByCode(String code) {
        return null;
    }

    @Override
    public INode findByRenderer(String renderer) {
        return null;
    }

    @Override
    public int count() {
        return nodes.size();
    }

    public void addNode(Node node) {
        nodes.add(node);
    }
}