package pl.samouczekprogramisty.szs.filtering;

import java.util.LinkedList;
import java.util.List;


public class MyStructure implements IMyStructure {
    private final List<INode> nodes = new LinkedList<>();

    @Override
    public INode findByCode(String code) {
        if (code == null) {
            throw new IllegalArgumentException("Code is null!");
        }
        return nodes.stream()
                .filter(n -> code.equals(n.getCode()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public INode findByRenderer(String renderer) {
        if (renderer == null) {
            throw new IllegalArgumentException("Renderer is null!");
        }
        return nodes.stream()
                .filter(n -> renderer.equals(n.getRenderer()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public int count() {
        return nodes.size();
    }

    public void addNode(Node node) {
        nodes.add(node);
    }
}