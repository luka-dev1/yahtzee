import javax.swing.JButton;


public class GameButton extends JButton {
    int i;
    int j;
    int flag;
    int value;

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public int getFlag() {
        return flag;
    }

    public int getValue() {
        return value;
    }


    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public GameButton() {

    }

    public GameButton(int i, int j) {
        this.i = i;
        this.j = j;
        this.flag = 0;
        this.value = 0;
    }

}
