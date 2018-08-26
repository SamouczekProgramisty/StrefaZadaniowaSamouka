package pl.samouczekprogramisty.szs.filtering;


import java.util.stream.Stream;

public interface INode {
    String getCode();
    String getRenderer();

    Stream<INode> toStream();
}

