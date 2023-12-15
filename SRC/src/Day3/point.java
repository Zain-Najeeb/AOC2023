package Day3;

public class point {
    private boolean checked = false ;
    private int value ;
    private int x;
    private  int y ;
    public point(int x, int y ){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setValue(int value) {
        this.value = value;
    }


    public int getValue() {
        return value;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
