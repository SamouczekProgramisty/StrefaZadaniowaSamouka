package pl.samouczekprogramisty.szs.filtering;

import java.util.List;

public interface ICompositeNode extends INode {
    List<INode> getNodes();
}