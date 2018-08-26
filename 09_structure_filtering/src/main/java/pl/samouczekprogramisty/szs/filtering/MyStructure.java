package pl.samouczekprogramisty.szs.filtering;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;


public class MyStructure implements IMyStructure {
    private final List<INode> nodes = new LinkedList<>();

    @Override
    public INode findByCode(String code) {
        if (code == null) {
            throw new IllegalArgumentException("Code is null!");
        }
        return findByPredicate(n -> code.equals(n.getCode()));
    }

    @Override
    public INode findByRenderer(String renderer) {
        if (renderer == null) {
            throw new IllegalArgumentException("Renderer is null!");
        }
        return findByPredicate(n -> renderer.equals(n.getRenderer()));
    }

    private INode findByPredicate(Predicate<INode> predicate) {
        return nodes.stream()
                .flatMap(INode::toStream)
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }

    @Override
    public int count() {
        return (int) nodes.stream().flatMap(INode::toStream).count();
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MyStructure that = (MyStructure) o;
        return Objects.equals(nodes, that.nodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodes);
    }
}