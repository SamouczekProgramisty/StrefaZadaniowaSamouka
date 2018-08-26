package pl.samouczekprogramisty.szs.filtering;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;


public class MyStructure implements IMyStructure {
    private final List<INode> nodes = new LinkedList<>();

    private static Stream<INode> toStream(INode node) {
        if (node instanceof ICompositeNode) {
            return ((ICompositeNode) node).getNodes().stream();
        }
        return Stream.of(node);
    }

    @Override
    public INode findByCode(String code) {
        if (code == null) {
            throw new IllegalArgumentException("Code is null!");
        }
        return nodes.stream()
                .flatMap(MyStructure::toStream)
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