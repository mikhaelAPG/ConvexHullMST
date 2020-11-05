package View;

/**
 *
 * @author 1118038-Kevin Hartono,1118014-Mikhael Adriel,1118020-Albertus
 * Kevin,1118015-Ferani
 */
import Control.Controller;
import Model.Coordinate;
import Model.MST;
import static View.GUI.pointList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class DrawPanel extends JPanel implements MouseListener {

    int x = 0;
    int y = 0;
    boolean isClicked = false;

    public DrawPanel() {
        this.addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        JPanel clickPanel = new DrawPanel();

        int count = 1;
        for (Coordinate c : pointList) {
            g.setColor(Color.white);
            g.fillOval(c.getX(), c.getY(), 10, 10);
            g.drawString(" " + count, c.getX(), c.getY());
            count++;
        }

        if (GUI.drawAxis == true) {
            for (int i = 0; i < Controller.ConvexHull(GUI.pointList).size() - 1; i++) {
                g.drawLine(Controller.ConvexHull(GUI.pointList).get(i).getX(), Controller.ConvexHull(GUI.pointList).get(i).getY(), Controller.ConvexHull(GUI.pointList).get(i + 1).getX(), Controller.ConvexHull(GUI.pointList).get(i + 1).getY());
            }

            for (Coordinate c : pointList) {
                g.fillOval(c.getX(), c.getY(), 10, 10);
                g.drawString("(" + c.getX() + "," + c.getY() + ")", c.getX(), c.getY());
            }

        } else if (GUI.deleteAxis == true) {
            g.setColor(Color.black);
            GUI.pointList.clear();
            GUI.deleteAxis = false;
        }

        //mst
        if (GUI.tree == true) {
            for (MST node : GUI.listNodes) {
                int i = 0;
                int midx, midy;
                g.drawLine(node.getCoordinate().get(i).getX(),
                        node.getCoordinate().get(i).getY(),
                        node.getCoordinate().get(i + 1).getX(),
                        node.getCoordinate().get(i + 1).getY());
                midx = (node.getCoordinate().get(i).getX() + node.getCoordinate().get(i + 1).getX()) / 2;
                midy = (node.getCoordinate().get(i).getY() + node.getCoordinate().get(i + 1).getY()) / 2;
                g.drawString("" + node.getWeight(), midx, midy);
            }
            GUI.tree = false;
        }

        //spanning
        if (GUI.spanningTree == true) {
            for (MST node : GUI.minimumSpaningTree) {
                int i = 0;
                int midx, midy;
                g.drawLine(node.getCoordinate().get(i).getX(),
                        node.getCoordinate().get(i).getY(),
                        node.getCoordinate().get(i + 1).getX(),
                        node.getCoordinate().get(i + 1).getY());
                midx = (node.getCoordinate().get(i).getX() + node.getCoordinate().get(i + 1).getX()) / 2;
                midy = (node.getCoordinate().get(i).getY() + node.getCoordinate().get(i + 1).getY()) / 2;
                g.drawString("" + node.getWeight(), midx, midy);
            }
            GUI.spanningTree = false;
            GUI.minimumSpaningTree.clear();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        System.out.println("(" + x + ", " + y + ")");
        Coordinate c = new Coordinate(x, y);
        GUI.pointList.add(c);
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
