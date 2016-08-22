package pl.samouczekprogramisty.szs;

public class ChristmasTree {
    public final static char TREE_SYMBOL = '*';
    public final static char PADDING_SYMBOL = ' ';
    private final int height;

    public ChristmasTree(int height) {
        if (height < 1) {
            this.height = 1;
        }
        else {
            this.height = height;
        }
    }

    @Override
    public String toString() {
        int maxWidth = (height - 1) * 2 + 1;

        StringBuilder treeRepresentation = new StringBuilder();

        for (int width = 1, indentation = height - 1; width <= maxWidth; width += 2, indentation--) {
            for (int i = 0; i < indentation; i++) {
                treeRepresentation.append(PADDING_SYMBOL);
            }
            for (int w = 0; w < width; w++) {
                treeRepresentation.append(TREE_SYMBOL);
            }
            treeRepresentation.append(System.lineSeparator());
        }

        return treeRepresentation.toString();
    }

    public static void main(String[] args) {
        int height = Integer.parseInt(args[0]);
        System.out.println(new ChristmasTree(height));
    }
}
