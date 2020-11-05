package Model;

import java.util.ArrayList;

/**
 *
 * @author 1118038-Kevin Hartono,1118014-Mikhael Adriel,1118020-Albertus
 * Kevin,1118015-Ferani
 */
public class MST {

    private ArrayList<Coordinate> Coordinate = new ArrayList<>();
    private int weight;

    public MST() {

    }

    public MST(int weight) {
        this.weight = weight;
    }

    public ArrayList<Coordinate> getCoordinate() {
        return Coordinate;
    }

    public void setCoordinate(ArrayList<Coordinate> Coordinate) {
        this.Coordinate = Coordinate;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
