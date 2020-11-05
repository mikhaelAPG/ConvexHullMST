package Control;

/**
 *
 * @author 1118038-Kevin Hartono,1118014-Mikhael Adriel,1118020-Albertus
 * Kevin,1118015-Ferani
 */
import Model.Coordinate;
import Model.MST;
import View.GUI;
import java.util.*;
import javax.swing.JOptionPane;

public class Controller {

    public static int Orientation(Coordinate p, Coordinate q, Coordinate r) {
        int val = (q.getY() - p.getY()) * (r.getX() - q.getX()) - (q.getX() - p.getX()) * (r.getY() - q.getY());

        if (val == 0) {
            return 0;
        }

        return (val > 0) ? 1 : 2;
    }

    public static ArrayList<Coordinate> ConvexHull(ArrayList<Coordinate> pointList) {
        ArrayList<Coordinate> convexHull = new ArrayList<>();

        int l = 0;
        int q = 0;
        int p = 0;
        for (int i = 1; i < pointList.size(); i++) {
            if (pointList.get(i).getX() < pointList.get(l).getX()) {
                l = i;
            }
        }

        p = l;
        do {

            convexHull.add(pointList.get(p));

            q = (p + 1) % pointList.size();
            for (int i = 0; i < pointList.size(); i++) {

                if (Orientation(pointList.get(p), pointList.get(i), pointList.get(q)) == 2) {
                    q = i;
                }
            }

            p = q;
            if (p == l) {
                convexHull.add(pointList.get(p));
            }
        } while (p != l);

        return convexHull;

    }

    //Main Spanning Tree
    public static boolean isSameTreeNodes(MST a, MST b) {
        ArrayList<Coordinate> thisPoint = new ArrayList<>();
        boolean same = true;

        for (Coordinate point : a.getCoordinate()) {
            thisPoint.add(point);
        }

        for (Coordinate point : b.getCoordinate()) {
            if (!thisPoint.contains(point)) {
                same = false;
            }
        }

        return same;

    }

    public static boolean checkTreeNodesArrayList(ArrayList<MST> lst, MST b) {
        boolean ada = false;

        for (MST node : lst) {
            if (isSameTreeNodes(node, b)) {
                ada = true;
                break;
            }
        }

        return ada;
    }

    public static void createTreeNodes() {

        MST temp;

        for (int i = 0; i < GUI.pointList.size(); i++) {

            for (int j = 0; j < GUI.pointList.size(); j++) {
                if (j != i) {
                    temp = new MST();
                    ArrayList<Coordinate> thisPoint = new ArrayList<>();
                    thisPoint.add(GUI.pointList.get(i));
                    thisPoint.add(GUI.pointList.get(j));
                    temp.setCoordinate(thisPoint);

                    if (!checkTreeNodesArrayList(GUI.listNodes, temp)) {
                        String text = "add edge to these points " + thisPoint.get(0).getX() + " - " + thisPoint.get(0).getY() + " and " + thisPoint.get(1).getX() + " - " + thisPoint.get(1).getY() + " ?";
                        int confirm = JOptionPane.showConfirmDialog(null, text);
                        if (confirm == 0) {
                            int weight = Integer.parseInt(JOptionPane.showInputDialog(null, "insert weight :"));
                            temp.setWeight(weight);
                            GUI.listNodes.add(temp);
                        }
                    }

                }
            }
        }
    }

    public static boolean checkNodes(ArrayList<MST> lst, MST obj) {
        ArrayList<Coordinate> listPoint = new ArrayList<>();

        Coordinate a, b;
        Coordinate a1, b1;
        a = obj.getCoordinate().get(0);
        b = obj.getCoordinate().get(1);

        for (MST node : lst) {
            a1 = node.getCoordinate().get(0);
            b1 = node.getCoordinate().get(1);
            listPoint.add(a1);
            listPoint.add(b1);
        }

        if (listPoint.contains(a) && listPoint.contains(b)) {
            return false;
        } else {
            return true;
        }
    }

    //Mengurutkan weight dari terkecil ke terbesar seluruh edge
    public static ArrayList<MST> sortList() {
        ArrayList<MST> returnList = new ArrayList<>();

        returnList = GUI.listNodes;
        int length = GUI.listNodes.size();
        int indexMinimum = 0;
        MST minimum;
        MST temp;
        for (int i = 0; i < length; i++) {
            minimum = returnList.get(i);
            for (int j = i + 1; j < length; j++) {
                if (returnList.get(j).getWeight() < minimum.getWeight()) {
                    minimum = returnList.get(j);
                    indexMinimum = j;
                }
            }
            temp = returnList.get(i);
            returnList.set(i, minimum);
            returnList.set(indexMinimum, temp);

        }

        return returnList;
    }

    public static void createMinimumSpanningTree() {
        ArrayList<MST> sortedList = new ArrayList<>();

        sortedList = sortList();

        for (MST treenode : sortedList) {
            if (checkNodes(GUI.minimumSpaningTree, treenode)) {
                GUI.minimumSpaningTree.add(treenode);

            }
        }

    }

    public static int resultMinimunSpanningTree(ArrayList<MST> minimumSpanningTree) {
        int hasil = 0;

        for (MST treenode : minimumSpanningTree) {
            hasil += treenode.getWeight();
        }
        return hasil;
    }
}
