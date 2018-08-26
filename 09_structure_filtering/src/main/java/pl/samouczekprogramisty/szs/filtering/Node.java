package pl.samouczekprogramisty.szs.filtering;

public class Node implements INode {
    private final String code;
    private final String renderer;

    public Node(String code, String renderer) {
        this.code = code;
        this.renderer = renderer;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getRenderer() {
        return renderer;
    }
}
