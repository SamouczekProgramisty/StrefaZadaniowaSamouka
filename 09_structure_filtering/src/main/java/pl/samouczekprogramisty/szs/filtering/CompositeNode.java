package pl.samouczekprogramisty.szs.filtering;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public class CompositeNode extends Node implements ICompositeNode {

    @Getter private List<INode> nodes = new LinkedList<>();

    public CompositeNode(String code, String renderer) {
        super(code, renderer);
    }

    @Override
    public List<INode> getNodes() {
        return Collections.unmodifiableList(nodes);
    }

    public void addNode(INode node) {
        nodes.add(node);
    }

    /**
     * Transforms a node to a stream taking into account nested nodes. Stream handles also multiple nested composite nodes.
     * Returned stream expands nested composite nodes in a depth first manner.
     *
     * C1(N2 C3(N4 N5) N6) will return stream of (C1 N2 C3 N4 N5 N6)
     *
     * This method doesn't handle case when composite node is a member of itself: C1(C1(C1...)))
     */
    @Override
    public Stream<INode> toStream() {
        return Stream.concat(
                super.toStream(),
                nodes.stream().flatMap(INode::toStream)
        );
    }
}
