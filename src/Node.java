import java.io.File;
import java.util.ArrayList;

public class Node {
    private File folder;
    private ArrayList<Node> children;
    private Long size;
    private int level = 0;
    private Node parent;

    public Node(File folder)
    {
        this.folder = folder;
        children = new ArrayList<>();
    }
    public Node(File folder, Node parent)
    {
        this.folder = folder;
        children = new ArrayList<>();
        this.parent = parent;
    }

    public File getFolder()
    {
        return folder;
    }

    public void addChild(Node node)
    {
        node.setLevel(level + 1);
        children.add(node);
    }

    public ArrayList<Node> getChildren()
    {
        return children;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getSize() {
        return size;
    }

    public String toString()
    {
        //
        StringBuilder builder = new StringBuilder();
        Long sizeForHuman = getSize();
        String size1 = "";
        if (sizeForHuman != null)
        {
            size1 = SizeCalculator.getHumanReadableSize(sizeForHuman);
        }
        builder.append(folder.getName() + " - " + size1 + "\n");

        for (Node child : children){
            builder.append(getTabLevel() + child.toString());
        }
        return builder.toString();
    }

    private void setLevel(int level)
    {
        this.level = level;
    }

    private String getTabLevel()
    {
        String tabLevel = "";
        for (int i = 0; i <= level; i++)
        {
            tabLevel = tabLevel + "     ";
        }
        return tabLevel;
    }

    public void deleteChild(Node node)
    {
        children.remove(node);
    }

    public Node getParent()
    {
        return parent;
    }


}
