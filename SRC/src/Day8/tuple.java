package Day8;


public class tuple {
    private String left = "";
    private String right = "";
    private String parent = "";

    public tuple(String left, String right , String parent) {
        this.left= left;
        this.right = right;
        this.parent= parent;
    }


    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}

