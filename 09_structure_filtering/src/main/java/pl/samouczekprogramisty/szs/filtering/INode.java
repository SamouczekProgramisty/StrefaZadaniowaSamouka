package pl.samouczekprogramisty.szs.filtering;


import java.util.stream.Stream;

public interface INode {
    String getCode();
    String getRenderer();

    /**
     * Transforms node to a stream.
     */
    Stream<INode> toStream();

    /**
     * Checks if node attributes are unique
     */
    boolean isUnique(INode node);
}

