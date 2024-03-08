import java.io.Serializable;

public class Rocket implements Serializable {
    private int typeRocket;
    private int numRocket;

    public Rocket() {
    }

    public Rocket(int typeRocket, int numRocket) {
        this.typeRocket = typeRocket;
        this.numRocket = numRocket;
    }

    public int getTypeRocket() {
        return typeRocket;
    }

    public void setTypeRocket(int typeRocket) {
        this.typeRocket = typeRocket;
    }

    public int getNumRocket() {
        return numRocket;
    }

    public void setNumRocket(int numRocket) {
        this.numRocket = numRocket;
    }
}
