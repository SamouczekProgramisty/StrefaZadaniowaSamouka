package pl.samouczekprogramisty.szs.filtering;

import java.util.List;


public class MyStructure implements IMyStructure {
    private List<INode> nodes;

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
        return 0;
    }
}